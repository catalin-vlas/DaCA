package com.wade.daca.uploader.api;

import com.wade.daca.uploader.storage.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

public interface UploaderService {

    @GetMapping("/")
    String listUploadedFiles(Model model) throws IOException;

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    ResponseEntity<Resource> serveFile(@PathVariable String filename);

    @PostMapping("/")
    String handleFileUpload(@RequestParam("file") MultipartFile file,
                            @RequestParam("namespace") String namespace,
                            RedirectAttributes redirectAttributes);

    @ExceptionHandler(StorageFileNotFoundException.class)
    ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc);
}
