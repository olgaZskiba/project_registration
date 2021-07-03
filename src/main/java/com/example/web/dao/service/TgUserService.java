package com.example.web.dao.service;

import com.example.web.bean.TgUserTable;

import java.util.Optional;

public interface TgUserService {
    void save(TgUserTable tgUserTable);
    Optional<TgUserTable> findByChatId(Long chatId);
}
