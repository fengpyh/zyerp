package com.cryptomind.trading.utils;

import com.fastfintech.sdk.util.ExceptionUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class HttpUtil {

    static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        @Override
        public String handleResponse(
                final HttpResponse response) throws ClientProtocolException, IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    };

    public static String httpGet(String url) {
    	long start = System.currentTimeMillis();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            String responseBody = httpclient.execute(httpget, responseHandler);
            return responseBody;
            //System.out.println("----------------------------------------");
            //System.out.println(responseBody);
        }catch (Exception e){
            log.error("error: {}", ExceptionUtil.toString(e));
        } finally {
            try {
                httpclient.close();
            }catch (Exception e) {
                log.error(ExceptionUtil.toString(e));
            }
            long end = System.currentTimeMillis();
//            log.info("HttpUtil, url={}, {} mills", url, end-start);
            
        }
        return null;
    }

    /**
     *
     * @param url
     * @param map 是head
     * @return
     */
    public static String httpGet(String url, HashMap<String, String> map) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpget.setHeader(entry.getKey(),entry.getValue());
            }
            String responseBody = httpclient.execute(httpget, responseHandler);
//            log.info("responseBody:{}",responseBody);
            return responseBody;
        }catch (Exception e){
            log.error("error: {}", ExceptionUtil.toString(e));
        } finally {
            try {
                httpclient.close();
            }catch (Exception e) {
                log.error(ExceptionUtil.toString(e));
            }
        }
        return null;
    }

    public static String httpPost(String url, Map<String, Object> param) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);

//            log.info("Executing post request " + httpPost.getRequestLine());
            HttpEntity entity = new UrlEncodedFormEntity(createParam(param), Consts.UTF_8);
            httpPost.setEntity(entity);
            String responseBody = httpclient.execute(httpPost, responseHandler);
            return responseBody;
        }catch (Exception e){
            log.error("post request error: {}", ExceptionUtil.toString(e));
        } finally {
            try {
                httpclient.close();
            }catch (Exception e) {
                log.error(ExceptionUtil.toString(e));
            }
        }
        return null;
    }

    public static String httpPostMatch(String url, String jsonParam) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type", "application/json");
            httpPost.addHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
            //HttpEntity entity = new UrlEncodedFormEntity(createParam(param), Consts.UTF_8);
            //httpPost.setEntity(entity);
            String responseBody = httpclient.execute(httpPost, responseHandler);
            return responseBody;
        }catch (Exception e){
            log.error("post request error: {}", ExceptionUtil.toString(e));
        } finally {
            try {
                httpclient.close();
            }catch (Exception e) {
                log.error(ExceptionUtil.toString(e));
            }
        }
        return null;
    }


    public static String httpPost(String url, List list) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            Gson gson = new Gson();
            StringEntity entity=new StringEntity(gson.toJson(list), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            return httpclient.execute(httpPost, responseHandler);
        }catch (Exception e){
            //log.error("post request error: {}", ExceptionUtil.toString(e));
        } finally {
            try {
                httpclient.close();
            }catch (Exception e) {
                //log.error(ExceptionUtil.toString(e));
            }
        }
        return null;
    }

    private static List<NameValuePair> createParam(Map<String, Object> param) {
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(param != null) {
            for(String k : param.keySet()) {
                if(param.get(k) != null) {
                    nvps.add(new BasicNameValuePair(k, param.get(k).toString()));
                }
            }
        }
        return nvps;
    }
}
