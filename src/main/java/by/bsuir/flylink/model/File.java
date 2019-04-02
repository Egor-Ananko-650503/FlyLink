package by.bsuir.flylink.model;

import org.springframework.core.io.Resource;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_seq")
    private Long id;

    private String name;

    private String type;

    private Long size;

    private String path;

    @Column(name = "uploader_id")
    private Long uploaderId;

    public File() {
    }

    public File(Long id, String name, String type, Long size, String path, Long uploaderId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.path = path;
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

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }
}
