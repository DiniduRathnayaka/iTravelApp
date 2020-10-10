package com.example.itravel;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Update extends AppCompatActivity {
    Spinner catup;
    TextView RestUp;
    TextView DiUp;
    TextView offUp;
    Button UPbtn;

    ImageView img_added_;
    Button btn_add_attachment_;
    private static int RESULT_LOAD_IMAGE_ = 1;
    private String base64_encode_image_=null;

    AwesomeValidation awesomeValidation;

    TextView restname;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference reference;


    public Bundle getBundele=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        catup = (Spinner)findViewById(R.id.spinner1);
        RestUp=(EditText)findViewById(R.id.reUp);
        DiUp=(EditText)findViewById(R.id.DiUptxt);
        offUp=(EditText)findViewById(R.id.ofUp);

        img_added_ = findViewById(R.id.img_added_);
        base64_encode_image_ = null;
        btn_add_attachment_ = findViewById(R.id.btn_add_attachment_);


        //Initialize validation style

        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation For Name

        awesomeValidation.addValidation(this, R.id.reUp,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);


        awesomeValidation.addValidation(this, R.id.DiUptxt,
                RegexTemplate.NOT_EMPTY, R.string.invalid_dis);

        awesomeValidation.addValidation(this, R.id.ofUp,
                RegexTemplate.NOT_EMPTY, R.string.invalid_offer);


        btn_add_attachment_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base64_encode_image_=null;
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE_);
            }
        });


        reference = FirebaseDatabase.getInstance().getReference().child("ADDOffer");

        UPbtn=(Button)findViewById(R.id.updateof);
        restname = findViewById(R.id.resttxt);


        getBundele=this.getIntent().getExtras();

        String categ=getBundele.getString("Category");
        String RestName=getBundele.getString("Restaurent");
        String Discri=getBundele.getString("Description");
        String OfferName=getBundele.getString("OfferName");
        String imgbitmap=getBundele.getString("ImgBitmap");

        final String rowkey=getBundele.getString("key");


        if (categ.equals("Premium")){
            catup.setSelection(0);
        }else if (categ.equals("Gold")){
            catup.setSelection(1);
        }else if (categ.equals("Basic")){
           catup.setSelection(3);
        }else {
            catup.setSelection(0);
        }


        RestUp.setText(RestName);
        DiUp.setText(Discri);
        offUp.setText(OfferName);

        if(imgbitmap != null){
            loadImageFromStorage(imgbitmap);
        }

        UPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  categUp=catup.getSelectedItem().toString();

                String  RestNameUp=RestUp.getText().toString().trim();
                String  DiscriUp=DiUp.getText().toString().trim();
                String  OfferNameUp=offUp.getText().toString().trim();
                Offer offer=new Offer(categUp,RestNameUp,DiscriUp,OfferNameUp,base64_encode_image_);


                //check validation
                if(awesomeValidation.validate()){
                    //on success
                    Toast.makeText(getApplicationContext(),
                            "Form Validate Succefully...",Toast.LENGTH_SHORT).show();
                    reference.child(rowkey).setValue(offer);
                    Toast.makeText(Update.this,"Data Update Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Update.this, Hotels.class));
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

        if (requestCode == RESULT_LOAD_IMAGE_ && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            img_added_.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            base64_encode_image_= BitmapUtil.getStringImage(BitmapFactory.decodeFile(picturePath));
        }
    }







    private void loadImageFromStorage(String path)
    {

        try {

            File f = new File(Environment.getExternalStorageDirectory()
                    + "/Android/data/"
                    + getApplicationContext().getPackageName()
                    + "/Files",path);

            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

            img_added_.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }


}