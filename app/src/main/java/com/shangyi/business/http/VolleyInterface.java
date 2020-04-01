package com.shangyi.business.http;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public abstract class VolleyInterface {

    private Listener<String> mListener;
    private ErrorListener mErrorListener;

    public VolleyInterface(){

    }

    public VolleyInterface(Listener<String> listener, ErrorListener errorListener) {
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public Listener<String> loadingListener() {
        mListener = new Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                try {
                    if (arg0 == null) {
                        arg0 = "";
                    }
                    VolleyInterface.this.onMySuccess(new HttpResponse(arg0));
                } catch (Exception e) {
                    e.printStackTrace();
                    VolleyInterface.this.onMySuccess(null);
                }
            }
        };
        return mListener;

    }

    public ErrorListener errorListener() {
        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                VolleyInterface.this.onMyError(arg0);
            }
        };
        return mErrorListener;
    }

    public abstract void onMySuccess(HttpResponse response);

    public abstract void onMyError(VolleyError arg0);
}
