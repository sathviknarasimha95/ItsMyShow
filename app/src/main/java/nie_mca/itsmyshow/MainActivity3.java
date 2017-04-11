package nie_mca.itsmyshow;

import android.app.Activity;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
 * Created by Shreesha K L on 20/11/2016.
 */

public class MainActivity3 extends AppCompatActivity
{
    static int flag = 0;
    static ProgressDialog loadingDialog;
    String fil[][] = new String[50][7];
    ListView lv3;
    Context t;
    String sel_m_name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("mainAct2", "1");
        setContentView(R.layout.main_activity3);
        lv3 = (ListView) findViewById(R.id.show3list);

        t = this;
        Intent intent = getIntent();
        sel_m_name = intent.getExtras().getString("name");
        LoadAsync7 ob = new LoadAsync7();
        ob.execute("");
        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                         String[] s=new String[3];
                        for(int i=1,j=0;i<4;i++,j++)
                            s[j]=fil[position][i];

                        final ArrayAdapter<String> adp = new ArrayAdapter<String>(t,
                        android.R.layout.simple_spinner_item, s);
                         String desc = "Movie: " + sel_m_name + "\nTheatre: " + fil[position][0] +"\nFare: " + fil[position][4] + "rs"+"\nTime";

                        final Spinner sp = new Spinner(t);
                        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        sp.setAdapter(adp);

                        AlertDialog.Builder ad=new AlertDialog.Builder(t);
                        ad.setTitle("Continue to Booking");
                        ad.setMessage(desc);
                        ad.setView(sp);
                        ad.setNegativeButton("Cancel", null);
                        ad.setPositiveButton("Book", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface arg0, int arg1)
                            {

                                    Intent aa=new Intent("android.intent.action.SEATT");
                                    aa.putExtra("t_name",fil[position][0]);
                                    aa.putExtra("time",sp.getSelectedItem().toString());
                                    aa.putExtra("fare",fil[position][4]);
                                    aa.putExtra("m_name",sel_m_name);
                                    startActivity(aa);
                                    finish();

                            }
                        });
                       /* ad.setNeutralButton("Book by points", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
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
                        }); */
                        ad.create().show();
            }
        });

    }

    class LoadAsync7 extends AsyncTask<String, Void, String>
    {
        String result;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            flag = 1;
            loadingDialog = ProgressDialog.show(MainActivity3.this, "Please wait", "Loading...");

        }

        @Override
        protected String doInBackground(String... params)
        {
            InputStream is = null;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("m_name", sel_m_name));
            try
            {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://122.172.178.242/php/theatre_fetch.php");
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
            int len = 0;
            int ii = 0;
            try
            {
                Log.i("test", "1");
                JSONObject jsonObj = new JSONObject(s);
                JSONArray movies = jsonObj.getJSONArray("result");
                len = movies.length();
                Log.i("test", "2");
                for (int i = 0; i < movies.length(); i++)
                {
                    Log.i("test", "3");
                    JSONObject jsonObject = movies.getJSONObject(i);
                    fil[ii][0] = jsonObject.getString("t_name");
                    Log.i("test", "4");
                    fil[ii][1] = jsonObject.getString("time_1");
                    Log.i("test", "5");
                    fil[ii][2] = jsonObject.getString("time_2");
                    fil[ii][3] = jsonObject.getString("time_3");
                    fil[ii][4] = jsonObject.getString("m_fare");
                    // fil[ii][5]=jsonObject.getString("e_time");
                    //  fil[ii][6]=jsonObject.getString("e_fare");
                    ii++;
                }
            } catch (JSONException je)
            {
                Log.i("josn error", je.getMessage());
            }
            Log.i("test", "10");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(t, android.R.layout.simple_list_item_1)
            {

                @Override
                public View getView(int position, View convertView, ViewGroup parent){

                    View view = super.getView(position, convertView, parent);


                    TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);

                    ListItemShow.setTextColor(Color.parseColor("#FFFFFF"));

                    return view;
                }

            };
            Log.i("test", "11");
            for (int k = 0; k < len; k++)
            {
                arrayAdapter.add(fil[k][0]);
                Log.i("test", "12");
            }
            lv3.setAdapter(arrayAdapter);
            loadingDialog.dismiss();
            flag = 0;
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
