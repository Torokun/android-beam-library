android-beam-library
====================

Android Beamのライブラリ

<h2>このライブラリのAPIリファレンス</h2>
<h3>BeamHelperクラス</h3>
<h4>・コンストラクタ</h4>
<strong>public BeamHelper(Activity activity, String pushPackageName, String pushMessage, boolean isAAR)</strong>
<h5>引数</h5>
* activity : {Activity} Beamを送信するActivity
* pushPackageName : {String} 送信するメッセージを受け取るアプリケーションのパッケージ名(通常、自アプリのパッケージ名を指定することが多いと思います)
* pushMessage : {String} 送信するデータ
* isAAR : {Boolean} Beamを受け取った際、受け取った端末がpushPackageNameに指定したアプリケーションがインストールされていない時にGoogle Playのアプリ画面を表示する(=true)か否か(=false)

<h4>・静的メソッド</h4>
<strong>public static String getReceivedBeamString(Intent intent)</strong>
* Beamを受け取った際に受け取ったデータをIntentから取り出し、取り出した文字列を返す
* 送信側のプログラムでBeamHelperのコンストラクタのpushMessageに指定した文字列になる
<h5>引数</h5>
* intent : {Intent} Activityに受け取ったIntent

TODO : サンプルプログラムの作成
