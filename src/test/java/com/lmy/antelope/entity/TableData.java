package com.lmy.antelope.entity;

import lombok.Data;

/**
 * @author yangmeiliang
 * @date 2018/1/26
 */
@Data
public class TableData {
    private Integer index;
    private String type;
    private String level;
    private String message;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
