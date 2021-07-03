package com.example.telegramBot.user.command.commands;

import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.user.keyboard.inline.UserInlineKeyboardSource;

import com.example.web.bean.StatisticUser;
import com.example.web.bean.TgUserTable;
import com.example.web.bean.UserRoles;
import com.example.web.dao.service.StatisticUserService;
import com.example.web.dao.service.TgUserService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;



public class MainMenuComm implements Command {

    private final UserInlineKeyboardSource userInlineKeyboardSource = new UserInlineKeyboardSource();

    public final static String MAIN_MENU_MESSAGE = "Здравствуйте, если вы это читаете значит вы решили выучить английски, а мы, WorldLand, поможем Вам в этом!";
    private final ReplyKeyboard mainMenuKeyboard = userInlineKeyboardSource.getMainMenuKeyboard();

    private final SendBotMessageService sendBotMessageService;
    private final StatisticUserService statisticUserService;
    private final TgUserService tgUserService;

    public MainMenuComm(SendBotMessageService sendBotMessageService, StatisticUserService statisticUserService, TgUserService tgUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.statisticUserService = statisticUserService;
        this.tgUserService = tgUserService;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        String userName = update.getMessage().getChat().getUserName();
        String userFirstName = update.getMessage().getChat().getFirstName();
        String userLastName = update.getMessage().getChat().getLastName();
        statisticUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {user.setActive(true);
                statisticUserService.save(user);
                },
                () -> {
                    StatisticUser statisticUser = new StatisticUser();
                    statisticUser.setActive(true);
                    statisticUser.setChatId(chatId);
                    statisticUserService.save(statisticUser);
                }
        );

        tgUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {user.setActive(true);
                tgUserService.save(user);
                },
                () -> {
                    TgUserTable tgUserTable = new TgUserTable();
                    tgUserTable.setActive(true);
                    tgUserTable.setUserName(userName);
                    tgUserTable.setFirstName(userFirstName);
                    tgUserTable.setLastName(userLastName);
                    tgUserTable.setBlockUser(false);
                    tgUserTable.setPayment(false);
                    tgUserTable.setChatId(chatId);
                    tgUserService.save(tgUserTable);
                }
        );

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), MAIN_MENU_MESSAGE, mainMenuKeyboard);


    }
}
