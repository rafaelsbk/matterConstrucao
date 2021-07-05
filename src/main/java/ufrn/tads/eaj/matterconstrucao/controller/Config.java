package ufrn.tads.eaj.matterconstrucao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
@RequestMapping ("/configDB")
public class Config {

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DbConnect.getConnection();
            pstm = con.prepareStatement("create table tblProdutos (idProduto SERIAL PRIMARY KEY, nomeProduto varchar(50), descricao varchar(100), fabricante varchar(50), categoria varchar(50),  qtdEstoque int, precoProduto float, precoCompraProduto float);");
            pstm.execute();
            pstm = con.prepareStatement("INSERT INTO tblProdutos (nomeproduto, descricao, fabricante, categoria, qtdestoque, precoproduto, precocompraproduto) values ('Micro Retifica Dremel 300', 'Furadeira MicroRetifica Dremel','Dremel','Maquina Furadeira','20', '250.0', '125.0'), ('Parafusadeira Dewalt Storm', 'Parafuradeira Dewalt','Dewalt','Maquina Furadeira','3', '700.0', '350.0'), ('Serra Copo Bosch 50mm', 'Serra Copo Madeira','Bosch','Serra Madeira','15', '30.0', '15.0'), ('Broca Ferro IronMan 8mm', 'Broca Para Ferro','Marvel','Broca','70', '7.0', '3.50'), ('Multimetro XingLing 10A', 'Multimetro 10A','ChinaCorporation','Medidores Eletronicos','20', '57.0', '28.5')");
            pstm.execute();
            response.getWriter().println("Configuração instalada com sucesso!");
        } catch (SQLException | URISyntaxException ex) {
            response.getWriter().append("Connection Failed! Check output console");
        }
    }
}
