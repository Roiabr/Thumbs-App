//package com.example.thumbs_app;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.os.Bundle;
//import android.widget.ListView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyPrivateDrivers extends AppCompatActivity {
//    Toolbar toolbar;
//    ListView listView;
//    List<Tremp> list;
//    DatabaseReference databaseList;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_private_drivers);
//    //    toolbar = (Toolbar) findViewById(R.id.toolbar);
//        list = new ArrayList<>();
//
//        listView = (ListView) findViewById(R.id.List_private_Driver);
//
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        databaseList = FirebaseDatabase.getInstance().getReference("Users").child(currentFirebaseUser.getUid()).child("Drives");
//
//        //      toolbar.inflateMenu(R.menu.main_menu);
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        databaseList.addValueEventListener(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//                for (DataSnapshot trempData : dataSnapshot.getChildren()){
//                    Tremp tremp = trempData.getValue(Tremp.class);
//                    list.add(tremp);
//                }
//                final DrivesList adpter = new DrivesList(MyPrivateDrivers.this,list);
//                listView.setAdapter(adpter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//
//
//}
