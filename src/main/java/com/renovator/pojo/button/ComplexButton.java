package com.renovator.pojo.button;

/**
 * Created by darlingtld on 2015/2/20.
 */

/**
 * ���Ӱ�ť������ť��
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
