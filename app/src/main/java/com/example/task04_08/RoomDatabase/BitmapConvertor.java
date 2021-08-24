package com.example.task04_08.RoomDatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.Format;

public class BitmapConvertor {
@TypeConverter
    public byte[] Bitmaptobyts(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,60,outputStream);
        byte btyes[];
        btyes=   outputStream.toByteArray();
        return btyes;
    }
@TypeConverter
    public Bitmap bytToBitmap(byte[] bytes)
{
    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    return bitmap;
}



}
