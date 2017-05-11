package com.example.a668.searchapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.Toast;


import com.example.a668.searchapp.controller.JsonController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private ArrayList<SearchResult> dataList;
    JsonController controller;
    SearchResultIndex searchResultIndex;
    SearchResult[] parsedRes = new SearchResult[1];
    String temp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = this.getActivity();

        rootView= (ViewGroup)inflater.inflate(R.layout.activity_result,container,false);

        recycler_view= (RecyclerView) rootView.findViewById(R.id.recycleview_items_id);
        recylerManager = new LinearLayoutManager(getActivity());

        //Set Up Recycler View
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(recylerManager);

        controller = new JsonController(
                new JsonController.OnResponseListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.i("\nonFailure", "onFailure\n");
                    }
                });


        //Receive Search Data
        Bundle bundle_box= getArguments();
        searchData= bundle_box.getString("SearchData");

        if(searchData != null) {
//            Log.i("Result.java", searchData);
            controller.cancelAllRequests(context);
            controller.sendRequest(searchData, context);
//            Log.i("\nResult.java: ", "After Request");
        } else {
            Toast.makeText(getActivity(), "Must provide more than one character", Toast.LENGTH_SHORT).show();
            recycler_view.setVisibility(View.GONE);
        }

        //Add data to Recycler View
        initializeData();
//        Log.i("\nAfter init", "After initiate data\n");

        //Enable Search Field
        setHasOptionsMenu(true);

        //Set up Recycler View
        initializeAdapter();

        return rootView;
    }

    public void jsonString(String crawlerResponse)
    {
//        Log.i("\n Result.java", crawlerResponse);
        searchResultIndex = new SearchResultIndex(crawlerResponse);

//        parsedRes[0] = searchResultIndex.searchResultIndex[0];

//        Log.i("parsedRes array: ", parsedRes[0].toString());


        for(SearchResult result: searchResultIndex.searchResultIndex)
        {
            //Log.i("Parsed Result: ", result.toString() + "\n");
            Log.i("Title: ", "\n\n" + result.getTitle() + "\n\n");
            Log.i("URL: ", "\n\n" + result.getURL() + "\n\n");
            Log.i("\n-----", "----\n\n");
        }

    }

    //Add Data to dataList object from the Data class.
    public void initializeData(){
        Log.i("\n Result.java initData", " ");
        dataList = new ArrayList<>();
//        dataList.add(new SearchResult("Title", parsedRes[0].getTitle(), "des"));
//        Log.i("parsedRes", parsedRes[0].getTitle());

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

        final Context context = this.getActivity();

        //Add Search Function
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("search", query);
                if(query.length() > 1) {
                    controller.cancelAllRequests(context);
                    controller.sendRequest(query, context);
                    return false;
                } else {
                    Toast.makeText(getActivity(), "Must provide more than one character", Toast.LENGTH_SHORT).show();
                    recycler_view.setVisibility(View.GONE);
                    return true;
                }
            }

            //Filter Text to Search
            @Override
            public boolean onQueryTextChange(String newText){

//                newText = newText.toLowerCase();
//                ArrayList<Data> newList = new ArrayList<Data>();
//
//                //Look for a search key work in dataList object
//                for (Data data : dataList){
//                   String title = data.getTitle().toLowerCase();
//                    if(title.contains(newText)){
//                        newList.add(data);
//                    }
//                }
//
//                //Show result in Recycler View
//                rvadapter.setFilter(newList);

                if(newText.length() > 1) {
                    controller.cancelAllRequests(context);
                    controller.sendRequest(newText, context);
                } else if(newText.equals("")) {
                    recycler_view.setVisibility(View.GONE);
                    Log.i("nothing", "\nnothing entered\n");
                }
                return true;
            }
        });

        //Receive Search Data from Previous Fragment
        search.setQuery(searchData,false);

        super .onCreateOptionsMenu(menu, inflater);
    }
}
