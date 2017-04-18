package com.example.a668.searchapp.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;


/**
 * Created by spenc on 4/16/2017.
 */

public class HomeFrame extends Fragment {
    View homeFrame;
    Button enter_search;
    EditText search_bar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeFrame= inflater.inflate(R.layout.home_frame_layout,container,false);

        enter_search=(Button) homeFrame.findViewById(R.id.enter_search_btn_id);
        search_bar= (EditText) homeFrame.findViewById(R.id.search_text_id);

        //Launch Search
        enter_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass Search Data
                String searchData= search_bar.getText().toString();

                Bundle bundleBox = new Bundle();
                bundleBox.putString("SearchData",searchData);

                //Go to Result Fragment
                Fragment nextView= new Result();
                nextView.setArguments(bundleBox);

                FragmentManager fraMng= getFragmentManager();
                FragmentTransaction fraT= fraMng.beginTransaction()
                        .replace(R.id.frame_layout_id,nextView);
                fraT.addToBackStack(nextView.getTag());
                fraT.commit();
            }
        });

        return homeFrame;
    }
}
