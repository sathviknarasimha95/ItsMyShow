package nie_mca.itsmyshow;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shreesha K L on 19/11/2016.
 */

public class Prelogin extends Activity
{
    String fav;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);
        SharedPreferences sharedPref = this.getSharedPreferences("itsmyshow", Context.MODE_PRIVATE);
        String mailsp=sharedPref.getString("email","No value");
        String passsp=sharedPref.getString("password","No password");
        //long highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);

      /*  Thread aa=new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException ie)
                {

                }
            }
        };
        aa.start(); */
        if(mailsp.equals("No value")==false && passsp.equals("No value")==false)
        {

            //Toast.makeText(this,"logged from sharedPreference",Toast.LENGTH_LONG).show();
            //Intent rintent=new Intent();
            // setResult(Activity.RESULT_OK,rintent);
            Intent intent11 = new Intent("android.intent.action.MAINACT");
            intent11.putExtra("loginflag","success");
            startActivity(intent11);
            finish();

        }
        else
        {
            Intent intent11 = new Intent("android.intent.action.MAINACT");
            intent11.putExtra("loginflag","failed");
            startActivity(intent11);
            finish();
           // finish();
        }
    }




}
