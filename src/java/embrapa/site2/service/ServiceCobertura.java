/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOCobertura;
import embrapa.to.TOCobertura;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@Path("cobertura")
public class ServiceCobertura {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceCobertura() {
    }

    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOCobertura());
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
    //http://localhost:8084/embrapa.site2/services/cobertura/insert/99999,999999,999999,999999,01012018,TE
    @Path("insert/{codigo},{pai},{mae},{receptora},{data},{tipo}")

    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("codigo") Integer codigo,
            @PathParam("pai") Integer pai,
            @PathParam("mae") Integer mae,
            @PathParam("receptora") Integer receptora,
            @PathParam("data") String dataTemp,
            @PathParam("tipo") String tipo) throws Exception {
        JSONObject j = new JSONObject();

        try {
            TOCobertura t = new TOCobertura();
            t.setCodigo(codigo);
            t.setPai(pai);
            t.setMae(mae);
            t.setReceptora(receptora);

            DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

            String dateTemp = dataTemp.substring(0, 2) + "-"
                    + dataTemp.substring(2, 4) + "-" + dataTemp.substring(4, 8);

            Date data = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());
            t.setData(data);

            BOFactory.insert(new DAOCobertura(), t);
            j.put("codigo", t.getCodigo());
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
    //http://localhost:8084/embrapa.site2/services/cobertura/insert/99999,999999,999999,999999,01012018,TE
    @Path("update/{codigo},{pai},{mae},{receptora},{data},{tipo}")
    public String update(@PathParam("codigo") Integer codigo,
            @PathParam("pai") Integer pai,
            @PathParam("mae") Integer mae,
            @PathParam("receptora") Integer receptora,
            @PathParam("data") String dataTemp,
            @PathParam("tipo") String tipo) throws Exception {

        DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

        String dateTemp = dataTemp.substring(0, 2) + "-"
                + dataTemp.substring(2, 4) + "-" + dataTemp.substring(4, 8);

        Date data = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());
       

        JSONObject j = new JSONObject();
        try {
            TOCobertura t = new TOCobertura();
            t.setCodigo(codigo);

            t = (TOCobertura) BOFactory.get(new DAOCobertura(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Cobertura não encontrada");
            } else {
                t.setPai(pai);
                t.setMae(mae);
                t.setReceptora(receptora);
                t.setData(data);
                t.setTipo(tipo);
               

                BOFactory.update(new DAOCobertura(), t);
                j.put("codigo", t.getCodigo());
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
    @Path("delete/{codigo}")
    public String DELETE(@PathParam("codigo") Integer codigo) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOCobertura t = new TOCobertura();
            t.setCodigo(codigo);

            t = (TOCobertura) BOFactory.get(new DAOCobertura(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Cobertura não encontrada");
            } else {
                BOFactory.delete(new DAOCobertura(), t);
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