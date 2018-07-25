package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject name = null;
        try {
            name = jsonObject.getJSONObject("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String mainName = null;
        try {
            mainName = name.getString("mainName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<String> alsoKnownAsList = new ArrayList<String>();
        JSONArray alsoKnownAs = null;
        try {
            alsoKnownAs = name.getJSONArray("alsoKnownAs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (alsoKnownAs != null)
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                try {
                    alsoKnownAsList.add(alsoKnownAs.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        String placeOfOrigin = null;
        try {
            placeOfOrigin = jsonObject.getString("placeOfOrigin");
        } catch (JSONException e) {
            e.printStackTrace();
        }

       String description = null;
        try {
            description = jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String image = null;
        try {
            image = jsonObject.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<String> ingredientsList = new ArrayList<String>();
        JSONArray ingredients = null;
        try {
            ingredients = jsonObject.getJSONArray("ingredients");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(ingredients != null)
            for (int i = 0; i < ingredients.length(); i++) {
                try {
                    ingredientsList.add(ingredients.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        return new Sandwich(mainName,alsoKnownAsList,placeOfOrigin,description,image,ingredientsList);
    }


}
