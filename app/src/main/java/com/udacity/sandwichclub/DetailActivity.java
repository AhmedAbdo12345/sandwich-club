package com.udacity.sandwichclub;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.udacity.sandwichclub.model.Sandwich.*;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    String descib=null;
    String placeOrigin=null;
    List<String> AlsoKnow=null;
    List<String> ingredit=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        descib=sandwich.getDescription();
        AlsoKnow=sandwich.getAlsoKnownAs();
        ingredit =sandwich.getIngredients();
        placeOrigin=sandwich.getPlaceOfOrigin();

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage()).placeholder(R.mipmap.ic_launcher)  .error(R.mipmap.ic_launcher_round)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }



    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
//        JsonUtils s=new JsonUtils();
//        Sandwich sd = null;
//JsonUtils.parseSandwichJson("sandwich_details");

        TextView describtion=findViewById(R.id.description_tv);
        TextView ingred=findViewById(R.id.ingredients_tv);
        TextView known=findViewById(R.id.also_known_tv);
        TextView placeOfOrigin=findViewById(R.id.origin_tv);


       describtion.setText(descib);
        placeOfOrigin.setText(placeOrigin);


        for (int i = 0; i < AlsoKnow.size(); i++) {
           // known.append(AlsoKnow.get(i));
            known.append(AlsoKnow.get(i));
            known.append(", ");
        }

        for (int i = 0; i < ingredit.size(); i++) {
           // ingred.append(ingredit.get(i));
            ingred.append(ingredit.get(i));
            ingred.append(", ");
        }


    }
}
