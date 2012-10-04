
package com.toro.android.lib.androidbeam;

import android.app.Activity;
import android.content.Intent;

public class BeamHelper {
    
    private static String TAG = "BeamHelper";

    private static boolean mBeamSendAvailable;

    private static boolean mBeamReceiveAvailable;

    private BeamAppendHelper mBeam = null;

    /*
     * establish whether the "Beam" class and the "ReceiveBeam" class are
     * available to us
     */
    static {
        try {
            BeamAppendHelper.checkAvailable();
            mBeamSendAvailable = true;
        } catch (Throwable t) {
            mBeamSendAvailable = false;
        }
        try {
            BeamReceiveHelper.checkAvailable();
            mBeamReceiveAvailable = true;
        } catch (Throwable t) {
            mBeamReceiveAvailable = false;
        }
    }

    public BeamHelper(Activity activity, String pushPackageName, String pushMessage, boolean isAAR) {
        if (mBeamSendAvailable) {
            mBeam = BeamAppendHelper.createInstance(activity, pushPackageName, pushMessage, isAAR);
        }
    }

    public void closeBeam() {
        if (mBeamSendAvailable) {
            if (mBeam != null) {
                mBeam.closeBeam();
                mBeam = null;
            }
        }
    }

    public boolean isAndroidBeam() {
        boolean isBeam = false;
        if (mBeamSendAvailable) {
            if (mBeam != null) {
                isBeam = mBeam.isAndroidBeam();
            }
        }
        return isBeam;
    }

    public static String getReceivedBeamString(Intent intent) {
        if (mBeamReceiveAvailable) {
            return BeamReceiveHelper.getReceivedBeamString(intent);
        }
        return null;
    }

}
