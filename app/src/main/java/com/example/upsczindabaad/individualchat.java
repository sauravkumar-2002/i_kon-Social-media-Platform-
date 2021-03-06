package com.example.upsczindabaad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class individualchat extends AppCompatActivity {
    TextView icusername;
    ImageView icprofilepic, ie1;
    RecyclerView icrecv;
    userdatamodel umd2;
    ArrayList<icmodel> list;
    icadapter icadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualchat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        icusername = (TextView) findViewById(R.id.icusername);
        icprofilepic = (ImageView) findViewById(R.id.icprofilepic);
        icrecv = (RecyclerView) findViewById(R.id.icrecview);
        icrecv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        icadapter = new icadapter(list, this);
        icrecv.setAdapter(icadapter);

        updateStatusUser();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("List of Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    userdatamodel umd3 = dataSnapshot.getValue(userdatamodel.class);
                    if (umd3.getUid().equals(uid)) {
                        continue;
                    }
                    String individual_chat = umd3.getUsername();
                    icmodel icm = new icmodel();
                    icm.setTv(individual_chat);
                    String st = umd3.getPimage();

                    if (st.equals("notuploaded")) {
                        icm.setImageUrl("https://firebasestorage.googleapis.com/v0/b/upsczindabaad-6d9bf.appspot.com/o/image%2Fprof.png?alt=media&token=6166f093-4a90-4dce-8034-539a9dce792f");
                    } else {
                        icm.setImageUrl(st);
                    }
                    list.add(icm);
                }
                icadapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        setUserName();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Status").child(uid);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat1.format(calendar.getTime());


        updatestatus updatestatus = new updatestatus();
        updatestatus.setChatting("Not chatting");
        updatestatus.setStatus("offline");
        reference.setValue(updatestatus);
    }


    private void updateStatusUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Status").child(uid);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat1.format(calendar.getTime());


        updatestatus updatestatus = new updatestatus();
        updatestatus.setChatting("Not chatting");
        updatestatus.setStatus("online");
        reference.setValue(updatestatus);


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Status").child(uid);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat1.format(calendar.getTime());


        updatestatus updatestatus = new updatestatus();
        updatestatus.setChatting("Not chatting");
        updatestatus.setStatus("online");
        reference.setValue(updatestatus);
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Status").child(uid);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat1.format(calendar.getTime());


        updatestatus updatestatus = new updatestatus();
        updatestatus.setChatting("Not chatting");
        updatestatus.setStatus("online");
        reference.setValue(updatestatus);
    }

    @Override
    protected void onStop() {
        super.onStop();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Status").child(uid);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat1.format(calendar.getTime());


        updatestatus updatestatus = new updatestatus();
        updatestatus.setChatting("Not chatting");
        updatestatus.setStatus("offline");
        reference.setValue(updatestatus);

    }

    private void setUserName() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid = user.getUid();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("List of Users").child(uid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                umd2 = new userdatamodel();
                umd2 = snapshot.getValue(userdatamodel.class);
                String userimage = umd2.getPimage();
                String username = umd2.getUsername();
                if (userimage.equals("notuploaded")) {
                    icprofilepic.setImageResource(R.drawable.prof);
                    // Toasty.success(getApplicationContext(), "Default image has been setted").show();
                } else {
                    Glide.with(individualchat.this)
                            .load(userimage)
                            .into(icprofilepic);
                    //Toasty.success(getApplicationContext(), "Updated").show();
                }

                icusername.setText(username);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void ictomyprofile(View view) {
        Intent intent = new Intent(getApplicationContext(), myprofile.class);
        startActivity(intent);

    }
}