package com.vlive.app.com.vlive.rest;

/**
 * https://apiplug.com/blog/how-to-consume-a-rest-api-in-android-with-retrofit/
 * Created by GDEGAUQUIER on 28/11/2016.
 */

public class ApiClient {

    private static APIPlug REST_CLIENT;
    private static final String API_URL = "http://localhost/vlive-api/api/v1/";

    static {
        setupRestClient();
    }

    private ApiClient() {}

    public static APIPlug get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //Uncomment these lines below to start logging each request.

        /*
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        REST_CLIENT = retrofit.create(APIPlug.class);
    }

}
