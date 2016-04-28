package commdgriest.httpsgithub.wimk;

import android.content.Context;

public class Item {

    /* Item attributes */
    private String name;
    private float quantity;
    private int iconId;
    private int shouldShow;
    private int color;
    private int id;

    /* Construct default item */
    public Item() {
        this.name = "DEFAULT";
        this.quantity = 2;
        this.iconId = 0;
        this.shouldShow = 1;
        this.color = 0;
    }

    /* Getters and Setters */
    public String getName() {
        return this.name;
    }
    public float getQuantity() {
        return this.quantity;
    }
    public int getIconID(){
        return this.iconId;
    }
    public int getID(){
        return this.id;
    }
    public int getShouldShow(){
        return this.shouldShow;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setQuantity(float newQuantity){
        this.quantity = newQuantity;
    }
    public void setIconID(int newIconID){
        this.iconId = newIconID;
    }
    public void setShouldShow( int newVal ){
        this.shouldShow = newVal;
    }

    public void setID(int newID){ this.id = newID; }

    public int getColor(){
        return color;
    }
    public void setColor(int c) { color = c;
    }
}
