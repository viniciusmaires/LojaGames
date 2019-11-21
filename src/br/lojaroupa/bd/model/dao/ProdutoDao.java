/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.model.dao;

import br.lojaroupa.bd.model.Produto;
import br.lojaroupa.bd.controller.Conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JoaoPaulo
 */
public class ProdutoDao {
    boolean sucesso = false;

    public boolean inserirProduto(Conexao c, Produto p) {
        String inserirP = "INSERT INTO produto (prod_nome,prod_tamanho,prod_preco,prod_genero,prod_quantidade)VALUES(?,?,?,?,?)";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(inserirP));
            c.getPreparedStatement().setString(1, p.getNome());
            c.getPreparedStatement().setString(2, p.getTamanho());
            c.getPreparedStatement().setString(3, p.getPreco());
            c.getPreparedStatement().setString(4, p.getGenero());
            c.getPreparedStatement().setInt(5, p.getQuantidade());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    public boolean editarProduto(Conexao c, Produto p) {
        String editarP = "UPDATE produto SET prod_nome = ?, prod_tamanho = ?, prod_preco = ?, prod_genero = ?, prod_quantidade = ? WHERE prod_nome = ?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(editarP));
            c.getPreparedStatement().setString(1, p.getNome());
            c.getPreparedStatement().setString(2, p.getTamanho());
            c.getPreparedStatement().setString(3, p.getPreco());
            c.getPreparedStatement().setString(4, p.getGenero());
            c.getPreparedStatement().setInt(5, p.getQuantidade());
            c.getPreparedStatement().setString(6, p.getNome());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    public boolean removerProduto(Conexao c, Produto p) {
        String removerP = "DELETE from produto WHERE prod_nome = ?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(removerP));
            c.getPreparedStatement().setString(1, p.getNome());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    public ArrayList<Produto> listaTabProduto(Conexao c) {

        ArrayList<Produto> listaprod = new ArrayList<>();

        String selectCommand = "SELECT * FROM produto";
        try {
            c.setStatement(c.getConnection().createStatement());
            c.setResult(c.getStatement().executeQuery(selectCommand));
            while (c.getResult().next()) {
                Produto p = new Produto();
                p.setNome(c.getResult().getString("prod_nome"));
                p.setTamanho(c.getResult().getString("prod_tamanho"));
                p.setPreco(c.getResult().getString("prod_preco"));
                p.setGenero(c.getResult().getString("prod_genero"));
                p.setQuantidade(c.getResult().getInt("prod_quantidade"));

                listaprod.add(p);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        }
        
        return listaprod;
    }
    
}


