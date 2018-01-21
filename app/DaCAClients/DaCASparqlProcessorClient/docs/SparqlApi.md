# SparqlApi

All URIs are relative to *http://localhost:1994*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeSparqlQuery**](SparqlApi.md#executeSparqlQuery) | **GET** /sparql/query | Execute custom SPARQL query
[**executeSparqlUpdate**](SparqlApi.md#executeSparqlUpdate) | **POST** /sparql/query | Execute custom update SPARQL query


<a name="executeSparqlQuery"></a>
# **executeSparqlQuery**
> List&lt;RdfTriple&gt; executeSparqlQuery(namespaceId, query)

Execute custom SPARQL query



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SparqlApi;


SparqlApi apiInstance = new SparqlApi();
String namespaceId = "namespaceId_example"; // String | The namespace in which the query is run
String query = "query_example"; // String | The SPARQL query to be executed
try {
    List<RdfTriple> result = apiInstance.executeSparqlQuery(namespaceId, query);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SparqlApi#executeSparqlQuery");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace in which the query is run |
 **query** | **String**| The SPARQL query to be executed |

### Return type

[**List&lt;RdfTriple&gt;**](RdfTriple.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="executeSparqlUpdate"></a>
# **executeSparqlUpdate**
> executeSparqlUpdate(namespaceId, query)

Execute custom update SPARQL query



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SparqlApi;


SparqlApi apiInstance = new SparqlApi();
String namespaceId = "namespaceId_example"; // String | The namespace in which the query is run
String query = "query_example"; // String | The SPARQL query to be executed
try {
    apiInstance.executeSparqlUpdate(namespaceId, query);
} catch (ApiException e) {
    System.err.println("Exception when calling SparqlApi#executeSparqlUpdate");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace in which the query is run |
 **query** | **String**| The SPARQL query to be executed |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

