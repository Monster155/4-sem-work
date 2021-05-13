package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.services.MessageService;
import ru.itlab.sem.repositories.MessageRepo;
import ru.itlab.sem.models.Message;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public void addMessage(Message message) {
        messageRepo.saveAndFlush(message);
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepo.findById(id).orElse(null);
    }

    @Override
    public List<Message> getAll() {
        return messageRepo.findAll();
    }

}
