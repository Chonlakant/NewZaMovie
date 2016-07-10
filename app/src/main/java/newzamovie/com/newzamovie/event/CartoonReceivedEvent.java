package newzamovie.com.newzamovie.event;


import newzamovie.com.newzamovie.model.Cartoon;
import newzamovie.com.newzamovie.model.Movie;

/**
 * Created by marcus on 22/04/15
 */

public class CartoonReceivedEvent {

    private static final String TAG = CartoonReceivedEvent.class.getSimpleName();
    private Cartoon post;

    public CartoonReceivedEvent(Cartoon post){
        this.post = post;
    }

    public Cartoon getPost() {
        return post;
    }
}