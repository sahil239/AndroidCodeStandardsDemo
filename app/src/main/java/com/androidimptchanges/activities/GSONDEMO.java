package com.androidimptchanges.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidimptchanges.R;
import com.androidimptchanges.model.CornTab;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class GSONDEMO extends AppCompatActivity {


    ArrayList<CornTab> tags = new ArrayList<>();

    String users = "{\"users\": [\n" +
            "    {\n" +
            "      \"57c435300d9babd45378fe63\": {\n" +
            "        \"count\": 1\n" +
            "      },\n" +
            "      \"57c514fa45d606b46b4ef884\": {\n" +
            "        \"count\": 15\n" +
            "      },\n" +
            "      \"57c516c045d606b46b4ef886\": {\n" +
            "        \"count\": 2\n" +
            "      },\n" +
            "      \"57c3fdb9edb53b75927653ff\": {\n" +
            "        \"count\": 7\n" +
            "      },\n" +
            "      \"5835ad4f4c94ba1bc7617749\": {\n" +
            "        \"count\": 1\n" +
            "      }\n" +
            "    }]}";

    String tagsStr = "{\"tags\":[{\"Translation\":{\"count\":4},\"\\u062a\\u0645\":{\"count\":1},\"\\u0627\\u0644\\u062a\\u0633\\u062c\\u064a\\u0644\":{\"count\":1},\"\\u062a\":{\"count\":1},\"test25\":{\"count\":1}}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsondemo);

        try{

            JSONObject jsonObject = new JSONObject(users);
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            JsonParser parser = new JsonParser();
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            JsonElement element = parser.parse(jsonArray.getString(0));
            JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
            for (Map.Entry<String, JsonElement> entry: entries) {
                System.out.println(entry.getKey());
                CornTab cornTab = new CornTab();
                cornTab.tags = entry.getKey();
                cornTab.count = jsonObject1.getJSONObject(entry.getKey()).getInt("count");
                tags.add(cornTab);
            }

            Collections.sort(tags, new Comparator<CornTab>() {
                @Override
                public int compare(CornTab o1, CornTab o2) {

                    if(o1.count > o2.count){
                        return -1;
                    }else if(o1.count < o2.count){
                        return 1;
                    }else{
                        return 0;
                    }


                }
            });



        }catch (Exception e){

        }

    }


}
