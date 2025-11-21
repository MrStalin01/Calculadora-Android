package com.example.calculadora;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Double numero1, numero2, resultado;
    String operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button17).setOnClickListener(this::onClickButtonCero);
        findViewById(R.id.button1).setOnClickListener(this::onClickButtonUno);
        findViewById(R.id.button9).setOnClickListener(this::onClickButtonDos);
        findViewById(R.id.button10).setOnClickListener(this::onClickButtonTres);
        findViewById(R.id.button14).setOnClickListener(this::onClickButtonCuatro);
        findViewById(R.id.button5).setOnClickListener(this::onClickButtonCinco);
        findViewById(R.id.button6).setOnClickListener(this::onClickButtonSeis);
        findViewById(R.id.button4).setOnClickListener(this::onClickButtonSiete);
        findViewById(R.id.button3).setOnClickListener(this::onClickButtonOcho);
        findViewById(R.id.button2).setOnClickListener(this::onClickButtonNueve);


        findViewById(R.id.button18).setOnClickListener(this::onClickButtonPunto);
        findViewById(R.id.button11).setOnClickListener(this::onClickSuma);
        findViewById(R.id.button7).setOnClickListener(this::onClickResta);
        findViewById(R.id.button13).setOnClickListener(this::onClickMultiplicacion);
        findViewById(R.id.button23).setOnClickListener(this::onClickDivision);
        findViewById(R.id.button19).setOnClickListener(this::onClickIgual);
        findViewById(R.id.button21).setOnClickListener(this::onClickLimpia);
        findViewById(R.id.button20).setOnClickListener(this::onClickBorrar);
        findViewById(R.id.button12).setOnClickListener(this::onClickPorcentaje);
        findViewById(R.id.button16).setOnClickListener(this::onClickCambiarSigno);
    }


    public void onClickButtonUno(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "1");
    }

    public void onClickButtonDos(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "2");
    }

    public void onClickButtonTres(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "3");
    }

    public void onClickButtonCuatro(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "4");
    }

    public void onClickButtonCinco(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "5");
    }

    public void onClickButtonSeis(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "6");
    }

    public void onClickButtonSiete(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "7");
    }

    public void onClickButtonOcho(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "8");
    }

    public void onClickButtonNueve(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "9");
    }

    public void onClickButtonCero(View miView) {
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText(pantalla.getText() + "0");
    }

    public void onClickButtonPunto(View miView) {
        TextView pantalla = findViewById(R.id.display);
        String valor = pantalla.getText().toString();
        if (!valor.contains(".")) {
            pantalla.setText(valor + ".");
        }
    }

    public void onClickOperacionCapturaNumero1(View miView) {
        TextView pantalla = findViewById(R.id.display);
        String valor = pantalla.getText().toString();
        if (!valor.isEmpty() && !valor.equals("-")) {
            try {
                numero1 = Double.parseDouble(valor);
                pantalla.setText("");
            } catch (NumberFormatException e) {
                pantalla.setText("");
            }
        }
    }

    public void onClickSuma(View miView) {
        operador = "+";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickResta(View miView) {
        operador = "-";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickMultiplicacion(View miView) {
        operador = "*";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickDivision(View miView) {
        operador = "/";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickIgual(View miView) {
        TextView pantalla = findViewById(R.id.display);
        String valor = pantalla.getText().toString();

        if (numero1 == null || operador == null || valor.isEmpty() || valor.equals("-")) {
            return;
        }

        try {
            numero2 = Double.parseDouble(valor);

            if (operador.equals("+")) {
                resultado = numero1 + numero2;
            } else if (operador.equals("-")) {
                resultado = numero1 - numero2;
            } else if (operador.equals("*")) {
                resultado = numero1 * numero2;
            } else if (operador.equals("/")) {
                if (numero2 == 0) {
                    pantalla.setText("");
                }
                resultado = numero1 / numero2;
            }

            pantalla.setText(resultado.toString());
            numero1 = resultado;
            operador = null;
        } catch (NumberFormatException e) {
            pantalla.setText("0");
        }
    }

    public void onClickLimpia(View miView) {
        numero1 = 0.0;
        numero2 = 0.0;
        operador = null;
        TextView pantalla = findViewById(R.id.display);
        pantalla.setText("0");
    }

    public void onClickBorrar(View miView) {
        TextView pantalla = findViewById(R.id.display);
        String valor = pantalla.getText().toString();
        if (valor.length() > 0 && !valor.equals("0")) {
            pantalla.setText(valor.substring(0, valor.length() - 1));
        }
        if (pantalla.getText().toString().isEmpty()) {
            pantalla.setText("");
        }
    }

    public void onClickPorcentaje(View miView) {
        TextView pantalla = findViewById(R.id.display);
        String valor = pantalla.getText().toString();
        if (!valor.isEmpty() && !valor.equals("-")) {
            try {
                double numero = Double.parseDouble(valor);
                pantalla.setText(String.valueOf(numero / 100.0));
            } catch (NumberFormatException e) {
                pantalla.setText("");
            }
        }
    }

    public void onClickCambiarSigno(View miView) {
        TextView pantalla = findViewById(R.id.display);
        String valor = pantalla.getText().toString();
        if (!valor.isEmpty() && !valor.equals("0")) {
            if (valor.startsWith("-")) {
                pantalla.setText(valor.substring(1));
            } else {
                pantalla.setText("-" + valor);
            }
        }
    }
}