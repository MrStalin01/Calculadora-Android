package com.example.calculadora;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.*;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display = findViewById(R.id.display);

        List<Button> Botones_Numericos = new ArrayList<>(Arrays.asList(
                findViewById(R.id.button17), // 0
                findViewById(R.id.button1),  // 1
                findViewById(R.id.button2),  // 2
                findViewById(R.id.button3),  // 3
                findViewById(R.id.button4),  // 4
                findViewById(R.id.button5),  // 5
                findViewById(R.id.button6),  // 6
                findViewById(R.id.button14), // 7
                findViewById(R.id.button9),  // 8
                findViewById(R.id.button10)  // 9
        ));


        Button Mas = findViewById(R.id.button11);
        Button menos = findViewById(R.id.button7);
        Button Division = findViewById(R.id.button23);
        Button Multiplicacion = findViewById(R.id.button13);
        Button MasoMenos = findViewById(R.id.button16);
        Button AC = findViewById(R.id.button21);
        Button Igual = findViewById(R.id.button19);
        Button Porcentaje = findViewById(R.id.button12);
        Button Borrar = findViewById(R.id.button20);
        Button Coma = findViewById(R.id.button18);

        for (Button boton : Botones_Numericos) {
            boton.setOnClickListener(v -> {
                String numero = ((Button) v).getText().toString();
                appendNumber(numero);
            });
        }


        Coma.setOnClickListener(v -> {
            if (currentInput.isEmpty()) {
                appendNumber("0.");
            } else if (!currentInput.contains(".")) {
                appendNumber(".");
            }
        });


        Mas.setOnClickListener(v -> setOperator("+"));
        menos.setOnClickListener(v -> setOperator("-"));
        Multiplicacion.setOnClickListener(v -> setOperator("×"));
        Division.setOnClickListener(v -> setOperator("÷"));
        Igual.setOnClickListener(v -> calculateResult());
        AC.setOnClickListener(v -> clearAll());

        Borrar.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                if (currentInput.isEmpty()) {
                    display.setText("0");
                    isNewOperation = true;
                } else {
                    display.setText(currentInput);
                }
            }
        });

        MasoMenos.setOnClickListener(v -> {
            if (!currentInput.isEmpty() && !currentInput.equals("0")) {
                if (currentInput.startsWith("-")) {
                    currentInput = currentInput.substring(1);
                } else {
                    currentInput = "-" + currentInput;
                }
                display.setText(currentInput);
            }
        });

        Porcentaje.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                value = value / 100;
                currentInput = formatResult(value);
                display.setText(currentInput);
                isNewOperation = true;
            }
        });
    }


    private void appendNumber(String num) {
        if (isNewOperation) {
            currentInput = num.equals(".") ? "0." : num;
            isNewOperation = false;
        } else {
            currentInput += num;
        }
        display.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = op;
            isNewOperation = true;
            display.setText(currentInput + " " + op + " ");
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        clearAll();
                        return;
                    }
                    break;
            }

            currentInput = formatResult(result);
            display.setText(currentInput);
            operator = "";
            isNewOperation = true;
        }
    }

    private void clearAll() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        isNewOperation = true;
        display.setText("0");
    }

    private String formatResult(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}