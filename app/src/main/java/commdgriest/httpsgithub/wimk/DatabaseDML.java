package commdgriest.httpsgithub.wimk;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import java.util.HashMap;


/**
 * Created by jlisicky on 4/21/16.
 */

//http://stackoverflow.com/questions/24844514/database-in-android-studio

//http://instinctcoder.com/android-studio-sqlite-database-example/
    //TODO need to make a sort sql command method

    //shouldShow might no longer be necessary because DB can query the items

public class DatabaseDML{

    private static final String COL1 = "COL1";

    private Database inventoryDB;

    public DatabaseDML(Context context) {
        inventoryDB = new Database(context);
    }

    public int insertItem(Item item) {
        //Open connection to write data
        SQLiteDatabase db = inventoryDB.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Item.KEY_ID, item.id);
//        values.put(Item.KEY_name, item.name);
//        values.put(Item.KEY_quantity, item.quantity);
//        values.put(Item.KEY_iconID, item.iconId);
//        values.put(Item.KEY_shouldShow, item.shouldShow);
//        values.put(Item.KEY_color, item.color);

        // Inserting Row
//        long newItem_ID = db.insert(Database.FIRST_TABLE_NAME, null, values);
        db.close(); // Closing database connection
//        return (int) newItem_ID;
        return 0;
    }

    public void delete(int item_id) {

        SQLiteDatabase db = inventoryDB.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Item.TABLE, Item.KEY_ID + "= ?", new String[] { String.valueOf(item_id) });
        db.close(); // Closing database connection
    }

    public void update(Item item) {

        SQLiteDatabase db = inventoryDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Item.KEY_ID, item.id);
        values.put(Item.KEY_name, item.name);
        values.put(Item.KEY_quantity, item.quantity);
        values.put(Item.KEY_iconID, item.iconId);
        values.put(Item.KEY_shouldShow, item.shouldShow);
        values.put(Item.KEY_color, item.color);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Item.TABLE, values, Item.KEY_ID + "= ?", new String[]{String.valueOf(item.id)});
        db.close(); // Closing database connection
    }


    //generates arraylist of all items to dispay in VI
    public ArrayList<HashMap<String, String>>  getItemsList() {
        //Open connection to read only
        SQLiteDatabase db = inventoryDB.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Item.KEY_ID + "," +
                Item.KEY_name + "," +
                Item.KEY_quantity + "," +
                Item.KEY_iconID + "," +
                Item.KEY_shouldShow + "," +
                Item.KEY_color +
                " FROM " + Item.TABLE;


        ArrayList<HashMap<String, String>> itemsList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put("id", cursor.getString(cursor.getColumnIndex(Item.KEY_ID)));
                item.put("name", cursor.getString(cursor.getColumnIndex(Item.KEY_name)));
                itemsList.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemsList;

    }

    //to show an item in view item screen after item is clicked in VI
    public Item getItemById(int Id){
        SQLiteDatabase db = inventoryDB.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Item.KEY_ID + "," +
                Item.KEY_name + "," +
                Item.KEY_quantity + "," +
                Item.KEY_iconID + "," +
                Item.KEY_shouldShow + "," +
                Item.KEY_color +
                " FROM " + Item.TABLE +
                " WHERE " +
                Item.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        //int iCount =0;
        Item item = new Item();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                item.id =cursor.getInt(cursor.getColumnIndex(Item.KEY_ID));
                item.name =cursor.getString(cursor.getColumnIndex(Item.KEY_name));
                item.quantity  =cursor.getInt(cursor.getColumnIndex(Item.KEY_quantity));
                item.iconId =cursor.getInt(cursor.getColumnIndex(Item.KEY_iconID));
                //item.shouldShow  =cursor.get(cursor.getColumnIndex(Student.KEY_email));
                item.color =cursor.getInt(cursor.getColumnIndex(Item.KEY_color));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return item;
    }



    /**
    //x is _id
    public List< DatabaseDML > getDataList(int x) {
        List< DatabaseDML > firstTableDataList = new ArrayList< DatabaseDML >();

        String refQuery = "Select" + COL1 + "From " + FIRST_TABLE_NAME + "Where" + _id = x;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(refQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    DatabaseDML itemData = new DatabaseDML();

                    itemData.setId(cursor.getInt(0));

                    itemData.setCol1(cursor.getString(1));
                    itemData.setCol2(cursor.getInt(2));
                    .....
                    .....

                    firstTableDataList.add(itemData);
                } while (cursor.moveToNext());
            }
        } finally {

            db.close();
        }

        Collections.sort(itemDataList);
        return itemDataList;
    }

    public int  addData(DatabaseDML data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL1, data.getCol1());
        values.put(COL2, data.getCol2());
        .....
        .....
        long x=db.insert(FIRST_TABLE_NAME, null, values);
        db.close();
        return (int)x;
    }



    public void updateItemData(DatabaseDML data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL1, data.getCol1());



        db.update(FIRST_TABLE_NAME, values, COL_ID + "=" + data.getId(),    null);
        db.close();
    }
**/


}
