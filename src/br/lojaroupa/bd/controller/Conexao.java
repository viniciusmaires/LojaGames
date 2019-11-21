/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JoaoPaulo
 */
public class Conexao {
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet result;
    
    //Atributos do Banco
    private String databaseName = "loja_vj";
    private String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private String user = "root";
    private String password = "root";
    
    public void connectMySQL(){
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void disconectMySQL(){
        try{
            connection.close();
            System.out.println("Disconnected!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public ResultSet getResult() {
        return result;
    }
    

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
      
}

