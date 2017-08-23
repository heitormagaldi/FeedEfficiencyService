/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embrapa.site2.service;

import static com.hp.hpl.jena.assembler.JA.FileManager;
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

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author Heitor
 */
//http://localhost:8084/embrapa.site2/services/ont/list
@Path("ont")
public class ServiceOntologia {

    public static final String lcURI = "http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#";
    private static final String lcOntology = "file:///D:/EmbrapaCleanV1_22082017.owl";
    //variavel para acesso à ontologia com inferencia
    private final InfModel infModel;
    //variavel para acesso à ontologia sem inferencia
    private final OntModel ontModel;

    public ServiceOntologia() {
        Model model = ModelFactory.createDefaultModel();
        model.read(lcOntology);

        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
        ontModel.prepare();

        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        infModel = ModelFactory.createInfModel(reasoner, model);
        infModel.prepare();

    }

    @GET
    @Path("CAR")
    public String ClassificacaoCAR() throws Exception {
        List<TOAnimal> lstAnimaisEficentes = GetCAR(1);
        List<TOAnimal> lstAnimaisIneficentes = GetCAR(2);
        List<TOAnimal> lstAnimaisIntermediarios = GetCAR(3);

        JSONArray ja = new JSONArray();
        
        JSONObject j2 = new JSONObject();
        
        for (TOAnimal animal : lstAnimaisEficentes) {
            ja.put(animal.getJson());
        }
        j2.put("eficientes", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIntermediarios) {
            ja.put(animal.getJson());
        }
        j2.put("intermediarios", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIneficentes) {
            ja.put(animal.getJson());
        }
        j2.put("ineficientes", ja);
        
        JSONObject j = new JSONObject();

        try {
            j.put("CAR", j2);
            //j.put("list", ja);
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }
    
    @GET
    @Path("ECA")
    public String ClassificacaoECA() throws Exception {
        List<TOAnimal> lstAnimaisEficentes = GetECA(1);
        List<TOAnimal> lstAnimaisIneficentes = GetECA(2);
        List<TOAnimal> lstAnimaisIntermediarios = GetECA(3);

        JSONArray ja = new JSONArray();
        
        JSONObject j2 = new JSONObject();
        
        for (TOAnimal animal : lstAnimaisEficentes) {
            ja.put(animal.getJson());
        }
        j2.put("eficientes", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIntermediarios) {
            ja.put(animal.getJson());
        }
        j2.put("intermediarios", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIneficentes) {
            ja.put(animal.getJson());
        }
        j2.put("ineficientes", ja);
        
        JSONObject j = new JSONObject();

        try {
            j.put("ECA", j2);
            //j.put("list", ja);
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }
    
    @GET
    @Path("GPR")
    public String ClassificacaoGPR() throws Exception {
        List<TOAnimal> lstAnimaisEficentes = GetGPR(1);
        List<TOAnimal> lstAnimaisIneficentes = GetGPR(2);
        List<TOAnimal> lstAnimaisIntermediarios = GetGPR(3);

        JSONArray ja = new JSONArray();
        
        JSONObject j2 = new JSONObject();
        
        for (TOAnimal animal : lstAnimaisEficentes) {
            ja.put(animal.getJson());
        }
        j2.put("eficientes", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIntermediarios) {
            ja.put(animal.getJson());
        }
        j2.put("intermediarios", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIneficentes) {
            ja.put(animal.getJson());
        }
        j2.put("ineficientes", ja);
        
        JSONObject j = new JSONObject();

        try {
            j.put("GPR", j2);
            //j.put("list", ja);
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }
    
    @GET
    @Path("CGPR")
    public String ClassificacaoCGPR() throws Exception {
        List<TOAnimal> lstAnimaisEficentes = GetCGPR(1);
        List<TOAnimal> lstAnimaisIneficentes = GetCGPR(2);
        List<TOAnimal> lstAnimaisIntermediarios = GetCGPR(3);

        JSONArray ja = new JSONArray();
        
        JSONObject j2 = new JSONObject();
        
        for (TOAnimal animal : lstAnimaisEficentes) {
            ja.put(animal.getJson());
        }
        j2.put("eficientes", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIntermediarios) {
            ja.put(animal.getJson());
        }
        j2.put("intermediarios", ja);
        ja = new JSONArray();
        
        for (TOAnimal animal : lstAnimaisIneficentes) {
            ja.put(animal.getJson());
        }
        j2.put("ineficientes", ja);
        
        JSONObject j = new JSONObject();

        try {
            j.put("CGPR", j2);
            //j.put("list", ja);
            j.put("success", true);
        } catch (Exception e) {
            j.put("success", false);
            j.put("message", e.getMessage());
        }

        return j.toString();

    }
    
    /// 1 = Eficiente 2 Ineficiente 3 Intermediario
    public List<TOAnimal> GetCAR(Integer lnIndice) throws Exception {

        List<TOAnimal> ListAnimais = new ArrayList<TOAnimal>();
        String NomeInstancia, Nome, Sexo, Numero, InstanciaCobertura, DataCobertura;
        String RGD, Nascimento;
        Date dNascimento;

        int Codigo;

        String queryString;
        Query query;

        queryString = "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX embrapa: <http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#>\n"
                + "SELECT distinct ?Nome ?Numero ?Registro ?Nascimento ?Codigo ";
        
        switch (lnIndice) {
            case 1:
                queryString += "WHERE {?Cattle a embrapa:Efficient_CAR.\n";
                break;
            case 2:
                queryString += "WHERE {?Cattle a embrapa:Inefficient_CAR.\n";
                break;
            case 3:
                queryString += "WHERE {?Cattle a embrapa:Intermediary_CAR.\n";
                break;
        }

        queryString += "?Cattle embrapa:Cattle_Name ?Nome.\n"
                + "?Cattle embrapa:Cattle_Number ?Numero.\n"
                + "?Cattle embrapa:Cattle_Birth ?Nascimento.\n"
                + "?Cattle embrapa:Cattle_RGD ?Registro.\n"
                + "?Cattle embrapa:Cattle_Code ?Codigo.\n"
                + "}";

        query = QueryFactory.create(queryString);
        try (QueryExecution execution = QueryExecutionFactory.create(query, infModel)) {
            ResultSet results = execution.execSelect();
            List<String> list = new ArrayList<>();

            while (results.hasNext()) {
                QuerySolution qs = results.next();
                String lxGado = qs.toString();
                Nome = qs.getLiteral("Nome").getString();
                Numero = qs.getLiteral("Numero").getString();
                Codigo = Integer.parseInt(qs.getLiteral("Codigo").getString());
                RGD = qs.getLiteral("Registro").getString();
                Nascimento = qs.getLiteral("Nascimento").getString();

                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = Nascimento.substring(0, 2) + "-"
                        + Nascimento.substring(2, 4) + "-" + Nascimento.substring(4, 8);

                Date dnascimento = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

                ListAnimais.add(new TOAnimal(Codigo, RGD, Numero, 0, 0, dnascimento,
                        Nome, ""));

            }
        }

        return ListAnimais;

    }
    /// 1 = Eficiente 2 Ineficiente 3 Intermediario
    public List<TOAnimal> GetECA(Integer lnIndice) throws Exception {

        List<TOAnimal> ListAnimais = new ArrayList<TOAnimal>();
        String NomeInstancia, Nome, Sexo, Numero, InstanciaCobertura, DataCobertura;
        String RGD, Nascimento;
        Date dNascimento;

        int Codigo;

        String queryString;
        Query query;

        queryString = "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX embrapa: <http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#>\n"
                + "SELECT distinct ?Nome ?Numero ?Registro ?Nascimento ?Codigo ";
        
        switch (lnIndice) {
            case 1:
                queryString += "WHERE {?Cattle a embrapa:Efficient_ECA.\n";
                break;
            case 2:
                queryString += "WHERE {?Cattle a embrapa:Inefficient_ECA.\n";
                break;
            case 3:
                queryString += "WHERE {?Cattle a embrapa:Intermediary_ECA.\n";
                break;
        }

        queryString += "?Cattle embrapa:Cattle_Name ?Nome.\n"
                + "?Cattle embrapa:Cattle_Number ?Numero.\n"
                + "?Cattle embrapa:Cattle_Birth ?Nascimento.\n"
                + "?Cattle embrapa:Cattle_RGD ?Registro.\n"
                + "?Cattle embrapa:Cattle_Code ?Codigo.\n"
                + "}";

        query = QueryFactory.create(queryString);
        try (QueryExecution execution = QueryExecutionFactory.create(query, infModel)) {
            ResultSet results = execution.execSelect();
            List<String> list = new ArrayList<>();

            while (results.hasNext()) {
                QuerySolution qs = results.next();
                String lxGado = qs.toString();
                Nome = qs.getLiteral("Nome").getString();
                Numero = qs.getLiteral("Numero").getString();
                Codigo = Integer.parseInt(qs.getLiteral("Codigo").getString());
                RGD = qs.getLiteral("Registro").getString();
                Nascimento = qs.getLiteral("Nascimento").getString();

                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = Nascimento.substring(0, 2) + "-"
                        + Nascimento.substring(2, 4) + "-" + Nascimento.substring(4, 8);

                Date dnascimento = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

                ListAnimais.add(new TOAnimal(Codigo, RGD, Numero, 0, 0, dnascimento,
                        Nome, ""));

            }
        }

        return ListAnimais;

    }
    
        /// 1 = Eficiente 2 Ineficiente 3 Intermediario
    public List<TOAnimal> GetGPR(Integer lnIndice) throws Exception {

        List<TOAnimal> ListAnimais = new ArrayList<TOAnimal>();
        String NomeInstancia, Nome, Sexo, Numero, InstanciaCobertura, DataCobertura;
        String RGD, Nascimento;
        Date dNascimento;

        int Codigo;

        String queryString;
        Query query;

        queryString = "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX embrapa: <http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#>\n"
                + "SELECT distinct ?Nome ?Numero ?Registro ?Nascimento ?Codigo ";
        
        switch (lnIndice) {
            case 1:
                queryString += "WHERE {?Cattle a embrapa:Efficient_GPR.\n";
                break;
            case 2:
                queryString += "WHERE {?Cattle a embrapa:Inefficient_GPR.\n";
                break;
            case 3:
                queryString += "WHERE {?Cattle a embrapa:Intermediary_GPR.\n";
                break;
        }

        queryString += "?Cattle embrapa:Cattle_Name ?Nome.\n"
                + "?Cattle embrapa:Cattle_Number ?Numero.\n"
                + "?Cattle embrapa:Cattle_Birth ?Nascimento.\n"
                + "?Cattle embrapa:Cattle_RGD ?Registro.\n"
                + "?Cattle embrapa:Cattle_Code ?Codigo.\n"
                + "}";

        query = QueryFactory.create(queryString);
        try (QueryExecution execution = QueryExecutionFactory.create(query, infModel)) {
            ResultSet results = execution.execSelect();
            List<String> list = new ArrayList<>();

            while (results.hasNext()) {
                QuerySolution qs = results.next();
                String lxGado = qs.toString();
                Nome = qs.getLiteral("Nome").getString();
                Numero = qs.getLiteral("Numero").getString();
                Codigo = Integer.parseInt(qs.getLiteral("Codigo").getString());
                RGD = qs.getLiteral("Registro").getString();
                Nascimento = qs.getLiteral("Nascimento").getString();

                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = Nascimento.substring(0, 2) + "-"
                        + Nascimento.substring(2, 4) + "-" + Nascimento.substring(4, 8);

                Date dnascimento = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

                ListAnimais.add(new TOAnimal(Codigo, RGD, Numero, 0, 0, dnascimento,
                        Nome, ""));

            }
        }

        return ListAnimais;

    }
           /// 1 = Eficiente 2 Ineficiente 3 Intermediario
    public List<TOAnimal> GetCGPR(Integer lnIndice) throws Exception {

        List<TOAnimal> ListAnimais = new ArrayList<TOAnimal>();
        String NomeInstancia, Nome, Sexo, Numero, InstanciaCobertura, DataCobertura;
        String RGD, Nascimento;
        Date dNascimento;

        int Codigo;

        String queryString;
        Query query;

        queryString = "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX embrapa: <http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#>\n"
                + "SELECT distinct ?Nome ?Numero ?Registro ?Nascimento ?Codigo ";
        
        switch (lnIndice) {
            case 1:
                queryString += "WHERE {?Cattle a embrapa:Efficient_CGPR.\n";
                break;
            case 2:
                queryString += "WHERE {?Cattle a embrapa:Inefficient_CGPR.\n";
                break;
            case 3:
                queryString += "WHERE {?Cattle a embrapa:Intermediary_CGPR.\n";
                break;
        }

        queryString += "?Cattle embrapa:Cattle_Name ?Nome.\n"
                + "?Cattle embrapa:Cattle_Number ?Numero.\n"
                + "?Cattle embrapa:Cattle_Birth ?Nascimento.\n"
                + "?Cattle embrapa:Cattle_RGD ?Registro.\n"
                + "?Cattle embrapa:Cattle_Code ?Codigo.\n"
                + "}";

        query = QueryFactory.create(queryString);
        try (QueryExecution execution = QueryExecutionFactory.create(query, infModel)) {
            ResultSet results = execution.execSelect();
            List<String> list = new ArrayList<>();

            while (results.hasNext()) {
                QuerySolution qs = results.next();
                String lxGado = qs.toString();
                Nome = qs.getLiteral("Nome").getString();
                Numero = qs.getLiteral("Numero").getString();
                Codigo = Integer.parseInt(qs.getLiteral("Codigo").getString());
                RGD = qs.getLiteral("Registro").getString();
                Nascimento = qs.getLiteral("Nascimento").getString();

                DateFormat ConversorDate2 = new SimpleDateFormat("dd-MM-yyyy");

                String dateTemp = Nascimento.substring(0, 2) + "-"
                        + Nascimento.substring(2, 4) + "-" + Nascimento.substring(4, 8);

                Date dnascimento = new java.sql.Date(ConversorDate2.parse(dateTemp).getTime());

                ListAnimais.add(new TOAnimal(Codigo, RGD, Numero, 0, 0, dnascimento,
                        Nome, ""));

            }
        }

        return ListAnimais;

    }

}
