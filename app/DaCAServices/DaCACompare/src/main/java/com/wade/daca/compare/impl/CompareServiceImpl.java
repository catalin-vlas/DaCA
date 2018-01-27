package com.wade.daca.compare.impl;

import com.wade.daca.compare.api.CompareService;
import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.api.DaCASparqlProcessorClient;
import com.wade.daca.sparql.client.model.RdfTriple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by catavlas on 1/27/2018.
 */
@RestController
public class CompareServiceImpl implements CompareService{

    DaCASparqlProcessorClient sparqlProcessorClient;

    @Autowired
    public CompareServiceImpl() {
        sparqlProcessorClient = new DaCASparqlProcessorClient();
    }

    @RequestMapping(value = "/compare/union/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public ArrayList<RdfTriple> constructNamespaceUnion(@PathVariable("namespaceId1") String namespaceId1,
                                                        @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        ArrayList<RdfTriple> result = new ArrayList<>();
        ArrayList<RdfTriple> graph1 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId1);
        ArrayList<RdfTriple> graph2 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId2);

        for (int i=0; i<graph2.size(); ++i) graph1.add(graph2.get(i));

        HashMap<String, Integer> hitMap = new HashMap<>();

        for (int i=0; i<graph1.size(); ++i) {
            String key = graph1.get(i).toString();
            Integer value = hitMap.get(key);

            if (value==null) {
                result.add(graph1.get(i));
                hitMap.put(key, new Integer(1));
            }
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/compare/intersection/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public ArrayList<RdfTriple> constructNamespaceIntersection(@PathVariable("namespaceId1") String namespaceId1,
                                                               @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        ArrayList<RdfTriple> result = new ArrayList<>();
        ArrayList<RdfTriple> graph1 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId1);
        ArrayList<RdfTriple> graph2 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId2);

        HashMap<String, Integer> hitMap = new HashMap<>();

        for (int i=0; i<graph1.size(); ++i) {
            String key = graph1.get(i).toString();

            hitMap.put(key, new Integer(1));
        }

        for (int i=0; i<graph2.size(); ++i) {
            String key = graph2.get(i).toString();

            Integer val = hitMap.get(key);

            if (val!=null) result.add(graph2.get(i));
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/compare/difference/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public ArrayList<RdfTriple> constructNamespaceDifference(@PathVariable("namespaceId1") String namespaceId1,
                                                             @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        ArrayList<RdfTriple> result = new ArrayList<>();
        ArrayList<RdfTriple> graph1 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId1);
        ArrayList<RdfTriple> graph2 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId2);

        HashMap<String, Integer> hitMap = new HashMap<>();

        for (int i=0; i<graph2.size(); ++i) {
            String key = graph2.get(i).toString();

            hitMap.put(key, new Integer(1));
        }

        for (int i=0; i<graph1.size(); ++i) {
            String key = graph1.get(i).toString();

            Integer val = hitMap.get(key);

            if (val==null) result.add(graph1.get(i));
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/compare/isomorphism/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public String checkIsomorphism(@PathVariable("namespaceId1") String namespaceId1,
                                   @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        ArrayList<RdfTriple> graph1 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId1);
        ArrayList<RdfTriple> graph2 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId2);

        //build Jena model
        Model model1 = ModelFactory.createDefaultModel();

        for (int i=0; i<graph1.size(); ++i) {
            model1.createResource(graph1.get(i).getSubject())
                    .addProperty(model1.createProperty(graph1.get(i).getPredicate()), graph1.get(i).getObject());
        }

        Model model2 = ModelFactory.createDefaultModel();

        for (int i=0; i<graph2.size(); ++i) {
            model2.createResource(graph2.get(i).getSubject())
                    .addProperty(model2.createProperty(graph2.get(i).getPredicate()), graph2.get(i).getObject());
        }

        if (model1.isIsomorphicWith(model2)) return "true";
        else return "false";
    }

}
