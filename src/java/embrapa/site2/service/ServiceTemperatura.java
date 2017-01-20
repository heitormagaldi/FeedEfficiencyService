/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOTemperatura;
import embrapa.to.TOTemperatura;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ws.rs.GET;
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
@Path("temperatura")
public class ServiceTemperatura {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceTemperatura() {
    }

    //http://localhost:8084/embrapa.site2/services/temperatura/list
    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOTemperatura());
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
    //http://localhost:8084/embrapa.site2/services/temperatura/insert/xxxx
    @Path("insert/{codigo},{equipamento},{sensor},{valor},{data},{hora}")
///{codigo},{registro},{manejo},{raca},{cobertura},{nascimento},{nome},{sexo}
    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("codigo") Integer codigo,
            @PathParam("equipamento") Integer equipamento,
            @PathParam("sensor") String sensor,
            @PathParam("valor") Float valor,
            @PathParam("data") String datatemp,
            @PathParam("hora") String hora) throws Exception {

        JSONObject j = new JSONObject();

        try {

            DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

            String dateTemp = datatemp.substring(0, 2) + "-"
                    + datatemp.substring(2, 4) + "-" + datatemp.substring(4, 8);

            Date data = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

            TOTemperatura t = new TOTemperatura();
            t.setCodigo(codigo);
            t.setEquipamento(equipamento);
            t.setSensor(sensor);
            t.setValor(valor);
            t.setData(data);
            t.setHora(hora);

            BOFactory.insert(new DAOTemperatura(), t);
            j.put("id", t.getCodigo());
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
    @Path("update/{codigo},{equipamento},{sensor},{valor},{data},{hora}")
    public String update(@PathParam("codigo") Integer codigo,
            @PathParam("equipamento") Integer equipamento,
            @PathParam("sensor") String sensor,
            @PathParam("valor") Float valor,
            @PathParam("data") String datatemp,
            @PathParam("hora") String hora) throws Exception {

        JSONObject j = new JSONObject();
        try {
            TOTemperatura t = new TOTemperatura();
            t.setCodigo(codigo);

            t = (TOTemperatura) BOFactory.get(new DAOTemperatura(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Temperatura não encontrada");
            } else {
                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = datatemp.substring(0, 2) + "-"
                        + datatemp.substring(2, 4) + "-" + datatemp.substring(4, 8);

                Date data = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

                t.setEquipamento(equipamento);
                t.setSensor(sensor);
                t.setValor(valor);
                t.setData(data);
                t.setHora(hora);
              
                BOFactory.update(new DAOTemperatura(), t);
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
    //@Path("update")
    //http://localhost:8084/embrapa.site2/services/temperatura/delete/9998
    @Path("delete/{codigo}")
    public String DELETE(@PathParam("codigo") Integer codigo) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOTemperatura t = new TOTemperatura();
            t.setCodigo(codigo);

            t = (TOTemperatura) BOFactory.get(new DAOTemperatura(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Temperatura não encontrada");
            } else {
                BOFactory.delete(new DAOTemperatura(), t);
                j.put("success", true);
            }

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
}
