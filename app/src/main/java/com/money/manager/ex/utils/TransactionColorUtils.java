package com.money.manager.ex.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.money.manager.ex.R;
import com.money.manager.ex.servicelayer.InfoService;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TransactionColorUtils {
    private final Context mContext;
    private final InfoService mInfoService;
    private int mCurColor;
    private AlertDialog mDialog;

    public interface OnColorSelect {
        void onColorSelect(int color);
    }

    public TransactionColorUtils(Context context) {
        mContext = context;
        mInfoService = new InfoService(mContext);
    }

    public TransactionColorUtils resetControl() {
        mCurColor = -1;
        return this;
    }

    public int getSelectedColor() { return mCurColor; }

    public void initColorControls(TextView colorTextView, int color, OnColorSelect onColorSelect) {
        mCurColor = color;
        updateColorDisplay(colorTextView);

        colorTextView.setOnClickListener(v -> showColorPicker(colorTextView, onColorSelect));
    }

    private void updateColorDisplay(TextView colorTextView) {
        if (mCurColor == -1) {
            colorTextView.setHint(mContext.getString(R.string.empty_color_message));
            colorTextView.setBackgroundColor(Color.TRANSPARENT);
            colorTextView.setText("");
        } else {
            colorTextView.setHint("");
            int actualColor;
            if (mCurColor >= 1 && mCurColor <= 7) {
                actualColor = mInfoService.getColorNumberFromInfoKey(mCurColor);
            } else {
                actualColor = mCurColor;
            }
            colorTextView.setBackgroundColor(actualColor);
            colorTextView.setText(String.format("#%06X", (0xFFFFFF & actualColor)));

            // Choose text color based on brightness
            if (isColorDark(actualColor)) {
                colorTextView.setTextColor(Color.WHITE);
            } else {
                colorTextView.setTextColor(Color.BLACK);
            }
        }
    }

    private boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }

    private void showColorPicker(TextView colorTextView, OnColorSelect onColorSelect) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.empty_color_message);

        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_color_picker, null);
        GridView gridView = dialogView.findViewById(R.id.colorGridView);
        EditText hexEditText = dialogView.findViewById(R.id.hexColorEditText);
        View previewView = dialogView.findViewById(R.id.colorPreview);
        Button noColorButton = dialogView.findViewById(R.id.noColorButton);

        // Predefined colors
        final int[] colors = new int[]{
                0xFFF44336, 0xFFE91E63, 0xFF9C27B0, 0xFF673AB7,
                0xFF3F51B5, 0xFF2196F3, 0xFF03A9F4, 0xFF00BCD4,
                0xFF009688, 0xFF4CAF50, 0xFF8BC34A, 0xFFCDDC39,
                0xFFFFEB3B, 0xFFFFC107, 0xFFFF9800, 0xFFFF5722,
                0xFF795548, 0xFF9E9E9E, 0xFF607D8B, 0xFF000000
        };

        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() { return colors.length; }
            @Override
            public Object getItem(int position) { return colors[position]; }
            @Override
            public long getItemId(int position) { return position; }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = new View(mContext);
                    int size = mContext.getResources().getDimensionPixelSize(R.dimen.mmx_icon_size) * 2;
                    view.setLayoutParams(new GridView.LayoutParams(size, size));
                }
                int color = colors[position];
                GradientDrawable shape = new GradientDrawable();
                shape.setShape(GradientDrawable.OVAL);
                shape.setColor(color);
                shape.setStroke(2, Color.LTGRAY);
                view.setBackground(shape);
                view.setOnClickListener(v -> {
                    mCurColor = color;
                    hexEditText.setText(String.format("#%06X", (0xFFFFFF & color)));
                    previewView.setBackgroundColor(color);
                });
                return view;
            }
        });

        // Hex Input
        if (mCurColor != -1) {
            int actualColor = (mCurColor >= 1 && mCurColor <= 7) ? mInfoService.getColorNumberFromInfoKey(mCurColor) : mCurColor;
            hexEditText.setText(String.format("#%06X", (0xFFFFFF & actualColor)));
            previewView.setBackgroundColor(actualColor);
        }

        hexEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String hex = s.toString();
                if (hex.startsWith("#") && (hex.length() == 7 || hex.length() == 9)) {
                    try {
                        int color = Color.parseColor(hex);
                        mCurColor = color;
                        previewView.setBackgroundColor(color);
                    } catch (Exception e) {
                        Timber.e(e, "Error parsing color");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        noColorButton.setOnClickListener(v -> {
            mCurColor = -1;
            mDialog.dismiss();
            updateColorDisplay(colorTextView);
            if (onColorSelect != null) onColorSelect.onColorSelect(mCurColor);
        });

        builder.setView(dialogView);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            updateColorDisplay(colorTextView);
            if (onColorSelect != null) onColorSelect.onColorSelect(mCurColor);
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        mDialog = builder.create();
        mDialog.show();
    }
}
