package com.example.mikasaloli.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class history extends Fragment {

    String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(curUid);

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v= inflater.inflate(R.layout.history,container,false);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<PieEntry> pieEntries=new ArrayList<>();
                if(dataSnapshot.child("1").child("money").exists())
                {
                    int f1 = dataSnapshot.child("1").child("money").getValue(Integer.class);
                    String m1="Thang 1";
                    pieEntries.add(new PieEntry(f1,m1));
                }

                if(dataSnapshot.child("2").child("money").exists())
                {
                    int f1 = dataSnapshot.child("2").child("money").getValue(Integer.class);
                    String m1="Thang 2";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("3").child("money").exists())
                {
                    int f1 = dataSnapshot.child("3").child("money").getValue(Integer.class);
                    String m1="Thang 3";
                    pieEntries.add(new PieEntry(f1,m1));
                }


                if(dataSnapshot.child("4").child("money").exists())
                {

                     int f4 = dataSnapshot.child("4").child("money").getValue(Integer.class);

                      String m4="Thang 4";
                    pieEntries.add(new PieEntry(f4,m4));
                    }

                if(dataSnapshot.child("5").child("money").exists())
                {
                    int f1 = dataSnapshot.child("5").child("money").getValue(Integer.class);
                    String m1="Thang 5";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("6").child("money").exists())
                {
                    int f1 = dataSnapshot.child("6").child("money").getValue(Integer.class);
                    String m1="Thang 6";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("7").child("money").exists())
                {
                    int f1 = dataSnapshot.child("7").child("money").getValue(Integer.class);
                    String m1="Thang 7";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("8").child("money").exists())
                {
                    int f1 = dataSnapshot.child("8").child("money").getValue(Integer.class);
                    String m1="Thang 8";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("9").child("money").exists())
                {
                    int f1 = dataSnapshot.child("9").child("money").getValue(Integer.class);
                    String m1="Thang 9";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("10").child("money").exists())
                {
                    int f1 = dataSnapshot.child("10").child("money").getValue(Integer.class);
                    String m1="Thang 10";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("11").child("money").exists())
                {
                    int f1 = dataSnapshot.child("11").child("money").getValue(Integer.class);
                    String m1="Thang 11";
                    pieEntries.add(new PieEntry(f1,m1));
                }
                if(dataSnapshot.child("12").child("money").exists())
                {
                    int f1 = dataSnapshot.child("12").child("money").getValue(Integer.class);
                    String m1="Thang 12";
                    pieEntries.add(new PieEntry(f1,m1));
                }


                PieDataSet dataSet = new PieDataSet(pieEntries,"Money");
                dataSet.setColors(new int[] {  Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
                        Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)});

                PieData data = new PieData(dataSet);
                PieChart chart=(PieChart) v.findViewById(R.id.chart);
                chart.setData(data);
                chart.invalidate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

}
