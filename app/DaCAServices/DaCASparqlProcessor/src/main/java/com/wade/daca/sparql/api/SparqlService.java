package com.wade.daca.sparql.api;

import com.wade.daca.sparql.dataobjects.RdfTriple;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface SparqlService {

    @RequestMapping(value = "/namespace", method = RequestMethod.GET)
    List<String> getNamespaces();

    @RequestMapping(value = "/namespace/{namespaceId}", method = RequestMethod.POST)
    String createNamespace(@PathVariable("namespaceId") String namespaceId);

    @RequestMapping(value = "/namespace/{namespaceId}", method = RequestMethod.DELETE)
    String deleteNamespace(@PathVariable("namespaceId") String namespaceId);

    @RequestMapping(value = "/triples/{namespaceId}", method = RequestMethod.GET)
    List<RdfTriple> getTriples(@PathVariable("namespaceId") String namespaceId);

    @RequestMapping(value = "/triples/{namespaceId}", method = RequestMethod.POST)
    String addTriples(@PathVariable("namespaceId") String namespaceId,
                      @RequestBody ArrayList<RdfTriple> triples);

    @RequestMapping(value = "/triples/{namespaceId}", method = RequestMethod.PUT)
    String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId,
                              @RequestParam("file") MultipartFile file);
}
