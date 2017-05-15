package com.example.a668.searchapp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by spenc on 5/14/2017.
 */

public class ResultPage extends Fragment {
    ViewGroup rootView;
    TextView title,url,description;
    String searchData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView= (ViewGroup) inflater.inflate(R.layout.result_page, container,false);

        title= (TextView) rootView.findViewById(R.id.title_tv_id);
        url=(TextView) rootView.findViewById(R.id.url_tv_id);
        description=(TextView) rootView.findViewById(R.id.description_tv_id);

        //Receive Search Data
        Bundle bundle_box= getArguments();
        searchData= bundle_box.getString("SearchData");

        //** Json Stuff here
        
        //Setting up Text View
        title.setText("Title");
        url.setText("url");
        description.setText("Description");

        //Enable Search Field
        setHasOptionsMenu(true);

        return rootView;
    }

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
                    //controller.cancelAllRequests(context);
                    //controller.sendRequest(query, context);
                    return false;
                } else {
                    Toast.makeText(getActivity(), "Must provide more than one character", Toast.LENGTH_SHORT).show();
                    //recycler_view.setVisibility(View.GONE);
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
                    //controller.cancelAllRequests(context);
                    //controller.sendRequest(newText, context);
                } else if(newText.equals("")) {
                    //recycler_view.setVisibility(View.GONE);
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
