package com.example.mikasaloli.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class main extends Fragment {

    EditText editText ;
    EditText eleinput2;
    EditText waterinput2;
    private TextView result;
    private TextView result1;
    private Button signout;
    TextView thang;
    EditText month;
    DatePickerDialog.OnDateSetListener mdatalis;
    Button save;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Button add;
    private FirebaseDatabase fbdata=FirebaseDatabase.getInstance();
    private DatabaseReference mRef=fbdata.getReference().child("users").child(currentFirebaseUser.getUid());
    String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(curUid).child("order");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.main,container,false);
        month=(EditText) v.findViewById(R.id.edt_month);
        eleinput2 = (EditText) v.findViewById(R.id.eleinput);
        waterinput2 = (EditText) v.findViewById(R.id.waterinput);
        add = (Button) v.findViewById(R.id.button);
        save = (Button) v.findViewById(R.id.button3);
        result= (TextView) v.findViewById(R.id.textView3);

        thang=(TextView) v.findViewById(R.id.textView5);



        if (user != null) {

            thang.setText(curUid);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String post = dataSnapshot.getValue(String.class);
                        result.setText(post);


                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }
        else
        {
            Toast.makeText(getActivity(),"not yet login",Toast.LENGTH_LONG).show();
        }


        return v;
    }

}
