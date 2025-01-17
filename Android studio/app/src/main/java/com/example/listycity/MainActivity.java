package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);

        Button addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);
        deleteCityButton.setEnabled(false);

        String[] cities ={"Edmonton", "Paris", "London", "Lahore", "Faisalabad", "Islamabad", "Quetta", "Peshawar", "Attabad", "Chaman"};
        dataList=new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);


        addCityButton.setOnClickListener(v -> {
            EditText inputCity = new EditText(this);

            inputCity.setHint("Enter City Name");


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add City")

                    .setView(inputCity)
                    .setPositiveButton("ADD", (dialog, which) -> {
                        String cityName = inputCity.getText().toString().trim();
                        if (!cityName.isEmpty()) {

                            dataList.add(cityName);
                            cityAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss())
                    .create()

                    .show();
        });

        // Delete city functionality
        cityList.setOnItemClickListener((parent, view, position, id) -> {

            cityList.setSelector(android.R.color.darker_gray); // Highlight selected city
            deleteCityButton.setEnabled(true);


            deleteCityButton.setOnClickListener(v -> {
                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();

                deleteCityButton.setEnabled(false); // Disable button after deletion
            });
        });


    }
}