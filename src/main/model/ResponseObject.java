package main.model;

import main.util.GsonUtil;

public class ResponseObject {
    public boolean isSuccess;
    public String message;
    public Object object;
    @Override
    public String toString() {
        return GsonUtil.ObjectToJson(this);
    }
}
