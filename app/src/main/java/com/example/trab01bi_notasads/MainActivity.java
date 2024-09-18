package com.example.trab01bi_notasads;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etIdade;
    private EditText etDisciplina;
    private EditText etNotaPrimeiroBi;
    private EditText etNotaSegundoBi;
    private Button btnSalvar;
    private Button btnLimpar;
    private TextView tvErro;
    private TextView tvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }
}