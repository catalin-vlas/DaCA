# TriplesApi

All URIs are relative to *http://localhost:1994*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addTriples**](TriplesApi.md#addTriples) | **POST** /triples/{namespaceId} | Add triples in given namespace
[**addTriplesFromFile**](TriplesApi.md#addTriplesFromFile) | **PUT** /triples/{namespaceId} | Add triples from file in given namespace
[**getTriples**](TriplesApi.md#getTriples) | **GET** /triples/{namespaceId} | Get triples of given namespace


<a name="addTriples"></a>
# **addTriples**
> addTriples(namespaceId, triples)

Add triples in given namespace



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TriplesApi;


TriplesApi apiInstance = new TriplesApi();
String namespaceId = "namespaceId_example"; // String | The namespace in which triples are added
List<RdfTriple> triples = Arrays.asList(new RdfTriple()); // List<RdfTriple> | The array of triples
try {
    apiInstance.addTriples(namespaceId, triples);
} catch (ApiException e) {
    System.err.println("Exception when calling TriplesApi#addTriples");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace in which triples are added |
 **triples** | [**List&lt;RdfTriple&gt;**](RdfTriple.md)| The array of triples |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="addTriplesFromFile"></a>
# **addTriplesFromFile**
> addTriplesFromFile(namespaceId, format, file)

Add triples from file in given namespace



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TriplesApi;


TriplesApi apiInstance = new TriplesApi();
String namespaceId = "namespaceId_example"; // String | The namespace in which triples are added
String format = "format_example"; // String | The format of the data inside the file
File file = new File("/path/to/file.txt"); // File | File containing triples
try {
    apiInstance.addTriplesFromFile(namespaceId, format, file);
} catch (ApiException e) {
    System.err.println("Exception when calling TriplesApi#addTriplesFromFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace in which triples are added |
 **format** | **String**| The format of the data inside the file |
 **file** | **File**| File containing triples | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="getTriples"></a>
# **getTriples**
> List&lt;RdfTriple&gt; getTriples(namespaceId)

Get triples of given namespace



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TriplesApi;


TriplesApi apiInstance = new TriplesApi();
String namespaceId = "namespaceId_example"; // String | The namespace to be created
try {
    List<RdfTriple> result = apiInstance.getTriples(namespaceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TriplesApi#getTriples");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace to be created |

### Return type

[**List&lt;RdfTriple&gt;**](RdfTriple.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

