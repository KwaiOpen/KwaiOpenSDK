package com.github.kwai.open.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-11
 */
public class RefreshTokenResponse extends BaseResponse {


    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private List<String> scopes;

    @JsonProperty("refresh_token_expires_in")
    private Long refreshTokenExpiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public Long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(Long refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RefreshTokenResponse{");
        sb.append("accessToken='").append(accessToken).append('\'');
        sb.append(", expiresIn=").append(expiresIn);
        sb.append(", refreshToken='").append(refreshToken).append('\'');
        sb.append(", scopes=").append(scopes);
        sb.append(", refreshTokenExpiresIn=").append(refreshTokenExpiresIn);
        sb.append('}');
        return sb.toString();
    }
}
