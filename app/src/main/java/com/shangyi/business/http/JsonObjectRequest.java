package com.shangyi.business.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class JsonObjectRequest extends JsonRequest<String> {
    public JsonObjectRequest(int method, String url, String jsonRequest, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public JsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(jsonRequest == null?0:1, url, jsonRequest == null?null:jsonRequest.toString(), listener, errorListener);
    }

    @Override
    public String getBodyContentType() {
        return String.format("application/octet-stream; charset=%s", new Object[]{"utf-8"});
    }

    @Override
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,parseCharset(response.headers));
            return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var3) {
            return Response.error(new ParseError(var3));
        }
    }

    public static String parseCharset(Map<String, String> headers) {
        String contentType = (String)headers.get("Content-Type");
        if(contentType != null) {
            String[] params = contentType.split(";");

            for(int i = 1; i < params.length; ++i) {
                String[] pair = params[i].trim().split("=");
                if(pair.length == 2 && pair[0].equals("charset")) {
                    return pair[1];
                }
            }
        }

        return "UTF-8";
    }
}
