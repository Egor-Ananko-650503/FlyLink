package by.bsuir.flylink.service;

import by.bsuir.flylink.model.File;

import java.util.List;
import java.util.Optional;

public interface FileService {
    File saveFile(File file);

    Optional<File> findByName(String name);

    List<File> listAll();
}
