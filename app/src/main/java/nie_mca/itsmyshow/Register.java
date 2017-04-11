package nie_mca.itsmyshow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity
{

    Button b;
    String username;
    String email;
    String passw,repas;
    EditText uName, Pass, eMail,rePass;
    Context con;
    static ProgressDialog pd;
    static int flag=0;

    // String err_code=null,err_msg;
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        con=this;
        b = (Button) findViewById(R.id.regButton);
        uName = (EditText) findViewById(R.id.regName);
        eMail = (EditText) findViewById(R.id.regEmail);
        Pass = (EditText) findViewById(R.id.regPass);
        rePass = (EditText) findViewById(R.id.regRepass);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                username=uName.getText().toString();
                email=eMail.getText().toString();
                passw=Pass.getText().toString();
                repas=rePass.getText().toString();
                if(username.isEmpty() || email.isEmpty() || passw.isEmpty() || repas.isEmpty())
                {
                    Toast.makeText(con,"Fill all the fields",Toast.LENGTH_LONG).show();
                }
                else
                    if(email.contains("@")==false)
                    {
                        Toast.makeText(con,"Please enter a valid email",Toast.LENGTH_LONG).show();
                    }
                else if (!repas.equals(passw))
                {
                    Toast.makeText(con,"Password doesn't match",Toast.LENGTH_LONG).show();
                    //Pass.refreshDrawableState();
                }
                else
                {
                    pd = new ProgressDialog(con);
                    pd.setMessage("Please wait");
                    pd.setIndeterminate(false);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setCancelable(true);
                    pd.setOnCancelListener(new DialogInterface.OnCancelListener()
                    {
                        @Override
                        public void onCancel(DialogInterface dialog)
                        {
                            finish();
                        }
                    });
                    RegTask runner = new RegTask();
                    runner.execute("");

                }
            }
        });
    }
    private class RegTask extends AsyncTask<String, String, String>
    {
        String result = null;
        @Override
        protected String doInBackground(String... params)
        {

           // String uname = params[0];
            //String pass = params[1];

            InputStream is = null;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("password", passw));


            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://122.172.178.242/php/register.php");
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
            pd.show();
        }

        @Override
        protected void onPostExecute(String s)
        {
            flag=0;
            String st=s.trim();
            pd.dismiss();
            if(st.equalsIgnoreCase("successful"))
            {
                Toast.makeText(con,"Registered",Toast.LENGTH_LONG).show();


                //Intent loginintent231 = new Intent("android.intent.action.LOGINN");
               // startActivity(loginintent231);
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

    @Override
    protected void onPause()
    {
        super.onPause();
        if(flag==1)
            pd.dismiss();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(flag==1)
            pd.show();
    }
}