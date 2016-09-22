package com.example.android.grupodot;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.grupodot.Model.Account;
import com.example.android.grupodot.Model.Unit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private static String URL_CON = "https://vmco-app-movil-grupodot.appspot.com/mapper/rest/accounts/accounts";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.account_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressDialog.setMessage("Getting Accounts ...");
        mProgressDialog.show();
        new JSONTask().execute(URL_CON);

    }

    private class JSONTask extends AsyncTask<String, String, List<Account>> {


        @Override
        protected List<Account> doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            OutputStream outputStream = null;
            StringBuffer stringBuffer = null;

            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.connect();

                JSONObject jsonObject =
                        new JSONObject()
                                .put("idTransaction", "123")
                                .put("canal", "MOBILE")
                                .put("subscriberId", "7300365");

                outputStream = new DataOutputStream(httpURLConnection.getOutputStream());

                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(jsonObject.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                int responseCode = httpURLConnection.getResponseCode();

                System.out.print("Response " + responseCode);

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    httpURLConnection.getInputStream()));
                    stringBuffer = new StringBuffer("");
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {

                        stringBuffer.append(line);
                        break;
                    }
                    bufferedReader.close();

                    String finalJson = stringBuffer.toString();

                    JSONObject parentObject = new JSONObject(finalJson);
                    JSONObject parentReturn = parentObject.getJSONObject("return");
                    JSONObject parentAccountDetailsType = parentReturn.getJSONObject("accountDetailsType");
                    JSONObject parentAccounts = parentAccountDetailsType.getJSONObject("accounts");
                    JSONArray parentArray = parentAccounts.getJSONArray("accountType");


                    List<Account> accountsList = new ArrayList<>();

                    for (int i = 0; i < parentArray.length(); i++) {

                        JSONObject accountObject = parentArray.getJSONObject(i);
                        JSONObject unitObject = parentArray.getJSONObject(i).getJSONObject("unit");

                        Account accountModel = new Account();

                        accountModel.setAccountId(accountObject.getInt("accountId"));
                        accountModel.setActive(accountObject.getBoolean("active"));
                        accountModel.setActiveFrom(accountObject.getInt("activeFrom"));
                        accountModel.setBalance(accountObject.getInt("balance"));
                        accountModel.setCategory(accountObject.getString("category"));
                        accountModel.setCurrencyId(accountObject.getInt("currencyId"));
                        accountModel.setExpiryDate(accountObject.getInt("expiryDate"));
                        accountModel.setInitialBalance(accountObject.getInt("initialBalance"));
                        accountModel.setName(accountObject.getString("name"));
                        accountModel.setReservedBalance(accountObject.getInt("reservedBalance"));
                        accountModel.setType(accountObject.getString("type"));

                        Unit unitModel = new Unit();

                        unitModel.setCurrencyId(unitObject.getInt("currencyId"));
                        unitModel.setId(unitObject.getInt("id"));
                        unitModel.setMantissa(unitObject.getInt("mantissa"));
                        unitModel.setName(unitObject.getString("name"));
                        unitModel.setRelation(unitObject.getInt("relation"));

                        accountModel.setUnit(unitModel);

                        accountsList.add(accountModel);

                    }

                    return accountsList;


                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Account> result) {
            super.onPostExecute(result);
            mProgressDialog.dismiss();
            if (result != null) {

                AccountAdapter adapter = new AccountAdapter(result);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_LONG).show();
            }

        }
    }

}
