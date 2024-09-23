package com.example.trab01bi_notasads;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trab01bi_notasads.models.Media;

import java.util.ArrayList;
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
    private LinearLayout linearLayoutErros;
    private LinearLayout linearLayoutListagem;

    public List<EditText> campos;

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
        linearLayoutErros = findViewById(R.id.erros);
        linearLayoutListagem = findViewById(R.id.listagem);

        //Lista dos campos EditText
        campos = List.of(
                etNome, etEmail, etIdade, etDisciplina, etNotaPrimeiroBi, etNotaSegundoBi);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutErros.removeAllViews();
                boolean camposValidos = validarCampos(campos);

                if (camposValidos){
                    criarNotas();
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutListagem.removeAllViews();
                linearLayoutErros.removeAllViews();
                for (EditText c : campos){
                    c.setText(null);
                }
            }
        });
    }

    private boolean validarCampos(@NonNull List<EditText> campos){
        boolean camposValidos = true;

        ArrayList<String> erros = new ArrayList<>();

        String msgCamposNulos = "Campos obrigatórios não preenchidos: ";

        for (EditText campo : campos) {
            if (campo.getText().toString().trim().isEmpty()) {
                campo.setError("O campo é obrigatório.");

                msgCamposNulos += campo.getTag() + ", ";
                camposValidos = false;

            }else if(campo == etEmail){
                if (!campo.getText().toString().contains("@")){
                    camposValidos = false;
                    erros.add("O e-mail inserido não é um e-mail válido.");
                    campo.setError("O e-mail inserido não é um e-mail válido.");
                }
            }else if (campo == etIdade) {
                String idade = etIdade.getText().toString();

                if (!idade.matches("\\d+")) {
                    campo.setError("Apenas números são permitidos.");
                    erros.add("O campo idade aceita apenas números.");
                    camposValidos = false;

                } else if (Integer.parseInt(idade) < 0) {
                    campo.setError("A idade não pode ser negativa.");
                    erros.add("A idade não pode ser negativa.");
                    camposValidos = false;
                }
            } else if (campo == etNotaPrimeiroBi || campo == etNotaSegundoBi) {
                String nota = campo.getText().toString();

                if (!nota.matches("^\\d+(\\.\\d+)?$")) {
                    campo.setError("O campo deve ser um número inteiro ou decimal.");
                    erros.add(campo.getTag() + " deve ser um número inteiro ou decimal.");
                    camposValidos = false;

                }else if (!(Double.parseDouble(nota) > -1 && Double.parseDouble(nota) < 11) ) {
                    campo.setError("A nota deve ser entre 0 e 10");
                    erros.add(campo.getTag() + " deve ser uma nota de 0 a 10.");
                    camposValidos = false;
                }
            }

        }


        if(!camposValidos){
            if(msgCamposNulos.length() > 2 ) {
                msgCamposNulos = msgCamposNulos.substring(0, msgCamposNulos.length()-2);
                msgCamposNulos += ".";

                erros.add(msgCamposNulos);
            }


            //Criando listagem de erros na tela
            for(String erro : erros){
                TextView tvErros = new TextView(this);
                tvErros.setText(erro);

                tvErros.setLayoutParams( new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));

                linearLayoutErros.addView(tvErros);

            }
        }





        return camposValidos;
    }

    private void criarNotas(){

        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        int idade = Integer.parseInt(etIdade.getText().toString());
        String disciplina = etDisciplina.getText().toString();
        double notaPrimBi = Double.parseDouble(etNotaPrimeiroBi.getText().toString());
        double notaSegBi = Double.parseDouble(etNotaSegundoBi.getText().toString());

        Media media = new Media( nome, email, idade, disciplina, notaPrimBi, notaSegBi);
        String msgAprovacao;
        if (media.isAprovado()) msgAprovacao = "Aluno aprovado";
        else {
            msgAprovacao = "Aluno reprovado";
        }

        String msg = "Nome: " + media.getNome() +
                    "\nEmail: " + media.getEmail() +
                    "\nIdade: " + media.getIdade() +
                    "\nDisciplna: "+ media.getDisciplina() +
                    "\nNotas 1° e 2° Bimestre: "+ media.getNotaPrimBi() + " e " + media.getNotaSegBi() +
                    "\nMédia: " + media.getMedia() +
                    "\n" + msgAprovacao +
                    "\n----------------------------------------------------------";

        TextView tvItemLista = new TextView(this);
        tvItemLista.setText(msg);

        tvItemLista.setLayoutParams( new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

        ));

        linearLayoutListagem.addView(tvItemLista);
    }
}
