/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.model.dao;

import br.lojaroupa.bd.controller.Conexao;
import br.lojaroupa.bd.model.Fornecedor;
import br.lojaroupa.bd.model.Funcionario;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JoaoPaulo
 */
public class FuncionarioDao {
    
    boolean sucesso = false;

    public boolean inserirFuncionario(Conexao c, Funcionario func) {
        String inserirFunc = "INSERT INTO funcionario (func_nome, func_cpf, func_telefone, func_salario, func_cargo)VALUES(?,?,?,?,?)";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(inserirFunc));
            c.getPreparedStatement().setString(1, func.getNome());
            c.getPreparedStatement().setString(2, func.getCpf());
            c.getPreparedStatement().setString(3, func.getTelefone());
            c.getPreparedStatement().setString(4, func.getSalario());
            c.getPreparedStatement().setString(5, func.getCargo());
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    public boolean editarFuncionario(Conexao c, Funcionario func) {
        String editarFunc = "UPDATE funcionario SET func_nome = ?, func_cpf = ?, func_telefone = ?, func_salario = ?, func_cargo = ? WHERE func_cpf =?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(editarFunc));
            c.getPreparedStatement().setString(1, func.getNome());
            c.getPreparedStatement().setString(2, func.getCpf());
            c.getPreparedStatement().setString(3, func.getTelefone());
            c.getPreparedStatement().setString(4, func.getSalario());
            c.getPreparedStatement().setString(5, func.getCargo());
            c.getPreparedStatement().setString(6, func.getCpf());
            
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    public boolean removerFuncionario(Conexao c, Funcionario func) {
        String removerFunc = "DELETE FROM funcionario WHERE func_cpf = ?";
        try {
            c.setPreparedStatement(c.getConnection().prepareStatement(removerFunc));
            c.getPreparedStatement().setString(1, func.getCpf());        
            c.getPreparedStatement().execute();
            sucesso = true;
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucesso = false;
        }
        return sucesso;

    }
    
    
    public ArrayList<Funcionario> listaTabFuncionario(Conexao c) {

        ArrayList<Funcionario> listafunc = new ArrayList<>();

        String selectCommand = "SELECT * FROM funcionario";
        try {
            c.setStatement(c.getConnection().createStatement());
            c.setResult(c.getStatement().executeQuery(selectCommand));
            while (c.getResult().next()) {
                Funcionario func = new Funcionario();
                func.setNome(c.getResult().getString("func_nome"));
                func.setCpf(c.getResult().getString("func_cpf"));
                func.setTelefone(c.getResult().getString("func_telefone"));
                func.setSalario(c.getResult().getString("func_salario"));
                func.setCargo(c.getResult().getString("func_cargo"));

                listafunc.add(func);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        }
        
        return listafunc;
    }
}
