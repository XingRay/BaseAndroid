package com.ray.lib.java.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * use for get data from stream
 */
public class StreamUtil {
    private static final int BUFFER_SIZE = 1024 * 4;

    public static String inputStreamToString(InputStream inputStream) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = -1;
        byte buffer[] = new byte[BUFFER_SIZE];
        try {
            while ((len = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, len);
                out.flush();
            }
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void inputStreamToFile(InputStream is, File target) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(target);
            int len = -1;
            byte buffer[] = new byte[BUFFER_SIZE];

            while ((len = is.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
