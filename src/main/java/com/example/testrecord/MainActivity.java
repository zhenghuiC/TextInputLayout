package com.example.testrecord;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.haozhang.lib.AnimatedRecordingView;

import java.sql.BatchUpdateException;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextInputLayout inputLayout ;
    private String RESULT_OK="OK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button)findViewById(R.id.btn);
        inputLayout =(TextInputLayout)findViewById(R.id.inputLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户输入的结果
                String string =inputLayout.getEditText().getText().toString();

                inputLayout.setErrorEnabled(false);
                String result =validate(string);
                if(result.equals(RESULT_OK)) {
                    Toast.makeText(MainActivity.this, "格式正确", Toast.LENGTH_SHORT).show();
                }else{
                    showError(inputLayout,result);
                }
            }
        });
    }

    //设置错误信息提示并将焦点放到出错的地方
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    //对输入格式进行检查
    public String validate(String string){
        if(string==null || string.equals("")){
            return "手机号码不能为空！";
        }else if(string.length()!=11){
            return "手机号码长度不对！";
        }
        return RESULT_OK;
    }

}
