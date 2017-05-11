package com.example.a668.searchapp.view;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rain-SB on 5/4/2017.
 */

public class SearchResult {

    @SerializedName("dc_title_s")
    public String title;

    @SerializedName("id")
    public String URL;

    //TODO: searlized name for des
    public String description;

    public SearchResult(){

    }

    public SearchResult(String titleIn, String URLIn, String descriptionIn){

        this.title = titleIn;
        this.URL = URLIn;
        this.description = descriptionIn;
    }

    public String getTitle(){
        return title;
    }
    public String getURL() {
        return URL;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + title + '\'' +
                ", URL='" + URL + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //TODO: Used for Testing remove later
    public static String getFileContents(final File file) throws IOException {
        final InputStream inputStream = new FileInputStream(file);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final StringBuilder stringBuilder = new StringBuilder();

        boolean done = false;

        while (!done) {
            final String line = reader.readLine();
            done = (line == null);

            if (line != null) {
                stringBuilder.append(line);
            }
        }

        reader.close();
        inputStream.close();

        return stringBuilder.toString();
    }

    public static void main(String args[]){

        File file = new File("C:\\Users\\rain2\\StudioProjects\\668-term-project\\app\\src\\main\\java\\com\\example\\a668\\searchapp\\view\\JSONparseTest.txt");
        String response = null;
        try {
            response = getFileContents(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        SearchResult p = gson.fromJson(response, SearchResult.class);
        System.out.println(p);
        System.out.println(p.getURL());

    }
}