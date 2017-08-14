package com.androidimptchanges.common_classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by android on 11/12/2016.
 */

public class Constants {

    public static final String BASE_URL = "http://test.alive-mind.com/PetMate/";
    public static final String BASE_URL_LOCAL = "http://192.168.2.3/developer/PetMate/";
    public static final String FILE_PATH = "api/api.php";

    public static final String TEXT_PLAIN = "text/plain";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String IMAGE_TO_UPLOAD = "/sdcard/wallpaper.jpg";

    public static final String ACTIVITY_KEY = "activity_key";
    public static final int SINGLE_PARAM = 0;
    public static final int MULTIPLE_PARAM = 1;
    public static final int MULTI_PART_SINGLE_IMAGE = 2;
    public static final int MULTI_PART_MULTIPLE_IMAGE = 3;


    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    public static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }


}
