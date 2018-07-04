package de.fh.mae.md2.app.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.entities.Category;
import de.fh.mae.md2.app.viewmodel.CategoryViewModel;

public class CategoryAddPopupActivity extends AppCompatActivity {
    private EditText mEditCategoryText;
    private RadioButton isIncome;
    private RadioButton isOutcome;
    private ImageView imageView;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_add_popup);

        mEditCategoryText = findViewById(R.id.category_add_text);
        isIncome = findViewById(R.id.radioButtonIncome);
        isOutcome = findViewById(R.id.radioButtonOutcome);
        imageView = findViewById(R.id.category_add_imageView);

        //Popup-Fenster-Einstellunugen
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Resources r = getResources();
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 242, r.getDisplayMetrics());
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width * .95), ViewGroup.LayoutParams.WRAP_CONTENT);

        //Categorie-Prefill

        //Ok-Button
        final ImageButton b = findViewById(R.id.category_add_button_done);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(mEditCategoryText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Bezeichnung fehlt!", Toast.LENGTH_LONG).show();
                }
                else if(image == 0){
                    Toast.makeText(getApplicationContext(), "Bildeingabe fehlt!", Toast.LENGTH_LONG).show();
                }
                else {
                    //SharedPreference für die Datenübertragung von Popup nach TabFragment
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("categoryName", mEditCategoryText.getText().toString());
                    editor.putInt("categoryImage", image);
                    editor.putBoolean("categoryIsIncome", isIncome.isChecked());
                    editor.putString("categoryAction", "insert");
                    editor.apply();
                    finish();
                }
            }
        });
    }
    //Event um das ausgewählte Symbol zu übertragen
    public void clickEvent(View v) {
        Intent myIntent = new Intent(CategoryAddPopupActivity.this, ChooseIconActivity.class);
        startActivityForResult(myIntent, 1);
    }

    //überträgt die Daten in ImageView
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                image = data.getIntExtra("drawableId", 7);
                imageView.setImageResource(image);
            }
        }
    }

    private int getIdFromCard(Bundle savedInstanceState){
        int id;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id = 0;
            } else {
                id = extras.getInt("categoryId");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("categoryId");
        }
        return id;
    }
}