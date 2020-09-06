package btech.oneandall.interactivelearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.Manifest;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    String Mypic, Mymail;
    FirebaseAuth mAuth;
    CircleImageView circleImageView;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_home);
        circleImageView = findViewById(R.id.i_pic);
        db = FirebaseFirestore.getInstance();



        mAuth = FirebaseAuth.getInstance();
      FirebaseUser user = mAuth.getCurrentUser();
        Mypic = String.valueOf(user.getPhotoUrl());
        Mymail = String.valueOf(user.getEmail());
        Glide.with(getApplicationContext()).load(Mypic).into(circleImageView);





        findViewById(R.id.c11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this,Settings.class));


            }
        });


        findViewById(R.id.pb2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this,Learn_AR.class));



            }
        });


        findViewById(R.id.b72).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this,Create_AR.class));


            }
        });


        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this,Chat_Forum.class));



            }
        });


        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this,LIVE_class.class));



            }
        });


        findViewById(R.id.pb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    startActivity(new Intent(Home.this,Pay_fees.class));
                String url = "https://rzp.io/l/RpUvqnv";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Home.this, Uri.parse(url));



            }
        });



        findViewById(R.id.b7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Home.this,Offline_PDF.class));



            }
        });








    }
}