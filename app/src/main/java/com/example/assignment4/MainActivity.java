package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    int q=1;
    int price = 0;
    double tip = 0.0;
    private EditText edit;
    private Spinner menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = findViewById(R.id.food);
        final EditText foodPrice = findViewById(R.id.price);
        foodPrice.setEnabled(false);
        foodPrice.setTextColor(Color.parseColor("#000000"));
        TextView showprice = findViewById(R.id.price);
        edit = findViewById(R.id.edit);

        final String[] prices =  getResources().getStringArray(R.array.food_prices);
        SeekBar seek  = findViewById(R.id.seekbar);
        RadioGroup rg = findViewById(R.id.radiog);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(String.valueOf(i));
                q = i+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                price = Integer.parseInt(prices[i]);
                foodPrice.setText(prices[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb1 :
                        tip = 1.10;
                        break;

                    case R.id.rb2 :
                        tip = 1.15;
                        break;

                    case R.id.rb3 :
                        tip = 1.20;
                        break;
                }
                double total = price * q * 1.13 * tip;
                edit.setText(String.valueOf(total));
            }
        });

        Button order = findViewById(R.id.order);
        order.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (((CheckBox)findViewById(R.id.check)).isChecked()){
            Toast.makeText(getApplicationContext(), "Order for "+((TextView)menu.getSelectedView()).getText().toString() + " has been placed 😋", Toast.LENGTH_SHORT).show();
        }
    }
}
