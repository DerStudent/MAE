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

}
