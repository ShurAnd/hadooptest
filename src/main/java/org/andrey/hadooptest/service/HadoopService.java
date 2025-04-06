package org.andrey.hadooptest.service;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class HadoopService {

    private FileSystem fs;

    @Autowired
    public HadoopService(FileSystem fs) {
        this.fs = fs;
    }

    public void uploadFile(String localFilePath, String hdfsFilePath) throws IOException {
        try (InputStream in = new FileInputStream(localFilePath);
             OutputStream out = fs.create(new Path(hdfsFilePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    public void downloadFile(String hdfsFilePath, String localFilePath) throws IOException {
        try (InputStream in = fs.open(new Path(hdfsFilePath));
             OutputStream out = new FileOutputStream(localFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
