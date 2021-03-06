package com.codepath.apps.twitter.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.codepath.apps.twitter.SmartFragmentStatePagerAdapter;

/**
 * Created by elliecorbus on 7/6/17.
 */

public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter {

    // instance variables
    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;

    // Constructor
    public TweetsPagerAdapter(FragmentManager fm, Context context) {
        super (fm);
        this.context = context;
    }

    // return the total # of fragments
    @Override
    public int getCount() {
        return 2;
    }

    // return the fragment to use depending on the position
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            return new HomeTimelineFragment();
        }
        else if (position == 1)
        {
            return new MentionsTimelineFragment();
        }
        else
        {
            return null;
        }
    }

    // return title
    @Override
    public CharSequence getPageTitle(int position) {
        // generate title based on item position
        return tabTitles[position];
    }
}
