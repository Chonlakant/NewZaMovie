package newzamovie.com.newzamovie.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
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

import newzamovie.com.newzamovie.MainActivity;
import newzamovie.com.newzamovie.R;
import newzamovie.com.newzamovie.adapter.MyRecyclerAdapterCartoon;
import newzamovie.com.newzamovie.adapter.MyRecyclerAdapterMusic;
import newzamovie.com.newzamovie.event.ActivityResultBus;
import newzamovie.com.newzamovie.event.ApiBus;
import newzamovie.com.newzamovie.event.CartoonRequestedEvent;
import newzamovie.com.newzamovie.event.MovieReceivedEvent;
import newzamovie.com.newzamovie.event.MusicReceivedEvent;
import newzamovie.com.newzamovie.event.MusicRequestedEvent;
import newzamovie.com.newzamovie.model.Movie;
import newzamovie.com.newzamovie.model.Music;

public class MusicActivity extends AppCompatActivity {
    ArrayList<Music> list = new ArrayList<>();

    RecyclerView listRec;
    Dialog loadingDialog;
    MyRecyclerAdapterMusic myRecyclerAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("เพลง");
        setSupportActionBar(toolbar);

        Location location = new Location("AdMobProvider");
        location.setLatitude(13.543296);
        location.setLatitude(100.924562);

        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adBuilder.setLocation(location);

        AdRequest adRequest = adBuilder.build();
        AdView adView = (AdView)findViewById(R.id.adView);
        adView.loadAd(adRequest);

        listRec = (RecyclerView) findViewById(R.id.listRec);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ApiBus.getInstance().postQueue(new MusicRequestedEvent());
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
    public void GetMusic(final MusicReceivedEvent event) {
        if (event != null) {


            for (int i = 0; i < event.getPost().getLoadpleng().size(); i++) {

                list.add(event.getPost());
                myRecyclerAdapter = new MyRecyclerAdapterMusic(getApplicationContext(), list);
                listRec.setAdapter(myRecyclerAdapter);
                progressBar.setVisibility(View.GONE);
            }


            myRecyclerAdapter.SetOnItemClickListener(new MyRecyclerAdapterMusic.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    final String title = list.get(position).getLoadpleng().get(position).getTitle();
                    final String urlMp3 = list.get(position).getLoadpleng().get(position).getUrlmp3();
                    String urlCover = list.get(position).getLoadpleng().get(position).getUrlcover();

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMp3));
                    startActivity(i);




                        }



            });

        }

    }

}
