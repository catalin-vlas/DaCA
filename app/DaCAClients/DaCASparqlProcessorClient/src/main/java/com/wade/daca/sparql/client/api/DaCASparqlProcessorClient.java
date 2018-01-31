package com.wade.daca.sparql.client.api;

import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.model.RdfStats;
import com.wade.daca.sparql.client.model.RdfTriple;

import java.io.File;
import java.util.List;

public class DaCASparqlProcessorClient {

    private NamespaceApi namespaceApi;
    private SparqlApi sparqlApi;
    private TriplesApi triplesApi;

    public DaCASparqlProcessorClient() {
        namespaceApi = new NamespaceApi();
        sparqlApi = new SparqlApi();
        triplesApi = new TriplesApi();
    }

    /**
     * Create a new namespace
     *
     * @param namespaceId The namespace to be created (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void createNamespace(String namespaceId) throws ApiException {
        namespaceApi.createNamespace(namespaceId);
    }

    /**
     * Delete a namespace
     *
     * @param namespaceId The namespace to be deleted (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteNamespace(String namespaceId) throws ApiException {
        namespaceApi.deleteNamespace(namespaceId);
    }

    /**
     * Get namespaces
     *
     * @return List&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<String> getNamespaces() throws ApiException {
        return namespaceApi.getNamespaces();
    }

    /**
     * Get namespace stats
     *
     * @param namespaceId The namespace for which stats are requested (required)
     * @return RdfStats
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RdfStats getNamespaceStats(String namespaceId) throws ApiException {
        return namespaceApi.getNamespaceStats(namespaceId);
    }

    /**
     * Execute custom SPARQL query
     *
     * @param namespaceId The namespace in which the query is run (required)
     * @param query The SPARQL query to be executed (required)
     * @return List&lt;RdfTriple&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<RdfTriple> executeSparqlQuery(String namespaceId, String query) throws ApiException {
        return sparqlApi.executeSparqlQuery(namespaceId, query);
    }

    /**
     * Execute custom update SPARQL query
     *
     * @param namespaceId The namespace in which the query is run (required)
     * @param query The SPARQL query to be executed (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void executeSparqlUpdate(String namespaceId, String query) throws ApiException {
        sparqlApi.executeSparqlUpdate(namespaceId, query);
    }

    /**
     * Add triples in given namespace
     *
     * @param namespaceId The namespace in which triples are added (required)
     * @param triples The array of triples (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void addTriples(String namespaceId, List<RdfTriple> triples) throws ApiException {
        triplesApi.addTriples(namespaceId, triples);
    }

    /**
     * Add triples from file in given namespace
     *
     * @param namespaceId The namespace in which triples are added (required)
     * @param file File containing triples (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void addTriplesFromFile(String namespaceId, String format, File file) throws ApiException {
        triplesApi.addTriplesFromFile(namespaceId, format, file);
    }

    /**
     * Get triples of given namespace
     *
     * @param namespaceId The namespace to be created (required)
     * @return List&lt;RdfTriple&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<RdfTriple> getTriples(String namespaceId) throws ApiException {
        return triplesApi.getTriples(namespaceId);
    }

}