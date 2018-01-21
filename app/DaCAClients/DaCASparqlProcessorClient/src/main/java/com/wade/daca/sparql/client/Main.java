package com.wade.daca.sparql.client;

import com.wade.daca.sparql.client.api.DaCASpaqrlProcessorClient;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            DaCASpaqrlProcessorClient client = new DaCASpaqrlProcessorClient();
            List<String> namespaces = client.getNamespaces();
            System.out.println(Arrays.toString(namespaces.toArray()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
