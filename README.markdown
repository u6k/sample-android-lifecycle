# AndroidにおけるActivityのライフサイクルを実験するサンプルコード

## Activityのライフサイクル

ActivityのライフサイクルはJavadocに図が記載されている。

[Activity - Android Developers](http://developer.android.com/reference/android/app/Activity.html)

## 普通に画面を開く→閉じる

まず開くときは、以下のようにイベントが実行された。

- onCreate
- onStart
- onResume
- 画面表示

画面表示後、Backキーを押下した時は、以下のようにイベントが実行された。

- onPause
- onStop
- onDestroy

これは予想通り。

## 別画面を開く→戻る

別画面を開くとき、および別画面から戻ってくるときにどのようにイベントが実行されるか確認した。
具体的には、画面にボタンを配置して、Webブラウザを開いて、それからBackキーで戻ってみた。
まず、Webブラウザを開くときは、以下のようにイベントが実行された。

- onPause
- Webブラウザ表示
- onSaveInstanceState
- onStop

onSaveInstanceStateが実行されるので、画面の状態はこのタイミングで保存すると良い。
Webブラウザ表示はonPause直後で、それからonSaveInstanceState、onStopが実行される感じだった。

次に、WebブラウザからBackキーで戻った時、以下のようにイベントが実行された。

- onRestart
- onStart
- onResume

onSaveInstanceStateに対するonRestoreInstanceStateが実行されなかったのは、Activityがまだ破棄されていなかったためと思われる。端末メモリが圧迫されてOSがActivityを破棄した場合、onResume前にonRestoreInstanceStateが実行されると思われる。

## 画面起動時、および再表示のIntent

ホームからアプリ(画面)を起動したときのIntentと、別画面から戻るなどして画面を再表示した時のIntentがどうなるか確認した。
具体的には、onStartでIntentのactionをログに出力してみた。

まず、ホームから画面を起動したときは以下のようになった。

- onCreate
- onStart
- intent.action: android.intent.action.MAIN
- onResume

次に、Webブラウザを開いてからBackキー押下で戻ったときは、以下のようになった。

- onRestart
- onStart
- intent.action: android.intent.action.MAIN
- onResume

つまり、画面起動時も再表示時も、同じIntentが渡される。

## 端末回転時のイベント

端末を回転させたときに実行されるイベントを確認した。

- onPause
- onSaveInstanceState
- onStop
- onDestroy
- onCreate
- onStart
- onRestoreInstanceState
- onResume

onDestroyとonCreateが実行されることから、一度Activityが破棄され、再度作成されていることが分かる。また、onSaveInstanceStateとonRestoreInstanceStateが実行されており、このタイミングで保持しておきたい画面データを保存/復元すればよいことが分かる。

### 実際に画面項目値がどうなるか確認

画面に以下のようにビューを配置した。

- data1 (TextView)
- data2 (TextView)
- data3 (EditText)

このうち、data1のみ、onSaveInstanceStateとonRestoreInstanceStateで値を保存/復元するように処理を書いた。

端末回転前に、data1、2、3に現在時刻を設定し、それから回転すると、以下のようになった。

- onPause
    - data1: 2012/10/01 21:44:15
    - data2: 2012/10/01 21:44:15
    - data3: 2012/10/01 21:44:15
- onSaveInstanceState
    - data1: 2012/10/01 21:44:15
    - data2: 2012/10/01 21:44:15
    - data3: 2012/10/01 21:44:15
- onStop
- onDestroy
- onCreate
- onStart
- onRestoreInstanceState
    - data1: 2012/10/01 21:44:15
    - data2: データ2 (保存されない)
    - data3: 2012/10/01 21:44:15
- onResume
    - data1: 2012/10/01 21:44:15
    - data2: データ2 (保存されない)
    - data3: 2012/10/01 21:44:15

onSaveInstanceStateまではdata1～3に値が保持されているが、onRestoreInstanceStateではdata2が消えてしまっている。これは、data1はonSaveInstanceStateで値を保存しているがdata2は保存していないためである。

data3の値はonSaveInstanceStateで保存していないにもかかわらず値が保持されているが、これはEditTextが内部でonSaveInstanceState、onRestoreInstanceState時に値を保存/復元しているためである。
