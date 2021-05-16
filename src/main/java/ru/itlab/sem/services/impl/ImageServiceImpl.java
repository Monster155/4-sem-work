package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.repositories.ImageRepo;
import ru.itlab.sem.services.ImageService;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepo imageRepo;

    @Override
    public Image addImage(Image image) {
        return imageRepo.saveAndFlush(image);
    }

    @Override
    public Image findImageById(Long id) {
        return imageRepo.findById(id).orElse(null);
    }

    @Override
    public List<Image> getAll() {
        return imageRepo.findAll();
    }

    @Override
    public Image findImageByName(String name) {
        return imageRepo.findFirstByName(name);
    }

}
