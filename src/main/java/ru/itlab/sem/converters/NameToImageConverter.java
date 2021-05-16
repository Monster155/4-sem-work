package ru.itlab.sem.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.services.ImageService;

@Slf4j
@Component
public class NameToImageConverter implements Converter<String, Image> {

    @Autowired
    private ImageService imageService;

    @Override
    public Image convert(String s) {
        try {
            return imageService.findImageById(Long.parseLong(s));
        } catch (NumberFormatException e) {
            log.info("Can't convert '" + s + "' to Long, try to find it from Database");
            Image image = imageService.findImageByName(s);
            log.info("Image " + s + " is " + (image == null ? "null" : "not null"));
            return image;
        }
    }
}