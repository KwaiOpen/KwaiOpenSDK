package com.github.kwai.open.api;

import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.utils.HttpUtils;
import com.github.kwai.open.utils.ValidatorUtils;
import com.github.kwai.open.constant.Constant;
import com.github.kwai.open.request.AccessTokenRequest;
import com.github.kwai.open.request.RefreshTokenRequest;
import com.github.kwai.open.response.AccessTokenResponse;
import com.github.kwai.open.response.RefreshTokenResponse;

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
