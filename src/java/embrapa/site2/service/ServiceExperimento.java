/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOExperimento;
import embrapa.to.TOExperimento;
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
@Path("experimento")
public class ServiceExperimento {
     @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceExperimento() {
    }

    //http://localhost:8084/embrapa.site2/services/experimento/list
    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOExperimento());
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
    //http://localhost:8084/embrapa.site2/services/experimento/insert/9999,9999,9999,3,1,01/01/2018,Boi Teste,F
    @Path("insert/{codigo},{inicio},{termino},{descricao},{sigla}")
///{codigo},{registro},{manejo},{raca},{cobertura},{nascimento},{nome},{sexo}
    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("codigo") Integer codigo,
            @PathParam("inicio") String iniciotemp,
            @PathParam("termino") String terminotemp,
            @PathParam("descricao") String descricao,
            @PathParam("sigla") String sigla) throws Exception {

        JSONObject j = new JSONObject();

        try {

            DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

            String dateTemp = iniciotemp.substring(0, 2) + "-"
                    + iniciotemp.substring(2, 4) + "-" + iniciotemp.substring(4, 8);

            Date inicio = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());
            
            dateTemp = terminotemp.substring(0, 2) + "-"
                    + terminotemp.substring(2, 4) + "-" + terminotemp.substring(4, 8);

            Date termino = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

            TOExperimento t = new TOExperimento();
            t.setCodigo(codigo);
            t.setInicio(inicio);
            t.setTermino(termino);
            t.setDescricao(descricao);
            t.setSigla(sigla);
           
            BOFactory.insert(new DAOExperimento(), t);
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
    //http://localhost:8084/embrapa.site2/services/experimento/update/6QR0HI6KKB7B31B5T4HB2S,EdilmaAtualizada,Heitor1234,edilma@terra.com.br,perfil2
    @Path("update/{codigo},{inicio},{termino},{descricao},{sigla}")
    public String update(@PathParam("codigo") Integer codigo,
            @PathParam("inicio") String iniciotemp,
            @PathParam("termino") String terminotemp,
            @PathParam("descricao") String descricao,
            @PathParam("sigla") String sigla
            ) throws Exception {

        JSONObject j = new JSONObject();
        try {
            TOExperimento t = new TOExperimento();
            t.setCodigo(codigo);

            t = (TOExperimento) BOFactory.get(new DAOExperimento(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Experimento não encontrado");
            } else {
                t.setDescricao(descricao);
                t.setSigla(sigla);
                
                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = iniciotemp.substring(0, 2) + "-"
                        + iniciotemp.substring(2, 4) + "-" + iniciotemp.substring(4, 8);

                Date inicio = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());
                t.setInicio(inicio);
                
                dateTemp = terminotemp.substring(0, 2) + "-"
                        + terminotemp.substring(2, 4) + "-" + terminotemp.substring(4, 8);

                Date termino = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());
                t.setTermino(termino);
                               
                BOFactory.update(new DAOExperimento(), t);
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
    //http://localhost:8084/embrapa.site2/services/experimento/delete/9998
    @Path("delete/{codigo}")
    public String DELETE(@PathParam("codigo") Integer codigo) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOExperimento t = new TOExperimento();
            t.setCodigo(codigo);

            t = (TOExperimento) BOFactory.get(new DAOExperimento(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Animal não encontrado");
            } else {
                BOFactory.delete(new DAOExperimento(), t);
                j.put("success", true);
            }

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
}
