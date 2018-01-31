# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.wade.daca.sparql</groupId>
    <artifactId>DaCASparqlProcessorClient</artifactId>
    <name>DaCASparqlProcessorClient</name>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.wade.daca.sparql:DaCASparqlProcessorClient:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.wade.daca.sparql.*;
import com.wade.daca.sparql.auth.*;
import com.wade.daca.sparql.model.*;
import com.wade.daca.sparql.api.NamespaceApi;

import java.io.File;
import java.util.*;

public class NamespaceApiExample {

    public static void main(String[] args) {
        
        NamespaceApi apiInstance = new NamespaceApi();
        String namespaceId = "namespaceId_example"; // String | The namespace to be created
        try {
            apiInstance.createNamespace(namespaceId);
        } catch (ApiException e) {
            System.err.println("Exception when calling NamespaceApi#createNamespace");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:1994*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*NamespaceApi* | [**createNamespace**](docs/NamespaceApi.md#createNamespace) | **POST** /namespace/{namespaceId} | Create a new namespace
*NamespaceApi* | [**deleteNamespace**](docs/NamespaceApi.md#deleteNamespace) | **DELETE** /namespace/{namespaceId} | Delete a namespace
*NamespaceApi* | [**getNamespaceStats**](docs/NamespaceApi.md#getNamespaceStats) | **GET** /namespace/stats/{namespaceId} | Get namespace stats
*NamespaceApi* | [**getNamespaces**](docs/NamespaceApi.md#getNamespaces) | **GET** /namespace/ | Get namespaces
*SparqlApi* | [**executeSparqlQuery**](docs/SparqlApi.md#executeSparqlQuery) | **GET** /sparql/query | Execute custom SPARQL query
*SparqlApi* | [**executeSparqlUpdate**](docs/SparqlApi.md#executeSparqlUpdate) | **POST** /sparql/query | Execute custom update SPARQL query
*TriplesApi* | [**addTriples**](docs/TriplesApi.md#addTriples) | **POST** /triples/{namespaceId} | Add triples in given namespace
*TriplesApi* | [**addTriplesFromFile**](docs/TriplesApi.md#addTriplesFromFile) | **PUT** /triples/{namespaceId} | Add triples from file in given namespace
*TriplesApi* | [**getTriples**](docs/TriplesApi.md#getTriples) | **GET** /triples/{namespaceId} | Get triples of given namespace


## Documentation for Models

 - [RdfStats](docs/RdfStats.md)
 - [RdfTriple](docs/RdfTriple.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

aurelian.hreapca@info.uaic.ro catalin.vlas@info.uaic.ro virgil.barcan@info.uaic.ro

