package com.example.a668.searchapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by spenc on 4/16/2017.
 */

public class Result extends Fragment {

    ViewGroup rootView;
    ArrayAdapter<String> adapter;
    String searchData;
    ListView list_view;
    WebsiteRedirection redirectSearchResult;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        redirectSearchResult = new WebsiteRedirection();
        rootView= (ViewGroup)inflater.inflate(R.layout.activity_result,container,false);

        list_view= (ListView) rootView.findViewById(R.id.food_list_view_id);
        ArrayList<String> foodArrayList= new ArrayList<>();
        String[] listOfData;

        //Receive Search Data
        Bundle bundle_box= getArguments();
        searchData= bundle_box.getString("SearchData");

        listOfData = new String[]{"Google", "SFSU", "Youtube", "Yahoo", "NBA", "ilearn"};

        //Add Data to ArrayList
        for (String aListOfData : listOfData)
        {
            foodArrayList.add(aListOfData);
        }

        //Connect ListView and Data
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, foodArrayList);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                String itemClicked = list_view.getItemAtPosition(position).toString();
                startActivity(redirectSearchResult.redirectedWebsite(itemClicked));
            }
        });

        //Set ListView on Result Layout
        list_view.setAdapter(adapter);

        //Enable Search Field
        setHasOptionsMenu(true);

        return rootView;
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
               adapter.getFilter().filter(newText);

               return false;
            }
        });

        //Receive Search Data from Previous Fragment
        search.setQuery(searchData,false);

        super .onCreateOptionsMenu(menu, inflater);
    }



}
