package de.fh.mae.md2.app.Category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.fh.mae.md2.app.activities.CategoryAddPopupActivity;
import de.fh.mae.md2.app.activities.CategoryEditPopupActivity;
import de.fh.mae.md2.app.entities.Category;

import java.util.List;

import de.fh.mae.md2.app.R;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    private final LayoutInflater mInflater;
    private List<Category> mCategories; // Cached copy of categories

    CategoryListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        if (mCategories != null) {
            final Category current = mCategories.get(position);
            holder.categoryItemView.setText(current.getName());
            holder.categoryImageView.setImageResource(current.getImage());
            holder.view.setOnClickListener(new View.OnClickListener() {  // <--- here
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.view.getContext(), CategoryEditPopupActivity.class);
                    intent.putExtra("categoryId", current.getId());
                    intent.putExtra("categoryName", current.getName());
                    intent.putExtra("categoryImage", current.getImage());
                    intent.putExtra("categoryIsIncomeCategory", current.getIsIncomeCategory());
                    holder.view.getContext().startActivity(intent);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.categoryItemView.setText("No Category");
        }
    }

    void setCategories(List<Category> categories){
        mCategories = categories;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mCategories has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCategories != null)
            return mCategories.size();
        else return 0;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryItemView;
        private final ImageView categoryImageView;
        public View view;


        private CategoryViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            categoryItemView = itemView.findViewById(R.id.text_category_card);
            categoryImageView = itemView.findViewById(R.id.image_category_card);
        }
    }
}