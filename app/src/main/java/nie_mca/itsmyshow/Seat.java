package nie_mca.itsmyshow;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreesha K L on 21/11/2016.
 */

public class Seat extends AppCompatActivity
{
    static int flag1 = 0;
    static ProgressDialog loadingDialog;
    int fil[];
    Context t;
    String t_name,time12,m_name;
    ImageButton[] ib;
    int count=0;
    int flag[];
    int se[]=new int[6];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("mainAct2", "1");
        setContentView(R.layout.seat_select);

        Intent intent = getIntent();
        t_name= intent.getExtras().getString("t_name");
        time12 = intent.getExtras().getString("time");
        m_name= intent.getExtras().getString("m_name");
        final int fare=Integer.parseInt(intent.getExtras().getString("fare"));
        Button b=(Button)findViewById(R.id.seatButton);
        t = this;
        LoadAsync8 ob = new LoadAsync8();
        ob.execute("");
        Log.i("test66","");
        for(int i=1;i<=68;i++)
        {
            ib[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(v.isSelected())
                    {
                        v.setBackgroundColor(Color.TRANSPARENT);
                        v.setSelected(false);
                    }
                    else
                    {
                        if(count>=5)
                        {
                            Toast.makeText(Seat.this,"Onetime max booking available is 5",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            v.setBackgroundColor(Color.GREEN);
                            v.setSelected(true);
                            count++;
                        }
                    }
                }
            });
        }
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("seatButton","clicked");
                for(int kk=0;kk<6;kk++)
                {
                    se[kk]=0;
                }
                int j=0;
                for(int i=1;i<=68;i++)
                {
                    if(ib[i].isSelected())
                    {
                        se[j++]=i;
                    }
                }
                final int ff=(j*fare);
                AlertDialog.Builder ad=new AlertDialog.Builder(t);
                ad.setTitle("Continue to Booking");
                ad.setMessage("\nFare: "+ff);
                ad.setNegativeButton("Cancel", null);
                ad.setPositiveButton("Book", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                        Intent bb=new Intent("android.intent.action.DEBITPAY");
                        bb.putExtra("fare",ff);
                        bb.putExtra("s1",se[0]);
                        bb.putExtra("s2",se[1]);
                        bb.putExtra("s3",se[2]);
                        bb.putExtra("s4",se[3]);
                        bb.putExtra("s5",se[4]);
                        bb.putExtra("m_name",m_name);
                        bb.putExtra("t_name",t_name);
                        bb.putExtra("time",time12);
                        startActivity(bb);
                    }
                });
                ad.create().show();
            }
        });

    }

    class LoadAsync8 extends AsyncTask<String, String, String>
    {
        String result;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            flag1 = 1;
            loadingDialog = ProgressDialog.show(Seat.this, "Please wait", "Loading...");

            ib=new ImageButton[69];
            ib[1]=(ImageButton)findViewById(R.id.imageButton1);
            ib[2]=(ImageButton)findViewById(R.id.imageButton2);
            ib[3]=(ImageButton)findViewById(R.id.imageButton3);
            ib[4]=(ImageButton)findViewById(R.id.imageButton4);
            ib[5]=(ImageButton)findViewById(R.id.imageButton5);
            ib[6]=(ImageButton)findViewById(R.id.imageButton6);
            ib[7]=(ImageButton)findViewById(R.id.imageButton7);
            ib[8]=(ImageButton)findViewById(R.id.imageButton8);
            ib[9]=(ImageButton)findViewById(R.id.imageButton9);
            ib[10]=(ImageButton)findViewById(R.id.imageButton10);
            ib[11]=(ImageButton)findViewById(R.id.imageButton11);
            ib[12]=(ImageButton)findViewById(R.id.imageButton12);
            ib[13]=(ImageButton)findViewById(R.id.imageButton13);
            ib[14]=(ImageButton)findViewById(R.id.imageButton14);
            ib[15]=(ImageButton)findViewById(R.id.imageButton15);
            ib[16]=(ImageButton)findViewById(R.id.imageButton16);
            ib[17]=(ImageButton)findViewById(R.id.imageButton17);
            ib[18]=(ImageButton)findViewById(R.id.imageButton18);
            ib[19]=(ImageButton)findViewById(R.id.imageButton19);
            ib[20]=(ImageButton)findViewById(R.id.imageButton20);
            ib[21]=(ImageButton)findViewById(R.id.imageButton21);
            ib[22]=(ImageButton)findViewById(R.id.imageButton22);
            ib[23]=(ImageButton)findViewById(R.id.imageButton23);
            ib[24]=(ImageButton)findViewById(R.id.imageButton24);
            ib[25]=(ImageButton)findViewById(R.id.imageButton25);
            ib[26]=(ImageButton)findViewById(R.id.imageButton26);
            ib[27]=(ImageButton)findViewById(R.id.imageButton27);
            ib[28]=(ImageButton)findViewById(R.id.imageButton28);
            ib[29]=(ImageButton)findViewById(R.id.imageButton29);
            ib[30]=(ImageButton)findViewById(R.id.imageButton30);
            ib[31]=(ImageButton)findViewById(R.id.imageButton31);
            ib[32]=(ImageButton)findViewById(R.id.imageButton32);
            ib[33]=(ImageButton)findViewById(R.id.imageButton33);
            ib[34]=(ImageButton)findViewById(R.id.imageButton34);
            ib[35]=(ImageButton)findViewById(R.id.imageButton35);
            ib[36]=(ImageButton)findViewById(R.id.imageButton36);
            ib[37]=(ImageButton)findViewById(R.id.imageButton37);
            ib[38]=(ImageButton)findViewById(R.id.imageButton38);
            ib[39]=(ImageButton)findViewById(R.id.imageButton39);
            ib[40]=(ImageButton)findViewById(R.id.imageButton40);
            ib[41]=(ImageButton)findViewById(R.id.imageButton41);
            ib[42]=(ImageButton)findViewById(R.id.imageButton42);
            ib[43]=(ImageButton)findViewById(R.id.imageButton43);
            ib[44]=(ImageButton)findViewById(R.id.imageButton44);
            ib[45]=(ImageButton)findViewById(R.id.imageButton45);
            ib[46]=(ImageButton)findViewById(R.id.imageButton46);
            ib[47]=(ImageButton)findViewById(R.id.imageButton47);
            ib[48]=(ImageButton)findViewById(R.id.imageButton48);
            ib[49]=(ImageButton)findViewById(R.id.imageButton49);
            ib[50]=(ImageButton)findViewById(R.id.imageButton50);
            ib[51]=(ImageButton)findViewById(R.id.imageButton51);
            ib[52]=(ImageButton)findViewById(R.id.imageButton52);
            ib[53]=(ImageButton)findViewById(R.id.imageButton53);
            ib[54]=(ImageButton)findViewById(R.id.imageButton54);
            ib[55]=(ImageButton)findViewById(R.id.imageButton55);
            ib[56]=(ImageButton)findViewById(R.id.imageButton56);
            ib[57]=(ImageButton)findViewById(R.id.imageButton57);
            ib[58]=(ImageButton)findViewById(R.id.imageButton58);
            ib[59]=(ImageButton)findViewById(R.id.imageButton59);
            ib[60]=(ImageButton)findViewById(R.id.imageButton60);
            ib[61]=(ImageButton)findViewById(R.id.imageButton61);
            ib[62]=(ImageButton)findViewById(R.id.imageButton62);
            ib[63]=(ImageButton)findViewById(R.id.imageButton63);
            ib[64]=(ImageButton)findViewById(R.id.imageButton64);
            ib[65]=(ImageButton)findViewById(R.id.imageButton65);
            ib[66]=(ImageButton)findViewById(R.id.imageButton66);
            ib[67]=(ImageButton)findViewById(R.id.imageButton67);
            ib[68]=(ImageButton)findViewById(R.id.imageButton68);


        }

        @Override
        protected String doInBackground(String... params)
        {
            InputStream is = null;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("t_name", t_name));
            nameValuePairs.add(new BasicNameValuePair("time", time12));
            Log.i("t_name",t_name);
            Log.i("time",time12);
            try
            {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://122.172.178.242/php/seat_book.php");
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
            } catch (ClientProtocolException e)
            {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            Log.i("RES", "" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result)
        {
            String s = result.trim();
            int ii = 1;
            fil=new int[79];
            try
            {
                JSONObject jsonObj = new JSONObject(s);
                JSONArray movies = jsonObj.getJSONArray("result");
                for (int i = 0; i < movies.length(); i++)
                {
                    JSONObject jsonObject = movies.getJSONObject(i);
                    fil[ii] = Integer.parseInt(jsonObject.getString("time"));
                    Log.i(""+ii,""+fil[ii]);
                    ii=ii+1;
                }
            } catch (JSONException je)
            {
                Log.i("josn error", je.getMessage());
            }
            flag=new int[69];
            for(int i=1;i<=68;i++)
            {
                flag[i]=0;
                ib[i].setBackgroundColor(Color.TRANSPARENT);
                if(fil[i]==1)
                {
                    ib[i].setEnabled(false);
                    ib[i].setBackgroundColor(Color.parseColor("#BDBDBD"));
                }
            }
            loadingDialog.dismiss();
            flag1 = 0;
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
        if(flag1==1)
            loadingDialog.dismiss();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(flag1==1)
            loadingDialog.show();
    }
}
