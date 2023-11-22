package com.example.imdstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alterar extends AppCompatActivity {

    EditText codigo;
    EditText nome;
    EditText descricao;
    EditText estoque;
    Button salvar;
    Button limpar;
    List<Produto> list = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar);
        codigo = findViewById(R.id.codigoproduto);
        nome = findViewById(R.id.nomeproduto);
        descricao = findViewById(R.id.descricaoproduto);
        estoque = findViewById(R.id.estoqueproduto);
        salvar = findViewById(R.id.alterar);
        limpar = findViewById(R.id.voltar);

        Intent intent = getIntent();
        if(intent.hasExtra("produtos")) {
            list = (List<Produto>) intent.getSerializableExtra("produtos");
        }
    }
    public void  setAlterar(View vien) {
        int estoqueProduto = 0;
        try {
            estoqueProduto = Integer.parseInt(estoque.getText().toString());
        } catch (NumberFormatException e) {
            //erro ao converter
        }
        String codigoProduto, nomeProduto, descricaoProduto, estoqueTexto;
        codigoProduto = codigo.getText().toString();
        nomeProduto = nome.getText().toString();
        descricaoProduto = descricao.getText().toString();
        estoqueTexto = estoque.getText().toString();
        int cont = 1;
        if (!codigoProduto.isEmpty() && !nomeProduto.isEmpty() || !descricaoProduto.isEmpty() || !estoqueTexto.isEmpty()) {
            for (Produto p: list) {
                if (p.getCodigoProduto().equals(codigoProduto)){
                    if(!nomeProduto.isEmpty()){
                        p.setNomeProduto(nomeProduto);
                    }
                    if(!descricaoProduto.isEmpty()){
                        p.setDescricaoProduto(descricaoProduto);
                    }
                    if(!estoqueTexto.isEmpty()){
                        p.setEstoqueProduto(estoqueProduto);
                    }
                    Toast.makeText(this, "Produto Alterado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("produtos", (Serializable) this.list); //
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
            Toast.makeText(this, "Codigo Inexistente", Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(this, "É necessario preencher Codigo + Outro Campo", Toast.LENGTH_LONG).show();
        }
    }
        public void setLimpar (View vien){
            codigo.setText("");
            nome.setText("");
            descricao.setText("");
            estoque.setText("");
        }

}