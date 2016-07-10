package newzamovie.com.newzamovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;
import java.util.TimerTask;

import newzamovie.com.newzamovie.R;

public class LoadingMovieActivity extends AppCompatActivity {
    private static final int WAIT_TIME = 5000;

    public String VERSION;
    // Your interstitial ad unit ID.
    private static final String AD_UNIT_ID = "ca-app-pub-1874751237565535/6566366807";

    private InterstitialAd interstitial;
    private Timer waitTimer;

    private boolean interstitialCanceled = false;
    String status;
    String title;
    String urlVdo;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_movie);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.VISIBLE);

        status = getIntent().getStringExtra("status");
        title = getIntent().getStringExtra("titles");
        urlVdo = getIntent().getStringExtra("key");

        Log.e("status",status);
        Log.e("title",title);
        Log.e("key",urlVdo);

        interstitial = new InterstitialAd(getApplicationContext());
        interstitial.setAdUnitId(AD_UNIT_ID);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (status.equals("1")) {

                    Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                    i.putExtra("key", urlVdo);
                    startActivity(i);


                } else {
                    Intent i = new Intent(getApplicationContext(), VideoCacheActivity.class);
                    i.putExtra("titles", title);
                    i.putExtra("key", urlVdo);
                    startActivity(i);
                    Log.e("urlVdo", urlVdo);
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                progressBar2.setVisibility(View.GONE);
                if (!interstitialCanceled) {
                    waitTimer.cancel();
                    interstitial.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

                if (status.equals("1")) {

                    Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                    i.putExtra("key", urlVdo);
                    startActivity(i);
                    finish();


                } else {
                    Intent i = new Intent(getApplicationContext(), VideoCacheActivity.class);
                    i.putExtra("titles", title);
                    i.putExtra("key", urlVdo);
                    startActivity(i);
                    finish();
                    Log.e("urlVdo", urlVdo);
                }
            }
        });


        interstitial.loadAd(new AdRequest.Builder().build());

        waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                interstitialCanceled = true;
                LoadingMovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (status.equals("1")) {

                            Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                            i.putExtra("key", urlVdo);
                            startActivity(i);


                        } else {
                            Intent i = new Intent(getApplicationContext(), VideoCacheActivity.class);
                            i.putExtra("titles", title);
                            i.putExtra("key", urlVdo);
                            startActivity(i);
                            Log.e("urlVdo", urlVdo);
                        }
                    }
                });
            }
        }, WAIT_TIME);
    }

    public void nextActivity() {

        if (status.equals("1")) {

            Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
            i.putExtra("key", urlVdo);
            startActivity(i);


        } else {
            Intent i = new Intent(getApplicationContext(), VideoCacheActivity.class);
            i.putExtra("titles", title);
            i.putExtra("key", urlVdo);
            startActivity(i);
            Log.e("urlVdo", urlVdo);
        }
    }

}
