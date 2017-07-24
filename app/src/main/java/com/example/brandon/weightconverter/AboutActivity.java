package com.example.brandon.weightconverter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    //region Methods responsible for handling the activity lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null)
            toolbar.setDisplayHomeAsUpEnabled(true);
    }
    //endregion -- end --

    //region Methods responsible for handling events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void linkButtonClicked(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.about_github:
                openLink("https://github.com/BrandonBahret");
                break;

            case R.id.about_linkedin:
                openLink("https://www.linkedin.com/in/brandon-bahret-436012125");
                break;

            case R.id.about_freelancer:
                openLink("https://www.freelancer.com/u/brandonbahretfre.html");
                break;
        }
    }
    //endregion -- end --

    private void openLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public static void startActivity(Context context) {
        Intent launchActivity = new Intent(context, AboutActivity.class);
        context.startActivity(launchActivity);
    }
}
