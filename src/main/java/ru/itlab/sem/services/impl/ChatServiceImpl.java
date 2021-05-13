package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.services.ChatService;
import ru.itlab.sem.repositories.ChatRepo;
import ru.itlab.sem.models.Chat;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public void addChat(Chat chat) {
        chatRepo.saveAndFlush(chat);
    }

    @Override
    public Chat getChatById(Long id) {
        return chatRepo.findById(id).orElse(null);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepo.findAll();
    }

}
