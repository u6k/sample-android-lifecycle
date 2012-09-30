/*
Copyright (c) 2012 Yuichi Naono (u6k.yu1.main@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package jp.gr.java_conf.u6k.sample_activity_lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// 各イベントでDEBUGログを出力した。

// 他画面に遷移する場合、戻ってくる場合の挙動を確認するため、画面にボタンを1個配置した。
// ボタンを押下すると、Webブラウザ(Yahoo)が開く。

// 画面が再表示された時のIntentの内容を確認するため、onStart()でIntentの内容をログ出力した。
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.d("onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openBrowserButton = (Button) findViewById(R.id.open_browser_button);
        openBrowserButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://yahoo.co.jp"));
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onStart() {
        LogUtil.d("onStart");

        Intent intent = getIntent();
        LogUtil.d("intent.action: " + intent.getAction());

        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtil.d("onResume");

        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtil.d("onPause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtil.d("onStop");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtil.d("onDestroy");

        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        LogUtil.d("onRestart");

        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtil.d("onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtil.d("onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

}
