package com.kuaishou.open.sdk.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuaishou.open.sdk.KwaiOpenException;
import com.kuaishou.open.sdk.KwaiOpenResultCode;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-11
 */
public class BaseResponse {

    private int result;

    @JsonProperty("error_msg")
    private String errorMsg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorMsg() {
        if (errorMsg != null && !errorMsg.isEmpty()) {
            return errorMsg;
        }
        if (getResultCode() == KwaiOpenResultCode.UNKNOWN) {
            return "unknown error code: " + result;
        }
        return getResultCode().name();
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public KwaiOpenResultCode getResultCode() {
        return KwaiOpenResultCode.valueOf(result);
    }


}
