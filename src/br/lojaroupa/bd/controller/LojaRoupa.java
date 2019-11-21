/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lojaroupa.bd.controller;

/**
 *
 * @author JoaoPaulo
 */
public class LojaRoupa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao c = new Conexao();
        
        c.connectMySQL();
        // TODO code application logic here
    }
    
}
