package ufrn.tads.eaj.matterconstrucao.DAO;

import ufrn.tads.eaj.matterconstrucao.controller.DbConnect;
import ufrn.tads.eaj.matterconstrucao.model.Produtos;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO {
    public void doCadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Produtos produto = new Produtos();
        Connection con = null;
        PreparedStatement pstm = null;
        try {

            con = DbConnect.getConnection();
            pstm = con.prepareStatement("INSERT INTO tblProdutos (nomeproduto, descricao, fabricante, categoria, qtdestoque, precoproduto, precocompraproduto) values (?,?,?,?,?,?,?);");
            pstm.setString(1, produto.getNomeProduto());
            pstm.setString(2, produto.getDescricao());
            pstm.setString(3, produto.getFabricante());
            pstm.setString(4, produto.getCategoria());
            pstm.setInt(5, produto.getQtdEstoque());
            pstm.setFloat(6, produto.getPrecoProduto());
            pstm.setFloat(7, produto.getPrecoCompraProduto());
            pstm.execute();

        } catch (SQLException | URISyntaxException ex) {
            response.getWriter().append("");
        }
    }
}
