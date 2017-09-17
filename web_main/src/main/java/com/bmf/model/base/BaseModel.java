package com.bmf.model.base;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;

/**
 * Created by BMF on 2017/8/27.
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 5914984873695646461L;
    private int index;

    public int getIndex() {
        return index;
    }

    public BaseModel setIndex(int index) {
        this.index = index;
        return this;
    }

    public static interface Mapper extends RowMapper<Object> {
    }
}
