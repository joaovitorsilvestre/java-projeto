package com.example.joao.projetoestagio;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.joao.projetoestagio.Api.MakeRequest;
import com.example.joao.projetoestagio.Api.Models.Catalog;
import com.example.joao.projetoestagio.Api.Models.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonStart;
    private ListView listView;

    // is used to reference context in retrofit callback
    private Context CONTEXT = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        listView = (ListView) findViewById(R.id.listView);

        buttonStart.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (isConnected()) {

            MakeRequest.call().enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Catalog catalog = (Catalog) response.body();
                    Data[] catalogData = catalog.data;

                    ListAdapter customAdapter = new CustomAdapter(CONTEXT, catalogData);
                    listView.setAdapter(customAdapter);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    showAlert("Nenhum dado foi recebido do servidor.");
                }
            });

        } else {
            showAlert("Não há conexão com a internet.");
        }
    }

    private void showAlert(String message) {
        AlertDialog.Builder allertNoConnection = new AlertDialog.Builder(this);
        allertNoConnection.setMessage(message);
        allertNoConnection.setNeutralButton("OK", null);
        allertNoConnection.show();
    }

    private boolean isConnected() {
        ConnectivityManager managerConnectivity = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = managerConnectivity.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
