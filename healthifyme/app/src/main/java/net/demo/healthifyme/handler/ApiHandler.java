package net.demo.healthifyme.handler;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rajesh5kumar on 13/7/16.
 */
public class ApiHandler {

//    public static final String BASE_URL = "http://108.healthifyme.com/api/v1/booking/slots/all?username=alok%40x.coz&api_key=a4aeb4e27f27b5786828f6cdf00d8d2cb44fe6d7&vc=276&expert_username=neha%40healthifyme.com&format=json";

    public static final String BASE_URL = "http://108.healthifyme.com/api/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
