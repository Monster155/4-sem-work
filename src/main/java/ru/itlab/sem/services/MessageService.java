package ru.itlab.sem.services;

import ru.itlab.sem.models.Message;

import java.util.List;

public interface MessageService {

   void addMessage(Message message);

   Message getMessageById(Long id);

   List<Message> getAll();

}
