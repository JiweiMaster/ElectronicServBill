package main.util;


public class RespData {
    public int code;
    public String message;

    public RespData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return GsonUtil.ObjectToJson(this);
    }
}
