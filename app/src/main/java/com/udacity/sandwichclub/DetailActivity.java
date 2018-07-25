package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView sandwichOrigin;
    private TextView sandwichDescription;
    private TextView sandwichIngredients;
    private TextView sandwichAlsoKnownAs;

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

        populateUI();

        List<String> ingredientsList = sandwich.getIngredients();
        sandwichIngredients.setText("");
        for (int i = 0; i < ingredientsList.size(); i++){
            sandwichIngredients.append(ingredientsList.get(i) + " , ");
        }

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        if(alsoKnownAsList.size()==0){
            sandwichAlsoKnownAs.setText(R.string.not_available);
        }else {
            sandwichAlsoKnownAs.setText("");
            for (int i = 0; i < alsoKnownAsList.size(); i++) {
                sandwichAlsoKnownAs.append(alsoKnownAsList.get(i) + " , ");
            }
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()){

            sandwichOrigin.setText(R.string.not_available);
        }else {
            sandwichOrigin.setText(sandwich.getPlaceOfOrigin());
        }

        sandwichDescription.setText(sandwich.getDescription());

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        sandwichOrigin = (TextView) findViewById(R.id.origin_tv);
        sandwichDescription = (TextView) findViewById(R.id.description_tv);
        sandwichIngredients = (TextView) findViewById(R.id.ingredients_tv);
        sandwichAlsoKnownAs = (TextView) findViewById(R.id.also_known_tv);

    }
}
