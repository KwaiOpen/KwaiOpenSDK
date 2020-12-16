package com.github.kwai.open.api;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.KwaiOpenResultCode;
import com.github.kwai.open.model.UserPhoneInfo;
import com.github.kwai.open.utils.AESUtils;
import com.github.kwai.open.utils.HttpUtils;
import com.github.kwai.open.utils.JsonUtils;
import com.github.kwai.open.utils.ValidatorUtils;
import com.github.kwai.open.constant.Constant;
import com.github.kwai.open.request.UserInfoRequest;
import com.github.kwai.open.request.UserPhoneRequest;
import com.github.kwai.open.response.UserInfoResponse;
import com.github.kwai.open.response.UserPhoneResponse;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-10
 */
public class KwaiOpenUserApi implements IOpenAPI {

    private String appId = "";

    private KwaiOpenUserApi(String appId) {
        this.appId = appId;
    }


    /**
     * 初始化SDK
     *
     * @param appId appId
     */
    public static KwaiOpenUserApi init(String appId) {
        return new KwaiOpenUserApi(appId);
    }


    @Override
    public String getAppId() {
        return appId;
    }

    /**
     * [用户]获取用户信息 需要 user_info 授权
     *
     * @param request
     * @return
     * @throws KwaiOpenException 当获取失败时会抛出KwaiOpenException异常
     */
    public UserInfoResponse getUserInfo(UserInfoRequest request) throws KwaiOpenException {

        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_API_USER_INFO_URL, request.toQueryParam(this), UserInfoResponse.class);
    }

    /**
     * [用户]获取用户电话号码 需要用户的 user_phone 授权
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public UserPhoneResponse getUserPhone(UserPhoneRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        try {
            UserPhoneResponse userPhoneResponse = HttpUtils.get(Constant.KWAI_OPEN_API_USER_PHONE_URL, request.toQueryParam(this), UserPhoneResponse.class);
            List<String> array = Arrays.asList(userPhoneResponse.getEncryptedPhone().split(":"));
            byte[] iv = Base64.decodeBase64(array.get(0));
            String cipherPhone = array.get(1);

            byte[] decryptBytes = AESUtils.decrypt(Base64.decodeBase64(request.getAppSecret()), iv, cipherPhone);
            String plainPhone = new String(decryptBytes, "utf-8");
            UserPhoneInfo userPhoneInfo = JsonUtils.readValue(plainPhone, UserPhoneInfo.class);
            userPhoneResponse.setUserPhoneInfo(userPhoneInfo);

            return userPhoneResponse;
        } catch (KwaiOpenException e) {
            throw e;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.SDK_ERROR, "客户端解析失败", e);
        }

    }

}
