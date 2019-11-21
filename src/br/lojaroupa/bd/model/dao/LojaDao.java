/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.model.dao;

import br.lojaroupa.bd.controller.Conexao;
import br.lojaroupa.bd.model.Loja;
import java.sql.SQLException;

/**
 *
 * @author JoaoPaulo
 */
public class LojaDao {
    boolean sucesso = false;
    public boolean addLoja(Conexao c, String nome){
        String inserir = "insert into loja(loj_nome) values(?)";
        try{
            c.setPreparedStatement(c.getConnection().prepareStatement(inserir));
            c.getPreparedStatement().setString(1, nome);
            c.getPreparedStatement().execute();
            sucesso = true;
        }catch(SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
        
        return sucesso;
    }
    
}
