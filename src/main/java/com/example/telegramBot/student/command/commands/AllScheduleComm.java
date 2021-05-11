package com.example.telegramBot.student.command.commands;

import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.student.keyboards.StudentInlineKeyboardSource;
import com.example.telegramBot.user.command.commands.Command;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class AllScheduleComm implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final String MESSAGE = "Мое расписание";
    StudentInlineKeyboardSource studentInlineKeyboardSource = new StudentInlineKeyboardSource();
    public final InlineKeyboardMarkup allSchedule = studentInlineKeyboardSource.getAllSchedule();

    public AllScheduleComm(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, MESSAGE, allSchedule);
    }
}
