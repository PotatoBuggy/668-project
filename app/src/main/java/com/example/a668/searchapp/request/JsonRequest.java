package com.example.a668.searchapp.request;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Volley request to receive JSON as response and parse it to create list of movies
 */
public class JsonRequest extends Request{

    // Success listener implemented in controller


    /**
     * Class constructor
     * @param method            Request method
     * @param url               url to API
     * @param successListener   success listener
     * @param errorListener     failure listener
     */
    public JsonRequest( int method,
                        String url,
                        Response.Listener successListener,
                        Response.ErrorListener errorListener) {
        super(method, url, errorListener);

    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);
        Log.i("response string", jsonString);

        JSONObject jsonObject;

        return Response.success("1", getCacheEntry());
    }

    @Override
    protected void deliverResponse(Object response) {

    }

}

