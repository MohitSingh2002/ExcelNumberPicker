package com.example.excelnumberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.excelnumberpicker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    boolean showColumnNumber = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupButton();

    }

    private void setupButton() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.numberPicker.setVisibility(View.VISIBLE);
                setupNumberPicker(binding.etNumber1.getText().toString().trim(), binding.etNumber2.getText().toString().trim());
            }
        });
    }

    private void setupNumberPicker(String min, String max) {
        binding.numberPicker.setMinValue(Integer.parseInt(min));
        binding.numberPicker.setMaxValue(Integer.parseInt(max));

        binding.numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return showColumnNumber ? value + " - " + getColName(value) : getColName(value);
            }
        });

        View firstItem = binding.numberPicker.getChildAt(0);
        if(firstItem != null) {
            firstItem.setVisibility(View.INVISIBLE);
        }

    }

    public void changeColumnCondition(View view) {
        showColumnNumber = !showColumnNumber;
    }


     private String getColName(int col) {
         StringBuilder builder = new StringBuilder();
         while (col > 0) {
             int remainder = col % 26;
             if (remainder == 0) {
                 builder.append('Z');
                 col = (col / 26) - 1;
             } else {
                 builder.append((char) (64 + remainder));
                 col = col / 26;
             }
         }

         return builder.reverse().toString();
    }

}
