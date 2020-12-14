package com.kuaishou.open.sdk;

/**
 * 开放平台异常
 *
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-10
 */
public class KwaiOpenException extends Exception {

    private KwaiOpenResultCode result;

    public KwaiOpenException(KwaiOpenResultCode result) {
        super();
        this.result = result;
    }

    public KwaiOpenException(KwaiOpenResultCode result, String message) {
        super(message);
        this.result = result;
    }

    public KwaiOpenException(KwaiOpenResultCode result, String message, Throwable cause) {
        super(message, cause);
        this.result = result;
    }

    public KwaiOpenResultCode getResult() {
        return result;
    }

    public void setResult(KwaiOpenResultCode result) {
        this.result = result;
    }

}
