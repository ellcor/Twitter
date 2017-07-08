package com.codepath.apps.twitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TweetAdapter;
import com.codepath.apps.twitter.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by elliecorbus on 7/3/17.
 */

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener {

    public void onComposeNewTweet(Tweet tweet) {
        tweets.add(0, tweet);
        tweetAdapter.notifyItemInserted(0);
        rvTweets.scrollToPosition(0);
    }

    public interface TweetSelectedListener {
        // handle tweet selection
        public void onTweetSelected (Tweet tweet);
    }

    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;

    // inflation happens inside onCreateView

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the layout
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);

        // find the RecyclerView
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
//
//        // init the arrayList (data source)
//        tweets = new ArrayList<>();
//
//        // construct the adapter from this data source
//        tweetAdapter = new TweetAdapter(tweets);

        // RecyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));

        // set the adapter
//        rvTweets.setAdapter(tweetAdapter);

        return v;
    }

    public void addItems(JSONArray response) {

        tweets = new ArrayList();

        for (int i = 0; i < response.length(); i++) {

            try {
                tweets.add(Tweet.fromJSON((JSONObject)response.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // send array of tweets to adapter
        tweetAdapter = new TweetAdapter(tweets, this);
        rvTweets.setAdapter(tweetAdapter);
    }

    public void onNewTweetAvailable(Tweet tweet) {
        tweets.add(0, tweet);
        tweetAdapter.notifyItemInserted(0);
        rvTweets.scrollToPosition(0);
    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweet tweet = tweets.get(position);
        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }
}




























