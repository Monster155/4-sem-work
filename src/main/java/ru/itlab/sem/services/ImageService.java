package ru.itlab.sem.services;

import ru.itlab.sem.models.Image;

import java.util.List;

public interface ImageService {

    Image addImage(Image image);

    Image findImageById(Long id);

    List<Image> getAll();

    Image findImageByName(String name);
}
