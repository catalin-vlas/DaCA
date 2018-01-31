package com.wade.daca.uploader.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


public interface UploaderService {

    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.POST)
    String addTriplesFromUrl(@PathVariable("namespaceId") String namespaceId,
                             @RequestParam("format") String format,
                             @RequestParam("url") String url);

    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.PUT)
    String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId,
                              @RequestParam("format") String format,
                              @RequestParam("file")  MultipartFile file);
}
