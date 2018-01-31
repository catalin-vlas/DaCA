package com.wade.daca.compare.impl;

import com.wade.daca.compare.api.CompareService;
import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.api.DaCASparqlProcessorClient;
import com.wade.daca.sparql.client.model.RdfTriple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by catavlas on 1/27/2018.
 */
@SuppressWarnings("ALL")
@RestController
public class CompareServiceImpl implements CompareService{

    DaCASparqlProcessorClient sparqlProcessorClient;

    @Autowired
    public CompareServiceImpl() {
        sparqlProcessorClient = new DaCASparqlProcessorClient();
    }

    @Override
    @RequestMapping(value = "/compare/union/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public ArrayList<RdfTriple> constructNamespaceUnion(@PathVariable("namespaceId1") String namespaceId1,
                                                        @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        return getNamespaceUnion(namespaceId1, namespaceId2);
    }

    @Override
    @RequestMapping(value = "/export/union/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public void exportNamespaceUnion(@PathVariable("namespaceId1") String namespaceId1,
                                     @PathVariable("namespaceId2") String namespaceId2,
                                     @RequestParam(value = "format", required = false) String format,
                                     HttpServletResponse response) throws ApiException {
        if(format == null || format.equals("n3")) {
            List<RdfTriple> triples = getNamespaceUnion(namespaceId1, namespaceId2);
            response.setHeader("Content-disposition", "attachment; filename=union.n3");
            try (OutputStream os = response.getOutputStream()) {
                for(RdfTriple triple: triples) {
                    String line = triple.getSubject() + " " + triple.getPredicate() + " " + triple.getObject() + "\n";
                    os.write(line.getBytes());
                }
            } catch(Exception e) {
                try {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad format.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @RequestMapping(value = "/compare/intersection/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public ArrayList<RdfTriple> constructNamespaceIntersection(@PathVariable("namespaceId1") String namespaceId1,
                                                               @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        return getNamespaceIntersection(namespaceId1, namespaceId2);
    }

    @Override
    @RequestMapping(value = "/export/intersection/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public void exportNamespaceIntersection(@PathVariable("namespaceId1") String namespaceId1,
                                            @PathVariable("namespaceId2") String namespaceId2,
                                            @RequestParam(value = "format", required = false) String format,
                                            HttpServletResponse response) throws ApiException {
        if(format == null || format.equals("n3")) {
            List<RdfTriple> triples = getNamespaceIntersection(namespaceId1, namespaceId2);
            response.setHeader("Content-disposition", "attachment; filename=intersection.n3");
            try (OutputStream os = response.getOutputStream()) {
                for(RdfTriple triple: triples) {
                    String line = triple.getSubject() + " " + triple.getPredicate() + " " + triple.getObject() + "\n";
                    os.write(line.getBytes());
                }
            } catch(Exception e) {
                try {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad format.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @RequestMapping(value = "/compare/difference/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public ArrayList<RdfTriple> constructNamespaceDifference(@PathVariable("namespaceId1") String namespaceId1,
                                                             @PathVariable("namespaceId2") String namespaceId2) throws ApiException {
        return getNamespaceDifference(namespaceId1, namespaceId2);
    }

    @Override
    @RequestMapping(value = "/export/difference/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    public void exportNamespaceDifference(@PathVariable("namespaceId1") String namespaceId1,
                                          @PathVariable("namespaceId2") String namespaceId2,
                                          @RequestParam(value = "format", required = false) String format,
                                          HttpServletResponse response) throws ApiException {
        if(format == null || format.equals("n3")) {
            List<RdfTriple> triples = getNamespaceDifference(namespaceId1, namespaceId2);
            response.setHeader("Content-disposition", "attachment; filename=difference.n3");
            try (OutputStream os = response.getOutputStream()) {
                for(RdfTriple triple: triples) {
                    String line = triple.getSubject() + " " + triple.getPredicate() + " " + triple.getObject() + "\n";
                    os.write(line.getBytes());
                }
            } catch(Exception e) {
                try {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad format.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    private ArrayList<RdfTriple> getNamespaceUnion(String namespaceId1, String namespaceId2) throws ApiException {
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

    private ArrayList<RdfTriple> getNamespaceIntersection(String namespaceId1, String namespaceId2) throws ApiException {
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

    private ArrayList<RdfTriple> getNamespaceDifference(String namespaceId1, String namespaceId2) throws ApiException {
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

}
