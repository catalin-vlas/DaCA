package com.wade.daca.uploader.impl;

import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.api.DaCASparqlProcessorClient;
import com.wade.daca.uploader.api.UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class UploaderServiceImpl implements UploaderService {

    DaCASparqlProcessorClient sparqlProcessorClient;

    @Autowired
    public UploaderServiceImpl() {
        sparqlProcessorClient = new DaCASparqlProcessorClient();
    }

    @Override
    @RequestMapping(value = "/upload/{namespaceId}/{url}", method = RequestMethod.PUT)
    public String addTriplesFromUrl(@PathVariable("namespaceId") String namespaceId, @PathVariable("url") String url) {
        String result = "Success";

        return result;
    }

    @Override
    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.PUT)
    public String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId, @RequestParam("file")  MultipartFile file) {
        String result = "Success";

        File convertedFile = new File(file.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();

            // create the namespace (if it doesn't already exist)
            sparqlProcessorClient.createNamespace(namespaceId);

            // add the triples to the new namespace
            sparqlProcessorClient.addTriplesFromFile(namespaceId, convertedFile);
        } catch (IOException e) {
            e.printStackTrace();
            result = "Failure";
        } catch (ApiException e) {
            e.printStackTrace();
            result = "Failure";
        }

        return result;
    }
}


