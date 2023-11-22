package com.example.imdstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Listar extends AppCompatActivity {

    ListView listaProdutos;
    Button voltar;
    List<Produto> list = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);

        voltar = findViewById(R.id.voltar);
        listaProdutos = findViewById(R.id.listView);
        Intent intent = getIntent();
        if (intent.hasExtra("produtos")) {
            list = (List<Produto>) intent.getSerializableExtra("produtos");
        }

        List<String> listAuxiliar = new ArrayList<String>();
        for (Produto produto : list) {
            listAuxiliar.add(produto.getCodigoProduto());
        }
        if (list.size() != 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listAuxiliar);
            listaProdutos.setAdapter(adapter);
        }

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textoClicado = "Codigo:" + list.get(position).getCodigoProduto()+ "\n";
                textoClicado = textoClicado+"Nome:" + list.get(position).getNomeProduto()+ "\n";
                textoClicado = textoClicado+"Descrição:" + list.get(position).getDescricaoProduto()+ "\n";
                textoClicado = textoClicado+"Estoque:" + list.get(position).getEstoqueProduto();

                AlertDialog.Builder al = new AlertDialog.Builder(Listar.this);
                al.setMessage(textoClicado);
                al.setTitle("Produto Selecionado");
                al.show();

            }
        });
    }


    public void setVoltar(View vien){
        finish();
    }
}