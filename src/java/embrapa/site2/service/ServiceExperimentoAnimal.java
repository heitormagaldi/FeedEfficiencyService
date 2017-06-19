/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import embrapa.bo.BOFactory;
import embrapa.dao.DAOExperimento_Animal;
import embrapa.to.TOExperimento_Animal;
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
@Path("experimento_animal")
public class ServiceExperimentoAnimal {
    public ServiceExperimentoAnimal() {
    }

    //http://localhost:8084/embrapa.site2/services/Experimento_Animal/list
    @GET
    @Path("list")
    public String list() throws JSONException {
        JSONObject j = new JSONObject();
        try {
            JSONArray ja = BOFactory.list(new DAOExperimento_Animal());
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
    @Path("insert/{animal},{experimento},{gpd},{ims},{pm_medio}")
///{codigo},{registro},{manejo},{raca},{cobertura},{nascimento},{nome},{sexo}
    /*public String insert(@FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("perfil") String perfil) throws Exception {
     
     */
    public String insert(@PathParam("animal") Integer animal,
            @PathParam("experimento") Integer experimento,
            @PathParam("gpd") float gpd,
            @PathParam("ims") float ims,
            @PathParam("pm_medio") float pm_medio) throws Exception {

        JSONObject j = new JSONObject();

        try {

            
            TOExperimento_Animal t = new TOExperimento_Animal();
            t.setAnimal(animal);
            t.setExperimento(experimento);
            t.setGpd(gpd);
            t.setIms(ims);
            t.setPm_medio(pm_medio);
           
            BOFactory.insert(new DAOExperimento_Animal(), t);
                       
            j.put("id", t.getAnimal());
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
    @Path("update/{animal},{experimento},{gpd},{ims},{pm_medio}")
    public String update(@PathParam("animal") Integer animal,
            @PathParam("experimento") Integer experimento,
            @PathParam("gpd") float gpd,
            @PathParam("ims") float ims,
            @PathParam("pm_medio") float pm_medio
            ) throws Exception {

        JSONObject j = new JSONObject();
        try {
            TOExperimento_Animal t = new TOExperimento_Animal();
            t.setAnimal(animal);
            t.setExperimento(experimento);

            t = (TOExperimento_Animal) BOFactory.get(new DAOExperimento_Animal(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Experimento/Animal não encontrado");
            } else {
                t.setGpd(gpd);
                t.setIms(ims);
                t.setPm_medio(pm_medio);
                
                                               
                BOFactory.update(new DAOExperimento_Animal(), t);
                j.put("codigo", t.getAnimal());
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
    @Path("delete/{animal},{experimento}")
    public String DELETE(@PathParam("animal") Integer animal,
            @PathParam("experimento") Integer experimento) throws Exception {
        /*public String update(@FormParam("id") String id) throws Exception {*/
        JSONObject j = new JSONObject();
        try {
            TOExperimento_Animal t = new TOExperimento_Animal();
            t.setAnimal(animal);
            t.setExperimento(experimento);
            

            t = (TOExperimento_Animal) BOFactory.get(new DAOExperimento_Animal(), t);

            if (t == null) {
                j.put("success", false);
                j.put("message", "Animal/Experimento não encontrado");
            } else {
                BOFactory.delete(new DAOExperimento_Animal(), t);
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
            
            int lnretorno = BOFactory.count(new DAOExperimento_Animal());
            
            j.put("quantidade", lnretorno);
                       

        } catch (Exception e) {
            j.put("quantidade", 0);
            j.put("message", e.getMessage());
        }

        return j.toString();
    }
}
