/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author leping
 * @version $Id: HttpDemo.java, v 0.1 2021-07-19 下午5:06 leping Exp $$
 */
public class HttpDemo {
    private HttpRequestBase httpMethod;

    private HttpClient httpClient = HttpClientBuilder.create().build();

    private static final int CONNECT_TIMEOUT = 6000;

    private static final int SOCKET_TIMEOUT = 6000;

    public void buildGetMethod(String url, Map<String, String> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
           params.forEach(uriBuilder::setParameter);
        }

        httpMethod = new HttpGet(uriBuilder.build());
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();
        httpMethod.setConfig(requestConfig);
    }

    public void execute(){
        try {
            HttpResponse httpResponse = httpClient.execute(httpMethod);
            if (httpResponse != null) {
                HttpEntity httpEntity = httpResponse.getEntity();
                System.out.println(EntityUtils.toString(httpEntity));
            }
        } catch (IOException e) {
            //
        }
    }

}
