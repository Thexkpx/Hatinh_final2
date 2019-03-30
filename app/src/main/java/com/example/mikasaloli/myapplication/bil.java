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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class bil extends Fragment {

    TextView dc1;
    TextView dc2;
    TextView dc3;
    TextView dc4;
    TextView dc5;
    TextView nc1;
    TextView nc2;
    TextView nc3;
    TextView nc4;
    TextView nc5;
    TextView tt;
    EditText cu;
    EditText moi;
    Button tim;

    String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(curUid);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v= inflater.inflate(R.layout.bil,container,false);
        dc1 = (TextView) v.findViewById(R.id.Dcscu);

        dc2 = (TextView) v.findViewById(R.id.Dcsmoi);

        dc3 = (TextView) v.findViewById(R.id.Dtthu);

        dc4 = (TextView) v.findViewById(R.id.Ddg);

        dc5 = (TextView) v.findViewById(R.id.Dtt);
        nc1 = (TextView) v.findViewById(R.id.Ncscu);

        nc2 = (TextView) v.findViewById(R.id.Ncsmoi);

        nc3 = (TextView) v.findViewById(R.id.Ntthu);

        nc4 = (TextView) v.findViewById(R.id.Ndg);

        nc5 = (TextView) v.findViewById(R.id.Ntt);
        tt = (TextView) v.findViewById(R.id.total);


        cu = (EditText) v.findViewById(R.id.editText3);
        moi = (EditText) v.findViewById(R.id.editText2);
        tim = (Button) v.findViewById(R.id.button4);
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String cu1 = cu.getText().toString();

                        String moi1 = moi.getText().toString();
                        if(dataSnapshot.child(cu1).exists())
                        {
                            if (dataSnapshot.child(moi1).exists())
                            {

                                int f1 = dataSnapshot.child(cu1).child("ele").getValue(Integer.class);

                                int f2 = dataSnapshot.child(moi1).child("ele").getValue(Integer.class);
                                int thu = f2-f1;
                                int m=thu*1820;
                                dc1.setText(String.valueOf(f1));

                                dc2.setText(String.valueOf(f2));
                                dc3.setText(String.valueOf(thu));
                                dc5.setText(String.valueOf(m));
                                int f3 = dataSnapshot.child(cu1).child("water").getValue(Integer.class);

                                int f4 = dataSnapshot.child(moi1).child("water").getValue(Integer.class);
                                int thu1 = f4-f3;
                                int m1=thu1*10000;
                                int m2=m+m1;
                                nc1.setText(String.valueOf(f3));
                                nc2.setText(String.valueOf(f4));
                                nc3.setText(String.valueOf(thu1));
                                nc5.setText(String.valueOf(m1));
                                DecimalFormat formatter = new DecimalFormat("###,###,###");

                                tt.setText(String.valueOf(formatter.format(m2)+" VND"));
                            }
                            else
                            {

                                Toast.makeText(getActivity(),"Không có dữ liệu của tháng "+moi1,Toast.LENGTH_LONG).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Không có dữ liệu của tháng "+cu1,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        return v;

    }
}
