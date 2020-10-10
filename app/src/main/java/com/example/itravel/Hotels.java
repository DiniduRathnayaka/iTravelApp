package com.example.itravel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hotels extends AppCompatActivity {
    private FirebaseRecyclerOptions<Offer> options;
    private FirebaseRecyclerAdapter<Offer,MyViewHolder> adapter;

    private RecyclerView recyclerView;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);


        recyclerView=findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        reff = FirebaseDatabase.getInstance().getReference().child("ADDOffer");

        options=new FirebaseRecyclerOptions.Builder<Offer>().setQuery(FirebaseDatabase.getInstance().getReference().child("ADDOffer"),Offer.class).build();
        adapter=new FirebaseRecyclerAdapter< Offer, MyViewHolder >(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i, @NonNull final Offer offer) {
                myViewHolder.dis.setText("Description  :"+ offer.getDescription());
                myViewHolder.cat.setText("Category     :"+ offer.getCategory());
                myViewHolder.ofName.setText("Offer Name    :"+offer.getOfferName());
                myViewHolder.RestName.setText("Restaurent Name    :"+offer.getRestaurent());

                if(offer.getImgbitmap()!= null){
                    byte[] decodedString = Base64.decode(offer.getImgbitmap(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    myViewHolder.img_up_img.setImageBitmap(decodedByte);
                }


                final String postkey=getRef(i).getKey();
                myViewHolder.upbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();

                        bundle.putString("Category",offer.getCategory());

                        bundle.putString("Restaurent",offer.getRestaurent());

                        bundle.putString("OfferName",offer.getOfferName());

                        if(offer.getImgbitmap()!= null){
                            byte[] decodedString = Base64.decode(offer.getImgbitmap(), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            bundle.putString("ImgBitmap",storeImage(decodedByte));
                        }else {
                            bundle.putString("ImgBitmap",null);
                        }



                        bundle.putString("Description",offer.getDescription());
                        bundle.putString("key",postkey);


                        Intent  intent =new Intent(Hotels.this,Update.class);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                });

                myViewHolder.delebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reff.child(postkey).removeValue();
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }



    public String storeImage(Bitmap image) {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        String mImageName="MI_"+ timeStamp +".jpg";
        File pictureFile = getOutputMediaFile(mImageName);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();

            return mImageName;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public File getOutputMediaFile(String mImageName){


        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");



        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

}