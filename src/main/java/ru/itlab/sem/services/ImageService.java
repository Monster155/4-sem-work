package ru.itlab.sem.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itlab.sem.models.Image;

import java.util.List;

public interface ImageService {

    Image addImage(MultipartFile multipartFile, String name);

    Image findImageById(Long id);

    List<Image> getAll();

    Image findImageByName(String name);
}
