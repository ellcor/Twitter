package com.codepath.apps.twitter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitter.fragments.UserTimelineFragment;
import com.codepath.apps.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // info for other users
        String otherScreenName = getIntent().getStringExtra("NAME");
        long userID = getIntent().getLongExtra("userID", 20);

        // get the screen name from the intent
        String screenName = getIntent().getStringExtra("screen_name");


        // FRAGMENT STUFF

        if (otherScreenName == null){
            // create the user fragment
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);
            // display the user timeline fragment inside the container dynamically
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // make change
            ft.replace(R.id.flContainer, userTimelineFragment);
            // commit
            ft.commit();
        }
        else
        {
            // create the user fragment
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(otherScreenName);
            // display the user timeline fragment inside the container dynamically
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // make change
            ft.replace(R.id.flContainer, userTimelineFragment);
            // commit
            ft.commit();
        }

        client = TwitterApplication.getTwitterClient();


        // calling own profile
        if (otherScreenName == null){
            client.getUserInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    // deserialize the User object
                    try {
                        User user = User.fromJSON(response);

                        // set the title of the ACtionBar based on the user info
                        getSupportActionBar().setTitle(user.screenName);

                        // populate the user headline
                        populateUserHeadline(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else
        {
            client.getOtherUserTimeline(String.valueOf(userID), otherScreenName, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    // deserialize the User object
                    try {
                        User user = User.fromJSON(response);

                        // set the title of the ActionBar based on the user info
                        getSupportActionBar().setTitle(user.screenName);

                        // populate the user headline
                        populateUserHeadline(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void populateUserHeadline(User user) {
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);

        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvName.setText(user.name);

         tvTagline.setText(user.tagLine);
         tvFollowers.setText(user.followersCount + " Followers");
         tvFollowing.setText(user.followingCount + " Following");

        // load profile image with Glide
        Glide.with(this).load(user.profileImageUrl).into(ivProfileImage);
    }
}
