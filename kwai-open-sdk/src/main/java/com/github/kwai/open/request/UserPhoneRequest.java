package com.github.kwai.open.request;

import com.github.kwai.open.anotation.NotNull;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class UserPhoneRequest extends BaseOpenApiRequest {


    @NotNull
    private String appSecret;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public UserPhoneRequest() {
    }

    public UserPhoneRequest(String accessToken, String appSecret) {
        super(accessToken);
        this.appSecret = appSecret;
    }
}
