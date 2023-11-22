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

public class Deletar extends AppCompatActivity {

    EditText codigo;
    Button salvar;
    Button limpar;
    List<Produto> list = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletar);
        codigo = findViewById(R.id.codigoproduto);
        salvar = findViewById(R.id.alterar);
        limpar = findViewById(R.id.voltar);

        Intent intent = getIntent();
        if(intent.hasExtra("produtos")) {
            list = (List<Produto>) intent.getSerializableExtra("produtos");
        }
    }
    public void  setDeletar(View vien){
        int cont = 1;
        String codigoProduto = codigo.getText().toString();
        if (!codigoProduto.isEmpty()){
            for (Produto p: list) {
                if (p.getCodigoProduto().equals(codigoProduto)){
                    cont=0;
                    list.remove(p);
                    break;
                }
            }
            if(cont==0){
                Toast.makeText(this, "Produto Excluido", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("produtos", (Serializable) this.list); //
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(this, "Não achou", Toast.LENGTH_LONG).show();
            }

        }
        if (codigoProduto.isEmpty()){
            Toast.makeText(this, "Campo Codigo É Obrigatorio", Toast.LENGTH_LONG).show();
        }

    }
    public void  setLimpar(View vien){
        codigo.setText("");
    }
}