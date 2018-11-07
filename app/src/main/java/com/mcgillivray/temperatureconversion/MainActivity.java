package com.mcgillivray.temperatureconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float convertTemperature;
    float temperature;
    int radioBtnCel = 2131165219; //id of celsius radio button
    int radioBtnFar = 2131165220; //id of fahrenheit radio button

    public void convertTmp(View view) {

        //get user input
        EditText userInput = (EditText) findViewById(R.id.tmpInput);

        //check for empty user input - if empty setting temperature to 0
        if (TextUtils.isEmpty(userInput.getText().toString())) {
            temperature = 0f;
            makeToast(getString(R.string.tstNothingSet));
        } else {
            temperature = Float.valueOf(userInput.getText().toString());
        }

        //check which radio button is selected
        RadioGroup grpBtn = (RadioGroup) findViewById(R.id.grpRadioBtn);
        int radioBtnId = grpBtn.getCheckedRadioButtonId();
        if (radioBtnId == radioBtnFar) {
            convertFahrenheit(temperature);
        } else {
            convertCelsius(temperature);
        }

        //display results showing the degree symbol and measurement
        if (radioBtnId == radioBtnFar) {
            setContentView(R.layout.activity_main);
            TextView convertedTmp = (TextView) findViewById(R.id.convertedTmp);
            convertedTmp.setText((String.format("%.2f", convertTemperature)) + (char) 0x00B0 + " F");
        } else {
            setContentView(R.layout.activity_main);
            TextView convertedTmp = (TextView) findViewById(R.id.convertedTmp);
            convertedTmp.setText((String.format("%.2f", convertTemperature)) + (char) 0x00B0 + " C");
        }
    }

    //method to convert to Celsius
    private float convertCelsius(float convertFahrenheit) {
        convertTemperature = ((temperature - 32) * 5 / 9);
        return convertTemperature;
    }

    //method to convert to Fahrenheit
    private float convertFahrenheit(float convertCelsius) {
        convertTemperature = (((temperature * 9) / 5) + 32);
        return convertTemperature;
    }

    //method for Toast messages
    private void makeToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
