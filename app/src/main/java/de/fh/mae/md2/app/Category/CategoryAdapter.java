//package de.fh.mae.md2.app.Category;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import java.util.List;
//
//import de.fh.mae.md2.app.R;
//
//public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
//    //this context we will use to inflate the layout
//    private Context mCtx;
//
//    //we are storing all the products in a list
////    private List<Category> categoryList;
//
//    //getting the context and product list with constructor
//    public CategoryAdapter(Context mCtx, List<Category> categoryList) {
//        this.mCtx = mCtx;
////        this.categoryList = categoryList;
//    }
//
//    @NonNull
//    @Override
//    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        //inflating and returning our view holder
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.category_card, null);
//        return new CategoryViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
//        //getting the product of the specified position
////        Category category = categoryList.get(position);
////
////        holder.textCategory.setText(category.getName());
////        holder.imageCategory.setImageDrawable(mCtx.getResources().getDrawable(category.getImage()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0; //categoryList.size();
//    }
//
//    class CategoryViewHolder extends RecyclerView.ViewHolder {
////        TextView textCategory;
////        ImageView imageCategory;
//
//        CategoryViewHolder(View itemView) {
//            super(itemView);
////            textCategory = itemView.findViewById(R.id.text_category_card);
////            imageCategory = itemView.findViewById(R.id.image_category_card);
//        }
//    }
//}