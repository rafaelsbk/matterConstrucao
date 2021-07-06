package ufrn.tads.eaj.matterconstrucao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ufrn.tads.eaj.matterconstrucao.DAO.ProdutoDAO;
import ufrn.tads.eaj.matterconstrucao.model.Produtos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Cliente {
    List<Produtos> cartProduto = new ArrayList<>();

    @GetMapping(value = "/cliente")
    public void listarItens(HttpServletRequest request, HttpServletResponse response) throws IOException, ServerException {

        ProdutoDAO prodDAO = new ProdutoDAO();

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
        int i = 0;
        for ( Produtos c : prodDAO.clienteA()) {
            response.getWriter().println("<tr>");
            response.getWriter().println("<th>" + c.getNomeProduto() + "</th>");
            response.getWriter().println("<th>" + c.getDescricao() + "</th>");
            response.getWriter().println("<th>" + c.getFabricante() + "</th>");
            response.getWriter().println("<th>" + c.getQtdEstoque() + "</th>");
            response.getWriter().println("<th>" + c.getCategoria() + "</th>");
            response.getWriter().println("<th>" + c.getPrecoProduto() + "R$</th>");
            response.getWriter().println("<th>" + c.getPrecoCompraProduto() + "R$</th>");
            response.getWriter().println("<th> <a href=\"cartadd?id=" + prodDAO.clienteA().get(i).getIdProduto() + " \" >adicionar ao carrinho<a/></th>");
            response.getWriter().println("</tr>");
            i++;
        }

        response.getWriter().println("</table>");
        response.getWriter().println("<a href=\"/cartview\">Ver Carrinho</a> ");
    }

    @GetMapping(value = "/cartadd")
    public void getADDCartProduto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        HttpSession session = request.getSession();
        List<Produtos> carrinhoSession = (List<Produtos>) session.getAttribute("carrinho");
        if (request.getParameter("idproduto") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            ProdutoDAO prodDAO = new ProdutoDAO();
            for (Produtos p : prodDAO.listarIdProd(id)) {
                cartProduto.add(p);
            }
            for (Produtos p : cartProduto) {
                response.getWriter().println(p.getIdProduto() + " - " + p.getNomeProduto());
            }
        }
        session.setAttribute("carrinho", cartProduto);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/cliente");
        encaminhar.forward(request, response);
    }

    @GetMapping(value="/cartview")
    public void getCartView(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession();
        List<Produtos> cartSession = (List<Produtos>) s.getAttribute("carrinho");
        response.getWriter().println("<html>");
        response.getWriter().println("<body>");
        if (cartSession != null) {
            for (Produtos p : cartSession) {
                response.getWriter().println(p.getIdProduto() + " - " + p.getNomeProduto());
                response.getWriter().println("<br/>");
            }
        } else {
            response.sendRedirect("/cliente");
        }
        response.getWriter().println("<a href=\"/finalizarCompra\">Finalizar Compra</a>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    @GetMapping(value="/finalizarCompra")
    public void getFinish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession(false);
        if (s != null) {
            s.invalidate();
            response.sendRedirect("index.html");
        }
    }
}