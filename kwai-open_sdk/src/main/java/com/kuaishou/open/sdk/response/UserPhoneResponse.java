package com.kuaishou.open.sdk.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuaishou.open.sdk.model.UserPhoneInfo;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-11
 */
public class UserPhoneResponse extends BaseResponse {


    @JsonProperty("encrypted_phone")
    private String EncryptedPhone;

    private UserPhoneInfo userPhoneInfo;


    public String getEncryptedPhone() {
        return EncryptedPhone;
    }

    public void setEncryptedPhone(String encryptedPhone) {
        EncryptedPhone = encryptedPhone;
    }

    public UserPhoneInfo getUserPhoneInfo() {
        return userPhoneInfo;
    }

    public void setUserPhoneInfo(UserPhoneInfo userPhoneInfo) {
        this.userPhoneInfo = userPhoneInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserPhoneResponse{");
        sb.append("EncryptedPhone='").append(EncryptedPhone).append('\'');
        sb.append(", userPhoneInfo=").append(userPhoneInfo);
        sb.append('}');
        return sb.toString();
    }
}
