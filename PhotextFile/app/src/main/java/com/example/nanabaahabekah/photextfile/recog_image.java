package com.example.nanabaahabekah.photextfile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * Created by nanabaahabekah on 2017-06-25.
 */


/*
So far, this program just allows the user to select an image from gallery.
 */
public class recog_image extends AppCompatActivity{

    private static int IMG_RESULT = 1;
    private static int PIC_RESULT = 0;
    String imageDecode;
    ImageView IMAGE_SVS;
    Button TP;
    Button GAL;
    Intent intent;
    String[] FILE;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        Button for getting image from gallery.
         */
        ////////////////////////////////////////////////////////////////////////////////////////////
        /*IMAGE_SVS = (ImageView)findViewById(R.id.image_sv);
        GAL = (Button)findViewById(R.id.gallery);

        GAL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_RESULT);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////


        /*
        Button to take a picture
         */
        ////////////////////////////////////////////////////////////////////////////////////////////
        /*TP = (Button)findViewById(R.id.take_picture);

        TP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PIC_RESULT);
            }
        });*/

        final CharSequence[] items = {"Take Picture", "Camera"};
        new AlertDialog.Builder(this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, PIC_RESULT);
                                break;
                            case 1:
                                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, IMG_RESULT);
                                break;
                        }
                    }
                });

    }

    /*
    Getting an image from gallery and take picture;
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        /*
        Switch case to chose which option to run.
         */
        ////////////////////////////////////////////////////////////////////////////////////////////
        switch (requestCode) {
            case 0:
                try {
                    if (requestCode == IMG_RESULT && resultCode == RESULT_OK && null != data) {
                        Uri uri = data.getData();
                        String[] FILE = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(uri, FILE, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(FILE[0]);
                        imageDecode = cursor.getString(columnIndex);
                        cursor.close();

                        IMAGE_SVS.setImageBitmap(BitmapFactory.decodeFile(imageDecode));
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                IMAGE_SVS.setImageBitmap(bitmap);
                break;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
