package de.fh.mae.md2.app.Category;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryType;

public class Category {
    private long id;
    private static long idCounter = 0L;

    private String name;

    private int image;

    private int type;

    public Category(String name, int image, int type){
        this.id = idCounter++;
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public long getId() {
        return id;
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

    public int getType(){
        return type;
    }

    public void setType(int income){
        this.type = type;
    }

    public static Category[] populateData() {
        return new Category[] {
                //Ausgaben
                new Category("Körperpflege", R.drawable.ic_category_icon_1, ICategroryType.OUTCOME),
                new Category("Staat", R.drawable.ic_category_icon_2, ICategroryType.OUTCOME),
                new Category("Büro", R.drawable.ic_category_icon_3, ICategroryType.OUTCOME),
                new Category("Kammerjäger", R.drawable.ic_category_icon_4, ICategroryType.OUTCOME),
                new Category("Reparatur", R.drawable.ic_category_icon_5, ICategroryType.OUTCOME),
                new Category("Geburtstag", R.drawable.ic_category_icon_6, ICategroryType.OUTCOME),
                new Category("Kleinigkeiten", R.drawable.ic_category_icon_7, ICategroryType.OUTCOME),
                new Category("Baby", R.drawable.ic_category_icon_8, ICategroryType.OUTCOME),
                new Category("Computer", R.drawable.ic_category_icon_9, ICategroryType.OUTCOME),
                new Category("Schifffahrt", R.drawable.ic_category_icon_10, ICategroryType.OUTCOME),
                new Category("Sport", R.drawable.ic_category_icon_11, ICategroryType.OUTCOME),
                new Category("Kosmetik", R.drawable.ic_category_icon_12, ICategroryType.OUTCOME),
                new Category("Gesundheit", R.drawable.ic_category_icon_13, ICategroryType.OUTCOME),
                new Category("Fitness", R.drawable.ic_category_icon_14, ICategroryType.OUTCOME),
                new Category("Flug", R.drawable.ic_category_icon_15, ICategroryType.OUTCOME),
                new Category("Café", R.drawable.ic_category_icon_16, ICategroryType.OUTCOME),
                new Category("Musik", R.drawable.ic_category_icon_17, ICategroryType.OUTCOME),
                new Category("Lebensmittel", R.drawable.ic_category_icon_19, ICategroryType.OUTCOME),
                new Category("Tanken", R.drawable.ic_category_icon_20, ICategroryType.OUTCOME),
                new Category("Medizin", R.drawable.ic_category_icon_21, ICategroryType.OUTCOME),
                new Category("Hotel", R.drawable.ic_category_icon_22, ICategroryType.OUTCOME),
                new Category("Taxi", R.drawable.ic_category_icon_23, ICategroryType.OUTCOME),
                new Category("Schwimmen", R.drawable.ic_category_icon_24, ICategroryType.OUTCOME),
                new Category("Reisen", R.drawable.ic_category_icon_25, ICategroryType.OUTCOME),
                new Category("Speicher", R.drawable.ic_category_icon_26, ICategroryType.OUTCOME),
                new Category("App-Store", R.drawable.ic_category_icon_27, ICategroryType.OUTCOME),
                new Category("Handy", R.drawable.ic_category_icon_28, ICategroryType.OUTCOME),
                new Category("Rauchen", R.drawable.ic_category_icon_29, ICategroryType.OUTCOME),
                new Category("TV", R.drawable.ic_category_icon_30, ICategroryType.OUTCOME),
                new Category("Augen", R.drawable.ic_category_icon_31, ICategroryType.OUTCOME),
                new Category("Kino", R.drawable.ic_category_icon_32, ICategroryType.OUTCOME),
                new Category("Shopping", R.drawable.ic_category_icon_33, ICategroryType.OUTCOME),
                new Category("VideoOnDemand", R.drawable.ic_category_icon_34, ICategroryType.OUTCOME),
                new Category("Restaurant", R.drawable.ic_category_icon_35, ICategroryType.OUTCOME),
                new Category("Supermarkt", R.drawable.ic_category_icon_36, ICategroryType.OUTCOME),
                new Category("Zugfahrt", R.drawable.ic_category_icon_37, ICategroryType.OUTCOME),
                new Category("Partner", R.drawable.ic_category_icon_38, ICategroryType.OUTCOME),
                new Category("Wohnen", R.drawable.ic_category_icon_39, ICategroryType.OUTCOME),
                new Category("Fast Food", R.drawable.ic_category_icon_40, ICategroryType.OUTCOME),

                //Einnahmen
                new Category("Arbeit", R.drawable.ic_category_icon_18, ICategroryType.INCOME),
                new Category("Pfand", R.drawable.ic_category_icon_33, ICategroryType.INCOME),
                new Category("Geschenk", R.drawable.ic_category_icon_13, ICategroryType.INCOME),
        };
    }
}
