package com.github.kwai.open.request;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class UserInfoRequest extends BaseOpenApiRequest {

    public UserInfoRequest() {
    }

    public UserInfoRequest(String accessToken) {
        super(accessToken);
    }
}
