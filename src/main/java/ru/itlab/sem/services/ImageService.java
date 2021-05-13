package ru.itlab.sem.services;

import ru.itlab.sem.models.Image;

import java.util.List;

public interface ImageService {

   void addImage(Image image);

   Image getImageById(Long id);

   List<Image> getAll();

}
