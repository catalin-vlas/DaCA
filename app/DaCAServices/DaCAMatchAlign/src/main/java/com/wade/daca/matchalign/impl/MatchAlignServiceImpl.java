package com.wade.daca.matchalign.impl;

import com.wade.daca.matchalign.api.MatchAlignService;
import com.wade.daca.matchalign.dataobject.Alignment;
import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.api.DaCASparqlProcessorClient;
import com.wade.daca.sparql.client.model.RdfTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class MatchAlignServiceImpl implements MatchAlignService {

    DaCASparqlProcessorClient sparqlProcessorClient;

    @Autowired
    public MatchAlignServiceImpl() {
        sparqlProcessorClient = new DaCASparqlProcessorClient();
    }

    @RequestMapping(value = "/matchalign/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public List<Alignment> constructMatchAlign(@PathVariable("namespaceId1") String namespaceId1,
                                               @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        List<Alignment> alignments = new ArrayList<>();

        ArrayList<RdfTriple> graph1 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId1);
        ArrayList<RdfTriple> graph2 = (ArrayList<RdfTriple>) sparqlProcessorClient.getTriples(namespaceId2);

        Set<String> distinctNodes1 = graph1.stream().flatMap(x -> Stream.of(x.getSubject(), x.getObject())).collect(Collectors.toSet());
        Set<String> distinctNodes2 = graph2.stream().flatMap(x -> Stream.of(x.getSubject(), x.getObject())).collect(Collectors.toSet());

        for(String node1: distinctNodes1) {
            String bestNode2 = null;
            double bestScore = 0.9;
            for(String node2: distinctNodes2) {
                double dist = 1.0 - 1.0 * getEditDistance(node1, node2) / Math.max(node1.length(), node2.length());
                if(dist > bestScore) {
                    bestScore = dist;
                    bestNode2 = node2;
                }
            }
            if(bestNode2 != null) {
                alignments.add(new Alignment(node1, bestNode2, "=", bestScore));
            }
        }

        Collections.sort(alignments, (a1, a2) -> Double.compare(a2.getConfidence(), a1.getConfidence()));

        List<Alignment> solution = new ArrayList<>();
        Set<String> secondNodes = new HashSet<>();
        for(Alignment alignment: alignments) {
            if(secondNodes.contains(alignment.getEntity2()) == false) {
                secondNodes.add(alignment.getEntity2());
                solution.add(alignment);
            }
        }

        return solution;
    }

    private static int getEditDistance(String x, String y) {
        int[][] distance = new int[x.length() + 1][y.length() + 1];

        for(int i = 0; i <= x.length(); i++) {
            distance[i][0] = i;
        }
        for(int i = 0; i <= y.length(); i++) {
            distance[0][i] = i;
        }

        for(int i = 0; i < x.length(); i++) {
            for(int j = 0; j < y.length(); j++) {
                if(x.charAt(i) == y.charAt(j)) {
                    distance[i+1][j+1] = distance[i][j];
                } else {
                    distance[i+1][j+1] = distance[i][j] + 1;
                }

                distance[i+1][j+1] = Math.min(distance[i+1][j+1], distance[i+1][j] + 1);
                distance[i+1][j+1] = Math.min(distance[i+1][j+1], distance[i][j+1] + 1);
            }
        }

        return distance[x.length()][y.length()];
    }

}
