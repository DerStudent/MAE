package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.dao.CategoryDao;

public class CategoryPopupActivity extends AppCompatActivity {

    private CategoryDao cd;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "MyPaymentsDatabase").build();

        cd = db.categoryDao();*/

        setContentView(R.layout.category_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Resources r = getResources();
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 242, r.getDisplayMetrics());
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width * .95), ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageButton b = findViewById(R.id.category_add_button_done);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //cd.insertCategory(new Category(((EditText)findViewById(R.id.category_add_text)).getText().toString(), image, true));
                finish();
            }
        });
    }

    public void clickEvent(View v)
    {
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