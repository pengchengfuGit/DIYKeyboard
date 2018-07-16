package com.pengchengfu.keybraod;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by pengchengfu on 2018/3/12.
 */

public class DIYKeyboardView extends KeyboardView {


    public DIYKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DIYKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) return;
        List<Keyboard.Key> keys = keyboard.getKeys();
        if (keys != null && keys.size() > 0) {
            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);
            Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
            paint.setTypeface(font);
            paint.setAntiAlias(true);
            for (Keyboard.Key key : keys) {
                if (key.codes[0] == -4) {
                    Drawable dr = getContext().getResources().getDrawable(R.drawable.keyboard_blue);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                } else {
                    Drawable dr = getContext().getResources().getDrawable(R.drawable.keyboard_white);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                }
                if (key.label != null) {
                    if (key.codes[0] == -4 ||
                            key.codes[0] == -5) {
                        paint.setTextSize(17 * 2);
                    } else {
                        paint.setTextSize(20 * 2);
                    }
                    if (key.codes[0] == -4) {
                        paint.setColor(getContext().getResources().getColor(android.R.color.white));
                    } else {
                        paint.setColor(getContext().getResources().getColor(R.color.blue_03A9F4));
                    }
                    Rect rect = new Rect(key.x, key.y, key.x + key.width, key.y + key.height);
                    Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
                    int baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                    // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
                    paint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(key.label.toString(), rect.centerX(), baseline, paint);
                }
            }
        }
    }
}