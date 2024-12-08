package com.example.iman_efficientrestaurantbookingmanagementmobileapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CancelBooking extends AppCompatActivity implements ImageAdapterCancelBooking.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private ImageAdapterCancelBooking mAdapter;
    private DatabaseReference mDatabaseRef, ref1;
    private FirebaseAuth firebaseAuth;
    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserBooking> mUploads;
    private ProgressBar mProgressCircle;
    TextView txt_toast;

    String selectedKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_booking);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle1);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterCancelBooking(CancelBooking.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(CancelBooking.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("all");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.exists()){
                        UserBooking userBooking= postSnapshot.getValue(UserBooking.class);
                        userBooking.setKey(postSnapshot.getKey());
                        mUploads.add(userBooking);
                    }
                    else{
                        Toast.makeText(CancelBooking.this, "There is no table reservation yet", Toast.LENGTH_SHORT).show();
                    }


                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CancelBooking.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        UserBooking selectedItem = mUploads.get(position);
        selectedKey = selectedItem.getKey();
        deleteBookingFunction();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    public void deleteBookingFunction() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                CancelBooking.this);
        // Setting Dialog Title
        alertDialog.setTitle("Cancel Reservation");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to cancel this table reservation?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.logo);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ref1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("all").child(selectedKey);
                        ref1.getRef().removeValue();
                        Toast.makeText(CancelBooking.this, "Table Reservation successfully Cancelled", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CancelBooking.this, CancelBooking.class));
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CancelBooking.this, HomePage.class));
        finish();

    }
}