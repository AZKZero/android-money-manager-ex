package com.money.manager.ex.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

public class RobotoCheckedTextView extends AppCompatCheckedTextView {
    public RobotoCheckedTextView(Context context) {
        super(context);
    }

    public RobotoCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public RobotoCheckedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        RobotoView.parseAttributes(context, this, attrs);
    }
}
