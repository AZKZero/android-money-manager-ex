package com.money.manager.ex.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import com.google.android.material.theme.MaterialComponentsViewInflater;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;

public class MmxViewInflater extends MaterialComponentsViewInflater {

    @NonNull
    @Override
    protected AppCompatTextView createTextView(@NonNull Context context, AttributeSet attrs) {
        return new RobotoTextView(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatButton createButton(@NonNull Context context, AttributeSet attrs) {
        return new RobotoButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatEditText createEditText(@NonNull Context context, AttributeSet attrs) {
        return new RobotoEditText(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatCheckBox createCheckBox(@NonNull Context context, AttributeSet attrs) {
        return new RobotoCheckBox(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatRadioButton createRadioButton(@NonNull Context context, AttributeSet attrs) {
        return new RobotoRadioButton(context, attrs);
    }

    @NonNull
    @Override
    protected AppCompatCheckedTextView createCheckedTextView(@NonNull Context context, AttributeSet attrs) {
        return new RobotoCheckedTextView(context, attrs);
    }

    // Add others if needed (Switch, RadioButton, etc.)
}
