package ufrn.tads.eaj.matterconstrucao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ufrn.tads.eaj.matterconstrucao.DAO.AdminDAO;
import ufrn.tads.eaj.matterconstrucao.DAO.ClienteDAO;
import ufrn.tads.eaj.matterconstrucao.model.Produtos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

@Controller
public class Cliente {
    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public void listarItens(HttpServletRequest request, HttpServletResponse response) throws IOException, ServerException {

        ClienteDAO cliente = new ClienteDAO();


        response.getWriter().println("<table>");

        response.getWriter().println("<tr>");
        response.getWriter().println("<th>Nome</th>");
        response.getWriter().println("<th>Descricao do produto</th>");
        response.getWriter().println("<th>Fabricante</th>");
        response.getWriter().println("<th>Quantidade em Estoque</th>");
        response.getWriter().println("<th>Categoria</th>");
        response.getWriter().println("<th>Preco do Produto</th>");
        response.getWriter().println("<th>Preco para Terceirizados</th>");
        response.getWriter().println("</tr>");

        int i =0;

        for (Produtos c : cliente.clienteA()) {
            response.getWriter().println("<tr>");
            response.getWriter().println("<th>" + c.getNomeProduto() + "</th>");
            response.getWriter().println("<th>" + c.getDescricao() + "</th>");
            response.getWriter().println("<th>" + c.getFabricante()+ "</th>");
            response.getWriter().println("<th>" + c.getQtdEstoque()+ "</th>");
            response.getWriter().println("<th>" + c.getCategoria() + "</th>");;
            response.getWriter().println("<th>" + c.getPrecoProduto() + "R$</th>");
            response.getWriter().println("<th>" + c.getPrecoCompraProduto() + "R$</th>");
            //response.getWriter().println("<th> <a href=\"/adicionarcarrinho?id=\" " + p.getIdProduto() + ">Adicionar ao carrinho</a></th>");

            response.getWriter().println("<th> <a href=\"adicionar?id= " + Integer.toString(cliente.clienteA().get(i).getIdProduto())  + " \" >adicionar ao carrinho<a/></th>");



            response.getWriter().println("</tr>");
            i++;
        }

        // for (int i = 0; i < pdao.listarProdutos().size(); i++ ){
        //   response.getWriter().println("<a href=\"adicionarcarrinho?id=" + pdao.listarProdutos().get(i).getIdProduto() + " \" \"></a>");
        // }


        response.getWriter().println("</table>");


    }
}
