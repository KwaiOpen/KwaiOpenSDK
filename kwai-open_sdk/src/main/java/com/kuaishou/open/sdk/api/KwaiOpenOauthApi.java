package com.kuaishou.open.sdk.api;

import com.kuaishou.open.sdk.KwaiOpenException;
import com.kuaishou.open.sdk.constant.Constant;
import com.kuaishou.open.sdk.request.AccessTokenRequest;
import com.kuaishou.open.sdk.request.RefreshTokenRequest;
import com.kuaishou.open.sdk.response.AccessTokenResponse;
import com.kuaishou.open.sdk.response.RefreshTokenResponse;
import com.kuaishou.open.sdk.utils.HttpUtils;
import com.kuaishou.open.sdk.utils.ValidatorUtils;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-10
 */
public class KwaiOpenOauthApi implements IOpenAPI {

    private String appId = "";

    private KwaiOpenOauthApi(String appId) {
        this.appId = appId;
    }


    /**
     * 初始化SDK
     *
     * @param appId     appId
     */
    public static KwaiOpenOauthApi init(String appId) {
        return new KwaiOpenOauthApi(appId);
    }


    public String getAppId() {
        return appId;
    }

    /**
     * [授权]获取AccessToken
     *
     * @param request
     * @return
     * @throws KwaiOpenException 当获取失败时会抛出KwaiOpenException异常
     */
    public AccessTokenResponse getAccessToken(AccessTokenRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_CODE_TO_TOKEN_URL, request.toQueryParam(this), AccessTokenResponse.class);
    }

    /**
     * [授权]刷新AccessToken
     *
     * @param request
     * @return
     * @throws KwaiOpenException 当获取失败时会抛出KwaiOpenException异常
     */
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_REFRESH_TOKEN_URL, request.toQueryParam(this), RefreshTokenResponse.class);
    }


}
