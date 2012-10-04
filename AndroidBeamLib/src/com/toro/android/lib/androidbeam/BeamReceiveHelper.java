
package com.toro.android.lib.androidbeam;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;

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
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        return new String(msg.getRecords()[0].getPayload());
    }
}
