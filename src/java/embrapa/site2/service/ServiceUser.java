/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOUser;
import embrapa.fw.Criptografia;
import embrapa.fw.Guid;
import embrapa.to.TOUser;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Heitor
 */
@Path("user")
public class ServiceUser {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceUser() {
    }

    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOUser());
            j.put("list", ja);
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }
        return j.toString();
    }

    @GET
    //@POST
    //@Path("insert")
    //http://localhost:8084/embrapa.site2/services/user/insert/Heitor#Heitor1234#heitor@terra.com.br#perfil1
    @Path("insert/{login},{senha},{email},{perfil}")

    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("login") String login,
            @PathParam("senha") String senha,
            @PathParam("email") String email,
            @PathParam("perfil") String perfil) throws Exception {

        JSONObject j = new JSONObject();

        try {
            TOUser t = new TOUser();
            t.setId(Guid.getString());
            t.setLogin(login);
            t.setEmail(email);
            t.setPerfil(perfil);
            t.setSenha(Criptografia.md5(senha));
            BOFactory.insert(new DAOUser(), t);
            j.put("id", t.getId());
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }

    //@POST
    @GET
    //@Path("update")
    //http://localhost:8084/embrapa.site2/services/user/update/6QR0HI6KKB7B31B5T4HB2S,EdilmaAtualizada,Heitor1234,edilma@terra.com.br,perfil2
    @Path("update/{id},{login},{senha},{email},{perfil}")
    public String update(@PathParam("id") String id,
            @PathParam("login") String login,
            @PathParam("senha") String senha,
            @PathParam("email") String email,
            @PathParam("perfil") String perfil) throws Exception {
        /*public String update(@FormParam("id") String id,
            @FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOUser t = new TOUser();
            t.setId(id);

            t = (TOUser) BOFactory.get(new DAOUser(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Usu&aacute;rio n&atilde;o encontrado");
            } else {
                t.setLogin(login);
                t.setEmail(email);
                t.setPerfil(perfil);
                t.setSenha(Criptografia.md5(senha));
                BOFactory.update(new DAOUser(), t);
                j.put("id", t.getId());
                j.put("success", true);
            }

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }

    //@POST
    @GET
    //@Path("update")
    //http://localhost:8084/embrapa.site2/services/user/delete/6QR0HI6KKB7B31B5T4HB2S
    @Path("delete/{id}")
    public String DELETE(@PathParam("id") String id) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOUser t = new TOUser();
            t.setId(id);

            t = (TOUser) BOFactory.get(new DAOUser(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Usu&aacute;rio n&atilde;o encontrado");
            } else {
                BOFactory.delete(new DAOUser(), t);
                j.put("success", true);
            }

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }

    @POST
    @Path("teste")
    public String teste() throws Exception {
        JSONObject j = new JSONObject();
        j.put("id", "Mensagem");
        j.put("sucess", true);
        return j.toString();
    }
    
     //@POST
    @GET
    //@Path("update")
    //http://localhost:8084/embrapa.site2/services/user/update/6QR0HI6KKB7B31B5T4HB2S,EdilmaAtualizada,Heitor1234,edilma@terra.com.br,perfil2
    @Path("check/{login},{senha}")
    public String check(@PathParam("login") String login,
            @PathParam("senha") String senha) throws Exception {
        
        JSONObject j = new JSONObject();
        try {
            TOUser t = new TOUser();
            
            t.setLogin(login);
            t.setSenha(senha);
            
            
            t = (TOUser) BOFactory.check(new DAOUser(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Usu&aacute;rio n&atilde;o encontrado");
            } else {
                j.put("login", t.getLogin());
                j.put("success", true);
            }

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
    
    
}
