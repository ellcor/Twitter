package com.codepath.apps.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }

    public void onSubmit(View v) {
        EditText etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
        // Prepare data intent
        Intent data = new Intent(ComposeActivity.this, TimelineActivity.class);
        // Pass relevant data back as a result
        data.putExtra("name", etComposeTweet.getText().toString());
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }


}
