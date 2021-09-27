package com.example.shingubotanic.info.spring;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.shingubotanic.R;
import com.example.shingubotanic.info.info;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class info_spring_plant6_jang extends DialogFragment {

    public static String TAG_EVENT_DIALOG = "info_spring_plant6_jang";   //장수만리화

    public static info_spring_plant6_jang getInstance(){
        info_spring_plant6_jang ispd6 = new info_spring_plant6_jang();
        return ispd6;
    }

    ImageButton cancel;
    ImageView img1, img2;
    Button back, next;
    View.OnClickListener cl;
    Intent i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v6 = inflater.inflate(R.layout.info_spring_plant6_jang, container);

        cancel = (ImageButton) v6.findViewById(R.id.cancel);
        img1 = (ImageView) v6.findViewById(R.id.ispd6_img1);
        img2 = (ImageView) v6.findViewById(R.id.ispd6_img2);
        back = (Button) v6.findViewById(R.id.jang_back);
        next = (Button) v6.findViewById(R.id.jang_next);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://shingubotanic-d2239.appspot.com/");
        StorageReference storageRef = storage.getReference("plantInfo").child("spring");

        //Storage
        storageRef.child("spring_plant6_jang.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onSuccess(Uri uri) {
                //이미지 로드 성공
                Glide.with(Objects.requireNonNull(getContext()))
                        .load(uri)
                        .into(img1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Log.d("TEST", "error" + exception.getLocalizedMessage());
            }
        });

        //Storage2
        storageRef.child("spring_plant6_jang_ex.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onSuccess(Uri uri) {
                //이미지 로드 성공
                Glide.with(Objects.requireNonNull(getContext()))
                        .load(uri)
                        .into(img2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Log.d("TEST", "error" + exception.getLocalizedMessage());
            }
        });

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.cancel:
                        i = new Intent(getActivity().getApplicationContext(), info.class);
                        startActivity(i);
                        break;
                    case R.id.jang_back:
                        v6.setVisibility(v6.GONE);
                        info_spring_plant5_je ispd5 = info_spring_plant5_je.getInstance();
                        ispd5.show(getFragmentManager(), info_spring_plant5_je.TAG_EVENT_DIALOG);
                        break;
                    case R.id.jang_next:
                        v6.setVisibility(v6.GONE);
                        info_spring_plant7_soo ispd7 = info_spring_plant7_soo.getInstance();
                        ispd7.show(getFragmentManager(), info_spring_plant7_soo.TAG_EVENT_DIALOG);
                        break;
                }
            }
        };
        cancel.setOnClickListener(cl);
        back.setOnClickListener(cl);
        next.setOnClickListener(cl);
        setCancelable(false);

        return v6;
    }


}
