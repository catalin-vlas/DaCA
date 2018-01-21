package com.wade.daca.uploader.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


public interface UploaderService {

    @RequestMapping(value = "/upload/{namespaceId}/{url}", method = RequestMethod.PUT)
    String addTriplesFromUrl(@PathVariable("namespaceId") String namespaceId, @PathVariable("url") String url);

    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.PUT)
    String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId, @RequestParam("file")  MultipartFile file);
}
