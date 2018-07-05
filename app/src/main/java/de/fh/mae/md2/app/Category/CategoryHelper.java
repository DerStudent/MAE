package de.fh.mae.md2.app.Category;

import java.util.List;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryType;

public class CategoryHelper {
    private static List<Category> list;

    static {
        list = MyPayments.getCategoryList();

        //Ausgaben
        add(new Category("Körperpflege", R.drawable.ic_category_icon_1, ICategroryType.OUTCOME));
        add(new Category("Staat", R.drawable.ic_category_icon_2, ICategroryType.OUTCOME));
        add(new Category("Büro", R.drawable.ic_category_icon_3, ICategroryType.OUTCOME));
        add(new Category("Kammerjäger", R.drawable.ic_category_icon_4, ICategroryType.OUTCOME));
        add(new Category("Reparatur", R.drawable.ic_category_icon_5, ICategroryType.OUTCOME));
        add(new Category("Geburtstag", R.drawable.ic_category_icon_6, ICategroryType.OUTCOME));
        add(new Category("Kleinigkeiten", R.drawable.ic_category_icon_7, ICategroryType.OUTCOME));
        add(new Category("Baby", R.drawable.ic_category_icon_8, ICategroryType.OUTCOME));
        add(new Category("Computer", R.drawable.ic_category_icon_9, ICategroryType.OUTCOME));
        add(new Category("Schifffahrt", R.drawable.ic_category_icon_10, ICategroryType.OUTCOME));
        add(new Category("Sport", R.drawable.ic_category_icon_11, ICategroryType.OUTCOME));
        add(new Category("Kosmetik", R.drawable.ic_category_icon_12, ICategroryType.OUTCOME));
        add(new Category("Gesundheit", R.drawable.ic_category_icon_13, ICategroryType.OUTCOME));
        add(new Category("Fitness", R.drawable.ic_category_icon_14, ICategroryType.OUTCOME));
        add(new Category("Flug", R.drawable.ic_category_icon_15, ICategroryType.OUTCOME));
        add(new Category("Café", R.drawable.ic_category_icon_16, ICategroryType.OUTCOME));
        add(new Category("Musik", R.drawable.ic_category_icon_17, ICategroryType.OUTCOME));
        add(new Category("Lebensmittel", R.drawable.ic_category_icon_19, ICategroryType.OUTCOME));
        add(new Category("Tanken", R.drawable.ic_category_icon_20, ICategroryType.OUTCOME));
        add(new Category("Medizin", R.drawable.ic_category_icon_21, ICategroryType.OUTCOME));
        add(new Category("Hotel", R.drawable.ic_category_icon_22, ICategroryType.OUTCOME));
        add(new Category("Taxi", R.drawable.ic_category_icon_23, ICategroryType.OUTCOME));
        add(new Category("Schwimmen", R.drawable.ic_category_icon_24, ICategroryType.OUTCOME));
        add(new Category("Reisen", R.drawable.ic_category_icon_25, ICategroryType.OUTCOME));
        add(new Category("Speicher", R.drawable.ic_category_icon_26, ICategroryType.OUTCOME));
        add(new Category("App-Store", R.drawable.ic_category_icon_27, ICategroryType.OUTCOME));
        add(new Category("Handy", R.drawable.ic_category_icon_28, ICategroryType.OUTCOME));
        add(new Category("Rauchen", R.drawable.ic_category_icon_29, ICategroryType.OUTCOME));
        add(new Category("TV", R.drawable.ic_category_icon_30, ICategroryType.OUTCOME));
        add(new Category("Augen", R.drawable.ic_category_icon_31, ICategroryType.OUTCOME));
        add(new Category("Kino", R.drawable.ic_category_icon_32, ICategroryType.OUTCOME));
        add(new Category("Shopping", R.drawable.ic_category_icon_33, ICategroryType.OUTCOME));
        add(new Category("VideoOnDemand", R.drawable.ic_category_icon_34, ICategroryType.OUTCOME));
        add(new Category("Restaurant", R.drawable.ic_category_icon_35, ICategroryType.OUTCOME));
        add(new Category("Supermarkt", R.drawable.ic_category_icon_36, ICategroryType.OUTCOME));
        add(new Category("Zugfahrt", R.drawable.ic_category_icon_37, ICategroryType.OUTCOME));
        add(new Category("Partner", R.drawable.ic_category_icon_38, ICategroryType.OUTCOME));
        add(new Category("Wohnen", R.drawable.ic_category_icon_39, ICategroryType.OUTCOME));
        add(new Category("Fast Food", R.drawable.ic_category_icon_40, ICategroryType.OUTCOME));

        //Einnahmen
        new Category("Arbeit", R.drawable.ic_category_icon_18, ICategroryType.INCOME);
        new Category("Pfand", R.drawable.ic_category_icon_33, ICategroryType.INCOME);
        new Category("Geschenk", R.drawable.ic_category_icon_13, ICategroryType.INCOME);
    }

    public static void add(Category category){
        list.add(category);
    }

    public static void delete(Category category){
        list.remove(category);
    }

    public static boolean hasCategories(){
        return !list.isEmpty();
    }

    public static Category getFirstCategory() {
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public static Category getCategoryById(long id) {
        if(list.isEmpty()) {
            return null;
        }

        for(Category c : list) {
            if(c.getId() == id) {
                return c;
            }
        }

        return null;
    }
}
