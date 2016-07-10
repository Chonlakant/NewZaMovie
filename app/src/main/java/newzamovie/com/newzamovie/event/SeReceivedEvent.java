package newzamovie.com.newzamovie.event;


import newzamovie.com.newzamovie.model.Music;
import newzamovie.com.newzamovie.model.Se;

/**
 * Created by marcus on 22/04/15
 */

public class SeReceivedEvent {

    private static final String TAG = SeReceivedEvent.class.getSimpleName();
    private Se post;

    public SeReceivedEvent(Se post){
        this.post = post;
    }

    public Se getPost() {
        return post;
    }
}