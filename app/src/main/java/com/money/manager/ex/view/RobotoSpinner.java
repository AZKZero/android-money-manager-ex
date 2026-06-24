package com.money.manager.ex.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatSpinner;
import android.util.AttributeSet;

public class RobotoSpinner extends AppCompatSpinner {
    public RobotoSpinner(Context context) {
        super(context);
    }

    public RobotoSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RobotoSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Spinner doesn't directly have text, its items do.
    // The items are handled by adapters which usually inflate standard TextViews.
    // Our MmxViewInflater will handle those TextViews if they are inflated from XML.
}
