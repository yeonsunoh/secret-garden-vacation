package com.example.shingubotanic;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class seasonalherbgarden extends DialogFragment implements View.OnClickListener{

    public static final String TAG_EVENT_DIALOG ="seasonalherbgarden"; //수정  ""

    public seasonalherbgarden(){} //계절초화원(3)

    public static seasonalherbgarden getInstance() {
        seasonalherbgarden sea = new seasonalherbgarden(); //수정 변수명 스펠링 앞세개
        return sea; //수정
    }

    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sacedInstanceState){
        View v = inflater.inflate(R.layout.seasonalherbgarden,container);// xml명 변경
        Button cancel =(Button) v.findViewById(R.id.cancel);
        TextView seaE = (TextView) v.findViewById(R.id.seaE);

        final ImageView sea =(ImageView) v.findViewById(R.id.sea);
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://shingubotanic-d2239.appspot.com");
        StorageReference storageRef = storage.getReference();
        String seas = "season.JPG";

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://shingubotanic-d2239-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = database.getReference("season");

        //Storage
        storageRef.child(seas).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시

                Glide.with(Objects.requireNonNull(getContext()))
                        .load(uri)
                        .into(sea);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Log.d("TEST", "error" + exception.getLocalizedMessage());
            }
        });

        //Database
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class); //dataSnapshot(객체)을 통해서 데이터가 전달됨
                seaE.setText(value);
//              Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        cancel.setOnClickListener(this);
        setCancelable(false);
        return v;

    }

    @Override
    public void onClick(View v) {
        dismiss();

    }
}
