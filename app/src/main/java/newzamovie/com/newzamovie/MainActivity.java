package newzamovie.com.newzamovie;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import newzamovie.com.newzamovie.activity.CartoonActivity;
import newzamovie.com.newzamovie.activity.LoadingMovieActivity;
import newzamovie.com.newzamovie.activity.MusicActivity;
import newzamovie.com.newzamovie.activity.SeActivity;
import newzamovie.com.newzamovie.activity.VideoCacheActivity;
import newzamovie.com.newzamovie.activity.WebViewActivity;
import newzamovie.com.newzamovie.adapter.MyRecyclerAdapter;
import newzamovie.com.newzamovie.event.ActivityResultBus;
import newzamovie.com.newzamovie.event.ApiBus;
import newzamovie.com.newzamovie.event.MovieReceivedEvent;
import newzamovie.com.newzamovie.event.MovieRequestedEvent;
import newzamovie.com.newzamovie.model.Movie;

public class MainActivity extends AppCompatActivity {

    MyRecyclerAdapter myRecyclerAdapter;
    ProgressBar progressBar;
    AQuery aq;
    String url = "http://project55.comlu.com/json-movie.php";

    ArrayList<Movie> list = new ArrayList<>();

    RecyclerView listRec;
    Dialog loadingDialog;
    InterstitialAd iAd;
    Dialog loadingDialog2;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aq = new AQuery(getApplicationContext());
        listRec = (RecyclerView) findViewById(R.id.listRec);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Location location = new Location("AdMobProvider");
        location.setLatitude(13.543296);
        location.setLatitude(100.924562);

        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adBuilder.setLocation(location);

        AdRequest adRequest = adBuilder.build();
        AdView adView = (AdView)findViewById(R.id.adView);
        adView.loadAd(adRequest);
        progressBar.setVisibility(View.VISIBLE);


        ApiBus.getInstance().postQueue(new MovieRequestedEvent());
        listRec.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));

        android.support.design.widget.FloatingActionButton fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loadingDialog2 = new Dialog(MainActivity.this, R.style.FullHeightDialog);
                loadingDialog2.setContentView(R.layout.dailog_list_movie);

                Button btn_cartoon = (Button) loadingDialog2.findViewById(R.id.btn_cartoon);
                Button btn_series = (Button) loadingDialog2.findViewById(R.id.btn_series);
                Button btn_music = (Button) loadingDialog2.findViewById(R.id.btn_music);

                btn_cartoon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CartoonActivity.class);
                        startActivity(i);
                    }
                });

                btn_series.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent i = new Intent(getApplicationContext(), SeActivity.class);
                        startActivity(i);

                    }
                });

                btn_music.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(getApplicationContext(), MusicActivity.class);
                        startActivity(i);
//
//                        Snackbar.make(view, "รออัพเดพ", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
                    }
                });

                loadingDialog2.show();


            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        ActivityResultBus.getInstance().register(this);
        ApiBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
        ActivityResultBus.getInstance().unregister(this);
        ApiBus.getInstance().unregister(this);
    }

    @Subscribe
    public void GetMovie(final MovieReceivedEvent event) {
        if (event != null) {


            for (int i = 0; i < event.getPost().getMovie().size(); i++) {

                list.add(event.getPost());
                myRecyclerAdapter = new MyRecyclerAdapter(getApplicationContext(), list);
                listRec.setAdapter(myRecyclerAdapter);

            }


            progressBar.setVisibility(View.GONE);
            myRecyclerAdapter.SetOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    final String title = list.get(position).getMovie().get(position).getTitle();
                    final String urlVdo = list.get(position).getMovie().get(position).getUrlvdo();
                    String urlCover = list.get(position).getMovie().get(position).getUrlcover();
                    final String status = list.get(position).getMovie().get(position).getStatus();

                    loadingDialog = new Dialog(MainActivity.this, R.style.FullHeightDialog);
                    loadingDialog.setContentView(R.layout.popup_detail);
                    loadingDialog.setTitle(title);
                    ImageView imageView = (ImageView) loadingDialog.findViewById(R.id.imageView);
                    ImageView cancel = (ImageView) loadingDialog.findViewById(R.id.cancel);
                    TextView textView = (TextView) loadingDialog.findViewById(R.id.textView);
                    TextView movie_name = (TextView) loadingDialog.findViewById(R.id.movie_name);
                    ImageView play = (ImageView) loadingDialog.findViewById(R.id.play);


                    Location location = new Location("AdMobProvider");
                    location.setLatitude(13.543296);
                    location.setLatitude(100.924562);

                    AdRequest.Builder adBuilder = new AdRequest.Builder();
                    adBuilder.setLocation(location);

                    AdRequest adRequest = adBuilder.build();
                    AdView adView = (AdView) loadingDialog.findViewById(R.id.adView1);
                    adView.loadAd(adRequest);


                    movie_name.setText(title);
                    textView.setText(title);
                    Picasso.with(getApplicationContext())
                            .load(urlCover)
                            .fit().centerCrop()
                            .into(imageView);


                    play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(getApplicationContext(), LoadingMovieActivity.class);
                            i.putExtra("titles", title);
                            i.putExtra("key", urlVdo);
                            i.putExtra("status",status);
                            startActivity(i);


                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadingDialog.dismiss();
                        }
                    });


                    loadingDialog.show();


                }

            });

        }


    }
}