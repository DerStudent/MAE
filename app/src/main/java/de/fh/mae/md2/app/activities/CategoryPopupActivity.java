package de.fh.mae.md2.app.activities;

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
import android.widget.Toast;


import de.fh.mae.md2.app.R;

public class CategoryPopupActivity extends AppCompatActivity {
    private EditText mEditCategoryText;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_dialog);

        //ViewModelProviders.of(this).get(CategoryRepository.class);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Resources r = getResources();
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 242, r.getDisplayMetrics());
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width * .95), ViewGroup.LayoutParams.WRAP_CONTENT);

        final ImageButton b = findViewById(R.id.category_add_button_done);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEditCategoryText = findViewById(R.id.category_add_text);

                if(mEditCategoryText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Bezeichnung fehlt!", Toast.LENGTH_LONG).show();
                }
                else if(image == 0){
                    Toast.makeText(getApplicationContext(), "Bildeingabe fehlt!", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), image + " " + mEditCategoryText.getText().toString(), Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("categoryName", mEditCategoryText.getText().toString());
                    Toast.makeText(getApplicationContext(), image + " " + mEditCategoryText.getText().toString(), Toast.LENGTH_LONG).show();
                    editor.putInt("categoryImage", image);
                    editor.commit();
                    finish();
                }
            }
        });
    }

    public void clickEvent(View v) {
        Intent myIntent = new Intent(CategoryPopupActivity.this, ChooseIconActivity.class);
        startActivityForResult(myIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                image = data.getIntExtra("drawableId", 7);
                ((ImageView) findViewById(R.id.category_add_imageView)).setImageResource(image);
            }
        }
    }
}