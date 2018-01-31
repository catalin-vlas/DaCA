package com.wade.daca.sparql.api;

import com.wade.daca.sparql.dataobjects.RdfStats;
import com.wade.daca.sparql.dataobjects.RdfTriple;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface SparqlService {

    @RequestMapping(value = "/namespace", method = RequestMethod.GET)
    List<String> getNamespaces();

    @RequestMapping(value = "/namespace/stats/{namespaceId}", method = RequestMethod.GET)
    RdfStats getNamespaceStats(@PathVariable("namespaceId") String namespaceId) throws Exception;

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
                              @RequestParam("format") String format,
                              @RequestParam("file") MultipartFile file);

    @RequestMapping(value = "/sparql/query", method = RequestMethod.GET)
    List<RdfTriple> executeSparqlQuery(@RequestParam(value = "namespaceId", required = true) String namespaceId,
                                       @RequestParam(value = "query", required = true) String query);

    @RequestMapping(value = "/sparql/query", method = RequestMethod.POST)
    String executeSparqlUpdate(@RequestParam(value = "namespaceId", required = true) String namespaceId,
                               @RequestParam(value = "query", required = true) String query);
}
