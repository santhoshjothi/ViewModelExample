package com.example.viewmodelwithretrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HerosViewModel extends ViewModel {

    private MutableLiveData<List<Hero>> mutableLiveData;

    public LiveData<List<Hero>> getHero() {

        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadHeroes();
        }
        return mutableLiveData;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                //finally we are setting the list to our MutableLiveData
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {


            }
        });
    }
}
