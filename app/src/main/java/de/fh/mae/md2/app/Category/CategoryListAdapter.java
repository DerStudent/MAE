package de.fh.mae.md2.app.Category;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.fh.mae.md2.app.Main;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.activities.AddTransactionActivity;
import de.fh.mae.md2.app.activities.AddTransactionAmountActivity;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    private final LayoutInflater mInflater;
    private Context context;
    private long categoryId = -1L;

    private List<Category> categoryList;
    View view;

    CategoryListAdapter(Context context, List<Category> categoryList, long categoryId) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryId = categoryId;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.view = mInflater.inflate(R.layout.category_card, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category category = categoryList.get(position);

        if (categoryList != null && !categoryList.isEmpty()) {
            Category current = categoryList.get(position);
            holder.categoryItemView.setText(current.getName());
            holder.categoryImageView.setImageResource(current.getImage());
        } else {
            // Covers the case of data not being ready yet.
            holder.categoryItemView.setText("No Category");
        }

        if(CategoryHelper.hasCategoryId(categoryId)) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent = new Intent(context, Main.class);
//                intent.putExtra("CATEGORY_ANSWER", category.getId());
//                context.startActivity(intent);

//                ((Activity)context).finish();

                    Intent intent = new Intent((Activity) context, AddTransactionActivity.class);
                    intent.putExtra("CATEGORY_ID", category.getId());

                    if (((Activity) context).getComponentName() != null) {
                        ((Activity) context).setResult(2, intent);
                    }

                    ((Activity) context).finish();
                }
            });
        } else {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, category.getName().toString() + " entfernt", Toast.LENGTH_SHORT).show();
                    CategoryHelper.delete(category);
                }
            });
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // mCategories has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (categoryList != null)
            return categoryList.size();
        else {
            return 0;
        }
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryItemView;
        private final ImageView categoryImageView;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            categoryItemView = itemView.findViewById(R.id.text_category_card);
            categoryImageView = itemView.findViewById(R.id.image_category_card);
        }
    }
}
