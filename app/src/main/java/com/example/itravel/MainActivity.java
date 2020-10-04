package com.example.itravel;
<<<<<<< Updated upstream

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
=======
>>>>>>> Stashed changes

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

<<<<<<< Updated upstream
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Spinner mOffer_categories_spinner;
    private EditText mRest_editTxt;
    private EditText   mOffer_editTxt;
    private EditText  mDescri_editTxt;

    ImageView img_added;
    Button btn_add_attachment;

=======
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Spinner mOffer_categories_spinner;
    private EditText mRest_editTxt;
    private EditText   mOffer_editTxt;
    private EditText  mDescri_editTxt;

    ImageView img_added;
    Button btn_add_attachment;

>>>>>>> Stashed changes
    AwesomeValidation awesomeValidation;


    private static int RESULT_LOAD_IMAGE = 1;
    private String base64_encode_image=null;

    private Button mAdd_btn;
    DatabaseReference reff;
    Offer off;
    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_added = findViewById(R.id.img_added);
        base64_encode_image = null;
        btn_add_attachment = findViewById(R.id.btn_add_attachment);


        btn_add_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base64_encode_image=null;
                Intent i = new Intent(
                        Intent.ACTION_PICK,
<<<<<<< Updated upstream
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
=======
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
>>>>>>> Stashed changes
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

<<<<<<< Updated upstream





        mOffer_categories_spinner=(Spinner) findViewById(R.id.offer_categories_spinner);
        mRest_editTxt=(EditText)findViewById(R.id.nameTxtadd);
        mOffer_editTxt=(EditText)findViewById(R.id.nameofTxtadd);
        mDescri_editTxt=(EditText)findViewById(R.id.desTextadd);

        //Initialize validation style

        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation For Name

        awesomeValidation.addValidation(this,R.id.nameTxtadd,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);


        awesomeValidation.addValidation(this,R.id.nameofTxtadd,
                RegexTemplate.NOT_EMPTY,R.string.invalid_offer);

        awesomeValidation.addValidation(this,R.id.desTextadd,
                RegexTemplate.NOT_EMPTY,R.string.invalid_dis);
=======





        mOffer_categories_spinner=(Spinner) findViewById(R.id.offer_categories_spinner);
        mRest_editTxt=(EditText)findViewById(R.id.nameTxtadd);
        mOffer_editTxt=(EditText)findViewById(R.id.nameofTxtadd);
        mDescri_editTxt=(EditText)findViewById(R.id.desTextadd);

        //Initialize validation style

        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation For Name

        awesomeValidation.addValidation(this, R.id.nameTxtadd,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);


        awesomeValidation.addValidation(this, R.id.nameofTxtadd,
                RegexTemplate.NOT_EMPTY, R.string.invalid_offer);

        awesomeValidation.addValidation(this, R.id.desTextadd,
                RegexTemplate.NOT_EMPTY, R.string.invalid_dis);
>>>>>>> Stashed changes




        mAdd_btn =(Button)findViewById(R.id.addOffBtnoff);
        off=new Offer();
        reff = FirebaseDatabase.getInstance().getReference().child("ADDOffer");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid=(snapshot.getChildrenCount());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              off.setCategory(mOffer_categories_spinner.getSelectedItem().toString());
              off.setRestaurent(mRest_editTxt.getText().toString().trim());
              off.setOfferName(mOffer_editTxt.getText().toString().trim());
              off.setDescription(mDescri_editTxt.getText().toString().trim());
              off.setImgbitmap(base64_encode_image);

              String did=reff.push().getKey();
              reff.child(String.valueOf(did)).setValue(off);

              //check validation
                if(awesomeValidation.validate()){
                    //on success
                    Toast.makeText(getApplicationContext(),
                            "Form Validate Succefully...",Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,"Data Insert Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Hotels.class));
                }else
                {

                    Toast.makeText(getApplicationContext(),
                            "Form Validate Faild...",Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
        }catch (Exception e){
            e.printStackTrace();
        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

          img_added.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            base64_encode_image=BitmapUtil.getStringImage(BitmapFactory.decodeFile(picturePath));
        }




    }
}
