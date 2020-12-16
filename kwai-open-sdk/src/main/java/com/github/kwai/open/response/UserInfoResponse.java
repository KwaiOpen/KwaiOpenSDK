package com.github.kwai.open.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.kwai.open.model.UserInfo;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-11
 */
public class UserInfoResponse extends BaseResponse  {

    @JsonProperty("user_info")
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfoResponse{");
        sb.append("userInfo=").append(userInfo);
        sb.append('}');
        return sb.toString();
    }


}
