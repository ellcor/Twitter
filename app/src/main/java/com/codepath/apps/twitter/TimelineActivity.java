package com.codepath.apps.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.twitter.fragments.TweetsListFragment;
import com.codepath.apps.twitter.fragments.TweetsPagerAdapter;
import com.codepath.apps.twitter.models.Tweet;

import org.parceler.Parcels;

public class TimelineActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener {

    final int REQUEST_CODE = 20;
    TweetsListFragment fragmentTweetsList;

    ViewPager vpPager;
    TweetsPagerAdapter tweetsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // get the view pager
        vpPager = (ViewPager) findViewById(R.id.viewpager);

        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager(), this);

        // set the adapter for the pager
        vpPager.setAdapter(tweetsPagerAdapter);

        // setup the TabLayout to use the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_menu, menu);
        return true;
    }

    public void onProfileView(MenuItem item) {
        // launch the profile view
        Intent i = new Intent (this, ProfileActivity.class);
        startActivity(i);
    }

    @Override
    public void onTweetSelected(Tweet tweet) {
        Toast. makeText(this, tweet.body, Toast.LENGTH_LONG).show();
    }


//
//
////        fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
//
//
//
    public void onComposeAction(MenuItem mi) {

        // handle click
        mi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {

                // FirstActivity, launching an activity for a result
                Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
                startActivityForResult(i, REQUEST_CODE);
                return true;
            }
        });
    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract tweet from result extras

            // the new tweet we just made
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            int position = vpPager.getCurrentItem();
            TweetsListFragment currentFragment = (TweetsListFragment) tweetsPagerAdapter.getRegisteredFragment(position);
            currentFragment.onComposeNewTweet(tweet);

        }
    }
}
//
