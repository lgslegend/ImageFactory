package ru.fuldaros.imgf.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import ru.fuldaros.imgf.util.DeviceUtils;
import ru.fuldaros.imgf.util.FileUtils;

/**
 * Created by fuldaros on 2016/8/14.
 */
public class Debug {
    private static final String TAG = "Debug";
    private static File LOG_FILE;
    private static FileWriter fw;

    private Debug() {

    }

    public static void init(File logFile) {
        LOG_FILE = logFile;
        try {
            fw = new FileWriter(LOG_FILE, false);
            fw.write("Приложение было запущено : " + DeviceUtils.getSystemTime() + "\n");
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void i(CharSequence text) {
        i(TAG, text.toString());
    }

    public static void e(CharSequence text) {
        e(TAG, text.toString());
    }

    public static void i(String TAG, CharSequence text) {
        android.util.Log.i(TAG, text.toString());
        writeLog(TAG, text);
    }

    public static void e(String TAG, CharSequence text) {
        android.util.Log.e(TAG, text.toString());
        writeLog(TAG, text);
    }

    public static void writeLog(String TAG, CharSequence text) {
        if (fw != null) {
            try {
                fw.write(TAG + ": " + text + "\n");
                fw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static StringBuilder getContent() {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream fis = new FileInputStream(LOG_FILE);
            sb.append(FileUtils.readInputStream(fis));
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb;
    }
}
