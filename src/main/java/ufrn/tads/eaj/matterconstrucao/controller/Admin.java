package ufrn.tads.eaj.matterconstrucao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ufrn.tads.eaj.matterconstrucao.DAO.ProdutoDAO;
import ufrn.tads.eaj.matterconstrucao.model.Produtos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class Admin {
    @RequestMapping(value ="/admin", method= RequestMethod.GET)
    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println ("<body>");
        response.getWriter().println("<form method=\"post\" action=\"/cadastra\">\n" +
                "  Nome do produto:<input type=\"text\" name=\"nomeproduto\"><br/>\n" +
                "  Descricao: <input type=\"text\" name=\"descricao\"><br/>\n" +
                "  Fabricante: <input type=\"text\" name=\"fabricante\"><br/>\n" +
                "  Categoria: <input type=\"text\" name=\"categoria\"><br/>\n" +
                "  Quantidade em Estoque: <input type=\"number\" name=\"qtdestoque\"><br/>\n" +
                "  Preco produto Varejo: <input type=\"number\" name=\"precoproduto\"><br/>\n" +
                "  Preco produto Revenda: <input type=\"number\" name=\"precocompraproduto\"><br/>\n" +
                "  <button>Cadastrar!</button>\n" +
                "</form>");
        response.getWriter().println ("</body>");
    }

    @RequestMapping(value = "/cadastra", method = RequestMethod.POST)
    public void cadastrarProdutoBanco(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeProduto = request.getParameter("nomeproduto");
        String descricao = request.getParameter("descricao");
        String fabricante = request.getParameter("fabricante");
        String categoria = request.getParameter("categoria");
        int qtdestoque = Integer.parseInt(request.getParameter("qtdestoque"));
        float precoproduto = Float.parseFloat(request.getParameter("precoproduto"));
        float precocompraproduto = Float.parseFloat(request.getParameter("precocompraproduto"));

        Produtos prod = new Produtos();
        ProdutoDAO cdao = new ProdutoDAO();

        prod.setNomeProduto(nomeProduto);
        prod.setDescricao(descricao);
        prod.setFabricante(fabricante);
        prod.setCategoria(categoria);
        prod.setQtdEstoque(qtdestoque);
        prod.setPrecoProduto(precoproduto);
        prod.setPrecoCompraProduto(precocompraproduto);
        cdao.doCadastrar(prod);

        HttpSession session = request.getSession();

        Date data = new Date(session.getCreationTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
        String currentTime = sdf.format(data);
        Cookie c = new Cookie("acesso",currentTime);
        c.setMaxAge(86400);
        response.addCookie(c);
        response.sendRedirect("/admin");
    }
}
