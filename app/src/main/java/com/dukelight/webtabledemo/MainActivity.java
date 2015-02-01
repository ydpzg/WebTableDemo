package com.dukelight.webtabledemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";
    private WebView mWebview;
    private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebview = (WebView) findViewById(R.id.webview);

        initWebView();
    }

    private void initWebView() {
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.setInitialScale(10);
        mWebview.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber,
                                         String sourceID) {
                Log.i(TAG, "console=" + message);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }
        });
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                readReport();
            }
        });
        mWebview.loadUrl("file:///android_asset/report/report.html");

    }

    private void readReport() {
        List<String> titleList = new ArrayList<String>();
        titleList.add("任务");
        titleList.add("人物");
        titleList.add("事件");
        List<List<String>> dataLists = new ArrayList<List<String>>();

        List<String> dataList = new ArrayList<String>();
        dataList.add("11111");
        dataList.add("22222");
        dataList.add("33333");

        List<String> dataList2 = new ArrayList<String>();
        dataList2.add("11111");
        dataList2.add("22222");
        dataList2.add("33333");

        dataLists.add(dataList);
        dataLists.add(dataList2);
        mWebview.loadUrl("javascript:setData('" + mGson.toJson(titleList) + "', '" + mGson.toJson(dataLists) + "')");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
