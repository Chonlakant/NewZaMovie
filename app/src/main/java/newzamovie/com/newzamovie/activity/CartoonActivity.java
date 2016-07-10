package newzamovie.com.newzamovie.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import newzamovie.com.newzamovie.R;
import newzamovie.com.newzamovie.adapter.MyRecyclerAdapter;
import newzamovie.com.newzamovie.adapter.MyRecyclerAdapterCartoon;
import newzamovie.com.newzamovie.event.ActivityResultBus;
import newzamovie.com.newzamovie.event.ApiBus;
import newzamovie.com.newzamovie.event.CartoonReceivedEvent;
import newzamovie.com.newzamovie.event.CartoonRequestedEvent;
import newzamovie.com.newzamovie.event.MovieReceivedEvent;
import newzamovie.com.newzamovie.event.MovieRequestedEvent;
import newzamovie.com.newzamovie.model.Cartoon;
import newzamovie.com.newzamovie.model.Movie;

public class CartoonActivity extends AppCompatActivity {
    AQuery aq;
    String url = "http://project55.comlu.com/json-cartoon.php";

    ArrayList<Cartoon> list = new ArrayList<>();

    RecyclerView listRec;
    Dialog loadingDialog;
    MyRecyclerAdapterCartoon myRecyclerAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("การ์ตูน");
        setSupportActionBar(toolbar);

        aq = new AQuery(getApplicationContext());
        listRec = (RecyclerView) findViewById(R.id.listRec);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ApiBus.getInstance().postQueue(new CartoonRequestedEvent());



        Location location = new Location("AdMobProvider");
        location.setLatitude(13.543296);
        location.setLatitude(100.924562);

        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adBuilder.setLocation(location);

        AdRequest adRequest = adBuilder.build();
        AdView adView = (AdView)findViewById(R.id.adView);
        adView.loadAd(adRequest);

        progressBar.setVisibility(View.VISIBLE);


        //final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listRec.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));


    }

    @Override
    public void onResume() {
        super.onResume();
        ActivityResultBus.getInstance().register(this);
        ApiBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        ActivityResultBus.getInstance().unregister(this);
        ApiBus.getInstance().unregister(this);
    }

    @Subscribe
    public void GetCartoon(final CartoonReceivedEvent event) {
        if (event != null) {

            for (int i = 0; i < event.getPost().getCartoon().size(); i++) {


                list.add(event.getPost());
                myRecyclerAdapter = new MyRecyclerAdapterCartoon(getApplicationContext(), list);
                listRec.setAdapter(myRecyclerAdapter);
                progressBar.setVisibility(View.GONE);
            }





            myRecyclerAdapter.SetOnItemClickListener(new MyRecyclerAdapterCartoon.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    final String title = list.get(position).getCartoon().get(position).getTitle();
                    final String urlVdo = list.get(position).getCartoon().get(position).getUrlvdo();
                    String urlCover = list.get(position).getCartoon().get(position).getUrlcover();
                    final String status = list.get(position).getCartoon().get(position).getStatus();

                    loadingDialog = new Dialog(CartoonActivity.this, R.style.FullHeightDialog);
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
