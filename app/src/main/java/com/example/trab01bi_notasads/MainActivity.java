package com.example.trab01bi_notasads;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

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

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etIdade = findViewById(R.id.etIdade);
        etDisciplina = findViewById(R.id.etDisciplina);
        etNotaPrimeiroBi = findViewById(R.id.etNotaPrimeniroBi);
        etNotaSegundoBi = findViewById(R.id.etNotaSegundoBi);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        tvErro = findViewById(R.id.tvErro);
        tvLista = findViewById(R.id.tvLista);

        //Lista dos campos EditText
        List<EditText> campos = List.of(
                etNome, etEmail, etIdade, etDisciplina, etNotaPrimeiroBi, etNotaSegundoBi);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos(campos);
            }
        });
    }

    private boolean validarCampos(List<EditText> campos){
        boolean camposValidos = true;

        String msgCamposNulos = "Campos obrigatórios não preenchidos: ";
        String msgEmailInvalido = "";
        String msgIdadeInvalido = "";
        String msgNotaInvalida = "";

        for (EditText campo : campos) {
            if (campo.getText().toString().trim().isEmpty()) {
                campo.setError("O campo é obrigatório.");

                msgCamposNulos += campo.getTag() + ", ";
                camposValidos = false;

            }else if(campo == etEmail){
                if (!campo.getText().toString().contains("@")){
                    camposValidos = false;
                    msgEmailInvalido = "O e-mail inserido não é um e-mail válido.";
                    campo.setError("O e-mail inserido não é um e-mail válido.");
                }
            }else if (campo == etIdade) {
                String idade = etIdade.getText().toString();

                if (!idade.matches("\\d+")) {
                    campo.setError("Apenas números são permitidos.");
                    msgIdadeInvalido = "O campo idade aceita apenas números.";
                    camposValidos = false;

                } else if (Integer.parseInt(idade) < 0) {
                    campo.setError("A idade não pode ser negativa.");
                    msgIdadeInvalido = "A idade não pode ser negativa.";
                    camposValidos = false;
                }
            } else if (campo == etNotaPrimeiroBi || campo == etNotaSegundoBi) {
                String nota = campo.getText().toString();

                if (!nota.matches("^\\d+(\\.\\d+)?$")) {
                    campo.setError("O campo deve ser um número inteiro ou decimal.");
                    msgNotaInvalida += campo.getTag() + " deve ser um número inteiro ou decimal.\n";
                    camposValidos = false;

                }else if (!(Double.parseDouble(nota) > -1 && Double.parseDouble(nota) < 11) ) {
                    campo.setError("A nota deve ser entre 0 e 10");
                    msgNotaInvalida += campo.getTag() + " deve ser uma nota de 0 a 10.\n";
                    camposValidos = false;
                }
            }

        }
        if(!camposValidos){
            if(msgCamposNulos.length() > 2) {
                msgCamposNulos = msgCamposNulos.substring(0, msgCamposNulos.length()-2);
                msgCamposNulos += ".";
            }


            //Todo: Criar uma lista de mensagens e processar em um for onde sera criando um text view para cada erro, ignorando os erros nulos.
            // Pode se utilizar essa logica para criação das listas também
            tvErro.setText(msgCamposNulos +
                    "\n" + msgEmailInvalido +
                    "\n" + msgIdadeInvalido +
                    "\n" + msgNotaInvalida)
            ;
        }


        return camposValidos;
    }
}