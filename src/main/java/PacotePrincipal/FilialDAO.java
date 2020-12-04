/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacotePrincipal;

import Entidades.Filial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class FilialDAO {
    public static List<Filial> getFilial(){
         List<Filial> listaFilial = new ArrayList();
        Connection con;
        try {
            con = ConexaoBD.getConexao();
            String query = "select * from filial";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idfilial");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                listaFilial.add(new Filial(id, nome, endereco));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFilial;
    }
}
