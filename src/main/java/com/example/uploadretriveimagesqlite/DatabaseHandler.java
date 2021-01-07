package com.example.uploadretriveimagesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

public class DatabaseHandler extends SQLiteOpenHelper
{
    Context context;
    private static String DATABASE_NAME="testImageDb.db";
    private static int DATABASE_VERSION=1;
    private static String createTableQuery="create table imageInfo(imageName TEXT ,image BLOB)";
private ByteArrayOutputStream objectByteArrayOutputStream;
private byte[] imageInBytes;


    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
      this.context=  context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        try
        {
        sqLiteDatabase.execSQL(createTableQuery);
    }
        catch(Exception exp)
    {

        Toast.makeText(context,exp.getMessage(),Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public void storeImage(ModelClass objectModelClass)
    {
        try
        {
      SQLiteDatabase objectSqLiteDatabase=this.getWritableDatabase();
        Bitmap imageToStoreBitmap=objectModelClass.getImage();
        objectByteArrayOutputStream =new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
        imageInBytes=objectByteArrayOutputStream.toByteArray();
            ContentValues objectValues=new ContentValues();//imageInfo(imageName TEXT ,image BLOB)
            objectValues.put("imageName",objectModelClass.getImageName());
            objectValues.put("image",imageInBytes);
long result=objectSqLiteDatabase.insert("imageInfo",null,objectValues);
if(result!=-1)
{
    Toast.makeText(context,"Data is saved",Toast.LENGTH_SHORT).show();
objectSqLiteDatabase.close();
}
else{
    Toast.makeText(context,"Data is not saved",Toast.LENGTH_SHORT).show();

}


        }
        catch(Exception exp)
        {

            Toast.makeText(context,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }//store image


    public ArrayList<ModelClass> getAllImagesData()
    {
        try
        {
SQLiteDatabase objectSqliteDatabase=this.getReadableDatabase();
ArrayList<ModelClass> objectModelClassList=new ArrayList<>();
            Cursor objectCursor=objectSqliteDatabase.rawQuery("select * from imageInfo ",null);
if(objectCursor.getCount()!=0)
{
while(objectCursor.moveToNext())
{
    String nameOfImage=objectCursor.getString(0);
    byte[] imageByte=objectCursor.getBlob(1);
    Bitmap objectBitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
objectModelClassList.add(new ModelClass(nameOfImage,objectBitmap));

}
return objectModelClassList;
}
else{
    Toast.makeText(context,"value does not exist in database ",Toast.LENGTH_SHORT).show();
    return null;
}
        }
        catch(Exception exp)
        {
            Toast.makeText(context,exp.getMessage(),Toast.LENGTH_SHORT).show();


            return null;
        }


    }






}
