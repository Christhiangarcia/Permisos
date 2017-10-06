package com.example.chris.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;
    private final int REQUEST_ACCESS_OK=1;
    private final int REQUEST_ACCESS_OKK=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.button);
        btn2=(Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_ACCESS_OK);
                }else{
                    Toast.makeText(MainActivity.this, "YA EXISTE EL PERMISO", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_ACCESS_OKK);
                }else{
                    Toast.makeText(MainActivity.this, "YA EXISTE EL PERMISO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_ACCESS_OKK){
            if(grantResults.length > 0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intentCamara);
            }
        }else {
            Toast.makeText(this, "permiso denegado", Toast.LENGTH_SHORT).show();
        }

        if(requestCode==REQUEST_ACCESS_OK){
            if(grantResults.length > 0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "4451045015")));
            }
        }else{
            Toast.makeText(this, "permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }
}
