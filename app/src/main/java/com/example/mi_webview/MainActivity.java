package com.example.mi_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btnGoBack;
    private Button btnGoForward;
    private Button btnReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webView);
//findViewById returns an instance of View

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
// enable JavaScript.

        webView.setVerticalScrollBarEnabled(false);
// hide the Vertical scroll bar and does not remove it.

        webView.setHorizontalScrollBarEnabled(false);
//hide the Horizontal scroll bar and does not remove it.

//        webView.loadUrl("file:///android_asset/text.html");
//open local html file from assets folder.

//        webView.loadUrl("https://www.google.com/");
//open URL.
         webView.loadUrl("file:///android_asset/BarcodeReaderDemo.html");
// open WEB SDK sample and libs

        btnGoBack = (Button) findViewById(R.id.go_back);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button_go_back: Go Back clicked ");
                WebView webView = (WebView) findViewById(R.id.webView);
                webView.goBack();
            }
        });

        btnGoForward = (Button) findViewById(R.id.go_for);
        btnGoForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button_go_forward: Forward Back clicked ");
                WebView webView = (WebView) findViewById(R.id.webView);
                webView.goForward();
            }
        });

        btnReload = (Button) findViewById(R.id.reload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button_reset: Reset button clicked ");
                WebView webView = (WebView) findViewById(R.id.webView);
                webView.reload();
            }
        });

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView webView = (WebView) findViewById(R.id.webView);
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
