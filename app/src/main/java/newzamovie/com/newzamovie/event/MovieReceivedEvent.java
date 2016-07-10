package newzamovie.com.newzamovie.event;


import newzamovie.com.newzamovie.model.Movie;

/**
 * Created by marcus on 22/04/15
 */

public class MovieReceivedEvent {

    private static final String TAG = MovieReceivedEvent.class.getSimpleName();
    private Movie post;

    public MovieReceivedEvent(Movie post){
        this.post = post;
    }

    public Movie getPost() {
        return post;
    }
}