package commdgriest.httpsgithub.wimk;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.LinkedList;
import java.util.List;

/*
    On-Device database for storing the user's inventory
*/

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WIMK_DB";

    // Item table columns names
    public static final String TABLE_ITEMS = "item";
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_quantity = "quantity";
    public static final String KEY_iconID = "iconId";
    public static final String KEY_shouldShow = "shouldShow";
    public static final String KEY_color= "color";

    private static final String[] COLUMNS = {KEY_ID, KEY_name, KEY_quantity, KEY_iconID,
            KEY_shouldShow, KEY_color};

    /* Constructor */
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_INVENTORY = "CREATE TABLE item ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT UNIQUE, "
                + "quantity REAL, "
                + "iconId INTEGER, "
                + "shouldShow INTEGER, "
                + "color INTEGER )";

        db.execSQL(CREATE_TABLE_INVENTORY);
    }

    /*
        Executed only when the database version is updated (drops and creates queries)
        (Should never execute in our app)
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS item");
        /* Create tables again */
        onCreate(db);
    }

    // Based on http://hmkcode.com/android-simple-sqlite-database-tutorial/

    /* Add Item */
    public long addItem(Item item) {
        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_name, item.getName());
        values.put(KEY_quantity, item.getQuantity());
        values.put(KEY_iconID, item.getIconID());
        values.put(KEY_shouldShow, item.getShouldShow());
        values.put(KEY_color, item.getColor());

        // 3. Insert
        long uniqueID = db.insert(TABLE_ITEMS, null, values);

        // 4. Close
        db.close();

        return uniqueID;
    }

    /* Get Item */
    public Item getItem(String name) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ITEMS,   // a. table
                        COLUMNS,        // b. column names
                        " name = ?",    // c. selections
                        new String[]{String.valueOf(name)}, // d. selections args
                        null,   // e. group by
                        null,   // f. having
                        null,   // g. order by
                        null);  // h. limit

        // 3. if we got results get the first one
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

        // 4. Build item object
        Item item = new Item();
        item.setName(cursor.getString(1));
        item.setQuantity(Float.parseFloat(cursor.getString(2)));
        item.setIconID(Integer.parseInt(cursor.getString(3)));
        item.setShouldShow(Integer.parseInt(cursor.getString(4)));
        item.setColor(Integer.parseInt(cursor.getString(5)));

        return item;
        }
        else return new Item();
    }

    /* Get All Items */
    public List<Item> getAllItems() {
        List<Item> items = new LinkedList<Item>();

        // 1. Build the query
        String query = "SELECT * FROM " + TABLE_ITEMS;

        // 2. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Go over each row, build item and add it to list
        Item item = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                item = new Item();
                item.setName(cursor.getString(1));
                item.setQuantity(Float.parseFloat(cursor.getString(2)));
                item.setIconID(Integer.parseInt(cursor.getString(3)));
                item.setShouldShow(Integer.parseInt(cursor.getString(4)));
                item.setColor(Integer.parseInt(cursor.getString(5)));

                // Add item to items
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    /* Update Item */
    public int updateItem(Item item) {

        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", item.getName().toString());
        values.put("quantity", item.getQuantity());
        values.put("iconId", item.getIconID());
        values.put("shouldShow", item.getShouldShow());
        values.put("color", item.getColor());

        // 3. Update row
        int i = db.update(TABLE_ITEMS, values, KEY_ID+" = ?"+item.getID(), null);

        // 4. close
        db.close();

        return i;
    }

    /* Delete Item */
    public void deleteItem(String name) {

        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Delete
        db.delete(TABLE_ITEMS, //table name
                KEY_name+" = ?",  // selections
                new String[] { String.valueOf(name) }); //selections args

        // 3. Close
        db.close();
    }

    /* Search */
    public List<Item> search(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Item> items = new LinkedList<Item>();

        //search query, add % query %
        String searchQuery = "SELECT * FROM " + TABLE_ITEMS +  " WHERE name LIKE '%" + query + "%'";

        Cursor cursor = db.rawQuery(searchQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Item item = new Item();
                item.setID(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setQuantity(Float.parseFloat(cursor.getString(2)));
                item.setIconID(Integer.parseInt(cursor.getString(3)));
                item.setShouldShow(Integer.parseInt(cursor.getString(4)));
                item.setColor(Integer.parseInt(cursor.getString(5)));

                // Add item to items
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    /* Sort */
    public List<Item> sort(int rule){
        SQLiteDatabase db = this.getReadableDatabase();
        String searchQuery = "";

        switch (rule){
            /* Sort by Quantity, low to high */
            case 0:
                searchQuery = "SELECT * FROM " + TABLE_ITEMS +  " ORDER BY quantity ASC";
                break;
            /* Sort by Quantity, high to low */
            case 1:
                searchQuery = "SELECT * FROM " + TABLE_ITEMS +  " ORDER BY quantity DESC";
                break;
            /* Sort Alphabetically, A to Z */
            case 2:
                searchQuery = "SELECT * FROM " + TABLE_ITEMS +  " ORDER BY name COLLATE NOCASE ASC";
                break;
            /* Sort Alphabetically, Z to A */
            case 3:
                searchQuery = "SELECT * FROM " + TABLE_ITEMS +  " ORDER BY name COLLATE NOCASE DESC";
                break;
        }

        List<Item> items = new LinkedList<Item>();
        Cursor cursor = db.rawQuery(searchQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setID(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setQuantity(Float.parseFloat(cursor.getString(2)));
                item.setIconID(Integer.parseInt(cursor.getString(3)));
                item.setShouldShow(Integer.parseInt(cursor.getString(4)));
                item.setColor(Integer.parseInt(cursor.getString(5)));

                // Add item to items
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }
}
