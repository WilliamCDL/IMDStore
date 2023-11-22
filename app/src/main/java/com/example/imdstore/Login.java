package com.example.imdstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class Login extends AppCompatActivity {


    EditText login;
    EditText senha;
    Button entrar;
    Button esquecerSenha;
    String usuario="";
    String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById(R.id.logintexto);
        senha = findViewById(R.id.senhatexto);
        entrar = findViewById(R.id.alterar);
        esquecerSenha = findViewById(R.id.resetsenha);
        String vetorArquivos[] = fileList();
        if(buscarArquivo(vetorArquivos, "dadoslogin.txt")){
            try{
                InputStreamReader arquivo = new InputStreamReader(openFileInput("dadoslogin.txt"));
                BufferedReader br = new BufferedReader(arquivo);
                String linha = br.readLine();
                    usuario=linha;
                    linha = br.readLine();
                    password=linha;

            }catch(IOException e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void  setEntrar(View vien){
        String logUser,senUser;
        logUser = login.getText().toString();
        senUser = senha.getText().toString();
        if(!senUser.isEmpty() && !logUser.isEmpty()){
            if(logUser.equals(usuario) && senUser.equals(password)) {
                Intent i = new Intent(this, Menu.class);
                startActivity(i);
            }
        }
        if(senUser.isEmpty() || logUser.isEmpty()){
            Toast.makeText(this, "Usuario/Senha Não preenchido", Toast.LENGTH_LONG).show();
        }



    }

    public void  setAlterar(View vien){
        Intent intent = new Intent(this, ResetSenha.class);
        startActivityForResult(intent, 1);

    }

    private boolean buscarArquivo(String vetorArquivo[], String nome){
        for(int i = 0; i < vetorArquivo.length; i++){
            if(vetorArquivo[i].equals(nome)){
                return true;
            }
        }
        try {
            OutputStreamWriter arquivo = new OutputStreamWriter(openFileOutput("dadoslogin.txt", MODE_PRIVATE));
            arquivo.write("admin"+"\n");
            arquivo.write("admin"+"\n");
            arquivo.flush();
            arquivo.close();
            return true;
        }catch (IOException e){
            Toast.makeText(this, "Err", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // fazer um check para verificar o código de requisição e o de resposta
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

                try{
                    InputStreamReader arquivo = new InputStreamReader(openFileInput("dadoslogin.txt"));
                    BufferedReader br = new BufferedReader(arquivo);
                    String linha = br.readLine();
                    usuario=linha;
                    linha = br.readLine();
                    password=linha;

                }catch(IOException e){
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}