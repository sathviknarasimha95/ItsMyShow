package nie_mca.itsmyshow;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

public class Events extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    static int flag=0;
    ListView lv2;
    String fil[][]=new String[50][6];
    Context t;
    static Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        t=this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LoadAsync2 asynct=new LoadAsync2();
        asynct.execute("");
        lv2=(ListView)findViewById(R.id.event_listView);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                String desc="Event: "+fil[position][1]+"\nHost: "+fil[position][2]+"\nPlace: "+fil[position][3]+"\nDate: "+fil[position][4]+"\nTime: "+fil[position][5];
                new AlertDialog.Builder(t)
                        .setTitle("Book")
                        .setMessage(desc)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Book", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface arg0, int arg1)
                            {

                            }
                        })
                        .create().show();

            }
        });
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId(); /*
        if (id == R.id.side_shows)
        {
            Intent intent331= new Intent("android.intent.action.MAINACT");
            startActivity(intent331);
            finish();
            // Handle the camera action
        }
        else if (id == R.id.side_events)
        {
            Intent intent3311= new Intent("android.intent.action.EVENTT");
            startActivity(intent3311);
            finish();
        }
        else if (id == R.id.side_history)
        {
            Intent payintent=new Intent("android.intent.action.PAYMENT_HISTORY");
            startActivity(payintent);
        }
        else if (id == R.id.side_location)
        {


        } */
        if (id == R.id.side_points)
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(t);
            ad.setTitle("Points");
            final TextView tt=new TextView(Events.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            ad.setView(tt);
            tt.setText("Bullshit not iimplemented");
            ad.setNegativeButton("Cancel", null);
            ad.create().show();

        }
        else if (id == R.id.side_logout)
        {
            SharedPreferences sharedPref = t.getSharedPreferences("itsmyshow", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", "No value");
            editor.putString("password", "No value");
            editor.commit();
            Intent tem1=new Intent("android.intent.action.LOGINN");
            startActivity(tem1);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class LoadAsync2 extends AsyncTask<String, Void, String>
    {
        String result;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            flag=1;
            loadingDialog = ProgressDialog.show(Events.this, "Please wait", "Loading...");
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
            lv2.setAdapter(arrayAdapter);







            loadingDialog.dismiss();
            flag=0;
        }
    }
}