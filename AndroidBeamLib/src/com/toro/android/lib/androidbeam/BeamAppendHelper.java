
package com.toro.android.lib.androidbeam;

import android.app.Activity;
import android.os.Build;

public class BeamAppendHelper {

    /* class initialization fails when this throws an exception */
    static {
        try {
            Class.forName("com.toro.android.lib.androidbeam.Beam");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static BeamAppendHelper createInstance(Activity activity, String pushPackageName, String pushMessage, boolean isAAR) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return new BeamAppendHelper();
        }
        return new Beam(activity, pushPackageName, pushMessage, isAAR);
    }

    protected BeamAppendHelper() {
    }

    public boolean isAndroidBeam() {
        return false;
    }

    public void closeBeam() {
    }

    /* calling here forces class initialization */
    public static void checkAvailable() {
    }
}
