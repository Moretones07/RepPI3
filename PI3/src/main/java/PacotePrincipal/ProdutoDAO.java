package PacotePrincipal;

import Entidades.Produto;
import PacoteServlet.ServletBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
public class ProdutoDAO extends HttpServlet {

    public static List<Produto> getProdutos(){  //RETRIEVE
        List<Produto> listaProdutos = new ArrayList();
        try {
            Connection con = ConexaoBD.getConexao();
            String query = "select * from produto";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                int quantidade = rs.getInt("quantidade");
                float preço = rs.getFloat("preço");
                listaProdutos.add(new Produto(nome, categoria, quantidade, preço));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }
    
    public static void addProduto (Produto p) throws ClassNotFoundException, SQLException{  //CREATE
        Connection con = ConexaoBD.getConexao();
        String query = "insert into produto(nome, categoria, quantidade, preço) values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, p.getNome());
        ps.setString(2, p.getCategoria());
        ps.setInt(3, p.getQuantidade());
        ps.setDouble(4, p.getPreço());
        ps.execute();
    }
    
    public static void atualizarProduto (Produto p) throws ClassNotFoundException, SQLException{
                    Connection con = ConexaoBD.getConexao();
                    String query = "";

    }
    //UPDATE
    //DELETE
}
