package de.fh.mae.md2.app.entities;

import android.arch.persistence.room.*;

import de.fh.mae.md2.app.R;

@Entity(tableName = "categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int image;

    private boolean isIncomeCategory;

    public Category(String name, int image, boolean isIncomeCategory){
        this.name = name;
        this.image = image;
        this.isIncomeCategory = isIncomeCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsIncomeCategory() {
        return isIncomeCategory;
    }

    public void setIsIncomeCategory(boolean i) {
        this.isIncomeCategory = i;
    }
    
    public static Category[] populateData() {
        return new Category[] {
                //Ausgaben
            new Category("Körperpflege", R.drawable.ic_category_icon_1, false),
            new Category("Staat", R.drawable.ic_category_icon_2, false),
            new Category("Büro", R.drawable.ic_category_icon_3, false),
            new Category("Kammerjäger", R.drawable.ic_category_icon_4, false),
            new Category("Reparatur", R.drawable.ic_category_icon_5, false),
            new Category("Geburtstag", R.drawable.ic_category_icon_6, false),
            new Category("Kleinigkeiten", R.drawable.ic_category_icon_7, false),
            new Category("Baby", R.drawable.ic_category_icon_8, false),
            new Category("Computer", R.drawable.ic_category_icon_9, false),
            new Category("Schifffahrt", R.drawable.ic_category_icon_10, false),
            new Category("Sport", R.drawable.ic_category_icon_11, false),
            new Category("Kosmetik", R.drawable.ic_category_icon_12, false),
            new Category("Gesundheit", R.drawable.ic_category_icon_13, false),
            new Category("Fitness", R.drawable.ic_category_icon_14, false),
            new Category("Flug", R.drawable.ic_category_icon_15, false),
            new Category("Café", R.drawable.ic_category_icon_16, false),
            new Category("Musik", R.drawable.ic_category_icon_17, false),
            new Category("Lebensmittel", R.drawable.ic_category_icon_19, false),
            new Category("Tanken", R.drawable.ic_category_icon_20, false),
            new Category("Medizin", R.drawable.ic_category_icon_21, false),
            new Category("Hotel", R.drawable.ic_category_icon_22, false),
            new Category("Taxi", R.drawable.ic_category_icon_23, false),
            new Category("Schwimmen", R.drawable.ic_category_icon_24, false),
            new Category("Reisen", R.drawable.ic_category_icon_25, false),
            new Category("Speicher", R.drawable.ic_category_icon_26, false),
            new Category("App-Store", R.drawable.ic_category_icon_27, false),
            new Category("Handy", R.drawable.ic_category_icon_28, false),
            new Category("Rauchen", R.drawable.ic_category_icon_29, false),
            new Category("TV", R.drawable.ic_category_icon_30, false),
            new Category("Augen", R.drawable.ic_category_icon_31, false),
            new Category("Kino", R.drawable.ic_category_icon_32, false),
            new Category("Shopping", R.drawable.ic_category_icon_33, false),
            new Category("VideoOnDemand", R.drawable.ic_category_icon_34, false),
            new Category("Restaurant", R.drawable.ic_category_icon_35, false),
            new Category("Supermarkt", R.drawable.ic_category_icon_36, false),
            new Category("Zugfahrt", R.drawable.ic_category_icon_37, false),
            new Category("Partner", R.drawable.ic_category_icon_38, false),
            new Category("Wohnen", R.drawable.ic_category_icon_39, false),
            new Category("Fast Food", R.drawable.ic_category_icon_40, false),

                //Einnahmen
            new Category("Arbeit", R.drawable.ic_category_icon_18, true),
            new Category("Pfand", R.drawable.ic_category_icon_33, true),
            new Category("Geschenk", R.drawable.ic_category_icon_13, true),
        };
    }
}