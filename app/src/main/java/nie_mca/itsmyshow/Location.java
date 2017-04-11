package nie_mca.itsmyshow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreesha K L on 19/11/2016.
 */

public class Location extends Activity
{
    Context con;
    static ProgressDialog loadingDialog;
    static int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.LocationRadio);
        Button bt=(Button)findViewById(R.id.locationButton);
        con=this;
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selectde=rg.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton)findViewById(selectde);
                LocSetTask lt=new LocSetTask();
                lt.execute(rb.getText().toString());
            }
        });
    }
    private class LocSetTask extends AsyncTask<String, String, String>
    {
        String result = null;


        @Override
        protected String doInBackground(String... params)
        {

            // String uname = params[0];
            //String pass = params[1];
            String loc=params[0];
            InputStream is = null;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("location", loc));
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.0.106:8585/php/location.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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
        protected void onPreExecute()
        {

            flag=1;
            loadingDialog = ProgressDialog.show(Location.this, "Please wait", "Loading...");
        }

        @Override
        protected void onPostExecute(String s)
        {

            String st=s.trim();
            loadingDialog.dismiss();
            flag=0;
            if(st.equalsIgnoreCase("successful"))
            {
                Toast.makeText(con,"Loaction Set",Toast.LENGTH_LONG).show();
                finish();
            }
            else
                Toast.makeText(con,"Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
