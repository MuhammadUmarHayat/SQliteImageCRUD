package com.example.uploadretriveimagesqlite;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdopter extends RecyclerView.Adapter<RVAdopter.RVViewHolderClass>
{
Context context;
    private static final String TAG="NameTag:";
//Linearlayout1
    ArrayList <ModelClass> objectModelClassList;

    public RVAdopter(ArrayList<ModelClass> objectModelClassList)
    {
        this.objectModelClassList = objectModelClassList;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        this.context=parent.getContext();
        return new RVViewHolderClass(LayoutInflater.from( parent.getContext())
                .inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolderClass holder, int position)
    {
final ModelClass objectModelClass=objectModelClassList.get(position);
holder.imageNameTV.setText(objectModelClass.getImageName());
holder.objectImageView.setImageBitmap(objectModelClass.getImage());

holder.linearLayout.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View view)
    {
     //   objectModelClassList.get(0);

        //Toast.makeText();


        String sessionId = objectModelClass.getImageName();
         Toast.makeText(context,"You have selected "+objectModelClass.getImageName(),Toast.LENGTH_LONG).show();
      //  Intent intent = new Intent(getBaseContext(), UserDashBoard.class);
      //  intent.putExtra("USER_SESSION_ID", sessionId);
      //  startActivity(intent);


     //   Log.d(TAG,objectModelClass.getImageName());




    }
});


    }

    @Override
    public int getItemCount() {
        return objectModelClassList.size();
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder
    {

        TextView imageNameTV;
        ImageView objectImageView;
        LinearLayout linearLayout;
        public RVViewHolderClass(View itemView)
        {
            super(itemView);
            imageNameTV=itemView.findViewById(R.id.TXVheadingSR);
            objectImageView=itemView.findViewById(R.id.Image1SR);
            linearLayout=itemView.findViewById(R.id.Linearlayout1);
        }


    }



}
