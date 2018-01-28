package com.wade.daca.stats.impl;

import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.api.DaCASparqlProcessorClient;
import com.wade.daca.stats.api.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatsServiceImpl implements StatsService {

    DaCASparqlProcessorClient sparqlProcessorClient;

    @Autowired
    public StatsServiceImpl() {
        sparqlProcessorClient = new DaCASparqlProcessorClient();
    }

    @RequestMapping(value = "/stats/namespaces", method = RequestMethod.GET)
    public List<String> getNamespaces() {
        List<String> namespaces = new ArrayList<>();

        try {
            namespaces = sparqlProcessorClient.getNamespaces();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return namespaces;
    }
}
