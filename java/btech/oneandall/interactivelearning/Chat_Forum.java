package btech.oneandall.interactivelearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat_Forum extends AppCompatActivity {

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    String Myname, Mymsg,Myuid;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText editText;
    ArrayList<HashMap> arrList ,arrList2;
    Button button3;

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String Mypic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_chat__forum);
        editText= findViewById(R.id.editText);
        recyclerView= findViewById(R.id.rv);
        button3= findViewById(R.id.button3);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        Myname= user.getDisplayName();
        Myuid = user.getUid();
        Mypic = String.valueOf(user.getPhotoUrl());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




        getChats();



/*

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                AuthUI.getInstance()
                        .signOut(Chat_Romm.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>(){

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                startActivity(new Intent(Chat_Romm.this, MainActivity.class));

                            }
                        });
            }
        });


 */




        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMsg();
                editText.setText("");

                // getChats();
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }



    public  void addMsg()
    {
        Mymsg=editText.getText().toString();
        Map<String, Object> user = new HashMap<>();
        user.put("MSG", Mymsg);
        user.put("NAME",Myname);
        user.put("PIC",Mypic);
        //user.put("UID",Myuid);
        ArrayList<Map<String, Object>> msg= new ArrayList<>();
        msg.add(user);

        db.collection("user").document("q1AtzKHvms9R6PugJj80")
                .update("chats", FieldValue.arrayUnion(user)).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Chat_Forum.this,""+e,Toast.LENGTH_LONG).show();
            }
        });

        getChats();

    }

    public void getChats()
    {

        db.collection("user").document("q1AtzKHvms9R6PugJj80").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                arrList= (ArrayList<HashMap>) document.get("chats");

                                //arrList2= (ArrayList<HashMap>) arrList.clone();

                                ArrayList<String> names_list = new ArrayList<>();
                                ArrayList<String> msg_list= new ArrayList<>();
                                ArrayList<String> pic_list= new ArrayList<>();
                                for (int i =0;i<arrList.size();i++)
                                {
                                    Map<String, Object> uk = arrList.get(i);
                                    msg_list.add(i, String.valueOf(uk.get("MSG")));
                                    names_list.add(i, String.valueOf(uk.get("NAME")));
                                    pic_list.add(i, String.valueOf(uk.get("PIC")));

                                }

                                layoutManager = new LinearLayoutManager(Chat_Forum.this);
                                recyclerView.setLayoutManager(layoutManager);
                                mAdapter = new Adapter2(names_list,msg_list,pic_list,Chat_Forum.this);
                                recyclerView.setAdapter(mAdapter);
                                //  Toast.makeText(Chat_Romm.this,""+arrList.size(),Toast.LENGTH_SHORT).show();
                                recyclerView.scrollToPosition(arrList.size()-1);


                            } else {
                            }
                        } else {
                        }
                    }
                });



    }

    @Override
    protected void onStart() {
        super.onStart();
        db.collection("user").document("q1AtzKHvms9R6PugJj80").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {



                getChats();
            }
        });
    }
}
