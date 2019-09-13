package com.example.bola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT = "google_account" ;
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private ArrayList<ModelJadwal> dataList;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.aaa);
        AndroidNetworking.
                get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray datajadwal=response.getJSONArray("events");
                            ModelJadwal jadwal;
                            for (int i = 0; i <datajadwal.length() ; i++) {
                                jadwal=new ModelJadwal();
                                JSONObject dataku=datajadwal.getJSONObject(i);
                                jadwal.setStrHomeTeam(dataku.getString("strHomeTeam"));
                                jadwal.setStrAwayTeam(dataku.getString("strAwayTeam"));
                                jadwal.setStrDate(dataku.getString("strDate"));
                                dataList.add(jadwal);
                            }
                            adapter= new ScheduleAdapter(dataList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(adapter);
                            Log.d("hasil","jumlahdata:"+dataList.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("hasil","onError:"+anError.toString());


                    }
                });


    }
}
