# NamespaceApi

All URIs are relative to *http://localhost:1994*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createNamespace**](NamespaceApi.md#createNamespace) | **POST** /namespace/{namespaceId} | Create a new namespace
[**deleteNamespace**](NamespaceApi.md#deleteNamespace) | **DELETE** /namespace/{namespaceId} | Delete a namespace
[**getNamespaceStats**](NamespaceApi.md#getNamespaceStats) | **GET** /namespace/stats/{namespaceId} | Get namespace stats
[**getNamespaces**](NamespaceApi.md#getNamespaces) | **GET** /namespace/ | Get namespaces


<a name="createNamespace"></a>
# **createNamespace**
> createNamespace(namespaceId)

Create a new namespace



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NamespaceApi;


NamespaceApi apiInstance = new NamespaceApi();
String namespaceId = "namespaceId_example"; // String | The namespace to be created
try {
    apiInstance.createNamespace(namespaceId);
} catch (ApiException e) {
    System.err.println("Exception when calling NamespaceApi#createNamespace");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace to be created |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteNamespace"></a>
# **deleteNamespace**
> deleteNamespace(namespaceId)

Delete a namespace



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NamespaceApi;


NamespaceApi apiInstance = new NamespaceApi();
String namespaceId = "namespaceId_example"; // String | The namespace to be deleted
try {
    apiInstance.deleteNamespace(namespaceId);
} catch (ApiException e) {
    System.err.println("Exception when calling NamespaceApi#deleteNamespace");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace to be deleted |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getNamespaceStats"></a>
# **getNamespaceStats**
> RdfStats getNamespaceStats(namespaceId)

Get namespace stats



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NamespaceApi;


NamespaceApi apiInstance = new NamespaceApi();
String namespaceId = "namespaceId_example"; // String | The namespace for which stats are requested
try {
    RdfStats result = apiInstance.getNamespaceStats(namespaceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NamespaceApi#getNamespaceStats");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **namespaceId** | **String**| The namespace for which stats are requested |

### Return type

[**RdfStats**](RdfStats.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getNamespaces"></a>
# **getNamespaces**
> List&lt;String&gt; getNamespaces()

Get namespaces



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NamespaceApi;


NamespaceApi apiInstance = new NamespaceApi();
try {
    List<String> result = apiInstance.getNamespaces();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NamespaceApi#getNamespaces");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

