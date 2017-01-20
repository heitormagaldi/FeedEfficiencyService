/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.Servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Heitor
 */
@WebServlet(name = "addAnimal", urlPatterns = {"/addAnimal"})
public class addAnimal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addAnimal</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>do post Servlet addAnimal at " + request.getContextPath() + "</h1>");
           
            
            String endereco = request.getParameter("arquivo");
            ServletContext context = getServletContext();
            String enderecoCompleto = context.getRealPath(endereco);
            try (BufferedReader buff = new BufferedReader(new FileReader(enderecoCompleto))) {
                String str;
                while ((str = buff.readLine()) != null) {
                    out.println("<h1>" +str+ "</h1>");
                }
            }

           // out.println("<h1>" + request.getParameter("arquivo") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
