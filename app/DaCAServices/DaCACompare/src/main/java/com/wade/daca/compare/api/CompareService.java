package com.wade.daca.compare.api;

import com.wade.daca.sparql.client.ApiException;
import com.wade.daca.sparql.client.model.RdfTriple;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by catavlas on 1/27/2018.
 */
@RestController
public interface CompareService {

    @RequestMapping(value = "/compare/union/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    ArrayList<RdfTriple> constructNamespaceUnion(@PathVariable("namespaceId1") String namespaceId1,
                                                 @PathVariable("namespaceId2") String namespaceId2) throws ApiException;

    @RequestMapping(value = "/compare/intersection/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    ArrayList<RdfTriple> constructNamespaceIntersection(@PathVariable("namespaceId1") String namespaceId1,
                                                 @PathVariable("namespaceId2") String namespaceId2) throws ApiException;

    @RequestMapping(value = "/compare/difference/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    ArrayList<RdfTriple> constructNamespaceDifference(@PathVariable("namespaceId1") String namespaceId1,
                                                        @PathVariable("namespaceId2") String namespaceId2) throws ApiException;

    @RequestMapping(value = "/compare/isomorphism/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    String checkIsomorphism(@PathVariable("namespaceId1") String namespaceId1,
                            @PathVariable("namespaceId2") String namespaceId2) throws ApiException;
}
