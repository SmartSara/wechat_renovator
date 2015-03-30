package com.renovator.pojo.button;

/**
 * Created by darlingtld on 2015/2/20.
 */

/**
 * 按钮的基类
 */
public class ClickButton extends Button {
    private String type = TYPE_CLICK;
    private String key;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
