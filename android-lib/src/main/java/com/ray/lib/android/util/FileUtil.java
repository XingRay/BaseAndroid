package com.ray.lib.android.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class FileUtil {

    // 目标路径创建文件夹
    public static boolean copyDir(File dirFrom, File dirTo) {
        if (!dirFrom.isDirectory()) {
            return false;
        }
        File[] files = dirFrom.listFiles();
        for (File f : files) {
            String tempfrom = f.getAbsolutePath();
            // 后面的路径 替换前面的路径名
            String tempto = tempfrom.replace(dirFrom.getAbsolutePath(), dirTo.getAbsolutePath());
            if (f.isDirectory()) {
                File tempFile = new File(tempto);
                tempFile.mkdirs();
                copyDir(f, tempFile);
            } else {
                int endindex = tempto.lastIndexOf("/");
                String mkdirPath = tempto.substring(0, endindex);
                File tempFile = new File(mkdirPath);
                tempFile.mkdirs();// 创建立文件夹
                copyFile(tempfrom, tempto);
            }
        }
        return true;
    }

    public static boolean closeSilently(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean nioTransferCopy(String source, String target) {
        return nioTransferCopy(source, target, true);
    }

    public static boolean nioTransferCopy(String source, String target, boolean overlay) {
        return nioTransferCopy(new File(source), new File(target), overlay);
    }

    public static boolean nioTransferCopy(File source, File target, boolean overlay) {
        if (!source.exists() || (target.exists() && !overlay)) {
            return false;
        }
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            closeSilently(inStream);
            closeSilently(in);
            closeSilently(outStream);
            closeSilently(out);
        }
    }


    /**
     * 文件拷贝方法
     */

    public static boolean copyFile(String from, String to) {
        return copyFile(from, to, true);
    }

    public static boolean copyFile(String from, String to, boolean overlay) {
        return copyFile(new File(from), new File(to), overlay);
    }

    public static boolean copyFile(File from, File to, boolean overlay) {
        if (!from.exists() || (to.exists() && !overlay)) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);

            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            closeSilently(in);
            closeSilently(out);
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        return deleteDirectory(new File(sPath));
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(File dir) {
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File subFile = files[i];
            //删除子文件
            if (subFile.isFile()) {
                subFile.delete();
            } //删除子目录
            else {
                deleteDirectory(subFile.getAbsolutePath());
            }
        }
        //删除当前目录
        return dir.delete();
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean clearDirectory(File dir) {
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File subFile = files[i];
            //删除子文件
            if (subFile.isFile()) {
                subFile.delete();
            } //删除子目录
            else {
                deleteDirectory(subFile.getAbsolutePath());
            }
        }

        return true;
    }

}
