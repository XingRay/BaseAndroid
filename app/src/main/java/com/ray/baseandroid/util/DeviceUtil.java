package com.ray.baseandroid.util;

import android.content.Context;
import android.media.AudioManager;

/**
 * Author      : leixing
 * Date        : 2017-02-17
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DeviceUtil {
    private DeviceUtil() {
        throw new UnsupportedOperationException();
    }

    public static boolean isSystemSoundOpen(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int ringerMode = audioManager.getRingerMode();
        return ringerMode == AudioManager.RINGER_MODE_NORMAL;
    }
}
