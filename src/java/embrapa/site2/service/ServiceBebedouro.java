/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOBebedouro;
import embrapa.to.TOBebedouro;
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
@Path("bebedouro")
public class ServiceBebedouro {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceBebedouro() {
    }

    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOBebedouro());
            j.put("list", ja);
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }
        return j.toString();
    }

    @GET
    @Path("listcodigo/{codigo}")
    public String listCodigo(@PathParam("codigo") Integer codigo) throws JSONException {
        JSONObject j = new JSONObject();
        TOBebedouro t = new TOBebedouro();
        t.setCodigo(codigo);
        try {
            JSONArray ja = BOFactory.listCodigo(new DAOBebedouro(), t);
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
    //http://localhost:8084/embrapa.site2/services/cobertura/insert/99999,999999,999999,999999,01012018,TE
    @Path("insert/{codigo},{animal},{id},{peso},{duracao},{consumido},{data},{hora}")

    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("codigo") Integer codigo,
            @PathParam("animal") Integer animal,
            @PathParam("id") float id,
            @PathParam("peso") float peso,
            @PathParam("duracao") float duracao,
            @PathParam("consumido") float consumido,
            @PathParam("data") String dataTemp,
            @PathParam("hora") String hora) throws Exception {
        JSONObject j = new JSONObject();

        try {
            TOBebedouro t = new TOBebedouro();
            t.setCodigo(codigo);
            t.setAnimal(animal);
            t.setId(id);
            t.setPeso(peso);
            t.setDuracao(duracao);
            t.setConsumido(consumido);

            DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

            String dateTemp = dataTemp.substring(0, 2) + "-"
                    + dataTemp.substring(2, 4) + "-" + dataTemp.substring(4, 8);

            Date data = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

            t.setData(data);
            t.setHora(hora);

            BOFactory.insert(new DAOBebedouro(), t);
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
    @Path("update/{codigo},{animal},{id},{peso},{duracao},{consumido},{data},{hora}")

    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String update(@PathParam("codigo") Integer codigo,
            @PathParam("animal") Integer animal,
            @PathParam("id") float id,
            @PathParam("peso") float peso,
            @PathParam("duracao") float duracao,
            @PathParam("consumido") float consumido,
            @PathParam("data") String dataTemp,
            @PathParam("hora") String hora) throws Exception {

        DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

        String dateTemp = dataTemp.substring(0, 2) + "-"
                + dataTemp.substring(2, 4) + "-" + dataTemp.substring(4, 8);

        Date data = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

        JSONObject j = new JSONObject();
        try {
            TOBebedouro t = new TOBebedouro();
            t.setCodigo(codigo);

            t = (TOBebedouro) BOFactory.get(new DAOBebedouro(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Registro do Bebedouro nao encontrado");
            } else {
                t.setCodigo(codigo);
                t.setAnimal(animal);
                t.setId(id);
                t.setPeso(peso);
                t.setDuracao(duracao);
                t.setConsumido(consumido);

                BOFactory.update(new DAOBebedouro(), t);
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
            TOBebedouro t = new TOBebedouro();
            t.setCodigo(codigo);

            t = (TOBebedouro) BOFactory.get(new DAOBebedouro(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Registro do Bebedouro nao encontrado");
            } else {
                BOFactory.delete(new DAOBebedouro(), t);
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
