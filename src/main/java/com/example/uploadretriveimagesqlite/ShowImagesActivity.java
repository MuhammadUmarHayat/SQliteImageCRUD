package com.example.uploadretriveimagesqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ShowImagesActivity extends AppCompatActivity
{

    private DatabaseHandler objectDBHandler;
    private RecyclerView objectRecyclerView;
    private RVAdopter objectRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        try
        {
           objectRecyclerView=findViewById(R.id.RCImages) ;
           objectDBHandler=new DatabaseHandler(this);

        }
        catch(Exception exp){

            Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }



    }




    public void getData(View view)
    {

        try
        {
            objectRVAdapter=new RVAdopter(objectDBHandler.getAllImagesData());
objectRecyclerView.setHasFixedSize(true);
objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
objectRecyclerView.setAdapter(objectRVAdapter);


        }
        catch(Exception exp){

            Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
