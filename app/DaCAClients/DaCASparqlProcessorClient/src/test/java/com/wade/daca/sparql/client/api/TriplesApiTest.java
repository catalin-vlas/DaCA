/*
 * DaCASparqlProcessor
 * This is DaCA's Sparql Processor service.  You can find out more about     us at [https://github.com/catalin-vlas/DaCA](DaCA).
 *
 * OpenAPI spec version: 1.0.0
 * Contact: aurelian.hreapca@info.uaic.ro
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.wade.daca.sparql.client.api;

import com.wade.daca.sparql.client.ApiException;
import java.io.File;
import com.wade.daca.sparql.client.model.RdfTriple;
import org.junit.Test;
import org.junit.Ignore;

import java.util.List;

/**
 * API tests for TriplesApi
 */
@Ignore
public class TriplesApiTest {

    private final TriplesApi api = new TriplesApi();

    
    /**
     * Add triples in given namespace
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addTriplesTest() throws ApiException {
        String namespaceId = null;
        List<RdfTriple> triples = null;
        api.addTriples(namespaceId, triples);

        // TODO: test validations
    }
    
    /**
     * Add triples from file in given namespace
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addTriplesFromFileTest() throws ApiException {
        String namespaceId = null;
        String format = null;
        File file = null;
        api.addTriplesFromFile(namespaceId, format, file);

        // TODO: test validations
    }
    
    /**
     * Get triples of given namespace
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTriplesTest() throws ApiException {
        String namespaceId = null;
        List<RdfTriple> response = api.getTriples(namespaceId);

        // TODO: test validations
    }
    
}
