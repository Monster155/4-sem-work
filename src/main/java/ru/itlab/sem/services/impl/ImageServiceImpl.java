package ru.itlab.sem.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.repositories.ImageRepo;
import ru.itlab.sem.services.ImageService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepo imageRepo;

    @Override
    public Image addImage(MultipartFile multipartFile, String name) {
        byte[] img = null;
        try (InputStream fileContent = multipartFile.getInputStream()) {
            img = IOUtils.toByteArray(fileContent);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        if (img == null)
            return null;
        if (img.length <= 0)
            return null;
        return imageRepo.saveAndFlush(new Image(0, name, img));
    }

    @Override
    public Image addImage(URI uri, String name) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (InputStream inputStream = uri.toURL().openStream()) {
            int n = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return imageRepo.saveAndFlush(new Image(0, name, output.toByteArray()));
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
