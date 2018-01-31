package com.wade.daca.matchalign.api;

import com.wade.daca.matchalign.dataobject.Alignment;
import com.wade.daca.sparql.client.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface MatchAlignService {

    @RequestMapping(value = "/matchalign/{namespaceId1}/{namespaceId2}", method = RequestMethod.GET)
    List<Alignment> constructMatchAlign(@PathVariable("namespaceId1") String namespaceId1,
                                        @PathVariable("namespaceId2") String namespaceId2) throws ApiException;

}
