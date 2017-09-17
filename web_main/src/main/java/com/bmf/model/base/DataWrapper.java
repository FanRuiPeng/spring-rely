package com.bmf.model.base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by BMF on 2017/8/26.
 */
public class DataWrapper<T> implements Serializable {
    private static final long serialVersionUID = -6871356768669393367L;
    private int status;
    private String msg;
    private long time;
    private T data;

    public DataWrapper() {
    }

    public DataWrapper(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public DataWrapper setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public DataWrapper setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public long getTime() {
        return time;
    }

    public DataWrapper setTime(long time) {
        this.time = time;
        return this;
    }

    public T getData() {
        return data;
    }

    public DataWrapper setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataWrapper<?> that = (DataWrapper<?>) o;

        if (status != that.status) return false;
        if (time != that.time) return false;
        if (msg != null ? !msg.equals(that.msg) : that.msg != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = status;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DataWrapper{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                ", data=" + data +
                '}';
    }
}
