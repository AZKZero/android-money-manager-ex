package com.money.manager.ex.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.util.AttributeSet;

public class RobotoRadioButton extends AppCompatRadioButton {
    public RobotoRadioButton(Context context) {
        super(context);
    }

    public RobotoRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public RobotoRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        RobotoView.parseAttributes(context, this, attrs);
    }
}
