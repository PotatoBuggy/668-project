package com.example.a668.searchapp.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a668.searchapp.controller.JsonController;


/**
 * Created by spenc on 4/16/2017.
 */

public class Search extends Fragment {
    View homeFrame;
    Button enter_search;
    EditText search_bar;
    TextView title;
    String titleText;
    JsonController controller;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeFrame= inflater.inflate(R.layout.activity_search,container,false);

        enter_search=(Button) homeFrame.findViewById(R.id.enter_search_btn_id);
        search_bar= (EditText) homeFrame.findViewById(R.id.search_text_id);
        title=(TextView) homeFrame.findViewById(R.id.titile_textview_id);

        //Receive Argument to Set Title
        Bundle bundle_box= getArguments();
        titleText= bundle_box.getString("Title");
        title.setText(titleText);

        final Context context = this.getActivity();

        controller = new JsonController(
                new JsonController.OnResponseListener() {
                    @Override
                    public void onSuccess() {
                        Log.i("\nonSuccess", "Success");
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.i("\nonFailure", "onFailure\n");
                    }
                });

        //Launch Search
        enter_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass Search Data
                String searchData= search_bar.getText().toString();

                if(searchData.length() > 1) {
                    Log.i("Search.java", searchData);
                    controller.cancelAllRequests(context);
                    controller.sendRequest(searchData, context);
                    Log.i("\nSearch.java: ", "After Request");

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

                } else {
                    Toast.makeText(getActivity(), "Must provide more than one character", Toast.LENGTH_SHORT).show();
                    search_bar.setVisibility(View.GONE);
                }


//                Bundle bundleBox = new Bundle();
//                bundleBox.putString("SearchData",searchData);
//
//                //Go to Result Fragment
//                Fragment nextView= new Result();
//                nextView.setArguments(bundleBox);
//
//                FragmentManager fraMng= getFragmentManager();
//                FragmentTransaction fraT= fraMng.beginTransaction()
//                        .replace(R.id.frame_layout_id,nextView);
//                fraT.addToBackStack(nextView.getTag());
//                fraT.commit();
            }
        });

        return homeFrame;
    }
}
