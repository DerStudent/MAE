package de.fh.mae.md2.app.entities;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryStatus;

@Entity(tableName = "categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private int image;

    @NonNull
    @ICategroryStatus
    private int status;

    public Category(@NonNull String name, @NonNull int image, @NonNull int status){
        this.name = name;
        this.image = image;
        this.status = status;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ICategroryStatus
    public int getStatus(){
        return status;
    }

    public void setStatus(@ICategroryStatus int status){
        this.status = status;
    }
    
    public static Category[] populateData() {
        return new Category[] {
                //Ausgaben
            new Category("Körperpflege", R.drawable.ic_category_icon_1, ICategroryStatus.OUT),
            new Category("Staat", R.drawable.ic_category_icon_2, ICategroryStatus.OUT),
            new Category("Büro", R.drawable.ic_category_icon_3, ICategroryStatus.OUT),
            new Category("Kammerjäger", R.drawable.ic_category_icon_4, ICategroryStatus.OUT),
            new Category("Reparatur", R.drawable.ic_category_icon_5, ICategroryStatus.OUT),
            new Category("Geburtstag", R.drawable.ic_category_icon_6, ICategroryStatus.OUT),
            new Category("Kleinigkeiten", R.drawable.ic_category_icon_7, ICategroryStatus.OUT),
            new Category("Baby", R.drawable.ic_category_icon_8, ICategroryStatus.OUT),
            new Category("Computer", R.drawable.ic_category_icon_9, ICategroryStatus.OUT),
            new Category("Schifffahrt", R.drawable.ic_category_icon_10, ICategroryStatus.OUT),
            new Category("Sport", R.drawable.ic_category_icon_11, ICategroryStatus.OUT),
            new Category("Kosmetik", R.drawable.ic_category_icon_12, ICategroryStatus.OUT),
            new Category("Gesundheit", R.drawable.ic_category_icon_13, ICategroryStatus.OUT),
            new Category("Fitness", R.drawable.ic_category_icon_14, ICategroryStatus.OUT),
            new Category("Flug", R.drawable.ic_category_icon_15, ICategroryStatus.OUT),
            new Category("Café", R.drawable.ic_category_icon_16, ICategroryStatus.OUT),
            new Category("Musik", R.drawable.ic_category_icon_17, ICategroryStatus.OUT),
            new Category("Lebensmittel", R.drawable.ic_category_icon_19, ICategroryStatus.OUT),
            new Category("Tanken", R.drawable.ic_category_icon_20, ICategroryStatus.OUT),
            new Category("Medizin", R.drawable.ic_category_icon_21, ICategroryStatus.OUT),
            new Category("Hotel", R.drawable.ic_category_icon_22, ICategroryStatus.OUT),
            new Category("Taxi", R.drawable.ic_category_icon_23, ICategroryStatus.OUT),
            new Category("Schwimmen", R.drawable.ic_category_icon_24, ICategroryStatus.OUT),
            new Category("Reisen", R.drawable.ic_category_icon_25, ICategroryStatus.OUT),
            new Category("Speicher", R.drawable.ic_category_icon_26, ICategroryStatus.OUT),
            new Category("App-Store", R.drawable.ic_category_icon_27, ICategroryStatus.OUT),
            new Category("Handy", R.drawable.ic_category_icon_28, ICategroryStatus.OUT),
            new Category("Rauchen", R.drawable.ic_category_icon_29, ICategroryStatus.OUT),
            new Category("TV", R.drawable.ic_category_icon_30, ICategroryStatus.OUT),
            new Category("Augen", R.drawable.ic_category_icon_31, ICategroryStatus.OUT),
            new Category("Kino", R.drawable.ic_category_icon_32, ICategroryStatus.OUT),
            new Category("Shopping", R.drawable.ic_category_icon_33, ICategroryStatus.OUT),
            new Category("VideoOnDemand", R.drawable.ic_category_icon_34, ICategroryStatus.OUT),
            new Category("Restaurant", R.drawable.ic_category_icon_35, ICategroryStatus.OUT),
            new Category("Supermarkt", R.drawable.ic_category_icon_36, ICategroryStatus.OUT),
            new Category("Zugfahrt", R.drawable.ic_category_icon_37, ICategroryStatus.OUT),
            new Category("Partner", R.drawable.ic_category_icon_38, ICategroryStatus.OUT),
            new Category("Wohnen", R.drawable.ic_category_icon_39, ICategroryStatus.OUT),
            new Category("Fast Food", R.drawable.ic_category_icon_40, ICategroryStatus.OUT),

                //Einnahmen
            new Category("Arbeit", R.drawable.ic_category_icon_18, ICategroryStatus.IN),
            new Category("Pfand", R.drawable.ic_category_icon_33, ICategroryStatus.IN),
            new Category("Geschenk", R.drawable.ic_category_icon_13, ICategroryStatus.IN),
        };
    }
}