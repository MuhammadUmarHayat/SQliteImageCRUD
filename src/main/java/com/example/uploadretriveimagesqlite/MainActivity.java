package com.example.uploadretriveimagesqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private static final int PICK_IMAGE_REQUEST=100;
    private EditText imageDetailsET;
private ImageView objectImageView;
private Uri imageFilePath;
private Bitmap imageStore;
DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
dbHandler=new DatabaseHandler(this);
        imageDetailsET = (EditText) findViewById(R.id.editTextNameET);
        objectImageView = (ImageView) findViewById(R.id.Image1);

    }//oncreate

    public void chooseImage(View objectView)
    {
        try
        {
            Intent objectIntent=new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);

        }
        catch(Exception exp){

            Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    public void StoreImage(View view)
    {
        try {
if(!imageDetailsET.getText().toString().isEmpty()&& objectImageView.getDrawable()!=null && imageStore!=null)
{
dbHandler.storeImage(new ModelClass(imageDetailsET.getText().toString(),imageStore));

}
else{
    Toast.makeText(this,"please select image name and image ",Toast.LENGTH_SHORT).show();

}


        }
         catch(Exception exp){

                Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
            }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        try{
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null &&data.getData()!=null)
        {
imageFilePath=data.getData();
imageStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
objectImageView.setImageBitmap(imageStore);

        }


        }
        catch(Exception exp){

            Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    public void showNewActivity(View view){

        startActivity(new Intent(this,ShowImagesActivity.class));
    }


}//end of main
