
package com.toro.android.lib.androidbeam;

import java.nio.charset.Charset;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;

public class Beam extends BeamAppendHelper implements CreateNdefMessageCallback {
    private static final String TAG = "Beam";

    private Activity mActivity = null;

    private String mPushPackageName;

    private String mPushMessage;

    private boolean mIsAAR;

    private NfcAdapter mNfcAdapter = null;

    private boolean nfcAdapterEnabled = false;

    public Beam(Activity activity, String pushPackageName, String pushMessage, boolean isAAR) {
        mActivity = activity;
        mPushPackageName = pushPackageName;
        mPushMessage = pushMessage;
        mIsAAR = isAAR;
        // 利用可能なNFC アダプタのチェック
        mNfcAdapter = NfcAdapter.getDefaultAdapter(mActivity);
        if (mNfcAdapter == null) {
            return;
        }
        nfcAdapterEnabled = true;
        mNfcAdapter.setNdefPushMessageCallback(this, mActivity);
    }

    @Override
    public boolean isAndroidBeam() {
        return nfcAdapterEnabled;
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        NdefRecord[] ndefRecord;
        String mime = "application/" + mPushPackageName;
        NdefRecord mimeRecord = createMimeRecord(mime, mPushMessage.getBytes());
        if (mIsAAR) {
            ndefRecord = new NdefRecord[] {
                    mimeRecord, NdefRecord.createApplicationRecord(mPushPackageName)
            };
        } else {
            ndefRecord = new NdefRecord[] {
                mimeRecord
            };
        }
        NdefMessage msg = new NdefMessage(ndefRecord);
        return msg;
    }

    private NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0],
                payload);
        return mimeRecord;
    }

    @Override
    public void closeBeam() {
        mActivity = null;
    }

}
