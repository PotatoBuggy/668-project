package com.example.a668.searchapp.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by adisonlee on 4/18/17.
 */

public class WebsiteRedirection extends Fragment
{

    public Intent redirectedWebsite(String searchResult)
    {
        Intent browserIntent = null;

        if(searchResult != null)
        {
            if(searchResult.equals("Google"))
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            }
            else if(searchResult.equals("SFSU"))
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sfsu.edu/"));
            }
            else if(searchResult.equals("Yahoo"))
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yahoo.com"));
            }
            else if(searchResult.equals("NBA"))
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nba.com"));
            }
            else if(searchResult.equals("ilearn"))
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ilearn.sfsu.edu"));
            }
            else if(searchResult.equals("Youtube"))
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
            }
        }
        return browserIntent;
    }

}
