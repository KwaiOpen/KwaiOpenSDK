package com.github.kwai.open.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.KwaiOpenResultCode;
import com.github.kwai.open.response.BaseResponse;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-11
 */
public class HttpUtils {

    private static final int HTTP_SUCCESS = 200;

    private static final ObjectMapper objectMapper;

    private static final Map<String, Object> EMPTY_MAP = Collections.EMPTY_MAP;
    private static final List<FileParam> EMPTY_FILE_LIST = Collections.EMPTY_LIST;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T extends BaseResponse> T get(String url, Class<T> clazz) throws KwaiOpenException {
        return get(url, EMPTY_MAP, clazz);
    }


    public static <T extends BaseResponse> T get(String url, Map<String, Object> query, Class<T> clazz) throws KwaiOpenException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uri = new URIBuilder(url);
            List<NameValuePair> list = query.entrySet().stream().map(
                entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()))
            ).collect(Collectors.toList());
            uri.setParameters(list);
            RequestConfig requestConfig = RequestConfig.custom().build();
            HttpGet httpGet = new HttpGet(uri.build());
            //设置请求和传输超时时间
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            String httpEntityContent = getHttpEntityContent(response);
            return getResponse(httpEntityContent, clazz);
        } catch (IOException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.NETWORK_ERROR, "http IOException ", e);
        } catch (URISyntaxException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http URISyntaxException ", e);
        } catch (KwaiOpenException e) {
            throw e;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http Exception ", e);
        }
    }

    public static <T extends BaseResponse> T postJson(String url, Map<String, Object> query, String jsonData, Class<T> clazz) throws KwaiOpenException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uri = new URIBuilder(url);
            List<NameValuePair> list = query.entrySet().stream().map(
                entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()))
            ).collect(Collectors.toList());
            uri.setParameters(list);
            HttpPost httpPost = new HttpPost(uri.build());
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
            httpPost.setEntity(new StringEntity(jsonData, "utf-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String httpEntityContent = getHttpEntityContent(response);
            return getResponse(httpEntityContent, clazz);
        } catch (IOException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http IOException ", e);
        } catch (URISyntaxException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http URISyntaxException ", e);
        } catch (KwaiOpenException e) {
            throw e;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http Exception ", e);
        }
    }

    public static <T extends BaseResponse> T postBinary(String url, Map<String, Object> query, byte[] file, Class<T> clazz) throws KwaiOpenException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uri = new URIBuilder(url);
            List<NameValuePair> list = query.entrySet().stream().map(
                entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()))
            ).collect(Collectors.toList());
            uri.setParameters(list);
            HttpPost httpPost = new HttpPost(uri.build());
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().build();
            httpPost.setConfig(requestConfig);
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(file, ContentType.APPLICATION_OCTET_STREAM);
            httpPost.setEntity(byteArrayEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String httpEntityContent = getHttpEntityContent(response);
            return getResponse(httpEntityContent, clazz);
        } catch (IOException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http IOException ", e);
        } catch (URISyntaxException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http URISyntaxException ", e);
        } catch (KwaiOpenException e) {
            throw e;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http Exception ", e);
        }
    }

    public static <T extends BaseResponse> T postMultipart(String url, Map<String, Object> data, Class<T> clazz) throws KwaiOpenException {
        return postMultipart(url, EMPTY_MAP, data, EMPTY_FILE_LIST, clazz);
    }

    public static <T extends BaseResponse> T postMultipart(String url, Map<String, Object> query, Map<String, Object> data, Class<T> clazz) throws KwaiOpenException {
        return postMultipart(url, query, data, EMPTY_FILE_LIST, clazz);
    }

    public static <T extends BaseResponse> T postMultipart(String url, FileParam fileParam, Class<T> clazz) throws KwaiOpenException {
        return postMultipart(url, EMPTY_MAP, EMPTY_MAP, Arrays.asList(fileParam), clazz);
    }

    public static <T extends BaseResponse> T postMultipart(String url, List<FileParam> fileParams, Class<T> clazz) throws KwaiOpenException {
        return postMultipart(url, EMPTY_MAP, EMPTY_MAP, fileParams, clazz);
    }

    public static <T extends BaseResponse> T postMultipart(String url, Map<String, Object> data, FileParam fileParam, Class<T> clazz) throws KwaiOpenException {
        return postMultipart(url, EMPTY_MAP, data, Arrays.asList(fileParam), clazz);
    }

    public static <T extends BaseResponse> T postMultipart(String url, Map<String, Object> data, List<FileParam> fileParams, Class<T> clazz) throws KwaiOpenException {
        return postMultipart(url, EMPTY_MAP, data, fileParams, clazz);
    }

    public static <T extends BaseResponse> T postMultipart(String url, Map<String, Object> query, Map<String, Object> data, List<FileParam> fileParams, Class<T> clazz) throws KwaiOpenException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uri = new URIBuilder(url);
            List<NameValuePair> list = query.entrySet().stream().map(
                entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()))
            ).collect(Collectors.toList());
            uri.setParameters(list);
            HttpPost httpPost = new HttpPost(uri.build());
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().build();
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            fileParams.stream().forEach(
                fileParam -> {
                    if (fileParam.getFileName() != null && !fileParam.getFileName().isEmpty()) {
                        multipartEntityBuilder.addBinaryBody(fileParam.getParam(), fileParam.getFile(), ContentType.APPLICATION_OCTET_STREAM, fileParam.getFileName());
                    } else {
                        multipartEntityBuilder.addBinaryBody(fileParam.getParam(), fileParam.getFile());
                    }

                }
            );
            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
            data.entrySet().forEach(
                entry -> multipartEntityBuilder.addTextBody(entry.getKey(), String.valueOf(entry.getValue()), contentType)
            );
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String httpEntityContent = getHttpEntityContent(response);
            return getResponse(httpEntityContent, clazz);
        } catch (IOException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http IOException ", e);
        } catch (URISyntaxException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http URISyntaxException ", e);
        } catch (KwaiOpenException e) {
            throw e;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http Exception ", e);
        }
    }


    private static String getHttpEntityContent(HttpResponse response) throws KwaiOpenException {
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String responseStr = "";
            if (entity != null) {
                try (InputStream is = entity.getContent();
                     BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                    String line = br.readLine();
                    StringBuilder sb = new StringBuilder();
                    while (line != null) {
                        sb.append(line + "\n");
                        line = br.readLine();
                    }
                    responseStr = sb.toString();
                }
            }
            if (statusCode == HTTP_SUCCESS) {
                return responseStr;
            } else {
                throw new KwaiOpenException(KwaiOpenResultCode.HTTP_ERROR, "http code :[" + statusCode + "],responseStr:[" + responseStr + "]");
            }
        } catch (IOException e) {
            throw new KwaiOpenException(KwaiOpenResultCode.NETWORK_ERROR, "http IOException  :", e);
        }
    }

    public static class FileParam {
        private String param;
        private byte[] file;
        private String fileName;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public byte[] getFile() {
            return file;
        }

        public void setFile(byte[] file) {
            this.file = file;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }


    private static <T extends BaseResponse> T getResponse(String responseStr, Class<T> clazz) throws KwaiOpenException {
        T response = JsonUtils.readValue(responseStr, clazz);
        if (response.getResultCode() != KwaiOpenResultCode.SUCCESS) {
            throw new KwaiOpenException(response.getResultCode(), response.getErrorMsg());
        }
        return response;
    }


}
