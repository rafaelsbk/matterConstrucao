package ufrn.tads.eaj.matterconstrucao.DAO;

import ufrn.tads.eaj.matterconstrucao.controller.DbConnect;
import ufrn.tads.eaj.matterconstrucao.model.Produtos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
    public List<Produtos> clienteA(){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Produtos> arrayDeProdutos = new ArrayList<>();
        try {
            con = DbConnect.getConnection();
            pstm = con.prepareStatement("Select * from tblprodutos");
            rs = pstm.executeQuery();

            while (rs.next()){
                Produtos produtos = new Produtos();
                produtos.setIdProduto(rs.getInt("idproduto"));
                produtos.setNomeProduto(rs.getString("nomeproduto"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setFabricante(rs.getString("fabricante"));
                produtos.setCategoria(rs.getString("categoria"));
                produtos.setQtdEstoque(rs.getInt("qtdestoque"));
                produtos.setPrecoProduto(rs.getFloat("precoproduto"));
                produtos.setPrecoCompraProduto(rs.getFloat("precocompraproduto"));

                arrayDeProdutos.add(produtos);
            }
        } catch (SQLException | URISyntaxException ex) {
            //response.getWriter().append("Connection Failed! Check output console");
            System.out.print (ex);
        }
        return arrayDeProdutos;
    }

    public void doCadastrar(Produtos produto) {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {

            connection = DbConnect.getConnection();
            pstm = connection.prepareStatement("INSERT INTO tblprodutos (nomeproduto, descricao, fabricante, categoria, qtdestoque, precoproduto, precocompraproduto) values (?,?,?,?,?,?,?);");
            pstm.setString(1, produto.getNomeProduto());
            pstm.setString(2, produto.getDescricao());
            pstm.setString(3, produto.getFabricante());
            pstm.setString(4, produto.getCategoria());
            pstm.setInt(5, produto.getQtdEstoque());
            pstm.setFloat(6, produto.getPrecoProduto());
            pstm.setFloat(7, produto.getPrecoCompraProduto());
            pstm.execute();
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
        }
    }
    public List<Produtos> listarIdProd(int id) {

        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Produtos> listaDeProdutos = new ArrayList<>();

        try {
            connection = DbConnect.getConnection();

            pstm = connection.prepareStatement("select * from tblprodutos where idproduto=?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            //connection.close();
            if (rs.next()) {
                Produtos produtos = new Produtos();

                produtos.setIdProduto(rs.getInt("idproduto"));
                produtos.setNomeProduto(rs.getString("nomeproduto"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setFabricante(rs.getString("fabricante"));
                produtos.setCategoria(rs.getString("categoria"));
                produtos.setQtdEstoque(rs.getInt("qtdestoque"));
                produtos.setPrecoProduto(rs.getFloat("precoproduto"));
                produtos.setPrecoCompraProduto(rs.getFloat("precocompraproduto"));
                listaDeProdutos.add(produtos);
            }
        } catch (SQLException | URISyntaxException ex) {
            //response.getWriter().append("Connection Failed! Check output console");
        }
        return listaDeProdutos;
    }

}