package com.github.kwai.open;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-10
 */
public enum KwaiOpenResultCode {

    UNKNOWN(0), //占位，大概率SDK未及时更新导致的反序列化失败。
    SUCCESS(1), // 和快手保持一致


    INVALID_REQUEST(100200100), //请求缺少参数或参数类型错误
    UNAUTHORIZED_CLIENT(100200101), //未授权的 client，无效的 app 或 developer
    ACCESS_DENIED(100200102), //请求被拒绝，可能是无效的 token 等
    UNSUPPORTED_RESPONSE_TYPE(100200103), //请求的 responseType 错误
    UNSUPPORTED_GRANT_TYPE(100200104), //请求的 grantType 不支持
    INVALID_GRANT(100200105), //请求的 code 错误
    INVALID_SCOPE(100200106), //请求的 scope 错误
    INVALID_OPENID(100200107), //无效的 openid
    TOKEN_EXPIRED(100200108), // access_token过期
    AUTHORIZATION_REVOKED(100200109), // 用户取消该 app 授权
    AUTHORIZATION_EXPIRED(100200110), // 用户授权过期
    AUTHORIZATION_NEVER(100200111), // 用户未授权过
    INVALID_BUNDLE_TOKEN(100200112), // bundleToken不合法
    REFRESH_TOKEN_EXPIRED(100200113), // refresh_token过期
    SIGN_VERIFY_FAIL(100200114), // 签名校验失败
    RELATE_AUTHORIZATION_ILLEGAL(100200115), // 不合法的关联授权

    PHONE_DUPLICATED(100200130), //自定义手机号已绑定
    SMS_CODE_ERR(100200300), // 短信验证码错误
    ANTI_SPAM_HIT(100200301), // 被风控
    API_RATE_LIMIT(100200410), // api限流
    SERVER_ERROR(100200500), // 服务内部错误，一般不应该出现



    SDK_ERROR(300000),
    NETWORK_ERROR(300001),
    HTTP_ERROR(300002),


    //用户信息 openapi 错误码 号段是400000 到 499999
    RESOURCE_NOT_EXIST(400000), // Open Api 用户资源不存在异常


    VIDEO_NOT_EXISTS(120001),
    VIDEO_NOT_UPLOAD(120002),
    VIDEO_PUBLISH_FAIL(120003),
    BAD_PARAMETERS(100400),


    LIVE_BAD_PARAMETERS(400001),
    API_SPEED_LIMIT(400002),
    LIVE_SERVER_ERROR(400003),
    STEAM_NOT_EXISTS(400005),
    NO_LIVE_PERMISSION(400006),
    NO_MERCHART_LIVE_PERMISSION(400008),
    INVALID_STEAM_NAME(400009),
    UNAUTHORIZED_ACCESS_LIVE(400013),
    ILLEGAL_COVER(400014),

    ;


    private int code;

    public int getCode() {
        return code;
    }

    KwaiOpenResultCode(int code) {
        this.code = code;
    }

    public static KwaiOpenResultCode valueOf(int code) {
        for (KwaiOpenResultCode enums : KwaiOpenResultCode.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        return UNKNOWN;
    }

}
