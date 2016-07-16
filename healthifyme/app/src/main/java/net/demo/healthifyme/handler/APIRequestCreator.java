package net.demo.healthifyme.handler;

import net.demo.healthifyme.model.AvailableSlot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rajesh5kumar on 13/7/16.
 */
public interface APIRequestCreator
{
        @GET("booking/slots/all")
        Call<AvailableSlot> getAvailableSlots(
                @Query("username") String username,
                @Query("api_key") String apiKey,
                @Query("vc") String vc,
                @Query("expert_username") String expertUsername,
                @Query("format") String dataFormat
                );
}

