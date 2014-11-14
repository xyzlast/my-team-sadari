package me.xyzlast.sadari.api.aop;

import java.io.Serializable;

/**
 * Created by ykyoon on 14. 11. 7.
 */
public class ResultData implements Serializable {
    private boolean ok;
    private String message;
    private Object data;

    public ResultData() {
        //NOTE : for ObjectMapper
    }

    public ResultData(boolean ok, String message) {
        this.ok = false;
        this.message = message;
        this.data = null;
    }

    public ResultData(Object data) {
        this.ok = true;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
