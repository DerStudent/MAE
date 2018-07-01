package de.fh.mae.md2.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import de.fh.mae.md2.app.Category.CategoryImageAdapter;
import de.fh.mae.md2.app.R;

public class ChooseIconActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_choose_icon);

        GridView gridView = findViewById(R.id.add_category_gridview);
        gridView.setAdapter(new CategoryImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ChooseIconActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
