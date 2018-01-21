/*
 * SparqlProcessor
 * This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.wade.daca.sparql.client.api;

import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.model.RdfTriple;
import org.junit.Test;
import org.junit.Ignore;

import java.util.List;

/**
 * API tests for SparqlApi
 */
@Ignore
public class SparqlApiTest {

    private final SparqlApi api = new SparqlApi();

    
    /**
     * Execute custom SPARQL query
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeSparqlQueryTest() throws ApiException {
        String namespaceId = null;
        String query = null;
        List<RdfTriple> response = api.executeSparqlQuery(namespaceId, query);

        // TODO: test validations
    }
    
    /**
     * Execute custom update SPARQL query
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeSparqlUpdateTest() throws ApiException {
        String namespaceId = null;
        String query = null;
        api.executeSparqlUpdate(namespaceId, query);

        // TODO: test validations
    }
    
}
