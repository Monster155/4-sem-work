package ru.itlab.sem.services;

import ru.itlab.sem.models.Chat;

import java.util.List;

public interface ChatService {

   void addChat(Chat chat);

   Chat getChatById(Long id);

   List<Chat> getAll();

}
