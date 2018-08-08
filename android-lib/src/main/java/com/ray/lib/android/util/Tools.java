package com.ray.lib.android.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class Tools {
    public static final int STATE_UPDATE_NULL = 0;
    public static final int STATE_UPDATE_TRUE = 1;
    public static final int STATE_UPDATE_FALSE = 2;

    /**
     * 获取本地文件的md5值
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static String getMd5ByFile(String filePath) throws FileNotFoundException {
        String value = null;
        File file = new File(filePath);
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }


    /**
     * 删除指定文件
     *
     * @param name
     */
    public static void deleteFile(String name) {
        File file = new File(name);
        if (file.isAbsolute())
            file.delete();
    }

    /**
     * 删除文件夹里面的内容
     *
     * @param folderName
     * @return
     */
    public static boolean delAllFile(String folderName) {
        try {
            File file = new File(folderName);
            if (!file.exists()) {
                return false;
            }
            if (!file.isDirectory()) {
                return false;
            }
            boolean flag = false;
            String[] tempList = file.list();
            File temp;
            for (int i = 0; i < tempList.length; i++) {
                if (folderName.endsWith(File.separator)) {
                    temp = new File(folderName + tempList[i]);
                } else {
                    temp = new File(folderName + File.separator + tempList[i]);
                }
                if (temp.isFile()) {
                    temp.delete();
                }
                if (temp.isDirectory()) {
                    delAllFile(folderName + "/" + tempList[i]);// 先删除文件夹里面的文件
                    delFolder(folderName + "/" + tempList[i]);// 再删除空文件夹
                    flag = true;
                }
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除整个文件夹
     *
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Is it working time
     *
     * @param beginTime
     * @param endTime
     * @param currentTimeMillis
     * @return if true, means currentTime is on workTime
     */
    private static boolean isWorkTime(String beginTime, String endTime, long currentTimeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        sdf.applyPattern("HH:mm:ss");
        String currentTime = sdf.format(currentTimeMillis);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(beginTime));
            c2.setTime(df.parse(endTime));
            c3.setTime(df.parse(currentTime));
        } catch (java.text.ParseException e) {
            return false;
        }
        int result = c1.compareTo(c2);
        if (result == 0) {
            // begin time equal end time
            return false;
        } else if (result < 0) {
            // beginTime less than endTime
            // beginTime equals currentTime
            int result1 = c1.compareTo(c3);
            // endTime equals currentTime
            int result2 = c2.compareTo(c3);
            // beginTime less than currentTime
            return result1 < 0 && result2 > 0;
        } else {
            return false;
        }
    }

    /**
     * 获取apk文件版本号
     *
     * @param context
     * @param FilePath
     * @return
     */
    public static String getApkVersion(Context context, String FilePath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(FilePath, PackageManager.GET_ACTIVITIES);
        // 得到版本信息
        return (info == null) ? "" : info.versionName;
    }

    /**
     * 获取照片旋转度数
     *
     * @param filepath
     * @return
     */
    private static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    public static void setPictureDegreeZero(String srcPath) {
        File file = new File(srcPath);

        int degree = 90;
        if (degree != 0 && degree < 360) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                    matrix, true);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                /*
                 * 位图格式为JPEG 参数二位 0-100 的数值，100为最大值，表示无损压缩
				 * 参数三传入一个输出流对象，将图片数据输出到流中
				 */
                bitmap.compress(CompressFormat.JPEG, 50, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bitmap.recycle();
            }
        }
    }

    /**
     * 改变拍完生成的照片的尺寸
     *
     * @param srcPath
     */
    public static Bitmap getImageByPath(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
//		newOpts.inJustDecodeBounds = true;
//		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
//		int w = newOpts.outWidth;
//		int h = newOpts.outHeight;
//		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//		float hh = 800f;// 这里设置高度为800f
//		float ww = 600f;// 这里设置宽度为480f
//		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//		int zoom = 1;// zoom=1表示不缩放
//		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
//			zoom = (int) (newOpts.outWidth / ww);
//		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
//			zoom = (int) (newOpts.outHeight / hh);
//		}
//		if (zoom <= 0)
//			zoom = 1;
//		newOpts.inSampleSize = zoom;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // 计算角度,更改照片
        int degree = getExifOrientation(srcPath);
        if (degree != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                    matrix, true);
        }
        return bitmap;
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return -1表示不存在
     */
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file.length();
        }
        return -1;
    }

    /**
     * float类型数字小数点后截取位数
     *
     * @param f   准备处理数字
     * @param num 截取位数
     * @return
     */
    public static float getFloatRound(float f, int num) {
        BigDecimal b = new BigDecimal(f);
        float f1 = b.setScale(num, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    public static boolean getAllFileSize(String path, long[] totalSize) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                totalSize[0] += Tools.getFileSize(temp.toString());
            }
            if (temp.isDirectory()) {
                getAllFileSize(path + "/" + tempList[i], totalSize);// 先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return int[]{screenWidth,screenHeight}
     */
    public static int[] getScreenSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth, screenHeight;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        return new int[]{screenWidth, screenHeight};
    }

    /**
     * 获取DisplayMetrics对象
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    // 读取IMEI
    public static String getIMEI(Context activity) {
        TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        return imei;
    }

    // 检测网络是否可用
    public static boolean hasNetwork(Context activity) {
        ConnectivityManager cm = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

    // 判断是否wifi联网
    public static boolean isWifi(Context activity) {
        String type = getNetworkType(activity);
        return type.equals("NETTYPE_WIFI");
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static String getNetworkType(Context activity) {
        String netType = "none";
        ConnectivityManager connectivityManager = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if ((extraInfo != null) && (!extraInfo.equals(""))) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = "NETTYPE_CMNET";
                } else {
                    netType = "NETTYPE_CMWAP";
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = "NETTYPE_WIFI";
        }
        return netType;
    }

    // 判断是不是合法的电话号码，只是简单判断了数字，+，-，空格
    public static boolean isSimplePhone(String phoneNumber) {
        boolean isValid = false;
        String expression = "^[0-9+\\- ]+$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    // 检查是不是手机号码
    public static boolean isTelPhone(String paramString) {
        return Pattern
                .compile(
                        "^13[0-9]{1}[0-9]{8}$|14[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|16[0-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}$")
                .matcher(paramString).matches();
    }

    /**
     * @param telephone 输入的号码
     * @return 是否是座机或手机号码
     */
    public static boolean isPhoneNumber(String telephone) {
        return !(!isTelPhone(telephone) && !isLandLine(telephone) && !is4or8HotLine(telephone));
    }

    /**
     * @param phoneNumber 座机电话号码
     * @return 是否是座机号码
     */
    public static boolean isLandLine(String phoneNumber) {
        String regex = "^(\\d{3,4}|\\d{3,4}-)?\\d{7,8}$";
        return Pattern.compile(regex).matcher(phoneNumber).matches();
    }

    /**
     * @param phoneNumber
     * @return 是否是400和800的电话
     */
    public static boolean is4or8HotLine(String phoneNumber) {
        String regex = "^[48]00-?\\d{4}-?\\d{3}$";
        return Pattern.compile(regex).matcher(phoneNumber).matches();
    }

    // 检测是不是邮箱
    public static boolean isEmail(String paramString) {
        return Pattern
                .compile(
                        "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
                .matcher(paramString).matches();
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据文件绝对路径获取文件名
     */
    public static String getFileName(String filePath) {
        if (isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 取SD卡路径，不带最后分隔符的; 如没有sd卡，返回data/data/files路径; 如有异常，返回空
     *
     * @return
     */
    public static String getSdRootPath(Context context) {
        File sdDir = null;
        try {
            boolean sdCardExist = Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
            if (sdCardExist) {
                sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
            } else {
                sdDir = context.getFilesDir();
            }
            if (sdDir != null) {
                return sdDir.getPath();
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 获得项目在sd卡上的根目录, 不带结束符的
    public static String getModDir(Context context) {
        String strDir = getSdRootPath(context) + File.separator + "hecom";
        File cacheDir = new File(strDir);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return strDir;
    }

    /**
     * 发起自定义notification
     *
     * @param context
     * @param ticker         滚动的文字
     * @param contentTitle   文字标题
     * @param contentText    文字内容
     * @param pintent        点击意图
     * @param drawableId     展示图标
     * @param notificationID
     * @param soundUri       声音
     * @param priority       优先级
     */
    public static void setCustomNotification(Context context, String ticker, String contentTitle,
                                             String contentText, PendingIntent pintent,
                                             int drawableId, int notificationID,
                                             Uri soundUri, int priority) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(ticker)) {
            ticker = "";
        }
        if (TextUtils.isEmpty(contentTitle)) {
            contentTitle = "";
        }
        if (TextUtils.isEmpty(contentText)) {
            contentText = "";
        }
        try {
            NotificationManager mNotificationManager;
            NotificationCompat.Builder notificationBuilder;
            if (notificationID == -1) {
                notificationID = 0x10000;
            }
            mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationBuilder = new NotificationCompat.Builder(context);
            notificationBuilder.setSmallIcon(drawableId);
            notificationBuilder.setTicker(ticker);
            notificationBuilder.setContentTitle(contentTitle);
            notificationBuilder.setContentText(contentText);
            notificationBuilder.setAutoCancel(true);
            if (soundUri != null) {
                notificationBuilder.setSound(soundUri);
            } else {
                notificationBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
            }
            notificationBuilder.setPriority(priority);
            if (pintent != null) {
                notificationBuilder.setContentIntent(pintent);
            }
            mNotificationManager.notify(notificationID, notificationBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起notification
     *
     * @param context
     * @param ticker
     * @param contentTitle
     * @param contentText
     * @param pintent      如果点击没有动作，可以设为null
     * @param drawableId   R.drawabe.*
     */
    public static void setNotification(Context context, String ticker, String contentTitle,
                                       String contentText, PendingIntent pintent, int drawableId, int notificationID) {
        if (context == null) {
            return;
        }
        try {
            NotificationManager mNotificationManager;
            NotificationCompat.Builder notificationBuilder;
            if (notificationID == -1) {
                notificationID = 0x10000;
            }
            mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationBuilder = new NotificationCompat.Builder(context);
            notificationBuilder.setSmallIcon(drawableId);
            notificationBuilder.setTicker(ticker);
            notificationBuilder.setContentTitle(contentTitle);
            notificationBuilder.setContentText(contentText);
            notificationBuilder.setAutoCancel(true);
            notificationBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
            // notificationBuilder.setNumber(1);
            if (pintent != null) {
                notificationBuilder.setContentIntent(pintent);
            }
            mNotificationManager.notify(notificationID, notificationBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 获取程序包名
     *
     * @param cx
     * @return 程序包名
     */
    public static String getPackageName(Context cx) {
        String packageName = ""; // 初始化
        PackageManager packageManager = cx.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(cx.getPackageName(), 0);
            packageName = packageInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return packageName;
    }

    /**
     * 判断APK是否注册某项权限
     *
     * @param cx
     * @param permissionName 权限名称
     * @return
     */
    public static boolean checkPermission(Context cx, String permissionName) {
        try {
            PackageManager pm = cx.getPackageManager();
            int res = pm.checkPermission(permissionName, getPackageName(cx));
            return res != PackageManager.PERMISSION_DENIED;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 压缩图片（质量）大小
     *
     * @param image
     * @param Factor 比例缩放倍数
     * @return
     */
    public static Bitmap compressImage(Bitmap image, int Factor) {
        ByteArrayOutputStream output = null;
        ByteArrayInputStream input = null;
        int options = 100;
        try {
            output = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, output);
            while (output.toByteArray().length / 1024 > 1024) { // 如果大于一兆，压缩
                output.reset();
                if (options > 0) {
                    options = options - 20;
                } else {
                    break;
                }
                image.compress(Bitmap.CompressFormat.JPEG, options, output);
            }
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            input = new ByteArrayInputStream(output.toByteArray());
            newOpts.inJustDecodeBounds = true;
            image = BitmapFactory.decodeStream(input, null, newOpts);
            newOpts.inJustDecodeBounds = false;
            newOpts.inSampleSize = Factor;// 设置缩放比例
            newOpts.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            input = new ByteArrayInputStream(output.toByteArray());
            image = BitmapFactory.decodeStream(input, null, newOpts);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                output = null;
            }
            if (input != null) {
                try {
                    input.close();
                    input = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return image;
    }

    /**
     * 获取当前时间long值
     *
     * @return
     */
    public static long getCurrentLongTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    /**
     * 获取今天0点的毫秒数
     *
     * @return
     */
    public static long getTodayMills() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取今天24点的毫米数
     *
     * @return
     */
    public static long getTodayMillsEnd() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }


    /**
     * 本周周一0点的毫米数
     *
     * @return
     */
    public static long getWeekMills() {

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime().getTime();
    }

    /**
     * 本周日24点毫秒数
     */

    public static long getWeekMillsEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(getWeekMills()));
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime().getTime();
    }

    /**
     * 本月第一天0点毫秒数
     */
    public static long getMonthMills() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime().getTime();
    }

    /**
     * 本月最后一天毫秒数
     */

    public static long getMonthMillsEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime().getTime();
    }

    /**
     * 上月第一天0点毫秒数
     */
    public static long getLastMonthMills() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(getMonthMills()));
        cal.add(Calendar.MONTH, -1);
        return cal.getTime().getTime();
    }

    /**
     * 获取给定日期的开始毫秒数
     *
     * @param date
     * @return
     */
    public static long getDayStart(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取给定日期的结束毫秒数
     *
     * @param date
     * @return
     */
    public static long getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }


    /**
     * 获取给定日期的开始毫秒数
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static long getDayStart(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return getDayStart(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取给定日期的结束毫秒数
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static long getDayEnd(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return getDayEnd(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 是否开启定位
     *
     * @param context
     * @return
     */
    public static boolean isLocationEnabled(Context context) {
        if (!checkPermission(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return false;
        }
        LocationManager locationManager = (LocationManager) context.
                getSystemService(Context.LOCATION_SERVICE);
        try {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取应用名称
     *
     * @return
     */
    public static String getAppName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo.loadLabel(packageManager).toString();
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }

    /**
     * 显示软键盘
     * imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
     * boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
     */
    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 从map中获取value,因为Gson的自动转化原因
     *
     * @param map
     * @param key
     * @return
     */
    public static String getSimplyValue(Map map, Object key) {
        if (key == null) {
            return "";
        }
        if (map.get(key) == null) {
            return "";
        }
        String strValue = map.get(key).toString();
        try {
            Double doubleValue = Double.valueOf(strValue);
            return doubleValue.longValue() + "";
        } catch (Exception e) {
        }
        return strValue;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        boolean flag;
        try {
            String check = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
