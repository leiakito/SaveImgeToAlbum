package com.example.saveimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
    Author:leia
    Write The Code Change The World    
*/public class SaveImage {
    public static void Saveimage(Context context, Bitmap bitmap) throws FileNotFoundException {

//        Resources res=getResources();
//        Bitmap lions = BitmapFactory.decodeResource(res,R.drawable.lions);
//        Bitmap paypal=BitmapFactory.decodeResource(res,R.drawable.paypal);

        //创建目录 在sdcard/Pictures 下的文件为ale
        File file=new File("/sdcard/Pictures","ale");
        //判断如果文件不存在的话
        if (!file.exists()){
            //创建新文件夹
            file.mkdirs();
        }
        //创建字符串 获取当前时间+jpg
        String filename=System.currentTimeMillis()+".jpg";
        //新建文件对象 参数为1为ale ,文件名称为字符串时间
        File file1=new File(file,filename);
        //文件输出对象 参数为file1
        FileOutputStream fileOutputStream=new FileOutputStream(file1);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);

        try {
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通知图片管理器对象 更新
        MediaStore.Images.Media.insertImage(context.getContentResolver(),file1.getAbsolutePath(),filename,null);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE), String.valueOf(Uri.parse(file1.getAbsolutePath())));
    }
}
