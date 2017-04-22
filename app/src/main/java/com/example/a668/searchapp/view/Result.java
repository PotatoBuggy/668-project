package com.example.a668.searchapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;

/**
 * Created by spenc on 4/16/2017.
 */

public class Result extends Fragment {

    ViewGroup rootView;
    String searchData;
    RecyclerView  recycler_view;
    LinearLayoutManager recylerManager;
    RVAdapter rvadapter;
    private ArrayList<Data> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView= (ViewGroup)inflater.inflate(R.layout.activity_result,container,false);

        recycler_view= (RecyclerView) rootView.findViewById(R.id.recycleview_items_id);
        recylerManager = new LinearLayoutManager(getActivity());

        //Set Up Recycler View
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(recylerManager);

        //Receive Search Data
        Bundle bundle_box= getArguments();
        searchData= bundle_box.getString("SearchData");

        //Add data to Recycler View
        initializeData();

        //Enable Search Field
        setHasOptionsMenu(true);

        //Set up Recycler View
        initializeAdapter();

        return rootView;
    }


    //Add Data to dataList object from the Data class.
    private void initializeData(){
        dataList = new ArrayList<>();
        dataList.add(new Data("Google", "Google is an American multinational technology company specializing in Internet-related services and products. These include online advertising technologies, search, cloud computing, software, and hardware. "));
        dataList.add(new Data("SFSU", "San Francisco State University (commonly referred to as San Francisco State, SF State and SFSU) is a public research university located in San Francisco, California, United States. "));
        dataList.add(new Data("Yahoo", "Yahoo! Inc.[5] is an American multinational technology company headquartered in Sunnyvale, California. Yahoo was founded by Jerry Yang and David Filo in January 1994 and was incorporated on March 2, 1995"));
        dataList.add(new Data("Youtube", "YouTube is an American video-sharing website headquartered in San Bruno, California. The service was created by three former PayPal employees—Chad Hurley, Steve Chen, and Jawed Karim—in February 2005. "));
        dataList.add(new Data("NBA", "The National Basketball Association (NBA) is the major men's professional basketball league in North America, and is widely considered to be the premier men's professional basketball league in the world."));
        dataList.add(new Data("ilearn", "iLearn is a combination of several learning and teaching technologies that form a facility allowing academics and students to interact as part of a collaborative, flexible learning and teaching experience. "));
    }

    private void initializeAdapter(){
        rvadapter = new RVAdapter(dataList);
        recycler_view.setAdapter(rvadapter);
    }


    //Create Search Field Through Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Join Custom Layout
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem items= menu.findItem(R.id.menu_search_id);

        //Create Search
        SearchView search = (SearchView)items.getActionView();

        //Add Search Function
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String Query){
               return false;
            }

            //Filter Text to Search
            @Override
            public boolean onQueryTextChange(String newText){

                newText = newText.toLowerCase();
                ArrayList<Data> newList = new ArrayList<Data>();

                //Look for a search key work in dataList object
                for (Data data : dataList){
                   String title = data.getTitle().toLowerCase();
                    if(title.contains(newText)){
                        newList.add(data);
                    }
                }

                //Show result in Recycler View
                rvadapter.setFilter(newList);
                return true;
            }
        });

        //Receive Search Data from Previous Fragment
        search.setQuery(searchData,false);

        super .onCreateOptionsMenu(menu, inflater);
    }
}
