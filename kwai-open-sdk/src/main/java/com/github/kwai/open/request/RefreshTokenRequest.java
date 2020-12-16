package com.github.kwai.open.request;

import java.util.HashMap;
import java.util.Map;

import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.api.IOpenAPI;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class RefreshTokenRequest {

    @NotNull
    private String refreshToken;

    @NotNull
    private String appSecret;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public RefreshTokenRequest() {
    }

    public RefreshTokenRequest(String refreshToken, String appSecret) {
        this.refreshToken = refreshToken;
        this.appSecret = appSecret;
    }

    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = new HashMap<>();
        map.put("refresh_token", refreshToken);
        map.put("app_id", openAPI.getAppId());
        map.put("app_secret", appSecret);
        map.put("grant_type", "refresh_token");
        return map;
    }
}
