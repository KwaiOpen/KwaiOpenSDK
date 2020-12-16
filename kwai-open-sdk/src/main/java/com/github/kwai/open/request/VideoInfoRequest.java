package com.github.kwai.open.request;

import java.util.Map;

import com.github.kwai.open.anotation.NotNull;
import com.github.kwai.open.api.IOpenAPI;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class VideoInfoRequest extends BaseOpenApiRequest {

    public VideoInfoRequest() {
    }

    public VideoInfoRequest(String accessToken, String photoId) {
        super(accessToken);
        this.photoId = photoId;
    }

    @NotNull
    private String photoId;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @Override
    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = super.toQueryParam(openAPI);
        map.put("photo_id", photoId);
        return map;
    }
}
