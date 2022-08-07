package com.jps.test_suitmedia_jps;

import android.Manifest;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button next;
    private Button check;

    private EditText palindrome;
    private EditText username;

    private ImageView profile;

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palindrome = (EditText) findViewById(R.id.palindrome);
        username = (EditText) findViewById(R.id.username);

        check = (Button) findViewById(R.id.check_button);
        next = (Button) findViewById(R.id.next_button);

        profile = (ImageView) findViewById(R.id.profile_picture);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storedPalindrome = palindrome.getText().toString();
                checkPalindrome(storedPalindrome);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storedUsername = username.getText().toString();
                String storedPalindrome = palindrome.getText().toString();
                checkInput(storedPalindrome,storedUsername);
            }
        });

        profile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(
                            MainActivity.this, new String[]
                                    {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION);
                }
                else{
                    selectImage();
                    Log.d("URI", "Masuk1");
                }
                return true;
            }
        });
    }

    private void selectImage()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            Log.d("URI", "Masuk2");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((requestCode == REQUEST_CODE_STORAGE_PERMISSION) && (grantResults.length > 0)){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Log.d("URI", "Masuk3");
                selectImage();
            }else{
                Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode==REQUEST_CODE_SELECT_IMAGE) && (resultCode == RESULT_OK))
        {
            if(data!= null)
            {
                Log.d("URI", "Masuk4");
                Uri selectedImageUri = data.getData();
                if(selectedImageUri != null)
                {
                    Log.d("URI", "Masuk5");
                    try{
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        profile.setImageBitmap(bitmap);
                    }catch(Exception exception){
                        Toast.makeText(this,exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
                }
            }
        }
    }

    public void checkInput(String storedPalindrome, String storedUsername)
    {
        if((storedPalindrome.equals(""))||(storedUsername.equals("")))
        {
            Snackbar.make(check, "A field(s) is empty", Snackbar.LENGTH_LONG)
                    .show();
        }else
        {
            openScreen2(storedUsername);
        }
        openScreen2(storedUsername);
    }
    public void checkPalindrome(String storedPalindrome)
    {
        String normalString = storedPalindrome.toLowerCase();
        String reverse = "";
        for(int i=normalString.length()-1; i>=0;i--)
        {
            reverse = reverse + normalString.charAt(i);
        }
        if(reverse.equals(normalString))
        {
            Snackbar.make(check, "Is a Palindrome", Snackbar.LENGTH_LONG)
                    .show();
        }else
        {
            Snackbar.make(check, "Not a Palindrome", Snackbar.LENGTH_LONG)
                    .show();
        }
    }
    public void openScreen2(String storedUsername)
    {
        Intent intent = new Intent(this, screen2.class);
        intent.putExtra("USERNAME", storedUsername);
        startActivity(intent);
    }
}