package com.mkiisoft.builditbigger.utils;

import android.app.ProgressDialog;
import android.content.Context;

/*
 * Created by mzorilla on 5/19/18.
 */

public class SimpleProgressDialog extends ProgressDialog {

    public static final int SPINNER = 0;
    public static final int HORIZONTAL = 1;

    public SimpleProgressDialog(Context context) {
        super(context);
    }

    public SimpleProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public SimpleProgressDialog title(CharSequence title) {
        setTitle(title);
        return this;
    }

    public SimpleProgressDialog cancelable(boolean flag) {
        setCancelable(flag);
        return this;
    }

    public SimpleProgressDialog setStyle(int style) {
        setProgressStyle(style);
        return this;
    }

    public SimpleProgressDialog maxValue(int max) {
        setMax(max);
        return this;
    }

    public SimpleProgressDialog showDialog() {
        show();
        return this;
    }
}