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

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// data1、2、3の3個のViewを配置して、端末回転時に値がどのように変化するかを確認する。
// ボタンをタップするとdata1～3に現在時刻が設定されるので、その状態で端末を回転する。
// 各イベントでログを出力するが、その時にdata1～3がどのように変化するか確認できる。
public class RotateActivity extends Activity {

    private TextView _data1TextView;

    private TextView _data2TextView;

    private EditText _data3EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.entering(getClass(), "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        _data1TextView = (TextView) findViewById(R.id.data1_text);
        _data2TextView = (TextView) findViewById(R.id.data2_text);
        _data3EditText = (EditText) findViewById(R.id.data3_edit);
        Button setNowButton = (Button) findViewById(R.id.set_now_button);

        // ボタンをタップすると、data1, 2, 3に現在時刻を設定する。
        setNowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String now = sdf.format(new Date());

                _data1TextView.setText(now);
                _data2TextView.setText(now);
                _data3EditText.setText(now);
            }

        });
    }

    @Override
    protected void onStart() {
        LogUtil.entering(getClass(), "onStart");

        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtil.entering(getClass(), "onResume");

        super.onResume();

        LogUtil.d("data1: " + _data1TextView.getText());
        LogUtil.d("data2: " + _data2TextView.getText());
        LogUtil.d("data3: " + _data3EditText.getText());
    }

    @Override
    protected void onPause() {
        LogUtil.entering(getClass(), "onPause");

        super.onPause();

        LogUtil.d("data1: " + _data1TextView.getText());
        LogUtil.d("data2: " + _data2TextView.getText());
        LogUtil.d("data3: " + _data3EditText.getText());
    }

    @Override
    protected void onStop() {
        LogUtil.entering(getClass(), "onStop");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtil.entering(getClass(), "onDestroy");

        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        LogUtil.entering(getClass(), "onRestart");

        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtil.entering(getClass(), "onSaveInstanceState");

        super.onSaveInstanceState(outState);

        // data1のみ、値を保存する。
        String data1 = _data1TextView.getText().toString();
        outState.putString("data1", data1);

        LogUtil.d("data1: " + _data1TextView.getText());
        LogUtil.d("data2: " + _data2TextView.getText());
        LogUtil.d("data3: " + _data3EditText.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtil.entering(getClass(), "onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);

        // data1のみ、値を復元する。
        String data1 = savedInstanceState.getString("data1");
        _data1TextView.setText(data1);

        LogUtil.d("data1: " + _data1TextView.getText());
        LogUtil.d("data2: " + _data2TextView.getText());
        LogUtil.d("data3: " + _data3EditText.getText());
    }

}
