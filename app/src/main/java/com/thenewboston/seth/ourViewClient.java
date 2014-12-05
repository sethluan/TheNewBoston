package com.thenewboston.seth;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 22707561 on 12/4/2014.
 */
public class ourViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }
}
