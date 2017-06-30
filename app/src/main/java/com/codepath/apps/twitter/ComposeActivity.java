package com.codepath.apps.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText etComposeTweet;
    TextView tvCharsRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
        tvCharsRemaining = (TextView) findViewById(R.id.tvCharsRemaining);
        etComposeTweet.addTextChangedListener(mTextEditorWatcher);
    }

    TwitterClient client;


    final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCharsRemaining.setText(String.valueOf(140 - s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    public void onSubmit(View v) {
        client = new TwitterClient(this);
        int numCharsLeft = 140;


//        private TextView mTextView;
//        private EditText mEditText;






        client.sendTweet(etComposeTweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent intent = new Intent(ComposeActivity.this, TimelineActivity.class);
                    intent.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK, intent); // set result code and bundle data for response
                    finish(); // closes the activity, pass data to parent

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }

}
