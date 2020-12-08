package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String var_name = "name";
    public static Sandwich parseSandwichJson(String json) {
        Sandwich S = null;
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image=null;
        JSONArray alsoKnownAsArray = null;

        List<String> alsoKnownAs = null;
        JSONArray ingredientsArray = null;
        List<String> ingredients = null;


        try {
            JSONObject Sandawich = new JSONObject(json);
          //  JSONObject name = Sandawich.getJSONObject("name");
            JSONObject name = Sandawich.getJSONObject(var_name);
            mainName = name.getString("mainName").toString();

            alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }

            placeOfOrigin = Sandawich.getString("placeOfOrigin").toString();

            description = Sandawich.getString("description");
           // Sandawich.optString("description", "fall_back_string");

            image = Sandawich.getString("image");

            ingredientsArray = Sandawich.getJSONArray("ingredients");
            ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }
            S = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


        } catch (Exception ex) {
        }
        Sandwich Sand = new Sandwich();
        Sand.setMainName(mainName);
        Sand.setAlsoKnownAs(alsoKnownAs);
        Sand.setPlaceOfOrigin(placeOfOrigin);
        Sand.setDescription(description);
        Sand.setIngredients(ingredients);
        Sand.setImage(image);
        return Sand;

    }

}