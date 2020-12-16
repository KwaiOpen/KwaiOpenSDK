package com.github.kwai.open.request;

import java.util.Map;

import com.github.kwai.open.api.IOpenAPI;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class VideoListRequest extends BaseOpenApiRequest {

    public VideoListRequest() {
    }

    public VideoListRequest(String accessToken) {
        super(accessToken);
    }

    public VideoListRequest(String accessToken, Integer count) {
        super(accessToken);
        this.count = count;
    }

    public VideoListRequest(String accessToken, String cursor) {
        super(accessToken);
        this.cursor = cursor;
    }

    public VideoListRequest(String accessToken, String cursor, Integer count) {
        super(accessToken);
        this.cursor = cursor;
        this.count = count;
    }

    private String cursor;
    private Integer count;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Map<String, Object> toQueryParam(IOpenAPI openAPI) {
        Map<String, Object> map = super.toQueryParam(openAPI);
        if (cursor != null) {
            map.put("cursor", cursor);
        }
        if (count != null) {
            map.put("count", count);
        }
        return map;
    }
}
