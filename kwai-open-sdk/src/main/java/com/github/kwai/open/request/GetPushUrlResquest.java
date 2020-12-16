package com.github.kwai.open.request;

import java.util.Map;

import com.github.kwai.open.utils.HttpUtils;
import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.api.IOpenAPI;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class GetPushUrlResquest extends BaseOpenApiRequest {

    @NotNull
    private byte[] file;
    @NotNull
    private String fileName;

    private String caption;
    private Integer panoramic;
    private Boolean shopLive;
    private String deviceName;
    private Integer liveSceneType;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getPanoramic() {
        return panoramic;
    }

    public void setPanoramic(Integer panoramic) {
        this.panoramic = panoramic;
    }

    public Boolean getShopLive() {
        return shopLive;
    }

    public void setShopLive(Boolean shopLive) {
        this.shopLive = shopLive;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getLiveSceneType() {
        return liveSceneType;
    }

    public void setLiveSceneType(Integer liveSceneType) {
        this.liveSceneType = liveSceneType;
    }

    @Override
    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = super.toQueryParam(openAPI);
        if (caption != null) {
            map.put("caption", caption);
        }
        if (panoramic != null) {
            map.put("panoramic", panoramic);
        }
        if (shopLive != null) {
            map.put("shopLive", shopLive);
        }
        if (deviceName != null) {
            map.put("deviceName", deviceName);
        }
        if (liveSceneType != null) {
            map.put("liveSceneType", liveSceneType);
        }

        return map;
    }

    public HttpUtils.FileParam toFileParam() {
        HttpUtils.FileParam fileParam = new HttpUtils.FileParam();
        fileParam.setParam("file");
        fileParam.setFile(file);
        fileParam.setFileName(fileName);
        return fileParam;
    }
}
