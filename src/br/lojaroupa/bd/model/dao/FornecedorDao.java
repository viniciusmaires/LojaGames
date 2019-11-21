/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.model.dao;

import br.lojaroupa.bd.controller.Conexao;
import br.lojaroupa.bd.model.Cliente;
import br.lojaroupa.bd.model.Fornecedor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JoaoPaulo
 */
public class FornecedorDao {
     boolean sucesso = false;

    public boolean inserirFornecedor(Conexao c, Fornecedor f) {
        String inserirForn = "INSERT INTO fornecedor (for_nome, for_cnpj, for_telefone)VALUES(?,?,?)";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(inserirForn));
            c.getPreparedStatement().setString(1, f.getNome());
            c.getPreparedStatement().setString(2, f.getCnpj());
            c.getPreparedStatement().setString(3, f.getTelefone());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    public boolean editarFornecedor(Conexao c, Fornecedor f) {
        String editarForn = "UPDATE fornecedor SET for_nome = ?, for_cnpj = ?, for_telefone = ? WHERE for_cnpj = ?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(editarForn));
            c.getPreparedStatement().setString(1, f.getNome());
            c.getPreparedStatement().setString(2, f.getCnpj());
            c.getPreparedStatement().setString(3, f.getTelefone());
            c.getPreparedStatement().setString(4, f.getCnpj());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    public boolean removerFornecedor(Conexao c, Fornecedor f) {
        String removerForn = "DELETE from fornecedor WHERE for_cnpj = ?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(removerForn));
            c.getPreparedStatement().setString(1, f.getCnpj());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    
    public ArrayList<Fornecedor> listaTabFornecedor(Conexao c) {

        ArrayList<Fornecedor> listaf = new ArrayList<>();

        String selectCommand = "SELECT * FROM fornecedor";
        try {
            c.setStatement(c.getConnection().createStatement());
            c.setResult(c.getStatement().executeQuery(selectCommand));
            while (c.getResult().next()) {
                Fornecedor f = new Fornecedor();
                f.setNome(c.getResult().getString("for_nome"));
                f.setCnpj(c.getResult().getString("for_cnpj"));
                f.setTelefone(c.getResult().getString("for_telefone"));

                listaf.add(f);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        }
        
        return listaf;
    }
}
