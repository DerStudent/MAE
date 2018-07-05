package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import de.fh.mae.md2.app.R;

public class ChooseIconActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choose_icon);

        GridView gridView = findViewById(R.id.add_category_gridview);
//        gridView.setAdapter(new CategoryImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("drawableId", view.getContext().getResources().getIdentifier("ic_category_icon_" + (position+1), "drawable", view.getContext().getPackageName()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
