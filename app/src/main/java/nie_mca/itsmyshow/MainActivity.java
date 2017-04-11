package nie_mca.itsmyshow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    static int flag=0;
    ListView lv;
    String[][] fil;
    Context t;
    String logflag;
    static Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent in=getIntent();
        logflag= in.getExtras().getString("loginflag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t=this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FloatingActionButton fl = (FloatingActionButton) findViewById(R.id.floatingLogin);
       FloatingActionButton fs = (FloatingActionButton) findViewById(R.id.floatingSign);
        if(logflag.equalsIgnoreCase("failed"))
        {

            fl.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent tem = new Intent("android.intent.action.LOGINN");
                    startActivity(tem);
                }
            });
            fs.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent regintent = new Intent("android.intent.action.REGISTERRR");
                    startActivity(regintent);
                }
            });
        }
        else
        {
            fl.hide();
            fs.hide();
        }


        LoadAsync asynct=new LoadAsync();
        asynct.execute("");
        lv=(ListView)findViewById(R.id.main_listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                Intent iik=new Intent("android.intent.action.MAINACT3");
                iik.putExtra("name",fil[position][1]);
                startActivity(iik);
            }
        });
    }




  /*  @Override
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
    } */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        /*
        <item
            android:id="@+id/side_events"
            android:icon="@drawable/ic_menu_send"
            android:title="Events"/>
        <item
            android:id="@+id/side_location"
            android:icon="@drawable/ic_menu_send"
            android:title="Location"/>
         */

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        /*if (id == R.id.side_shows)
        {
            Intent intent331= new Intent("android.intent.action.MAINACT");
            startActivity(intent331);
            finish();
            // Handle the camera action
        } */
       if (id == R.id.side_events)
        {
            Log.i("mainAct","1");
            Intent intent3311= new Intent("android.intent.action.MAINACT2");
            Log.i("mainAct","2");
            startActivity(intent3311);
            Log.i("mainAct","3");
            finish();
        }
        /*
        else if (id == R.id.side_location)
        {


        } */
        else if (id == R.id.side_points)
        {
            SharedPreferences sharedPref = this.getSharedPreferences("itsmyshow", Context.MODE_PRIVATE);
            String poi=sharedPref.getString("point","No value");
            AlertDialog.Builder ad=new AlertDialog.Builder(t);
            ad.setTitle("Points");
            final TextView tt=new TextView(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            ad.setView(tt);
            tt.setText("    Available: "+poi);
            ad.setNegativeButton("Ok", null);
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
    class LoadAsync extends AsyncTask<String, Void, String>
    {
        String result;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            flag=1;
            loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");

        }
        @Override
        protected String doInBackground(String... params)
        {
            InputStream is = null;

            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://122.172.178.242/php/moviefetch.php");
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
            fil=new String[50][4];

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
                    String id = jsonObject.getString("id");
                    Log.i("test","4");
                    String name = jsonObject.getString("m_name");
                    Log.i("test","5");
                    String salary = jsonObject.getString("m_lang");
                    Log.i("test","6");
                    fil[ii][0] = id;
                    Log.i("test","7");
                    fil[ii][1] = name;
                    Log.i("test","8");
                    fil[ii][2] = salary;
                    Log.i("test","9");
                    ii++;
                }
            }
            catch(JSONException je)
            {
                Log.i("josn error",je.getMessage());
            }
            Log.i("test","10");
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
            Log.i("test","11");
            for (int k = 0; k < len; k++)
            {
                    arrayAdapter.add(fil[k][1]);
                Log.i("test","12");
            }
            lv.setAdapter(arrayAdapter);
            loadingDialog.dismiss();
            flag=0;
        }
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
            //super.onBackPressed();
        }
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
