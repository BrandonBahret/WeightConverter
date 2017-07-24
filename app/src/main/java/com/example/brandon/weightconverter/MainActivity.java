package com.example.brandon.weightconverter;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.brandon.weightconverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding ui;
    Double[] weightValuesConversionInOunces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Resources res = getResources();
        String[] weightOptionValues = res.getStringArray(R.array.weight_options_values);
        weightValuesConversionInOunces = new Double[weightOptionValues.length];
        for (int i = 0; i < weightOptionValues.length; i++) {
            String val = weightOptionValues[i];
            weightValuesConversionInOunces[i] = Double.valueOf(val);
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        ui.weightOptions.setAdapter(weightAdapter);

        ui.weightOptions.setOnItemSelectedListener(spinnerListener);

        ui.weightInput.addTextChangedListener(textWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            AboutActivity.startActivity(this);
        }
        return true;
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) { refreshConversions(); }
    };

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            refreshConversions();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) { }
    };

    private void refreshConversions() {
        String weightStr = ui.weightInput.getText().toString();
        double weight;
        try {
            weight = Double.parseDouble(weightStr);
        } catch (Exception e) {
            weight = 0;
        }

        int unitIndex = ui.weightOptions.getSelectedItemPosition();
        double conversionValue = weightValuesConversionInOunces[unitIndex];

        weight *= conversionValue; // convert weight into ounces

        ((CardView)ui.metricTon.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.green1)
                );
        ui.metricTon.unitName.setText("Metric Ton");
        ui.metricTon.unitValue.setText(String.valueOf(weight / 35274));

        ((CardView)ui.kilogram.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.red1)
                );
        ui.kilogram.unitName.setText("Kilogram");
        ui.kilogram.unitValue.setText(String.valueOf(weight / 35.274));

        ((CardView)ui.gram.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.green2)
                );
        ui.gram.unitName.setText("Gram");
        ui.gram.unitValue.setText(String.valueOf(weight / 0.035274));

        ((CardView)ui.milligram.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.blue1)
                );
        ui.milligram.unitName.setText("Milligram");
        ui.milligram.unitValue.setText(String.valueOf(weight / 3.5274e-5));

        ((CardView)ui.microgram.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.brown1)
                );
        ui.microgram.unitName.setText("Microgram");
        ui.microgram.unitValue.setText(String.valueOf(weight / 3.52740000455e-8));

        ((CardView)ui.imperialTon.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.blue3)
                );
        ui.imperialTon.unitName.setText("Imperial Ton");
        ui.imperialTon.unitValue.setText(String.valueOf(weight / 35840));

        ((CardView)ui.usTon.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.orange1)
                );
        ui.usTon.unitName.setText("Us Ton");
        ui.usTon.unitValue.setText(String.valueOf(weight / 32000));

        ((CardView)ui.stone.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.blue2)
                );
        ui.stone.unitName.setText("Stone");
        ui.stone.unitValue.setText(String.valueOf(weight / 224));

        ((CardView)ui.pound.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.green3)
                );
        ui.pound.unitName.setText("Pound");
        ui.pound.unitValue.setText(String.valueOf(weight / 16));

        ((CardView)ui.ounce.getRoot())
                .setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.teal1)
                );
        ui.ounce.unitName.setText("Ounce");
        ui.ounce.unitValue.setText(String.valueOf(weight));

    }

}
