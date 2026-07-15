package co.ke.eurochef.android.uiclass;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import co.ke.eurochef.android.uiclass.adapters.CatalogAdapter;

public class ListViewActivity extends AppCompatActivity {
ListView list;
Context context;
CatalogAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = findViewById(R.id.list);
        // Create a mixed list of headers and items
        List<String> myData = new ArrayList<>();
        myData.add("[HEADER] Euroken Audio Systems"); // This will be unclickable
        myData.add("EK-607 (20,000W PMPO)");          // Clickable
        myData.add("EK-830 (15,000W Extra Bass)");    // Clickable
        myData.add("[HEADER] Eurochef Cookers");      // This will be unclickable
        myData.add("EGT55-4G-E Standing Cooker");     // Clickable

        // set the adapter and data

        adapter = new CatalogAdapter(this, myData);
        list.setAdapter(adapter);
        //set up the onclick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // This will NEVER trigger for the rows where isEnabled(position) returned false.
                // You don't even need an "if" check here to prevent header clicks.
                String clickedItem = myData.get(position);
                Toast.makeText(context, "You clicked: " + clickedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}