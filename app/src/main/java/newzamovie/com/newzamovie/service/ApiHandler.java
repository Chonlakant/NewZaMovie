package newzamovie.com.newzamovie.service;

import android.content.Context;
import android.util.Log;


import com.squareup.otto.Subscribe;

import newzamovie.com.newzamovie.event.ApiBus;
import newzamovie.com.newzamovie.event.CartoonReceivedEvent;
import newzamovie.com.newzamovie.event.CartoonRequestedEvent;
import newzamovie.com.newzamovie.event.MovieReceivedEvent;
import newzamovie.com.newzamovie.event.MovieRequestedEvent;
import newzamovie.com.newzamovie.event.MusicReceivedEvent;
import newzamovie.com.newzamovie.event.MusicRequestedEvent;
import newzamovie.com.newzamovie.event.SeReceivedEvent;
import newzamovie.com.newzamovie.event.SeRequestedEvent;
import newzamovie.com.newzamovie.model.Cartoon;
import newzamovie.com.newzamovie.model.Movie;
import newzamovie.com.newzamovie.model.Music;
import newzamovie.com.newzamovie.model.Se;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ApiHandler {

    public Context context;
    private ApiService api;
    private ApiBus apiBus;

    public ApiHandler(Context context, ApiService api,
                      ApiBus apiBus) {

        this.context = context;
        this.api = api;
        this.apiBus = apiBus;
    }

    public void registerForEvents() {
        apiBus.register(this);
    }


    @Subscribe
    public void onGetLogistics(final MovieRequestedEvent event) {
        api.getMovie(new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {


                ApiBus.getInstance().postQueue(new MovieReceivedEvent(movie));


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Subscribe
    public void onGetCaetoon(final CartoonRequestedEvent event) {
        api.getCartoon(new Callback<Cartoon>() {
            @Override
            public void success(Cartoon cartoon, Response response) {
                ApiBus.getInstance().postQueue(new CartoonReceivedEvent(cartoon));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Subscribe
    public void onGetMusic(final MusicRequestedEvent event) {
        api.getMusic(new Callback<Music>() {
            @Override
            public void success(Music music, Response response) {
                ApiBus.getInstance().postQueue(new MusicReceivedEvent(music));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Subscribe
    public void onSe(final SeRequestedEvent event) {
        api.getSe(new Callback<Se>() {
            @Override
            public void success(Se se, Response response) {
                ApiBus.getInstance().postQueue(new SeReceivedEvent(se));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


}
