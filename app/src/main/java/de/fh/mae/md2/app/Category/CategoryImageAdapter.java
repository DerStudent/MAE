//package de.fh.mae.md2.app.Category;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//
//import java.util.ArrayList;
//
//import de.fh.mae.md2.app.R;
//
//public class CategoryImageAdapter extends BaseAdapter {
//    private Context mContext;
//    private String filename;
//    private ArrayList<Integer> mIcons = new ArrayList<>();
//
//    private Integer[] Images = {
//        R.drawable.ic_category_icon_1,
//        R.drawable.ic_category_icon_2,
//        R.drawable.ic_category_icon_3,
//        R.drawable.ic_category_icon_4,
//        R.drawable.ic_category_icon_5,
//        R.drawable.ic_category_icon_6,
//        R.drawable.ic_category_icon_7,
//        R.drawable.ic_category_icon_8,
//        R.drawable.ic_category_icon_9,
//        R.drawable.ic_category_icon_10,
//        R.drawable.ic_category_icon_11,
//        R.drawable.ic_category_icon_12,
//        R.drawable.ic_category_icon_13,
//        R.drawable.ic_category_icon_14,
//        R.drawable.ic_category_icon_15,
//        R.drawable.ic_category_icon_16,
//        R.drawable.ic_category_icon_17,
//        R.drawable.ic_category_icon_18,
//        R.drawable.ic_category_icon_19,
//        R.drawable.ic_category_icon_20,
//        R.drawable.ic_category_icon_21,
//        R.drawable.ic_category_icon_22,
//        R.drawable.ic_category_icon_23,
//        R.drawable.ic_category_icon_24,
//        R.drawable.ic_category_icon_25,
//        R.drawable.ic_category_icon_26,
//        R.drawable.ic_category_icon_27,
//        R.drawable.ic_category_icon_28,
//        R.drawable.ic_category_icon_29,
//        R.drawable.ic_category_icon_30,
//        R.drawable.ic_category_icon_31,
//        R.drawable.ic_category_icon_32,
//        R.drawable.ic_category_icon_33,
//        R.drawable.ic_category_icon_34,
//        R.drawable.ic_category_icon_35,
//        R.drawable.ic_category_icon_36,
//        R.drawable.ic_category_icon_37,
//        R.drawable.ic_category_icon_38,
//        R.drawable.ic_category_icon_39,
//        R.drawable.ic_category_icon_40
//    };
//
//    public CategoryImageAdapter(Context c){
//        mContext = c;
//    }
//
//    public void getImages() {
//        for(int i = 1; i <= 40; i++) {
//            filename = "ic_category_icon_" + i;
//            int id = mContext.getResources().getIdentifier(filename, "drawable", mContext.getPackageName());
//            mIcons.add(id);
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return Images.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final float scale = mContext.getResources().getDisplayMetrics().density;
//        int padding = ((int)(8 * scale));
//
//        ImageView imageView = new ImageView(mContext);
//        imageView.setPadding(padding, padding, padding, padding);
//        imageView.setMinimumHeight((int)(70*scale));
//        imageView.setBackgroundColor(Color.WHITE);
//
//        imageView.setImageResource(Images[position]);
//        return imageView;
//    }
//}
