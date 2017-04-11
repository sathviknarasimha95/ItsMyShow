package nie_mca.itsmyshow;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


public class Login extends AppCompatActivity
{
    Button b,r,fp;
    Context t;
    String email,pass;
    EditText eMail,pAss;
    static ProgressDialog pd;
    static int flag=0;
    static Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        t=this;
        b=(Button)findViewById(R.id.loginButton);
        eMail=(EditText)findViewById(R.id.loginEmail);
        pAss=(EditText)findViewById(R.id.loginPass);
        pd = new ProgressDialog(t);
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
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                email=eMail.getText().toString();
                pass=pAss.getText().toString();
                if(email.isEmpty() || email.contains("@")==false)
                {
                    Snackbar.make(v,"Enter a proper E-mail", Snackbar.LENGTH_LONG).show();
                }
                else if(pass.isEmpty())
                {
                    Snackbar.make(v,"Enter Password", Snackbar.LENGTH_LONG).show();
                }
                else
                {

                   // pd.show();
                    login(email,pass);

                }
            }
        });
    }
    private void login(final String username, String password)
    {

        class LoginAsync extends AsyncTask<String, Void, String>
        {



            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                flag=1;
                loadingDialog = ProgressDialog.show(Login.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params)
            {
                String uname = params[0];
                String pass = params[1];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://122.172.178.242/php/login.php");
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
            protected void onPostExecute(String result)
            {
                String s = result.trim();
                String points=s.substring(0,s.indexOf('+'));
                String msg=s.substring(s.indexOf('+')+1,s.length());
                loadingDialog.dismiss();
                flag=0;
                if(msg.equalsIgnoreCase("success login"))
                {
                    SharedPreferences sharedPref = t.getSharedPreferences("itsmyshow", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", email);
                    editor.putString("password", pass);
                    editor.putString("point",points);
                    editor.commit();
                  Intent intent33= new Intent("android.intent.action.MAINACT");
                    intent33.putExtra("loginflag","success");
                   startActivity(intent33);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }





    /*@Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case 1011: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Intent tem1=new Intent("android.intent.action.MPAGE");
                    tem1.putExtra("backendlesspath","/myfiles/");
                    startActivity(tem1);
                    finish();

                }
                else
                {

                    finish();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    } */

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        /*new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        Login.super.onBackPressed();
                    }
                }).create().show(); */
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

