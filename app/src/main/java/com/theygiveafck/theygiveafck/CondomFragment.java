package com.theygiveafck.theygiveafck;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CondomFragment extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public ArrayList<Item> itemList = new ArrayList<>();

    public Context mContext;

    public RecyclerView mRecyclerView;
    public ItemAdapter itemAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        mContext = getActivity();

        View rootView = inflater.inflate(R.layout.condom_fragment, container, false);

        mRecyclerView = rootView.findViewById(R.id.itemRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        databaseReference.child("startUp").removeValue();
        itemAdapter = new ItemAdapter(createList());




        mRecyclerView.setAdapter(itemAdapter);


        return rootView;
    }

    public ArrayList<Item> createList(){
        databaseReference.child("startUp").removeValue();

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Log.d("onDataChange", "in firebase");

                Iterable<DataSnapshot> result = dataSnapshot.getChildren();

                itemList.clear();

                for (DataSnapshot item : result){
                    Log.d("onDataChange", item.child("title").getValue().toString() + " " + item.child("desc").getValue().toString() + " " + item.child("img").getValue().toString());
                    Item itemData = new Item(
                            item.child("title").getValue().toString(),
                            item.child("desc").getValue().toString(),
                            item.child("img").getValue().toString()
                    );
                    itemList.add(itemData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("CondomFragment", "error: " + databaseError);
            }
        });

        return itemList;
    }
}