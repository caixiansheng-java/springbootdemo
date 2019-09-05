package com.hyx.demo.sdk.bean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
        // 总连接数
        connMgr.setMaxTotal(100);
        // 同路由的并发数
        connMgr.setDefaultMaxPerRoute(100);

        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(5000)
                .setConnectionRequestTimeout(2000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(defaultRequestConfig)
                .build();
        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        template.setMessageConverters(messageConverters);
        template.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                // 返回false表示不管response的status是多少都返回没有错
                // 这里可以自己定义哪些status code你认为是可以抛Error
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // 这里面可以实现你自己遇到了Error进行合理的处理
            }
        });
        return template;
    }
}
