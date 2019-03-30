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
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(curUid);


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

        thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR)+4;
                int month = c.get(Calendar.MONTH)-2;
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mdatalis,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



            }
        });
        mdatalis = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month1, int dayOfMonth) {
                month1 = month1 + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month1 + "/" + dayOfMonth + "/" + year);

                final String date = String.valueOf(month1);
                thang.setText("Tháng : "+date);

            }
        };

        if (user != null) {


            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    int ele1 = Integer.parseInt(eleinput2.getText().toString());
                    int water1 = Integer.parseInt(waterinput2.getText().toString());
                    String m = month.getText().toString();
                    mRef.child("water").setValue(water1);

                    mRef.child("ele").setValue(ele1);

                    mRef.child(m).child("ele").setValue(ele1);

                    mRef.child(m).child("water").setValue(water1);
                    Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();

                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            int ele = dataSnapshot.child("ele").getValue(Integer.class);

                            int water = dataSnapshot.child("water").getValue(Integer.class); // now do what you want from these

                            int thisele=Integer.parseInt(eleinput2.getText().toString());

                            int thiswater= Integer.parseInt(waterinput2.getText().toString());

                            int finalele=thisele - ele;

                            int finalwater=thiswater - water;

                            int sumele = finalele * 1820;
                            int sumwater = finalwater * 10000;
                            final int sum = sumele + sumwater;

                            DecimalFormat formatter = new DecimalFormat("###,###,###");
                            formatter.format(sum);

                            result.setText(String.valueOf(formatter.format(sum)+" VND"));
                            save.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {



                                    int ele1 = Integer.parseInt(eleinput2.getText().toString());
                                    int water1 = Integer.parseInt(waterinput2.getText().toString());
                                    int m = Integer.parseInt(month.getText().toString());
                                    int m1= m-1;
                                    String m2 =String.valueOf(m1);

                                    String m3 =String.valueOf(m);

                                    mRef.child("water").setValue(water1);

                                    mRef.child("ele").setValue(ele1);

                                    mRef.child(m3).child("ele").setValue(ele1);

                                    mRef.child(m3).child("water").setValue(water1);

                                    mRef.child(m2).child("money").setValue(sum);
                                    Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });




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
