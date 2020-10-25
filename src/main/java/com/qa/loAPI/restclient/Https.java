package com.qa.loAPI.restclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 要加入此 apache 的 Maven 依赖
 * <dependency>
 *     <groupId>org.apache.httpcomponents</groupId>
 *     <artifactId>httpclient</artifactId>
 *     <version>4.5.12</version>
 * </dependency>
 */

public class Https {

    /**
     * Get请求
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * Get请求
     */
    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    /**
     * Get请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        checkUrl(url);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(url);

        if (params != null && !params.isEmpty()) {
            uriBuilder.setParameters(covertParams(params));
        }

        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("httpGet请求参数异常", e);
        }
        HttpGet httpGet = new HttpGet(uri);

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpGet.addHeader(header.getKey(), header.getValue());
            }
        }

        return execute(httpGet);
    }



    /**
     * postForm 请求
     */
    public static String postForm(String url) {
        return postForm(url, null);
    }

    /**
     * postForm 请求
     */
    public static String postForm(String url, Map<String, String> params) {
        return postForm(url, params, null);
    }

    /**
     * postForm 请求
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers) {
        checkUrl(url);
        HttpPost httpPost = new HttpPost(url);

        if (params != null && !params.isEmpty()) {
            ArrayList<NameValuePair> pairs = covertParams(params);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, UTF_8);
            httpPost.setEntity(entity);
        }

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpPost.addHeader(header.getKey(), header.getValue());
            }
        }

        return execute(httpPost);
    }



    /**
     * postJson 请求
     */
    public static String postJson(String url) {
        return postJson(url, null);
    }

    /**
     * postJson 请求
     */
    public static String postJson(String url, String jsonParam) {
        return postJson(url, jsonParam, null);
    }

    /**
     * postJson 请求
     */
    public static String postJson(String url, String jsonParam, Map<String, String> headers) {
        checkUrl(url);
        HttpPost httpPost = new HttpPost(url);

        if (jsonParam != null && !"".equals(jsonParam)) {
            StringEntity entity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        }

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpPost.addHeader(header.getKey(), header.getValue());
            }
        }

        return execute(httpPost);
    }



    /**
     * 真正执行请求
     *
     * @param httpRequest 请求方法
     */
    private static String execute(HttpRequestBase httpRequest) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpRequest);
            if (response != null) {
                return EntityUtils.toString(response.getEntity(), UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("httpGet请求异常", e);
        } finally {
            responseClose(response);
            httpClientClose(httpClient);
        }
        return null;
    }

    /**
     * 参数转换
     */
    private static ArrayList<NameValuePair> covertParams(Map<String, String> params) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return nameValuePairs;
    }

    /**
     * 检查目标网址
     */
    private static void checkUrl(String url) {
        String http = "http";
        if (url == null || !url.startsWith(http)) {
            throw new RuntimeException("目标网址不正确");
        }
    }

    /**
     * 释放 httpClient
     */
    private static void httpClientClose(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 释放 response
     */
    private static void responseClose(CloseableHttpResponse response) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
