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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author Heitor
 */
@Path("ontologia")
public class ServiceOntologia {
    
    public static final String lcURI = "http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#";
    private static final String lcOntology = "file:///C:/Embrapa.owl";
      //variavel para acesso à ontologia com inferencia
    private final InfModel infModel;
    //variavel para acesso à ontologia sem inferencia
    private final OntModel ontModel;
    
    
    public ServiceOntologia(){
        Model model = ModelFactory.createDefaultModel();
        model.read(lcOntology);

        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
        ontModel.prepare();

        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        infModel = ModelFactory.createInfModel(reasoner, model);
        infModel.prepare();
        
        
    }
    
    
    public static List<TOAnimal> GadosExperimento(String NomeExperimento){
        
        
        List<TOAnimal> ListAnimais = new ArrayList<TOAnimal>();
        String NomeInstancia,Nome,Sexo,Numero,InstanciaCobertura,DataCobertura,ReceptorCobertura,TipoCobertura;
        String RGD,Nascimento; 
        
        Model model;
        String queryString;
        Query query;
               
       mexer................... 
        queryString =    "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"+
                                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
                                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                                "PREFIX embrapa: <http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#>\n" +
                                "SELECT distinct ?InstanciaGado ?Nome ?Numero?Sexo ?InstanciaCobertura?DataCobertura?Receptor ?RGD ?Nascimento "+
                                "WHERE {?"+NomeExperimento.trim()+" embrapa:hasParticipant ?InstanciaGado.\n"+
                                "?InstanciaGado embrapa:Cattle_Name ?Nome.\n"+
                                "?InstanciaGado embrapa:Cattle_Number ?Numero.\n"+
                                "?InstanciaGado embrapa:Cattle_Sex ?Sexo.\n"+
                                "?InstanciaGado embrapa:Cattle_RGD ?RGD.\n"+
                                "?InstanciaGado embrapa:Cattle_Birth ?Nascimento.\n"+
                                "?InstanciaGado embrapa:GenerateBy ?InstanciaCobertura. \n" +
                                "?InstanciaCobertura embrapa:Coverage_Date ?DataCobertura.\n" +
                                "OPTIONAL{?InstanciaCobertura embrapa:Coverage_Receive ?Receptor.}}";
   
        
        query = QueryFactory.create(queryString);
        try(QueryExecution qexec = QueryExecutionFactory.create(query,model)) {
            ResultSet results = qexec.execSelect();
            while( results.hasNext() ){
                QuerySolution soln = results.nextSolution();
                //Resource name = soln.getResource("subClass");
                String InstanciaGado  = soln.toString();
                InstanciaGado = InstanciaGado.substring(InstanciaGado.indexOf("#")+1, InstanciaGado.indexOf(">"));
                 
                NomeInstancia = InstanciaGado;
                Nome = soln.getLiteral("Nome").getString();
                Numero = soln.getLiteral("Numero").getString();
                Sexo = soln.getLiteral("Sexo").getString();
                RGD = soln.getLiteral("RGD").getString();
                Nascimento = soln.getLiteral("Nascimento").getString();
                
                String InstanciaCoberturaTemp  = soln.toString();
                
                int InicioCorte = InstanciaCoberturaTemp.indexOf("InstanciaCobertura = <http://www.semanticweb.org/heitor/ontologies/2016/6/Embrapa#")+82;
                int FimCorte = InstanciaCoberturaTemp.indexOf("> ) ( ?DataCobertura = " );
                        
                        
                InstanciaCobertura = InstanciaCoberturaTemp.substring(InicioCorte,FimCorte);
                               
                switch (InstanciaCobertura.substring(0, 2)){
                    
                case "TE":
                    TipoCobertura = "TRANSFERÊNCIA DE EMBRIÃO";
                    ReceptorCobertura = soln.getLiteral("Receptor").getString();
                    break;
                case "ET":
                    TipoCobertura = "TRANSFERÊNCIA DE EMBRIÃO";
                    ReceptorCobertura = soln.getLiteral("Receptor").getString();
                    break;    
                case "IA":
                    TipoCobertura = "INSEMINAÇÃO ARTIFICIAL";
                    ReceptorCobertura = "";
                    break;    
                case "AI":
                    TipoCobertura = "INSEMINAÇÃO ARTIFICIAL";
                    ReceptorCobertura = "";
                    break;    
                default:
                    TipoCobertura = "N/D";
                    ReceptorCobertura = "";
                    break;     
                }
                
                 
                DataCobertura = soln.getLiteral("DataCobertura").getString();
                
                        
                ListGados.add(new Gado(NomeInstancia,Numero,Sexo,Nome,
                        TipoCobertura,DataCobertura,ReceptorCobertura,
                        InstanciaCobertura,RGD,Nascimento));
                
                
                
            }
        }
        return ListGados;
        
    }
    
    
    
    @GET
    @Path("list")
    public String GraficoLinha() throws JSONException {
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
    
    public List<String> sparqlGetIndividualsByClassInf(String prefix, String entidade) {
        String queryStr = "PREFIX : <" + lcURI + "> \n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "SELECT DISTINCT ?" + entidade + "\n"
                + "WHERE { \n"
                + " ?" + entidade + " rdf:type :" + entidade + " . \n"
                + " }  \n";

        Query query = QueryFactory.create(queryStr);
        QueryExecution execution = QueryExecutionFactory.create(query, infModel);
        ResultSet results = execution.execSelect();
        List<String> list = new ArrayList<>();
        while (results.hasNext()) {
            QuerySolution qs = results.next();
            Resource resource = qs.getResource("?" + entidade + "");
            if (resource.toString() != null && !resource.toString().equals("null")) {
                list.add(resource.toString());
            }
        }
        execution.close();

        return list;
    }
    
}
