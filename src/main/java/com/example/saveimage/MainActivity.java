package com.example.saveimage;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    Button button;
    SaveImage saveImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        havePermission();

        button=findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                Resources res=getResources();
                Bitmap lions =BitmapFactory.decodeResource(res,R.drawable.lions);
                Bitmap paypal=BitmapFactory.decodeResource(res,R.drawable.paypal);

                @Override
                public void onClick(View view) {
                    try {
                        saveImage.Saveimage(getApplicationContext(),lions);
                        saveImage.Saveimage(getApplicationContext(),paypal);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setTitle("保存图片")
//                            .setMessage("保存图片到相册")
//                            .setCancelable(true)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    try {
//                                        saveImage.Saveimage(getApplicationContext(),lions);
//                                        saveImage.Saveimage(getApplicationContext(),paypal);
//                                    } catch (FileNotFoundException e) {
//                                        e.printStackTrace();
//                                    }
//                                    Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT);
//                                }
//                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    }).show();
                }
            });

    }
    public void havePermission(){
                String[] PERMISSIONS = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE" };
        //检测是否有写的权限
        int permission = ContextCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 没有写的权限，去申请写的权限，会弹出对话框
            ActivityCompat.requestPermissions(this, PERMISSIONS,1);
        }
    }
}
