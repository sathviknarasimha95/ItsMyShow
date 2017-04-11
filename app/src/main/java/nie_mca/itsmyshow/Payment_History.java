package nie_mca.itsmyshow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shreesha K L on 19/11/2016.
 */

public class Payment_History extends Activity
{
    static int flag=0;
    static ProgressDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_history);
        LoadAsync3 aa=new LoadAsync3();
        aa.execute("");
    }


    class LoadAsync3 extends AsyncTask<String, Void, String>
    {
        String result;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            flag=1;
            loadingDialog = ProgressDialog.show(Payment_History.this, "Please wait", "Loading...");
        }
        @Override
        protected String doInBackground(String... params)
        {
            InputStream is = null;

            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.0.106:8585/php/login.php");
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                result = sb.toString();
            }
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Log.i("RES",""+result);
            return result;
        }

        @Override
        protected void onPostExecute(String result)
        {
            String s = result.trim();








            loadingDialog.dismiss();
            flag=0;
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}

