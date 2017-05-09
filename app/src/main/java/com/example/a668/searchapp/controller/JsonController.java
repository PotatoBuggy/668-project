package com.example.a668.searchapp.controller;


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
    public void sendRequest(String query){

        // Request Method
        int method = Request.Method.GET;


        String test = "http://10.0.2.2:8983/solr/SFSU_test/select?indent=on&q=" + Uri.encode(query) + "&wt=json&rows=10&fl=dc_title_s,id";
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
        //VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    /**
     * <p>Cancels all request pending in request queue,</p>
     * <p> There is no way to control the request already processed</p>
     */
    public void cancelAllRequests() {
        //VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

//    /**
//     *  Interface to communicate between {@link android.app.Activity} and {@link JsonRequest}
//     *  <p>Object available in {@link JsonRequest} and implemented in {@link com.example.volley_template.MainActivity}</p>
//     */
    public interface OnResponseListener {
        void onSuccess();
        void onFailure(String errorMessage);
    }

}