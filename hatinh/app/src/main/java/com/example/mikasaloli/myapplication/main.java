package com.example.mikasaloli.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class main extends Fragment {

    EditText eleinput2;
    EditText waterinput2;
    private TextView result;
    private TextView result1;
    private Button signout;
    Button save;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Button add;
    private FirebaseDatabase fbdata=FirebaseDatabase.getInstance();
    private DatabaseReference mRef=fbdata.getReference().child("users").child(currentFirebaseUser.getUid());
    String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(curUid);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.main,container,false);

return v;
    }
}
