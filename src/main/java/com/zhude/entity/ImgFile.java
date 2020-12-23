package com.zhude.entity;

import org.bson.types.Binary;
import org.bson.types.ObjectId;

import java.io.InputStream;

public class ImgFile {
    private ObjectId id;
    private String filename;
    private String contentType;
    private InputStream inputStream;
    private long length;

    public ImgFile(ObjectId id, String filename, String contentType, InputStream inputStream, long length) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.inputStream = inputStream;
        this.length = length;
    }

    public ImgFile(String filename, String contentType, InputStream inputStream, long length) {
        this.filename = filename;
        this.contentType = contentType;
        this.inputStream = inputStream;
        this.length = length;
    }

    public ImgFile() {
    }

    @Override
    public String toString() {
        return "ImgFile{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                ", inputStream=" + inputStream +
                ", length=" + length +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
