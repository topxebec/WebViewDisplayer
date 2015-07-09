package com.topxebec.webviewdisplayer.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.topxebec.webviewdisplayer.R;
import com.topxebec.webviewdisplayer.WebViewDisplayer;

public class MainActivity extends AppCompatActivity {

    WebView mWebview;
    ProgressBar mProgressBar;
    WebViewDisplayer mWvDisplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebview = (WebView) findViewById(R.id.webview);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mWvDisplayer = new WebViewDisplayer(this);
        mWvDisplayer.setWebView(mWebview);
        mWvDisplayer.setProgressBar(mProgressBar);//or not

        mWvDisplayer.loadUrl("http://www.github.com");
    }

    public void goToFragmentActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, WithFragmentActivity.class);
        startActivity(intent);
    }
}
