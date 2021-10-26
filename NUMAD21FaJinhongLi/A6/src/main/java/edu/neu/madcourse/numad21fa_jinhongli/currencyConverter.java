package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class currencyConverter extends AppCompatActivity {

    private static final String TAG = "CurrencyConvertioneActivity";
    public static final String ACCESS_KEY = "bb38cab25bb0fd0d1404427375ff3f51";
    public static final String BASE_URL = "http://api.currencylayer.com/";
    public static final String ENDPOINT = "live";
    private TextView currencyView;
    private CheckBox CNY;
    private CheckBox EUR;
    private CheckBox GBP;
    String queryResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convert);
        currencyView = findViewById(R.id.currencyView);
        CNY = findViewById(R.id.checkCNY);
        EUR = findViewById(R.id.checkEUR);
        GBP = findViewById(R.id.checkGBP);

    }

    public void sendRequest(View v) throws Exception {
        Request task = new Request();
        String gbp = "";
        String cny = "";
        String eur = "";

        if (GBP.isChecked()){
            gbp = "GBP,";
        };
        if (CNY.isChecked()){
            cny = "CNY,";
        };
        if (EUR.isChecked()){
            eur = "EUR";
        };
        String url = (BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies=" +
                cny + gbp + eur + "&format=1");
        task.execute(url);
    }

    private class Request  extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Making progress...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            JSONObject jObject = new JSONObject();
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                InputStream inputStream = conn.getInputStream();
                String resp = convertStreamToString(inputStream);
                // JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
                // Transform String into JSONObject
                jObject = new JSONObject(resp);

                Log.i("log",jObject.getString("quotes"));
                //Log.i("jBody",jObject.getString("body"));
                return jObject;

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
            }
            return jObject;
        }

        @Override
        protected void onPostExecute(JSONObject jObject) {
            //String queryResult = "";
            super.onPostExecute(jObject);
            try {
                queryResult= jObject.getString("quotes").substring(1, jObject.getString("quotes").length() -1);
                currencyView.setText(queryResult);
                //currencyView.setText(jObject.getString("quotes"));
            } catch (JSONException e) {
                currencyView.setText(queryResult);
            }

        }
    }

    public static String convertStreamToString(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String len;
            while((len=bufferedReader.readLine())!=null){
                stringBuilder.append(len);
            }
            bufferedReader.close();
            return stringBuilder.toString().replace(",", ",\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}