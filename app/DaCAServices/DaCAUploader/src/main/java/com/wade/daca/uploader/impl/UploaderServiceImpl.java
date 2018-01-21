package com.wade.daca.uploader.impl;

import com.wade.daca.uploader.api.UploaderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploaderServiceImpl implements UploaderService {

    @Override
    @RequestMapping(value = "/upload/{namespaceId}/{url}", method = RequestMethod.PUT)
    public String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId,@PathVariable("url") String url){
        String result = "Success";



        return result;
    }

    @Override
    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.PUT)
    public String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId,@RequestParam("file")  MultipartFile file){
        String result = "Success";

        

        return result;
    }
}


