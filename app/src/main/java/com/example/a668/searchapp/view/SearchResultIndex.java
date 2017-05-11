package com.example.a668.searchapp.view;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.example.a668.searchapp.request.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by rain-SB on 5/4/2017.
 */

public class SearchResultIndex  {
    SearchResult[] searchResultIndex;
    int numFound;
    Result results;

//    public SearchResultIndex(NetworkResponse crawlerResponse) {
//        String jsonString = new String(crawlerResponse.data);
//        parseRes(jsonString);
//        //parseResponse(crawlerResponse);
////        if (searchResultIndex.length != numFound) {
////            System.out.println("Error number of Results Found: " + numFound);
////            System.out.println("Results Returned: " + searchResultIndex.length);
////        }
//    }

    public SearchResultIndex(String crawlerResponse)
    {
        Log.i("\nCrawler Response: ", crawlerResponse);
        parseRes(crawlerResponse);
        Log.i("\nParsing done", "parsed");
    }

    //Parses to get string INSIDE OF  [...]
    public void parseRes(String crawlRes){

        String[] parts = crawlRes.split("\\[",2 );
//        System.out.println("parts LENGTH" + parts.length);
//        System.out.println(parts[0]);
//        System.out.println(parts[1]);

        String[] parts2 = parts[1].split("\\]",2);
        //System.out.println("Parts 2" + parts2[0]);

        String results = "[" + parts2[0] + "]";
        parseResponse(results);

    }

    //Parses into SearchResult Objects
    public void parseResponse (String response){

        Gson gson = new GsonBuilder().create();
        searchResultIndex = gson.fromJson(response, SearchResult[].class);
        //System.out.print(searchResultIndex[0].getTitle());
        //System.out.print(searchResultIndex);
//        Log.i("\nparseResponse[0]: ", searchResultIndex[0].getTitle());
        for(SearchResult result: searchResultIndex)
        {
            Log.i("\nparseResponse", result.toString());
        }

    }


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

    public static void main(String args[]) {
        //File file = new File("C:\\Users\\rain2\\StudioProjects\\668-term-project\\app\\src\\main\\java\\com\\example\\a668\\searchapp\\view\\JSONparseTest2.txt");
        File file = new File("/Users/adisonlee/Documents/668/668-term-project/app/src/main/java/com/example/a668/searchapp/view/JSONparseTest2.txt");
        String response = null;
        try {
            response = getFileContents(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(response);
        //SearchResultIndex test = new SearchResultIndex(response);
        //System.out.println(test.searchResultIndex[1].getURL());
    }

}
