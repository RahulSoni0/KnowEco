package com.example.toastapp.classes;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Mybrower extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
