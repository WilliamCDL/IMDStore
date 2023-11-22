package com.example.imdstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    List<Produto> produtos = new ArrayList<Produto>();
    Button cadastrar;
    Button alterar;
    Button deletar;
    Button listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        cadastrar = findViewById(R.id.cadastrar);
        alterar = findViewById(R.id.alterar);
        deletar = findViewById(R.id.deletar);
        listar = findViewById(R.id.listar);

        String vetorArquivos[] = fileList();
        if(buscarArquivo(vetorArquivos, "produtosIMD.txt")){
            try{
                InputStreamReader arquivo = new InputStreamReader(openFileInput("produtosIMD.txt"));
                BufferedReader br = new BufferedReader(arquivo);
                String linha = br.readLine();


                while(linha != null){
                    Produto produto = new Produto();
                    produto.setCodigoProduto(linha);
                    linha = br.readLine();
                    produto.setNomeProduto(linha);
                    linha = br.readLine();
                    produto.setDescricaoProduto(linha);
                    linha = br.readLine();
                    produto.setEstoqueProduto(Integer.parseInt(linha));
                    produtos.add(produto);
                    linha = br.readLine();
                }
            }catch(IOException e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean buscarArquivo(String vetorArquivo[], String nome){
        for(int i = 0; i < vetorArquivo.length; i++){
            if(vetorArquivo[i].equals(nome)){
                return true;
            }
        }
        return false;
    }
    public void  setCadastrar(View vien){
        Intent intent = new Intent(this, Cadastro.class);
        intent.putExtra("produtos", (Serializable) produtos);
        startActivityForResult(intent, 1);
    }
    public void  setAtualizar(View vien){
        Intent intent = new Intent(this, Alterar.class);
        intent.putExtra("produtos", (Serializable) produtos);
        startActivityForResult(intent, 1);
    }
    public void  setDeletar(View vien){
        Intent intent = new Intent(this, Deletar.class);
        intent.putExtra("produtos", (Serializable) produtos);
        startActivityForResult(intent, 1);
    }
    public void  setListar(View vien){
        Intent intent = new Intent(this, Listar.class);
        intent.putExtra("produtos", (Serializable) produtos);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // fazer um check para verificar o código de requisição e o de resposta
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

             /*objeto enviado pela activity  através do método setResult(),
             fiz um cast pro tipo (List<Produto>) porque sei que vai ser esse tipo,
             mas pode  ser qualquer obj que seja serializable.
             Intent tem métodos para vários tipos para setar e recupera     vários tipos diferentes. */
                produtos= (List<Produto>) data.getSerializableExtra("produtos");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            OutputStreamWriter arquivo = new OutputStreamWriter(openFileOutput("produtosIMD.txt", MODE_PRIVATE));
            for (Produto p: produtos) {
                arquivo.write(p.getCodigoProduto()+"\n");
                arquivo.write(p.getNomeProduto()+"\n");
                arquivo.write(p.getDescricaoProduto()+"\n");
                arquivo.write(p.getEstoqueProduto()+"\n");
            }
            arquivo.flush();
            arquivo.close();

        }catch (IOException e){
            Toast.makeText(this, "Err", Toast.LENGTH_LONG).show();
        }
    }

}