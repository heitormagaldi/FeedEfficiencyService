/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.scene.control.Alert;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Heitor
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String retornoHttp = null;
        try {
            retornoHttp = sendPost(request.getParameter("login"), request.getParameter("senha"));
        } catch (Exception ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (retornoHttp == null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Falhou</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center><table cellspacing='10'>");
                out.println("<tr>");
                out.println("<td><center> <img src='imagens/log2.png'/> </center></td>");
                out.println("<td><center> <img src='imagens/nenc.png'/> </center></td>");
                out.println("</tr>");
                out.println("</table></center>");
                out.println("<center><img src='imagens/alerta.png'/><h2>Falha no preenchimento do login ou senha.</h2></center>");
                out.println("<center><input type = 'button' value='retornar ao login' onclick='history.back()'></center>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            try {
                JSONObject j = new JSONObject(retornoHttp);
                if (j.getBoolean("success")) {
                    request.setAttribute("login", request.getParameter("login"));
                    request.getRequestDispatcher("principal.jsp").forward(request, response);
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Falha no login</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<center><table cellspacing='10'>");
                        out.println("<tr>");
                        out.println("<td><center> <img src='imagens/log2.png'/> </center></td>");
                        out.println("<td><center> <img src='imagens/nenc.png'/> </center></td>");
                        out.println("</tr>");
                        out.println("</table></center>");
                        out.println("<center><img src='imagens/alerta.png'/><h2>Login ou senha n&atilde;o cadastrado.</h2></center>");
                        out.println("<center><input type = 'button' value='retornar ao login' onclick='history.back()'></center>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            } catch (JSONException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private String sendPost(String login, String senha) throws Exception {

        String url = "http://localhost:8084/embrapa.site2/services/user/check/" + login + "," + senha;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            return response.toString();
        } else {
            return null;
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
