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
import java.io.OutputStreamWriter;

public class ResetSenha extends AppCompatActivity {
    EditText login;
    EditText senha;
    Button alterar;
    Button voltar;
    String usuario="";
    String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset);

        login = findViewById(R.id.logintexto);
        senha = findViewById(R.id.senhaTexto);
        alterar = findViewById(R.id.alterar);
        voltar = findViewById(R.id.voltar);
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

    public void  setAlterar(View vien){
        String logUser,senUser;
        logUser = login.getText().toString();
        senUser = senha.getText().toString();
        if(!logUser.isEmpty() || !senUser.isEmpty()){
            try {
                OutputStreamWriter arquivo = new OutputStreamWriter(openFileOutput("dadoslogin.txt", MODE_PRIVATE));
                if(!logUser.isEmpty()){
                    arquivo.write(logUser+"\n");
                }else{
                    arquivo.write(usuario+"\n");
                }
                if(!senUser.isEmpty()){
                    arquivo.write(senUser+"\n");
                }else{
                    arquivo.write(password+"\n");
                }
                arquivo.flush();
                arquivo.close();
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }catch (IOException e){
                Toast.makeText(this, "Err", Toast.LENGTH_LONG).show();
            }

        }
        if(logUser.isEmpty() && senUser.isEmpty()){
            Toast.makeText(this, "Usuario/Senha Não preenchido", Toast.LENGTH_LONG).show();
        }



    }
    public void  setVoltar(View vien){
        finish();

    }
    private boolean buscarArquivo(String vetorArquivo[], String nome){
        for(int i = 0; i < vetorArquivo.length; i++){
            if(vetorArquivo[i].equals(nome)){
                return true;
            }
        }
        return false;
    }
}