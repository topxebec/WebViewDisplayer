package com.topxebec.webviewdisplayer.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.topxebec.webviewdisplayer.R;
import com.topxebec.webviewdisplayer.WebViewDisplayer;

public class WebFragment extends Fragment {

    WebView mWebview;
    ProgressBar mProgressBar;
    WebViewDisplayer mWvDisplayer;

    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_with_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWebview = (WebView) view.findViewById(R.id.webview);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);

        mWvDisplayer = new WebViewDisplayer(getActivity());
        mWvDisplayer.setWebView(mWebview);
//        mWvDisplayer.setProgressBar(mProgressBar);

        mWvDisplayer.loadUrl("http://www.github.com");

        super.onViewCreated(view, savedInstanceState);
    }
}
