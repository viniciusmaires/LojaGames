/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.model.dao;

import br.lojaroupa.bd.controller.Conexao;
import br.lojaroupa.bd.model.Cliente;
import br.lojaroupa.bd.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JoaoPaulo
 */
public class ClienteDao {
    
    
    boolean sucesso = false;

    public boolean inserirCliente(Conexao c, Cliente cli) {
        String inserirCli = "INSERT INTO cliente (cli_nome, cli_RG, cli_cpf, cli_sexo, cli_telefone)VALUES(?,?,?,?,?)";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(inserirCli));
            c.getPreparedStatement().setString(1, cli.getNome());
            c.getPreparedStatement().setString(2, cli.getRg());
            c.getPreparedStatement().setString(3, cli.getCpf());
            c.getPreparedStatement().setString(4, cli.getSexo());
            c.getPreparedStatement().setString(5, cli.getTelefone());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    public boolean editarCliente(Conexao c, Cliente cli) {
        String editarCli = "UPDATE cliente SET cli_nome = ?, cli_RG = ?, cli_cpf = ?, cli_sexo = ?, cli_telefone = ? WHERE cli_RG = ?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(editarCli));
            c.getPreparedStatement().setString(1, cli.getNome());
            c.getPreparedStatement().setString(2, cli.getRg());
            c.getPreparedStatement().setString(3, cli.getCpf());
            c.getPreparedStatement().setString(4, cli.getSexo());
            c.getPreparedStatement().setString(5, cli.getTelefone());
            c.getPreparedStatement().setString(6, cli.getRg());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    public boolean removerCliente(Conexao c, Cliente cli) {
        String removerCli = " DELETE from cliente WHERE cli_RG = ? ";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(removerCli));
            c.getPreparedStatement().setString(1, cli.getRg());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    public ArrayList<Cliente> listaTabCliente(Conexao c) {

        ArrayList<Cliente> listacli = new ArrayList<>();

        String selectCommand = "SELECT * FROM cliente";
        try {
            c.setStatement(c.getConnection().createStatement());
            c.setResult(c.getStatement().executeQuery(selectCommand));
            while (c.getResult().next()) {
                Cliente cli = new Cliente();
                cli.setNome(c.getResult().getString("cli_nome"));
                cli.setRg(c.getResult().getString("cli_RG"));
                cli.setCpf(c.getResult().getString("cli_cpf"));
                cli.setSexo(c.getResult().getString("cli_sexo"));
                cli.setTelefone(c.getResult().getString("cli_telefone"));

                listacli.add(cli);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        }
        
        return listacli;
    }
    
    
}
