package nie_mca.itsmyshow;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shreesha K L on 19/11/2016.
 */

public class MainActivity2 extends AppCompatActivity
{
    static int flag=0;
    static ProgressDialog loadingDialog;
    String fil[][]=new String[50][7];
    ListView lv1;
    Context t;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("mainAct2","1");
        setContentView(R.layout.main_activity2);
        lv1=(ListView) findViewById(R.id.show2list);

        t=this;
        LoadAsync6 ob=new LoadAsync6();
        ob.execute("");
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {

                String desc="Event: "+fil[position][1]+"\nHost: "+fil[position][2]+"\nPlace: "+fil[position][3]+"\nDate: "+fil[position][4]+"\nTime: "+fil[position][5]+"\nFare: "+fil[position][6]+"rs";
              //  Spinner sp.new Spinner(t)
                 //   sp.add
                new AlertDialog.Builder(t)
                        .setTitle("Book")
                        .setMessage(desc)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Book", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface arg0, int arg1)
                            {
                                Intent aa1=new Intent("android.intent.action.SEATT");
                                startActivity(aa1);
                                finish();
                            }
                        })
                        /*.setNeutralButton("Book by Points", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                SharedPreferences sharedPref = t.getSharedPreferences("itsmyshow", Context.MODE_PRIVATE);
                                int poi=Integer.parseInt(sharedPref.getString("point","No value"));
                               if(poi<1000)
                               {
                                   Toast.makeText(t,"Not enough points",Toast.LENGTH_LONG).show();
                               }
                                else
                               {

                               }
                            }
                        }) */
                        .create().show();







            }
        });

    }
    class LoadAsync6 extends AsyncTask<String, Void, String>
    {
        String result;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            flag=1;
            loadingDialog = ProgressDialog.show(MainActivity2.this, "Please wait", "Loading...");

        }
        @Override
        protected String doInBackground(String... params)
        {
            InputStream is = null;
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.0.106:8585/php/event.php");
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
            int len=0;
            int ii=0;
            try
            {
                Log.i("test","1");
                JSONObject jsonObj = new JSONObject(s);
                JSONArray movies= jsonObj.getJSONArray("result");
                len=movies.length();
                Log.i("test","2");
                for(int i=0;i<movies.length();i++)
                {
                    Log.i("test","3");
                    JSONObject jsonObject = movies.getJSONObject(i);
                    fil[ii][0]= jsonObject.getString("e_id");
                    Log.i("test","4");
                    fil[ii][1]  = jsonObject.getString("event_name");
                    Log.i("test","5");
                    fil[ii][2] = jsonObject.getString("organizer");
                    fil[ii][3]=jsonObject.getString("e_place");
                    fil[ii][4]=jsonObject.getString("e_date");
                    fil[ii][5]=jsonObject.getString("e_time");
                    fil[ii][6]=jsonObject.getString("e_fare");
                    ii++;
                }
            }
            catch(JSONException je)
            {
                Log.i("josn error",je.getMessage());
            }
            Log.i("test","10");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(t, android.R.layout.simple_list_item_1);
            Log.i("test","11");
            for (int k = 0; k < len; k++)
            {
                arrayAdapter.add(fil[k][1]);
                Log.i("test","12");
            }
            lv1.setAdapter(arrayAdapter);
            loadingDialog.dismiss();
            flag=0;
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
            loadingDialog.dismiss();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(flag==1)
            loadingDialog.show();
    }
}
