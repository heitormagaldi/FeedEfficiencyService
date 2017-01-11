/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;
import embrapa.bo.BOFactory;
import embrapa.dao.DAORaca;
import embrapa.to.TORaca;
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
 *
 * @author Heitor
 */
@Path("raca")
public class ServiceRaca {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceRaca() {
    }

    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAORaca());
            j.put("data", ja);
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
    //http://localhost:8084/embrapa.site2/services/raca/insert/99999#Raca Teste
    @Path("insert/{codigo},{descricao}")

    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("codigo") Integer codigo,
            @PathParam("descricao") String descricao) throws Exception {
        JSONObject j = new JSONObject();

        try {
            TORaca t = new TORaca();
            t.setCodigo(codigo);
            t.setDescricao(descricao);
            
            BOFactory.insert(new DAORaca(), t);
            j.put("descricao", t.getDescricao());
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
    //http://localhost:8084/embrapa.site2/services/raca/update/99999,Edicao Raca
    @Path("update/{codigo},{descricao}")
    public String update(@PathParam("codigo") Integer codigo,
            @PathParam("descricao") String descricao) throws Exception {
        /*public String update(@FormParam("id") String id,
            @FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TORaca t = new TORaca();
            t.setCodigo(codigo);

            t = (TORaca) BOFactory.get(new DAORaca(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Raça não encontrada");
            } else {
                t.setDescricao(descricao);
                BOFactory.update(new DAORaca(), t);
                j.put("id", t.getDescricao());
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
    @Path("delete/{codigo}")
    public String DELETE(@PathParam("codigo") Integer codigo) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TORaca t = new TORaca();
            t.setCodigo(codigo);

            t = (TORaca) BOFactory.get(new DAORaca(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Raça não encontrada");
            } else {
                BOFactory.delete(new DAORaca(), t);
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
}
