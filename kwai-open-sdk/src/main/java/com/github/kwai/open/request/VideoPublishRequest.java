package com.github.kwai.open.request;

import java.util.HashMap;
import java.util.Map;

import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.api.IOpenAPI;
import com.github.kwai.open.response.StartUploadResponse;
import com.github.kwai.open.utils.HttpUtils;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class VideoPublishRequest extends BaseOpenApiRequest {

    @NotNull
    private String uploadToken;
    @NotNull
    private String caption;
    private String stereoType;
    @NotNull
    private byte[] cover;

    private Long merchantProductId;

    public VideoPublishRequest() {
    }

    public VideoPublishRequest(String accessToken) {
        super(accessToken);
    }

    public VideoPublishRequest(CreateVideoRequest createVideoRequest, StartUploadResponse startUploadResponse) {
        super(createVideoRequest.getAccessToken());
        this.uploadToken = startUploadResponse.getUploadToken();
        this.caption = createVideoRequest.getCaption();
        this.stereoType = createVideoRequest.getStereoType();
        this.cover = createVideoRequest.getCover();
        this.merchantProductId = createVideoRequest.getMerchantProductId();
    }

    public VideoPublishRequest(String accessToken, String uploadToken, String caption, String stereoType, byte[] cover) {
        super(accessToken);
        this.uploadToken = uploadToken;
        this.caption = caption;
        this.stereoType = stereoType;
        this.cover = cover;
    }

    public VideoPublishRequest(String accessToken, String uploadToken, String caption, String stereoType, byte[] cover, Long merchantProductId) {
        super(accessToken);
        this.uploadToken = uploadToken;
        this.caption = caption;
        this.stereoType = stereoType;
        this.cover = cover;
        this.merchantProductId = merchantProductId;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getStereoType() {
        return stereoType;
    }

    public void setStereoType(String stereoType) {
        this.stereoType = stereoType;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public Long getMerchantProductId() {
        return merchantProductId;
    }

    public void setMerchantProductId(Long merchantProductId) {
        this.merchantProductId = merchantProductId;
    }

    @Override
    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = super.toQueryParam(openAPI);
        map.put("upload_token", uploadToken);
        return map;
    }

    public Map<String, Object> toDataParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("caption", caption);
        if (stereoType != null) {
            map.put("stereo_type", stereoType);
        }

        if (merchantProductId != null) {
            map.put("merchant_product_id", merchantProductId);
        }

        return map;
    }

    public HttpUtils.FileParam toFileParam() {
        HttpUtils.FileParam fileParam = new HttpUtils.FileParam();
        fileParam.setParam("cover");
        fileParam.setFile(cover);
        return fileParam;
    }
}
