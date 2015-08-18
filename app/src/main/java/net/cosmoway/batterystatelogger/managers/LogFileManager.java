package net.cosmoway.batterystatelogger.managers;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileManager {

    private static final String TAG = "LogFileManager";

    public static boolean isWriteableExternalStorage() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static File getFile(String fileName, String dirName) {

        if (!isWriteableExternalStorage()) {
            Log.e(TAG, "Cannot write into external storage.");
            return null;
        }

        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard, dirName);
        dir.mkdirs();
        File file = new File(dir, fileName);

        return file;
    }

    public static void write(String text, String fileName, String dirName) {
        write(text, fileName, dirName, false);
    }

    public static void write(String text, String fileName, String dirName,
                             boolean append) {
        File file = getFile(fileName, dirName);

        if (file != null) {
            write(text, file, append);
        }
    }

    public static void write(String text, File file, boolean append) {
        try {
            FileWriter writer = new FileWriter(file, append);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }
}
