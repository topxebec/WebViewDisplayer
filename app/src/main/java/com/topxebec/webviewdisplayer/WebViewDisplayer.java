package com.topxebec.webviewdisplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by xebec on 15/7/9.
 */
public class WebViewDisplayer {
    WebView mWebView = null;
    ProgressBar mProgressBar = null;
    Context mContext;
    View mErrorView;

    public WebViewDisplayer(Context mContext) {
        this.mContext = mContext;
    }

    public void setWebView(WebView webview) {
        this.mWebView = webview;
        setUpWebView();
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
        mProgressBar.setVisibility(View.GONE);
    }

    @SuppressLint("JavascriptInterface")
    void setUpWebView() {
        if (mWebView == null) {
            return;
        }
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.getSettings().setNeedInitialFocus(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "topxebec"/*add your js callback name*/);
        mWebView.requestFocus();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("http")) {
                    return true;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (mErrorView == null) {
                    mErrorView = View.inflate(mContext, R.layout.view_error, null);
                    ViewGroup parent = (ViewGroup) mWebView.getParent();
                    parent.addView(mErrorView);
                    mErrorView.getLayoutParams().height = mWebView.getLayoutParams().height;
                    mErrorView.getLayoutParams().width = mWebView.getLayoutParams().width;
                }
                mErrorView.setVisibility(View.VISIBLE);
                Button button = (Button) mErrorView.findViewById(R.id.btn_reload);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mWebView.reload();
                        mErrorView.setVisibility(View.GONE);
                    }
                });
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mProgressBar != null) {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                    if (newProgress == 100) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    public void loadUrl(String urlString) {
        mWebView.loadUrl(urlString);
    }

    @JavascriptInterface
    public void OpenSomeList(String id) {
        // respond to js callback here
    }

}
