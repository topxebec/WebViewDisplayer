package com.topxebec.webviewdisplayer.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.topxebec.webviewdisplayer.R;

public class WithFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragment);

        WebFragment firstFragment = new WebFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }
}
