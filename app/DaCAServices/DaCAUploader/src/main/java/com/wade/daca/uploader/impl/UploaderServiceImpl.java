package com.wade.daca.uploader.impl;

import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.api.DaCASparqlProcessorClient;
import com.wade.daca.uploader.api.UploaderService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UploaderServiceImpl implements UploaderService {

    DaCASparqlProcessorClient sparqlProcessorClient;

    @Autowired
    public UploaderServiceImpl() {
        sparqlProcessorClient = new DaCASparqlProcessorClient();
    }

    @Override
    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.POST)
    public String addTriplesFromUrl(@PathVariable("namespaceId") String namespaceId,
                                    @RequestParam("format") String format,
                                    @RequestParam("url") String url) {
        String result = "\"Success\"";

        try {
            URL u = new URL(url);

            File convertedFile = new File(namespaceId + System.currentTimeMillis());
            FileOutputStream fos = new FileOutputStream(convertedFile);

            URLConnection connection = u.openConnection();
            IOUtils.copy(connection.getInputStream(), fos);

            fos.close();

            // create the namespace (if it doesn't already exist)
            sparqlProcessorClient.createNamespace(namespaceId);

            // add the triples to the new namespace
            sparqlProcessorClient.addTriplesFromFile(namespaceId, format, convertedFile);

            convertedFile.delete();
        } catch (IOException | ApiException e) {
            e.printStackTrace();
            result = "\"Failure\"";
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/upload/{namespaceId}", method = RequestMethod.PUT)
    public String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId,
                                     @RequestParam("format") String format,
                                     @RequestParam("file")  MultipartFile file) {
        String result = "\"Success\"";

        File convertedFile = new File(file.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();

            // create the namespace (if it doesn't already exist)
            sparqlProcessorClient.createNamespace(namespaceId);

            // add the triples to the new namespace
            sparqlProcessorClient.addTriplesFromFile(namespaceId, format, convertedFile);
        } catch (IOException | ApiException e) {
            e.printStackTrace();
            result = "\"Failure\"";
        }

        return result;
    }
}


