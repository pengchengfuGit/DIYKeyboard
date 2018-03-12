package com.pengchengfu.keybraod;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.pengchengfu.keybraod.utils.KeyBoardUtil;

public class MainActivity extends AppCompatActivity {

    private EditText mInput;
    private KeyboardView mKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        mKeyboard = findViewById(R.id.ky_keyboard);
        mInput = findViewById(R.id.et_input);
    }

    private void initEvent() {
        mInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mInput.hasFocus()){
                    new KeyBoardUtil(mKeyboard,mInput).showKeyboard();
                }
                return false;
            }
        });
    }
}
