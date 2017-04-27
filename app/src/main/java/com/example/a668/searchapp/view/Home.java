package com.example.a668.searchapp.view;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Home Layer
        Fragment homeView= new HomeFrame();

        FragmentManager fraMng= getFragmentManager();
        FragmentTransaction fraT= fraMng.beginTransaction()
                .replace(R.id.frame_layout_id,homeView);
        fraT.commit();


    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if(count==0)
            super.onBackPressed();
        else
            getFragmentManager().popBackStack();

    }
}
