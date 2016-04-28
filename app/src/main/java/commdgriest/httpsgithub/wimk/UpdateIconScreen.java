package commdgriest.httpsgithub.wimk;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class UpdateIconScreen extends Activity {
    ListView listIconChoices;

    String[] web = {
        "Apples",
        "Bananas",
        "Milk",
        "Cheese",
    };

    Integer[] imageId = {
        R.drawable.apple_by_creative_stall,
        R.drawable.bananas_by_fernando_affonso,
        R.drawable.milk_by_norbert_kucsera,
        R.drawable.cheese_by_edward_boatman,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_icon_screen);

        CustomList adapter = new
                CustomList(UpdateIconScreen.this, web, imageId);
        listIconChoices=(ListView)findViewById(R.id.listIconChoices);
        listIconChoices.setAdapter(adapter);
        listIconChoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UpdateIconScreen.this, "You Clicked " +web[+ position], Toast.LENGTH_SHORT).show();
            }
        });

    }

}
