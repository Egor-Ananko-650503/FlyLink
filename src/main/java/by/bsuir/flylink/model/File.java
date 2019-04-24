package by.bsuir.flylink.model;

import org.springframework.core.io.Resource;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fl_file", schema = "public")
public class File {
    @Id
    @SequenceGenerator(name = "fl_file_id_gen", sequenceName = "fl_file_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "fl_file_id_gen")
    private Long id;

    private String name;

    private String type;

    private Long size;

    private String path;

    @Column(name = "upload_date")
    private LocalDateTime dateTime;

    @Column(name = "uploader_id")
    private Long uploaderId;

    public File() {
    }

    public File(String name, String type, Long size, String path, LocalDateTime dateTime, Long uploaderId) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.path = path;
        this.dateTime = dateTime;
        this.uploaderId = uploaderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }
}
