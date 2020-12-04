package PacoteServlet;

import Entidades.Cliente;
import Entidades.Filial;
import Entidades.Produto;
import Entidades.Venda;
import PacotePrincipal.ClienteDAO;
import PacotePrincipal.FilialDAO;
import PacotePrincipal.ProdutoDAO;
import PacotePrincipal.VendaDAO;
import Utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ivanyuratakano
 */
public class CadastrarVendas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> listaClientes = ClienteDAO.getClientes();
        request.setAttribute("listaClientes", listaClientes);

        List<Produto> listaProdutos = ProdutoDAO.getProdutos();
        request.setAttribute("listaProdutos", listaProdutos);

        List<Filial> listaFiliais = FilialDAO.getFilial();
        request.setAttribute("listaFiliais", listaFiliais);

        RequestDispatcher rd = getServletContext()
                .getRequestDispatcher("/Protegido/Vendedor/venda.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idproduto = Integer.parseInt(request.getParameter("idproduto"));
        Long cpf = Long.parseLong(request.getParameter("cpf"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        int filial = Integer.parseInt(request.getParameter("filial"));
        Venda p = new Venda(idproduto, cpf, quantidade, preco, filial);

        try {
            ProdutoDAO.vendeProduto(idproduto, quantidade);
            VendaDAO.addVenda(p);
            Utils.Sucesso(response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastrarVendas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
