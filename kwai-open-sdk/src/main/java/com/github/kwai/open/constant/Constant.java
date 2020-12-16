package com.github.kwai.open.constant;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-16
 */
public interface Constant {
    String KWAI_OPEN_DOMAIN = "https://open.kuaishou.com";
    String KWAI_OPEN_CODE_TO_TOKEN_URL = KWAI_OPEN_DOMAIN + "/oauth2/access_token";
    String KWAI_OPEN_REFRESH_TOKEN_URL = KWAI_OPEN_DOMAIN + "/oauth2/refresh_token";
    String KWAI_OPEN_API_USER_INFO_URL = KWAI_OPEN_DOMAIN + "/openapi/user_info";
    String KWAI_OPEN_API_USER_PHONE_URL = KWAI_OPEN_DOMAIN + "/openapi/user_phone";
    String KWAI_OPEN_API_START_UPLOAD_URL = KWAI_OPEN_DOMAIN + "/openapi/photo/start_upload";
    String KWAI_OPEN_API_UPLOAD_URL = "/api/upload";
    String KWAI_OPEN_API_UPLOAD_FRAGMENT_URL = "/api/upload/fragment";
    String KWAI_OPEN_API_UPLOAD_FRAGMENT_RESUME_URL = "/api/upload/resume";
    String KWAI_OPEN_API_UPLOAD_COMPLETE_URL = "/api/upload/complete";
    String KWAI_OPEN_API_VIDEO_PUBLISH_URL = KWAI_OPEN_DOMAIN + "/openapi/photo/publish";
    String KWAI_OPEN_API_VIDEO_LIST_URL = KWAI_OPEN_DOMAIN + "/openapi/photo/list";
    String KWAI_OPEN_API_VIDEO_INFO_URL = KWAI_OPEN_DOMAIN + "/openapi/photo/info";
    String KWAI_OPEN_API_GET_PUSH_URLS_URL = KWAI_OPEN_DOMAIN + "/openapi/kwaiUser/live/pushUrls";
    String KWAI_OPEN_API_GET_ALL_SCENE_URL = KWAI_OPEN_DOMAIN + "/openapi/kwaiUser/live/scene";
    String KWAI_OPEN_API_STOP_PUSH_URL = KWAI_OPEN_DOMAIN + "/openapi/kwaiUser/live/stopPush";
    String KWAI_OPEN_API_PUSH_STATUS_URL = KWAI_OPEN_DOMAIN + "/openapi/kwaiUser/live/status/";

    int KWAI_OPEN_FRAGMENT_SIZE = 10 * 1024 * 1024;
}
