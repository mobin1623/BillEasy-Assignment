package com.example.billeasy_assignment.API;




import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

;import static com.example.billeasy_assignment.Utils.Constants.BASE_URL;

/**
 * Created by Mobin on 22,June,2021
 */
public class ApiClient {
    private static Retrofit retrofit = null;


    public static Retrofit getApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
    }



}
