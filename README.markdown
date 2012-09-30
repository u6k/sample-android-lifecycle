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
