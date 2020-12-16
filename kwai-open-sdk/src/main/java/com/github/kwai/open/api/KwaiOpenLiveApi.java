package com.github.kwai.open.api;

import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.request.GetPushUrlResquest;
import com.github.kwai.open.utils.HttpUtils;
import com.github.kwai.open.utils.ValidatorUtils;
import com.github.kwai.open.constant.Constant;
import com.github.kwai.open.request.GetAllSceneRequest;
import com.github.kwai.open.request.PushStatusRequest;
import com.github.kwai.open.request.StopPushRequest;
import com.github.kwai.open.response.GetAllSceneResponse;
import com.github.kwai.open.response.GetPushUrlResponse;
import com.github.kwai.open.response.PushStatusResponse;
import com.github.kwai.open.response.StopPushResponse;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-10
 */
public class KwaiOpenLiveApi implements IOpenAPI {

    private String appId = "";

    private KwaiOpenLiveApi(String appId) {
        this.appId = appId;
    }


    /**
     * 初始化SDK
     *
     * @param appId     appId
     */
    public static KwaiOpenLiveApi init(String appId) {
        return  new KwaiOpenLiveApi(appId);
    }

    @Override
    public String getAppId() {
        return appId;
    }

    /**
     * [直播]获取推流地址 开始直播
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public GetPushUrlResponse getPushUrl(GetPushUrlResquest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.postMultipart(Constant.KWAI_OPEN_API_GET_PUSH_URLS_URL, request.toQueryParam(this), request.toFileParam(), GetPushUrlResponse.class);
    }

    /**
     * [直播]获取直播的分类列表
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public GetAllSceneResponse getAllScene(GetAllSceneRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_API_GET_ALL_SCENE_URL, request.toQueryParam(this), GetAllSceneResponse.class);
    }

    /**
     * [直播]结束直播
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public StopPushResponse stopPush(StopPushRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.postMultipart(Constant.KWAI_OPEN_API_STOP_PUSH_URL, request.toQueryParam(this), StopPushResponse.class);
    }

    /**
     * [直播]获取直播状态
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public PushStatusResponse pushStatus(PushStatusRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_API_PUSH_STATUS_URL + request.getStreamName(), request.toQueryParam(this), PushStatusResponse.class);
    }


}
