
package com.toro.android.lib.androidbeam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;

@SuppressLint("NewApi")
public class BeamReceiveHelper {
    static {
        try {
            Class.forName("android.nfc.NdefMessage");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static final String TAG = "BeamReceiveHelper";

    /* calling here forces class initialization */
    public static void checkAvailable() {
    }

    public static String getReceivedBeamString(Intent intent) {
        byte[] beam = getReceivedBeam(intent);
        if(beam == null) {
            return null;
        }
        return new String(beam);
    }

    public static byte[] getReceivedBeam(Intent intent) {
        if (intent == null) {
            Log.d(TAG, "intest is null");
            return null;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD_MR1) {
            Log.d(TAG, "non support version");
            return null;
        }
        if (!NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Log.d(TAG, "Intent Action is differnt");
            return null;
        }
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if(rawMsgs == null) {
            Log.d(TAG, "raw message is null");
            return null;
        }
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        if(msg == null) {
            Log.d(TAG, "ndef message is null");
            return null;
        }
        NdefRecord[] ndefRecords = msg.getRecords();
        if(ndefRecords == null) {
            Log.d(TAG, "ndefRecords is null");
            return null;
        } else if(ndefRecords.length < 1) {
            Log.d(TAG, "ndefRecords length is 0");
            return null;
        }
        NdefRecord firstNdefRecord = ndefRecords[0];
        if(firstNdefRecord == null) {
            Log.d(TAG, "firstNdefRecord is null");
            return null;
        }
        return firstNdefRecord.getPayload();
    }
}
