/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOAnimal;
import embrapa.to.TOAnimal;
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
@Path("animal")
public class ServiceAnimal {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceUser
     */
    public ServiceAnimal() {
    }

    //http://localhost:8084/embrapa.site2/services/animal/list
    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOAnimal());
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
    //http://localhost:8084/embrapa.site2/services/animal/insert/9999,9999,9999,3,1,01/01/2018,Boi Teste,F
    @Path("insert/{codigo},{registro},{manejo},{raca},{cobertura},{nascimento},{nome},{sexo}")
///{codigo},{registro},{manejo},{raca},{cobertura},{nascimento},{nome},{sexo}
    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("codigo") Integer codigo,
            @PathParam("registro") String registro,
            @PathParam("manejo") String manejo,
            @PathParam("raca") Integer raca,
            @PathParam("cobertura") Integer cobertura,
            @PathParam("nascimento") String nascimentotemp,
            @PathParam("nome") String nome,
            @PathParam("sexo") String sexo) throws Exception {

        JSONObject j = new JSONObject();

        try {

            DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

            String dateTemp = nascimentotemp.substring(0, 2) + "-"
                    + nascimentotemp.substring(2, 4) + "-" + nascimentotemp.substring(4, 8);

            Date nascimento = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

            TOAnimal t = new TOAnimal();
            t.setCodigo(codigo);
            t.setRegistro(registro);
            t.setManejo(manejo);
            t.setRaca(raca);
            t.setCobertura(cobertura);
            t.setNascimento(nascimento);
            t.setNome(nome);
            t.setSexo(sexo);

            BOFactory.insert(new DAOAnimal(), t);
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
    @Path("update/{codigo},{registro},{manejo},{raca},{cobertura},{nascimento},{nome},{sexo}")
    public String update(@PathParam("codigo") Integer codigo,
            @PathParam("registro") String registro,
            @PathParam("manejo") String manejo,
            @PathParam("raca") Integer raca,
            @PathParam("cobertura") Integer cobertura,
            @PathParam("nascimento") String nascimentotemp,
            @PathParam("nome") String nome,
            @PathParam("sexo") String sexo) throws Exception {

        JSONObject j = new JSONObject();
        try {
            TOAnimal t = new TOAnimal();
            t.setCodigo(codigo);

            t = (TOAnimal) BOFactory.get(new DAOAnimal(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Animal não encontrado");
            } else {
                t.setRegistro(registro);
                t.setManejo(manejo);
                t.setRaca(raca);
                t.setCobertura(cobertura);

                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = nascimentotemp.substring(0, 2) + "-"
                        + nascimentotemp.substring(2, 4) + "-" + nascimentotemp.substring(4, 8);

                Date nascimento = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());
                t.setNascimento(nascimento);
                t.setNome(nome);
                t.setSexo(sexo);

                BOFactory.update(new DAOAnimal(), t);
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
    //http://localhost:8084/embrapa.site2/services/animal/delete/9998
    @Path("delete/{codigo}")
    public String DELETE(@PathParam("codigo") Integer codigo) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOAnimal t = new TOAnimal();
            t.setCodigo(codigo);

            t = (TOAnimal) BOFactory.get(new DAOAnimal(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Animal não encontrado");
            } else {
                BOFactory.delete(new DAOAnimal(), t);
                j.put("success", true);
            }

        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
    
    @GET
    @Path("count")
    public String count() throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            
            int lnretorno = BOFactory.count(new DAOAnimal());
            
            j.put("quantidade", lnretorno);
                       

        } catch (Exception e) {
            j.put("quantidade", 0);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
}
