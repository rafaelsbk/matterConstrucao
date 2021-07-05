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
@RequestMapping("/verCarrinho")
public class VerCarrinho {

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}
