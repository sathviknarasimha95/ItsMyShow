package nie_mca.itsmyshow;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
 * Created by Shreesha K L on 20/11/2016.
 */

public class DebitPay extends Activity
{

    static Dialog loadingDialog;
    static int flag3=0;
    String mm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debit_card);
        final EditText cn1,cn2,cn3,cn4,dem,dey,cv;
        Button dp;
        cn1 =(EditText)findViewById(R.id.debitNum1);
        cn2 =(EditText)findViewById(R.id.debitnum2);
        cn3 =(EditText)findViewById(R.id.debitnum3);
        cn4 =(EditText)findViewById(R.id.debitNum4);
        dem =(EditText)findViewById(R.id.debitEndMonth);
        dey =(EditText)findViewById(R.id.debitEndYear);
        cv =(EditText)findViewById(R.id.debitCSV);
        dp=(Button)findViewById(R.id.debitPay);
        Intent intent = getIntent();
        final String m_name= intent.getExtras().getString("m_name");
        final String time=intent.getExtras().getString("time");
        final String fare=(intent.getExtras().getInt("fare"))+"";
        final String t_name=intent.getExtras().getString("t_name");
        final int s1=intent.getExtras().getInt("s1");
        final int s2=intent.getExtras().getInt("s2");
        final int s3=intent.getExtras().getInt("s3");
        final int s4=intent.getExtras().getInt("s4");
        final int s5=intent.getExtras().getInt("s5");
        Log.i("s",s1+" "+s2+" "+s3+" "+s4+" "+s5);

        dp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String card= cn1.getText().toString()+cn2.getText().toString()+cn3.getText().toString()+cn4.getText().toString();
                Log.i("Card num",card);
                if (cn1.getText().toString().isEmpty() || cn2.getText().toString().isEmpty() || cn3.getText().toString().isEmpty() ||
                        cn4.getText().toString().isEmpty() || dem.getText().toString().isEmpty() || dey.getText().toString().isEmpty()  ||
                        cv.getText().toString().isEmpty())
                {
                    Toast.makeText(DebitPay.this,"Please fill all details",Toast.LENGTH_LONG).show();
                }
                else
                if(card.length()!=16)
                {
                    Toast.makeText(DebitPay.this,"Please enter 16 digit card number",Toast.LENGTH_LONG).show();
                }
                else if(Integer.parseInt(dem.getText().toString())==0 || Integer.parseInt(dem.getText().toString())>12)
                {
                    Toast.makeText(DebitPay.this,"Please enter valid month",Toast.LENGTH_LONG).show();
                }
                else if(cv.getText().toString().length()!=3)
                {
                    Toast.makeText(DebitPay.this,"Please enter valid CVV",Toast.LENGTH_LONG).show();
                }
                else if((Integer.parseInt(dey.getText().toString())>2046) || (Integer.parseInt(dey.getText().toString())<2016))
                {
                    Toast.makeText(DebitPay.this,"Please enter valid year",Toast.LENGTH_LONG).show();
                }

                else
                {

                    SendTsk ob=new SendTsk();
                    ob.execute(m_name,time,fare,s1+"",s2+"",s3+"",s4+"",s5+"",card,t_name);
                }
            }
        });





    }
    private class SendTsk extends AsyncTask<String, String, String>
    {
        String result = null;
        String mname,time,t_name;
        @Override
        protected String doInBackground(String... params)
        {

            // String uname = params[0];
            //String pass = params[1];

            SharedPreferences sharedPref = DebitPay.this.getSharedPreferences("itsmyshow", Context.MODE_PRIVATE);
            String mailsp=sharedPref.getString("email","No value");
            mm=mailsp;
            InputStream is = null;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


            nameValuePairs.add(new BasicNameValuePair("booking_email", mailsp));
            nameValuePairs.add(new BasicNameValuePair("booking_card", params[8]));
            nameValuePairs.add(new BasicNameValuePair("booking_movie", params[0]));
            nameValuePairs.add(new BasicNameValuePair("booking_fare", params[2]));
            nameValuePairs.add(new BasicNameValuePair("s1", params[3]+""));
            nameValuePairs.add(new BasicNameValuePair("s2", params[4]+""));
            nameValuePairs.add(new BasicNameValuePair("s3", params[5]+""));
            nameValuePairs.add(new BasicNameValuePair("s4", params[6]+""));
            nameValuePairs.add(new BasicNameValuePair("s5", params[7]+""));
            nameValuePairs.add(new BasicNameValuePair("t_name", params[9]));
            nameValuePairs.add(new BasicNameValuePair("time", params[1]));
            Log.i("s1", params[3]);
            Log.i("s2", params[4]);
            Log.i("s3", params[5]);
            Log.i("s4", params[6]);
            Log.i("s5", params[7]);

            Log.i("booking_email", mailsp);
            Log.i("booking_card", params[8]);
             mname= params[0];
            time=params[1];
            Log.i("bookinh_fare", params[2]);
            t_name= params[9];
            Log.i("time", params[1]);













            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://122.172.178.242/php/booking_fetch.php");
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
            loadingDialog= ProgressDialog.show(DebitPay.this, "Please wait", "Loading...");
            flag3=1;

        }

        @Override
        protected void onPostExecute(String s)
        {
            flag3=0;
            String st=s.trim();
            loadingDialog.dismiss();
            if(st.equalsIgnoreCase("successful"))
            {
                Toast.makeText(DebitPay.this,"Booking successful",Toast.LENGTH_LONG).show();
                setContentView(R.layout.successful);
                TextView n=(TextView)findViewById(R.id.mm_name);
                TextView t2=(TextView)findViewById(R.id.th);
                TextView tim=(TextView)findViewById(R.id.time);
                n.setText("Movie Name: "+mname);
                t2.setText("Theatre: "+t_name);
                tim.setText("Time: "+time);
                //Intent loginintent231 = new Intent("android.intent.action.MAINACT");
                //startActivity(loginintent231);
                //finish();
                Button b=(Button)findViewById(R.id.buttonFinal);
                b.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent loginintent231 = new Intent("android.intent.action.MAINACT");
                        if(mm.equalsIgnoreCase("No value"))
                        {
                            loginintent231.putExtra("loginflag", "failed");
                        }
                        else
                        {
                            loginintent231.putExtra("loginflag","success");
                        }
                            startActivity(loginintent231);
                        finish();
                    }
                });
            }
            else
                Toast.makeText(DebitPay.this,"Booking failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed()
    {
       // super.onBackPressed();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        if(flag3==1)
            loadingDialog.dismiss();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(flag3==1)
            loadingDialog.show();
    }
}
