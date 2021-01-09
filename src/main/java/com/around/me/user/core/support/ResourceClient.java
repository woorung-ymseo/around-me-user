package com.around.me.user.core.support;

import com.around.me.user.core.constants.HeaderConstants;
import com.around.me.user.core.context.ResponseContextHolder;
import com.around.me.user.core.dto.Response;
import com.around.me.user.core.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ResourceClient {

    private final RestTemplate restTemplate;
    private final ServiceDomain serviceDomain;

    public String getServiceDomain(String serviceName) {
        return serviceDomain.getDomain(serviceName);
    }

    /**
     * RestTemplate get
     *
     * @param serviceName
     * @param parameter
     * @param httpHeaders
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> getForResponse(String serviceName, Object parameter, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.GET, parameter, httpHeaders, clazz);
    }

    /**
     * RestTemplate get
     *
     * @param serviceName
     * @param parameter
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> getForResponse(String serviceName, Object parameter, Class<T> clazz) {
        return this.getForResponse(serviceName, parameter, null, clazz);
    }

    /**
     * RestTemplate get
     *
     * @param serviceName
     * @param httpHeaders
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> getForResponse(String serviceName, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.GET, null, httpHeaders, clazz);
    }


    /**
     * RestTemplate get
     *
     * @param serviceName
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> getForResponse(String serviceName, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.GET, null, null, clazz);
    }



    /**
     * RestTemplate post
     *
     * @param serviceName
     * @param parameter
     * @param httpHeaders
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> postForResponse(String serviceName, Object parameter, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.POST, parameter, httpHeaders, clazz);
    }

    /**
     * RestTemplate post
     *
     * @param serviceName
     * @param httpHeaders
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> postForResponse(String serviceName, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.POST, null, httpHeaders, clazz);
    }

    /**
     * RestTemplate post
     *
     * @param serviceName
     * @param parameter
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> postForResponse(String serviceName, Object parameter, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.POST, parameter, null, clazz);
    }

    /**
     * RestTemplate post
     *
     * @param serviceName
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Response<T> postForResponse(String serviceName, Class<T> clazz) {
        return this.excuete(serviceName, HttpMethod.POST, null, null, clazz);
    }

    /**
     * RestTemplate Excuete
     *
     * @param serviceName
     * @param method
     * @param parameter
     * @param httpHeaders
     * @param clazz
     * @param <T>
     * @return
     */
    @SneakyThrows
    private <T> Response<T> excuete(String serviceName, HttpMethod method, Object parameter, HttpHeaders httpHeaders, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        String uri = this.getServiceDomain(serviceName);

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();

        if (parameter != null) {
            Map<String, Object> map = objectMapper.convertValue(parameter, Map.class);

            paramMap.setAll(map);

            // GET 방식은 경우 URI에 Query String  추가
            if (StringUtils.equals(method.toString(), HttpMethod.GET.toString())) {
                uri = uri + this.urlEncodeUTF8(map);
            }
        }

        HttpEntity httpEntity = this.getHttpEntity(paramMap, httpHeaders);


        ResponseEntity<Response<T>> responseEntity =
                restTemplate.exchange(uri, method, httpEntity
                        , new ParameterizedTypeReference<Response<T>>() {
                            public Type getType() {
                                return new ResourceParameterizedTypeImpl((ParameterizedType) super.getType(), new Type[] {clazz});
                            }
                        }, paramMap);

        return responseEntity.getBody();
    }


    /**
     * Get Header
     *
     * @return
     */
    private HttpHeaders getHeader() {
        return HttpHeaderBuilder.builder()
                .xAuthToken(ResponseContextHolder.xAuthToken())
                .xRefreshToken(ResponseContextHolder.xRefreshToken())
                .build();
    }

    /**
     * Get HttpEntity made by HttpHeaders
     *
     * @param httpHeaders
     * @return
     */
    private HttpEntity<MultiValueMap<String, Object>> getHttpEntity(HttpHeaders httpHeaders) {

        if (httpHeaders == null) {
            httpHeaders = this.getHeader();
        } else {
            this.setHeaderOfResponseContextHolder(httpHeaders);
        }

        return new HttpEntity<MultiValueMap<String, Object>>(httpHeaders);
    }

    /**
     * Get HttpEntity made by HttpHeaders
     *
     * @param httpHeaders
     * @return
     */
    private HttpEntity getHttpEntity(Object body, HttpHeaders httpHeaders) {

        if (httpHeaders == null) {
            httpHeaders = this.getHeader();
        } else {
            this.setHeaderOfResponseContextHolder(httpHeaders);
        }

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity(body, httpHeaders);
    }

    /**
     * Get HttpEntity (included token header)
     *
     * @return
     */
    private HttpEntity<String> getHttpEntity() {
        return new HttpEntity<String>(this.getHeader());
    }

    private String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
    private String urlEncodeUTF8(Map<?,?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?,?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }


    /**
     * Header 값 설정 (ResponseContextHolder 의 값 헤더 설정)
     *
     * @param httpHeaders
     */
    private void setHeaderOfResponseContextHolder(HttpHeaders httpHeaders) {
        HttpServletRequest request = HttpServletUtils.getRequest();

//        // Access Token
//        if (StringUtils.isEmpty(request.getHeader(HeaderConstants.AUTHORIZATION))) {
            httpHeaders.set(HeaderConstants.X_AUTH_TOKEN, ResponseContextHolder.xAuthToken());
//        }
    }
}
