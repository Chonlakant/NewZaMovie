package newzamovie.com.newzamovie.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import newzamovie.com.newzamovie.R;

/**
 * Created by root1 on 7/8/16.
 */
public class WebViewActivity  extends Activity {

    private WebView webView;
    String url;
    ProgressBar progressBar3;

    public void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.webview);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.VISIBLE);
        url = getIntent().getStringExtra("key");

        webView = (WebView) findViewById(R.id.webView1);
        loadWebViewLoad(webView);


    }

    private void loadWebViewLoad(WebView webview) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl(url);
        progressBar3.setVisibility(View.GONE);
    }

}