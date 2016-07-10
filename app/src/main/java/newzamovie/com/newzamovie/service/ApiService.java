package newzamovie.com.newzamovie.service;


import newzamovie.com.newzamovie.model.Cartoon;
import newzamovie.com.newzamovie.model.Movie;
import newzamovie.com.newzamovie.model.Music;
import newzamovie.com.newzamovie.model.Se;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

    @GET("/Temp/test/json-movie.php")
    void getMovie(Callback<Movie> callback);

    @GET("/Temp/test/json-cartoon.php")
    void getCartoon(Callback<Cartoon> callback);

    @GET("/Temp/test/json-loadpleng.php")
    void getMusic(Callback<Music> callback);

    @GET("/Temp/test/json-series.php")
    void getSe(Callback<Se> callback);




}
