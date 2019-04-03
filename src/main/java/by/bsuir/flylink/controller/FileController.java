package by.bsuir.flylink.controller;

import by.bsuir.flylink.exception.FileStorageException;
import by.bsuir.flylink.model.File;
import by.bsuir.flylink.playload.UploadFileResponse;
import by.bsuir.flylink.service.FileService;
import by.bsuir.flylink.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String path = fileStorageService.storeFile(file);
            File newFile = new File(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize(),
                    path,
                    LocalDateTime.now(),
                    1L
            );
            fileService.saveFile(newFile);

            //TODO: Replace hardcoded '1' to user id when it will be possible

            return new UploadFileResponse(file.getOriginalFilename(), file.getContentType(), file.getSize(), "OK");
        } catch (FileStorageException ex) {
            logger.info(ex.getMessage());
        }

        return new UploadFileResponse(file.getOriginalFilename(), file.getContentType(), file.getSize(), "FAIL");
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/test/downloadFile/{fileName:.+}")
    public ResponseEntity<File> testDownloadFile(@PathVariable String fileName) {
        Optional<File> optFile = fileService.findByName(fileName);
        return optFile.map(file -> new ResponseEntity<>(file, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/test/uploadFile")
    public ResponseEntity<File> testUploadFile(@RequestBody File file) {
        return new ResponseEntity<>(fileService.saveFile(file), HttpStatus.OK);
    }
}
