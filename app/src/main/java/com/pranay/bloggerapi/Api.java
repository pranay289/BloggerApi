package com.pranay.bloggerapi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Api
{
    private static final String key="YOUR_API_KEY";
    private static Postlist postlist=null;
    private static String url="YOUR_BLOGGER_SITE";



    public interface  Postlist
    {
        @GET("posts?key="+key)
        Call<Example> getpost();
    }


    public static Postlist getPostserivice() {
        if (postlist== null) {
            postlist = (Postlist) new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build().create(Postlist.class);
        }
        return postlist;
    }
}
