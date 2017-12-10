package com.qds.optimize.zuul.gateway.hystrix.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zuul的自定义断熔实现类和自定义响应信息内容
 *
 * @author neutron
 */
@Component
public class HelloServiceFallBack implements ZuulFallbackProvider {
    /**
     * 设置服务的断熔名称<br/>
     * 就是spring.application.name的值
     */
    @Override
    public String getRoute() {
        return "hello-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            /**
             * 返回响应头信息
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            /**
             * 返回响应主体信息
             */
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("服务不可用,请稍后重试".getBytes());
            }

            /**
             * 自定义响应状态码,比如200,或者404一类
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * 返回值是getStatusCode中的value值
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            /**
             * 状态文本信息
             */
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            /**
             * 关闭内容
             */
            @Override
            public void close() {

            }
        };
    }
}
