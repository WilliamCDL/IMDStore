package com.example.imdstore;

import java.io.Serializable;

public class Produto implements Serializable {
    private String codigoProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private int estoqueProduto;

    public Produto() {
    }
    public Produto(String codigo, String nome, String descricao, int estoque) {
        this.setCodigoProduto(codigo);
        this.setNomeProduto(nome);
        this.setDescricaoProduto(descricao);
        this.setEstoqueProduto(estoque);
    }

    public Produto(Produto produto) {
        this.setCodigoProduto(produto.getCodigoProduto());
        this.setNomeProduto(produto.getNomeProduto());
        this.setDescricaoProduto(produto.getDescricaoProduto());
        this.setEstoqueProduto(produto.getEstoqueProduto());
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getEstoqueProduto() {
        return estoqueProduto;
    }

    public void setEstoqueProduto(int estoqueProduto) {
        this.estoqueProduto = estoqueProduto;
    }


}
