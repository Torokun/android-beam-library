android-beam-library
====================

Android Beamのライブラリ

このライブラリのAPIリファレンス
BeamHelperクラス
<h3>このライブラリのAPIリファレンス</h3>
<h4>・コンストラクタ</h4>
<strong>public BeamHelper(Activity activity, String pushPackageName, String pushMessage, boolean isAAR)</strong></br>
h4. 引数
* activity : {Activity} Beamを送信するActivity</br>
* pushPackageName : {String} 送信するメッセージを受け取るアプリケーションのパッケージ名(通常、自アプリのパッケージ名を指定することが多いと思います)</br>
　pushMessage : {String} 送信するデータ</br>
　isAAR : {Boolean} Beamを受け取った際、受け取った端末がpushPackageNameに指定したアプリケーションがインストールされていない時にGoogle Playのアプリ画面を表示する(=true)か否か(=false)</br>

<h4>・静的メソッド</h4>
<strong>public static String getReceivedBeamString(Intent intent)</strong></br>
　Beamを受け取った際に受け取ったデータをIntentから取り出し、取り出した文字列を返す</br>
　送信側のプログラムでBeamHelperのコンストラクタのpushMessageに指定した文字列になる</br>
引数</br>
　intent : {Intent} Activityに受け取ったIntent</br>

