package by.bsuir.flylink.service.impl;

import by.bsuir.flylink.model.File;
import by.bsuir.flylink.repository.FileRepository;
import by.bsuir.flylink.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public Optional<File> findByName(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    public List<File> listAll() { return fileRepository.findAll(); }
}
