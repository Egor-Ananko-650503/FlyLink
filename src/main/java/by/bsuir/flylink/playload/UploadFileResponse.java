package by.bsuir.flylink.playload;

public class UploadFileResponse {
    private String fileName;
    private String fileType;
    private long size;
    private String message;

    public UploadFileResponse(String fileName, String fileType, long size, String message) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
