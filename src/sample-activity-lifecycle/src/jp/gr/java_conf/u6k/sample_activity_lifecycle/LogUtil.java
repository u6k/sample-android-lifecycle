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

import android.util.Log;

/**
 * ログ・ユーティリティ・クラス。
 * <p>
 * 以下のメソッドを提供する。
 * <ul>
 * <li>tag引数を省略した、{@link #e(String)}, {@link #w(String)}, {@link #i(String)}, {@link #d(String)}. {@link #v(String)}メソッド。
 * <li>メソッドの開始・終了ログを出力する、{@link #entering(Class, String)}, {@link #exiting(Class, String)}メソッド。
 * </ul>
 * </p>
 * 
 * <h3>ログレベル</h3>
 * <p>
 * Androidのログ・レベルは以下のように定義されている。
 * </p>
 * 
 * <pre>
 * ERROR &gt; WARN &gt; INFO(実機デフォルト) &gt; DEBUG &gt; VERBOSE
 * </pre>
 * <p>
 * ログ・レベルは以下のコマンドで設定できる。
 * </p>
 * 
 * <pre>
 * adb shell setprop log.tag.Hoge DEBUG
 * </pre>
 * 
 * <h3>ログの仕組み</h3>
 * <p>
 * Androidのログの仕組みについては、以下のページがよくまとまっている。<br>
 * <a href="http://blog.kmckk.com/archives/2936958.html">JMC Staff Blog: Androidのログの仕組み</a>
 * </p>
 */
public class LogUtil {

    /** ログのタグ */
    private static final String TAG = "sample-lifecycle";

    /**
     * インスタンス化を禁止する。
     */
    private LogUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * DEBUGログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     */
    public static void d(String msg) {
        if (Log.isLoggable(LogUtil.TAG, Log.DEBUG)) {
            Log.d(LogUtil.TAG, msg);
        }
    }

    /**
     * DEBUGログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     * @param tr
     *            例外オブジェクト。
     */
    public static void d(String msg, Throwable tr) {
        if (Log.isLoggable(LogUtil.TAG, Log.DEBUG)) {
            Log.d(LogUtil.TAG, msg, tr);
        }
    }

    /**
     * INFOログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     */
    public static void i(String msg) {
        if (Log.isLoggable(LogUtil.TAG, Log.INFO)) {
            Log.i(LogUtil.TAG, msg);
        }
    }

    /**
     * INFOログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     * @param tr
     *            例外オブジェクト。
     */
    public static void i(String msg, Throwable tr) {
        if (Log.isLoggable(LogUtil.TAG, Log.INFO)) {
            Log.i(LogUtil.TAG, msg, tr);
        }
    }

    /**
     * ERRORログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     * @param tr
     *            例外オブジェクト。
     */
    public static void e(String msg, Throwable tr) {
        if (Log.isLoggable(LogUtil.TAG, Log.ERROR)) {
            Log.e(LogUtil.TAG, msg, tr);
        }
    }

    /**
     * ERRORログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     */
    public static void e(String msg) {
        if (Log.isLoggable(LogUtil.TAG, Log.ERROR)) {
            Log.e(LogUtil.TAG, msg);
        }
    }

    /**
     * WARNログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     */
    public static void w(String msg) {
        if (Log.isLoggable(LogUtil.TAG, Log.WARN)) {
            Log.w(LogUtil.TAG, msg);
        }
    }

    /**
     * WARNログを出力する。
     * 
     * @param tr
     *            例外オブジェクト。
     */
    public static void w(Throwable tr) {
        if (Log.isLoggable(LogUtil.TAG, Log.WARN)) {
            Log.w(LogUtil.TAG, tr);
        }
    }

    /**
     * WARNログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     * @param tr
     *            例外オブジェクト。
     */
    public static void w(String msg, Throwable tr) {
        if (Log.isLoggable(LogUtil.TAG, Log.WARN)) {
            Log.w(LogUtil.TAG, msg, tr);
        }
    }

    /**
     * VERBOSEログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     */
    public static void v(String msg) {
        if (Log.isLoggable(LogUtil.TAG, Log.VERBOSE)) {
            Log.v(LogUtil.TAG, msg);
        }
    }

    /**
     * VERBOSEログを出力する。
     * 
     * @param msg
     *            ログ・メッセージ。
     * @param tr
     *            例外オブジェクト。
     */
    public static void v(String msg, Throwable tr) {
        if (Log.isLoggable(LogUtil.TAG, Log.VERBOSE)) {
            Log.v(LogUtil.TAG, msg, tr);
        }
    }

    /**
     * メソッド実行開始ログを出力する(VERBOSEログ)。
     * <p>
     * 以下の形式で出力される。
     * </p>
     * 
     * <pre>
     * &lt;クラス名&gt;.&lt;メソッド名&gt; entering
     * </pre>
     * 
     * @param clazz
     *            メソッドを持つクラス。
     * @param methodName
     *            実行を開始したメソッドの名前。
     */
    public static void entering(Class<?> clazz, String methodName) {
        if (Log.isLoggable(LogUtil.TAG, Log.VERBOSE)) {
            Log.v(LogUtil.TAG, clazz.getName() + "." + methodName + " entering");
        }
    }

    /**
     * メソッド実行終了ログを出力する(VERBOSEログ)。
     * <p>
     * 以下の形式で出力される。
     * </p>
     * 
     * <pre>
     * &lt;クラス名&gt;.&lt;メソッド名&gt; exiting
     * </pre>
     * 
     * @param clazz
     *            メソッドを持つクラス。
     * @param methodName
     *            実行が終了したメソッドの名前。
     */
    public static void exiting(Class<?> clazz, String methodName) {
        if (Log.isLoggable(LogUtil.TAG, Log.VERBOSE)) {
            Log.v(LogUtil.TAG, clazz.getName() + "." + methodName + " exiting");
        }
    }

}
