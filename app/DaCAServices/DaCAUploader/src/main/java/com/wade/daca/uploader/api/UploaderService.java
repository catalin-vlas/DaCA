package com.wade.daca.uploader.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


public interface UploaderService {

    @RequestMapping(value = "/upload/{namespaceId}/{url}", method = RequestMethod.PUT)
    String addTriplesFromFile(@PathVariable("namespaceId") String namespace_id,@PathVariable("url") String url);

    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.PUT)
    String addTriplesFromFile(@PathVariable("namespaceId") String namespace_id,@RequestParam("file")  MultipartFile file);
}
