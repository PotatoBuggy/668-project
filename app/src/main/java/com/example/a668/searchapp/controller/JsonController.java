package com.example.a668.searchapp.controller;


import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.a668.searchapp.request.JsonRequest;
import com.example.a668.searchapp.volley.VolleySingleton;


/**
 * <p> Provides interface between {@link android.app.Activity} and {@link com.android.volley.toolbox.Volley} </p>
 */
public class JsonController {

    private final int TAG = 100;

    private OnResponseListener responseListener;


    /**
     *
     * @param responseListener  {@link OnResponseListener}
     */
    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    /**
     * Adds request to volley request queue
     * @param query query term for search
     */
    public void sendRequest(String query, Context context){

        // Request Method
        int method = Request.Method.GET;


        String test = "http://10.0.2.2:8983/solr/SFSU_test/select?indent=on&q=" + Uri.encode(query) + "&wt=json&rows=10&fl=dc_title_s,id&omitHeader=true";
        String url = test;


        // Create new request using JsonRequest
        JsonRequest request
                = new JsonRequest(
                method,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void cancelAllRequests(Context context) {
        VolleySingleton.getInstance(context).cancelAllRequests(TAG);
    }

    public interface OnResponseListener {
        void onSuccess();
        void onFailure(String errorMessage);
    }

}