package com.around.me.user.core.support;

import com.around.me.user.core.constants.HeaderConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

public class HttpHeaderBuilder {

    private String xAuthToken;
    private String xRefreshToken;

    public HttpHeaderBuilder xAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;

        return this;
    }

    public HttpHeaderBuilder xRefreshToken(String xRefreshToken) {
        this.xRefreshToken = xRefreshToken;

        return this;
    }

    public HttpHeaders build() {
        HttpHeaders header = new HttpHeaders();

        if (StringUtils.isNoneEmpty(this.xAuthToken)) {
            header.set(HeaderConstants.X_AUTH_TOKEN, this.xAuthToken);
        }

        if (StringUtils.isNoneEmpty(this.xRefreshToken)) {
            header.set(HeaderConstants.X_REFRESH_TOKEN, this.xRefreshToken);
        }

        return header;
    }

    public static HttpHeaderBuilder builder() {
        return new HttpHeaderBuilder();
    }
}
