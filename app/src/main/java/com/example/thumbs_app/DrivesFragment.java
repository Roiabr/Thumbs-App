package com.example.thumbs_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrivesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrivesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrivesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public  static final String DRIVEID = "driveid";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference databaseList;
    Toolbar toolbar,toolTab;
    TabLayout layout;
    ViewPager viewPager;

    ListView listView;
    SearchView searchViewfrom ,searchViewwhere;
    FloatingActionButton add;
    PageAdapter pageAdapter;
    List<Tremp> list;
    List<Tremp> listFilter;
    String SearchfilterFrom="",SearchfilerWhere="";

    private OnFragmentInteractionListener mListener;

    public DrivesFragment() {
        DrivesList.callme=0;

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrivesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrivesFragment newInstance(String param1, String param2) {
        DrivesFragment fragment = new DrivesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrivesList.callme=0;

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drives,container,false);
        databaseList = FirebaseDatabase.getInstance().getReference("Drives");

        list = new ArrayList<>();
        listFilter = new ArrayList<>();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolTab = (Toolbar) view.findViewById(R.id.toolTab);
        layout = (TabLayout) view.findViewById(R.id.TabLyaout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        searchViewfrom = (SearchView) view.findViewById(R.id.search_view_to_from);
        searchViewwhere =(SearchView) view.findViewById(R.id.search_view_to_where);
        String to = "a_unique_key"; // the notification key

        DrivesList.callme=0;

        add = (FloatingActionButton) view.findViewById(R.id.floatingAdd);

        listView = (ListView) view.findViewById(R.id.listViewTremp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tremp tremp = list.get(i);
                Intent intent = new Intent(getActivity(),getTheDriver.class);
                intent.putExtra(DRIVEID,tremp.getId());

                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),askDriver.class));
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        DrivesList.callme=0;
        databaseList.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot trempData : dataSnapshot.getChildren()){
                    Tremp tremp = trempData.getValue(Tremp.class);
                    list.add(tremp);
                }
                final DrivesList adpter = new DrivesList(getActivity(),list);
                listView.setAdapter(adpter);

                searchViewfrom.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
//                        listFilter.clear();
//                        for (Tremp trempData : list){
//
//                            if(trempData.getLocationEnd().contains(newText))
//                            {
//                                listFilter.add(trempData);
//
//                            }
//                        }
//                        final DrivesList adpter2 = new DrivesList(getActivity(),listFilter);
//                        listView.setAdapter(adpter2);
                        SearchfilterFrom=newText;
                            update();
                        return false;
                    }
                });
                searchViewwhere.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        SearchfilerWhere=newText;
                        update();
                        return false;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void update()
    {
        listFilter.clear();
        for (Tremp trempData : list){

            if(trempData.getLocationStart().contains(SearchfilterFrom) && trempData.getLocationEnd().contains(SearchfilerWhere))
            {
                listFilter.add(trempData);

            }
        }
        final DrivesList adpter2 = new DrivesList(getActivity(),listFilter);
        listView.setAdapter(adpter2);



    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
