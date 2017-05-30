package me.wcy.music.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * Created by oreo on 2017/5/30.
 */

public class Httputils {

    public static JSONObject createJSON(HashMap map) {
        JSONObject result = new JSONObject();
        try {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                result.put("" + entry.getKey(), "" + entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "createJSON: createJSON" + e);
        }
        return result;
    }
}
