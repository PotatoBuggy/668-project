package com.example.a668.searchapp.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by spenc on 4/26/2017.
 */

public class HomeFrame extends Fragment {

    View rootView;
    Button crawl;
    EditText input_url;
    URL url;
    Bundle bundleBox;
    String host;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.home_frame, container, false);
        crawl= (Button) rootView.findViewById(R.id.crawl_button_id);
        input_url=(EditText) rootView.findViewById(R.id.url_edittext_id);

        //Go to Search page
        crawl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Take Input String
                String urlText= input_url.getText().toString();

                if(urlText.equals("")){
                    Toast.makeText(getActivity(), "Please Enter URL Before Proceed." , Toast.LENGTH_LONG)
                            .show();
                }else{

                    //Create Title for Search Page
                    if (urlText.substring(0,4).equals("http")){
                        try {
                            //Get Host Name
                            url = new URL(urlText);
                            host = url.getHost();

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            host= "Web Crawler";
                        }
                    }else
                        host= urlText;

                    //Send Host name to nextView
                    bundleBox = new Bundle();
                    bundleBox.putString("Title",host);

                    //Go to activity_search fragment
                    Fragment nextView= new Search();
                    nextView.setArguments(bundleBox);
                    FragmentManager fraMng= getFragmentManager();
                    FragmentTransaction fraT= fraMng.beginTransaction()
                            .replace(R.id.frame_layout_id,nextView);
                    fraT.addToBackStack(nextView.getTag());
                    fraT.commit();
                }
            }
        });

        return rootView;
    }
}
