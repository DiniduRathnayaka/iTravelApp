package com.example.itravel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView cat,dis,RestName,ofName;
    Button upbtn,delebtn;
    ImageView img_up_img;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        dis=itemView.findViewById(R.id.destxt);
        RestName=itemView.findViewById(R.id.resttxt);
        cat=itemView.findViewById(R.id.catTxt);
        ofName=itemView.findViewById(R.id.offtxt);
        upbtn=itemView.findViewById(R.id.updateof);
        delebtn=itemView.findViewById(R.id.deleteOff);
        img_up_img = itemView.findViewById(R.id.img_up_img);


    }
}
