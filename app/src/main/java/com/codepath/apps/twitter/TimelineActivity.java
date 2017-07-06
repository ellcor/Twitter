package com.codepath.apps.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.twitter.fragments.TweetsPagerAdapter;

public class TimelineActivity extends AppCompatActivity {

//    TweetsListFragment fragmentTweetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // get the view pager
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);

        // set the adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));

        // setup the TabLayout to use the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    public void onProfileView(MenuItem item) {
        // launch the profile view
        Intent i = new Intent (this, ProfileActivity.class);
        startActivity(i);
    }
}

//
//
////        fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present
//        getMenuInflater().inflate(R.menu.compose_tweet, menu);
//        return true;
//
//    }
//
//    // REQUEST_CODE can be any value we like, used to determine the result type later
//    final int REQUEST_CODE = 20;
//
//    public void onComposeAction(MenuItem mi) {
//
//        // handle click
//        mi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//
//            public boolean onMenuItemClick(MenuItem item) {
//
//
//                // FirstActivity, launching an activity for a result
//                Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
//                startActivityForResult(i, REQUEST_CODE);
//                return true;
//            }
//        });
//    }
//
//    // ActivityOne.java, time to handle the result of the sub-activity
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // REQUEST_CODE is defined above
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            // Extract tweet from result extras
//
//            // the new tweet we just made
//            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
//
//
//            // ^^ needs to be displayed in the RecyclerView of tweets
//                    // which is in the TweetsListFragment
//
//            // send the tweet variable ^^ to TweetsListFragment
//            fragmentTweetsList.onNewTweetAvailable(tweet);
//
//        }
//    }
//}
//
