package com.novaparse.qpass2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.prmja.http.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Webview
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://sabreok.com/projects/code/qpass/index.php");
        //Javascript Enable
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        FloatingActionButton scannerButton = (FloatingActionButton) findViewById(R.id.scannerButton);
        scannerButton.setImageResource(R.drawable.qpass_icon);
        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BarcodeScanner.class);
                startActivity(intent);
            }
        });
    }

    public static void send(String checkin_secret) {
        String[] params = {"checkin_secret", checkin_secret, "external_app_secret", "44nicememe44", "demo_user_id","3"};
        try {
            prmja_com.Post("http://sabreok.com/projects/code/qpass/process/checkin.php", params);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.webView);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}