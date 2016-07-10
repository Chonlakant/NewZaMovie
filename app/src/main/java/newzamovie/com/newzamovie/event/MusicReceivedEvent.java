package newzamovie.com.newzamovie.event;


import newzamovie.com.newzamovie.model.Movie;
import newzamovie.com.newzamovie.model.Music;

/**
 * Created by marcus on 22/04/15
 */

public class MusicReceivedEvent {

    private static final String TAG = MusicReceivedEvent.class.getSimpleName();
    private Music post;

    public MusicReceivedEvent(Music post){
        this.post = post;
    }

    public Music getPost() {
        return post;
    }
}