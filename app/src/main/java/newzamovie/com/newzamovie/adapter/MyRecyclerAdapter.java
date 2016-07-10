package newzamovie.com.newzamovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import newzamovie.com.newzamovie.R;
import newzamovie.com.newzamovie.model.Movie;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ContactViewHolder> {

    private ArrayList<Movie> contactList = new ArrayList<>();
    Context context;
    private static OnItemClickListener mItemClickListener;
    private static OnItemClickListener mItemClickListenerShare;
    private static OnItemClickListener mItemClickListenerRead;
    private static OnItemClickListener mItemClickListenerPlay;
    public MyRecyclerAdapter(Context context, ArrayList<Movie> contactList) {
        this.context =context;
        this.contactList = contactList;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
       // listMain ci = contactList.get(i);
        Movie item =  contactList.get(i);

        contactViewHolder.movie_name.setText(item.getMovie().get(i).getTitle());

        Picasso.with(context)
                .load(item.getMovie().get(i).getUrlcover())
                .fit().centerCrop()
                .into(contactViewHolder.movie_poster);

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_movie, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movie_name;
        ImageView movie_poster;


        public ContactViewHolder(View v) {
            super(v);
            movie_name = (TextView) v.findViewById(R.id.movie_name);
            movie_poster = (ImageView) v.findViewById(R.id.movie_poster);

            movie_poster.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.movie_poster:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                break;

            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListenerShare {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListenerShare(final OnItemClickListener mItemClickListener) {
        this.mItemClickListenerShare = mItemClickListener;
    }
    public interface OnItemClickListenerRead {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListenerRead(final OnItemClickListener mItemClickListener) {
        this.mItemClickListenerRead = mItemClickListener;
    }

    public interface OnItemClickListenerPlay {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListenerPlay(final OnItemClickListener mItemClickListener) {
        this.mItemClickListenerPlay = mItemClickListener;
    }


}