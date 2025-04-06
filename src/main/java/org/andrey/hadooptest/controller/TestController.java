package org.andrey.hadooptest.controller;

import org.andrey.hadooptest.service.HadoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final String TEST_DIRECTORY = "/mydata";

    public HadoopService hadoopService;

    @Autowired
    public TestController(HadoopService hadoopService) {
        this.hadoopService = hadoopService;
    }

    @GetMapping("/upload/{filename}")
    public ResponseEntity<String> uploadFile(@PathVariable String filename) throws Exception {
        hadoopService.uploadFile(filename, TEST_DIRECTORY + "/" + filename);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<String> downloadFile(@PathVariable String filename) throws Exception {
        hadoopService.downloadFile(TEST_DIRECTORY + "/" + filename, filename + "_downloaded");

        return ResponseEntity.ok("ok");
    }
}
