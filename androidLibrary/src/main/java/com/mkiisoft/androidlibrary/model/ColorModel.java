package com.mkiisoft.androidlibrary.model;

public class ColorModel {

    private int bgColor;
    private int textColor;

    public ColorModel(int bgColor, int textColor) {
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getTextColor() {
        return textColor;
    }
}