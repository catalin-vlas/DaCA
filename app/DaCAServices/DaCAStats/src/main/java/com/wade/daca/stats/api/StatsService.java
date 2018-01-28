package com.wade.daca.stats.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface StatsService {

    @RequestMapping(value = "/stats/namespaces", method = RequestMethod.GET)
    List<String> getNamespaces();

    // TODO: Add stats requests
}
