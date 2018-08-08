package com.ray.lib.android.util;

import android.content.Context;
import android.media.AudioManager;

/**
 * @author      : leixing
 * @date        : 2017-02-17
 * Email       : leixing1012@qq.com
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
