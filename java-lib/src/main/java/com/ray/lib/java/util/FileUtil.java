package com.ray.lib.java.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * use for operator file
 * Created by leixing on 2016/1/3.
 */
public class FileUtil {

    public static boolean isExist(File file) {
        return file != null && file.exists();
    }

    public static boolean isExistFile(File file) {
        return isExist(file) && file.isFile();
    }

    public static boolean isExistDir(File file) {
        return isExist(file) && file.isDirectory();
    }

    public static List<String> readLinesAsList(File file) {
        List<String> lines = new ArrayList<>();
        if (!isExistFile(file)) {
            return lines;
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(br);
            close(isr);
            close(fis);
        }

        return lines;
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLines(List<String> lines, File file) {
        if (lines == null || lines.size() == 0) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            if (i != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(lines.get(i));
        }

        writeLine(stringBuilder.toString(), file);
    }

    public static void writeLine(String line, File file) {
        if (file == null) {
            return;
        }

        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bw);
            close(osw);
            close(fos);
        }
    }

    public interface ReadLineHandler {
        void readLine(int lineNumber, String line);
    }

    public static void copy(File src, File target) {
        if (src == null || !src.exists() || !src.isFile()) {
            return;
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(target);

            byte[] buffer = new byte[1024 * 4];
            int len = -1;

            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
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

    public static void copyToDir(File src, File dir) {
        if (src == null || !src.exists() || !src.isFile()) {
            return;
        }

        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }

        File target = new File(dir, src.getName());
        copy(src, target);
    }

    public static void replace(File src, File target) {
        if (src == null || !src.exists()) {
            return;
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        if (target.exists()) {
            target.delete();
        }

        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(target);

            byte[] buffer = new byte[1024 * 4];
            int len = -1;

            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
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

    public static void writeInFile(String s, File file) {
        writeInFile(s, "GBK", file);
    }

    public static void writeInFile(String s, String charSet, File file) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, charSet);
            bw = new BufferedWriter(osw);
            bw.append(s);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void appendInFile(String s, File file) {
        appendInFile(s, "GBK", file);
    }

    public static void appendInFile(String s, String charSet, File file) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos, charSet);
            bw = new BufferedWriter(osw);
            bw.append(s);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readLineInFile(int lineNumber, File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return "";
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            String str;
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            int currentLineNumber = 0;
            while ((str = br.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == lineNumber) {
                    return str;
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String readLinesInFile(int startLineNumber, int endLineNumber, File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return "";
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String str;
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            int currentLineNumber = 0;
            while ((str = br.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber >= startLineNumber && currentLineNumber <= endLineNumber) {
                    stringBuilder.append(str);
                    if (currentLineNumber != endLineNumber) {
                        stringBuilder.append("\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static List<String> readLinesInFileAsList(File file) {
        return readLinesInFileAsList(0, Integer.MAX_VALUE, file);
    }

    public static List<String> readLinesInFileAsList(int startLineNumber, int endLineNumber, File file) {
        return readLinesInFileAsList(startLineNumber, endLineNumber, "GBK", file);
    }

    public static List<String> readLinesInFileAsList(int startLineNumber, int endLineNumber, String charSet, File file) {
        return readLinesInFileAsList(true, startLineNumber, endLineNumber, file, charSet, null);
    }

    public static void readLinesInFileWithHandler(File file, ReadLineHandler handler) {
        readLinesInFileWithHandler(0, Integer.MAX_VALUE, file, handler);
    }

    public static void readLinesInFileWithHandler(int startLineNumber, int endLineNumber, File file, ReadLineHandler handler) {
        readLinesInFileAsList(false, startLineNumber, endLineNumber, file, "GBK", handler);
    }

    public static List<String> readLinesInFileAsList(boolean returnList, int startLineNumber, int endLineNumber, File file, String charSet, ReadLineHandler handler) {
        List<String> stringList = new ArrayList<String>();

        if (file == null || !file.exists() || !file.isFile()) {
            return stringList;
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            String str;
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, charSet);
            br = new BufferedReader(isr);
            int currentLineNumber = 0;
            while ((str = br.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber >= startLineNumber && currentLineNumber <= endLineNumber) {
                    if (returnList) {
                        stringList.add(str);
                    }
                    if (handler != null) {
                        handler.readLine(currentLineNumber, str);
                    }
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringList;
    }

    public static String readFileAsOneLine(File file) {
        return readFileAsOneLine(file, "GBK");
    }

    public static String readFileAsOneLine(File file, String charSet) {
        if (file == null || !file.exists() || !file.isFile()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            String str;
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, charSet);
            br = new BufferedReader(isr);

            while ((str = br.readLine()) != null) {
                str = str.replaceAll("\t", " ");
                stringBuilder.append(str.trim());
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    public static String fileToString(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            String str;
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
                stringBuilder.append("\n");
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static String getRawFileName(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return "";
        }

        String name = file.getName();
        String[] split = name.split("\\.");
        return split[0];
    }

    public static List<File> getCurrentDirFiles() {
        return getCurrentDirFiles(new FileFilter() {
            @Override
            public boolean filter(File file) {
                return file != null && file.exists() && file.isFile();
            }
        });
    }

    public static List<File> getCurrentDirSubDirs() {
        return getCurrentDirFiles(new FileFilter() {
            @Override
            public boolean filter(File file) {
                return file != null && file.exists() && file.isDirectory();
            }
        });
    }

    public static List<File> getCurrentDirFiles(FileFilter filter) {
        ArrayList<File> fileList = new ArrayList<>();
        File currentDir = new File("./");
        File[] files = currentDir.listFiles();
        for (File file : files) {
            if (!filter.filter(file)) {
                continue;
            }
            fileList.add(file);
        }

        return fileList;
    }

    public interface FileFilter {
        boolean filter(File file);
    }

    public static String stringListToLines(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirstLine = true;
        for (String line : list) {
            if (!isFirstLine) {
                stringBuilder.append("\n");
            }

            if (isFirstLine) {
                isFirstLine = false;
            }

            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}


