package com.wade.daca.sparql.helper;

import com.bigdata.rdf.sail.webapp.SD;
import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import com.sun.org.apache.xerces.internal.util.URI;
import com.wade.daca.sparql.dataobjects.RdfStats;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.openrdf.model.Statement;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.rio.RDFFormat;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

//this need to be wrapped with a restful web service
//here is a spring example
//in order to start the blazegraph database we can use blazegraph.jar
//ex: C:\Users\catavlas>java -server -Xmx4g -jar C:\Users\catavlas\Downloads\blazegraph.jar

public class RdfStorageHelper {

    private static final Logger log = Logger.getLogger(RdfStorageHelper.class);
    private static final String serviceURL = "http://localhost:9999/blazegraph";
    private static RemoteRepositoryManager rpm;
    private static Properties defaultNamespaceProperties;
    private static RdfStorageHelper instance;

    public static RdfStorageHelper getInstance() {
        if (instance == null) instance = new RdfStorageHelper();
        return instance;
    }


    private static RemoteRepositoryManager getRemoteRepositoryManager() {
        if ( rpm==null ) rpm = new RemoteRepositoryManager(serviceURL);
        return rpm;
    }

    private static Properties getDefaultRepoProperties() {
        if (defaultNamespaceProperties == null) {
            defaultNamespaceProperties = new Properties();
            defaultNamespaceProperties.setProperty("com.bigdata.namespace.kb.spo.com.bigdata.btree.BTree.branchingFactor","1024");
            defaultNamespaceProperties.setProperty("com.bigdata.journal.AbstractJournal.bufferMode","DiskRW");
            defaultNamespaceProperties.setProperty("com.bigdata.journal.AbstractJournal.initialExtent","20971520");
            defaultNamespaceProperties.setProperty("com.bigdata.rdf.store.AbstractTripleStore.vocabularyClass","com.bigdata.rdf.vocab.core.BigdataCoreVocabulary_v20160317");
            defaultNamespaceProperties.setProperty("com.bigdata.rdf.store.AbstractTripleStore.textIndex","false");
            defaultNamespaceProperties.setProperty("com.bigdata.rdf.store.AbstractTripleStore.axiomsClass","com.bigdata.rdf.axioms.NoAxioms");
            defaultNamespaceProperties.setProperty("com.bigdata.service.AbstractTransactionService.minReleaseAge","1");
            defaultNamespaceProperties.setProperty("com.bigdata.relation.class","com.bigdata.rdf.store.LocalTripleStore");
            defaultNamespaceProperties.setProperty("com.bigdata.rdf.store.AbstractTripleStore.quads","false");
            defaultNamespaceProperties.setProperty("com.bigdata.rdf.store.AbstractTripleStore.statementIdentifiers","false");
        }

        return defaultNamespaceProperties;
    }

    public List<String> getNamespaces() throws Exception {
        List<String> namespaces = new ArrayList<>();

        // get all namespaces
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();
        GraphQueryResult result = rpm.getRepositoryDescriptions();

        boolean exists = false;
        while (result.hasNext()) {
            Statement stmt = result.next();
            if (stmt.getPredicate()
                    .toString()
                    .equals(SD.KB_NAMESPACE.stringValue())) {
                namespaces.add(stmt.getObject().stringValue());
            }
        }

        return namespaces;
    }

    //each data-set must have a different namespace, suggested format is: user_id-dataset_id
    //this will method create a namespace if it doesn't exists already
    public String createNamespace(String namespace, Properties properties) throws Exception {

        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();

        //check if namespace already exists
        GraphQueryResult result = rpm.getRepositoryDescriptions();

        boolean exists = false;
        while (result.hasNext()) {
            Statement stmt = result.next();
            if (stmt.getPredicate()
                    .toString()
                    .equals(SD.KB_NAMESPACE.stringValue())) {
                if (namespace.equals(stmt.getObject().stringValue())) {
                    exists = true;
                    break;
                }
            }
        }

        if (!exists) {//create a new namespace
            if (properties == null) {
                properties = getDefaultRepoProperties();
                properties.setProperty("com.bigdata.journal.AbstractJournal.file", namespace + ".jnl");
            }

            rpm.createRepository(namespace, properties);
        }

        return "Success";
    }

    public void deleteNamespace(String namespace) throws Exception {
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();

        rpm.deleteRepository(namespace);
    }

    public void insertTriples(String namespace, List<Statement> stmts) throws Exception {
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();

        rpm.getRepositoryForNamespace(namespace).add(new RemoteRepository.AddOp(stmts));
    }

    public void insertTriples(String namespace, InputStream tripleIS, RDFFormat rdfFormat) throws IOException {
        if (tripleIS == null) {
            throw new IOException("null input stream");
        }
        try {
            RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();
            rpm.getRepositoryForNamespace(namespace).add(new RemoteRepository.AddOp(tripleIS, rdfFormat));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tripleIS.close();
        }
    }

    public ArrayList<BindingSet> listNamespaceTriples(String namespace) throws Exception {
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();

        //if the number of triples is big this needs to be paginated
        TupleQueryResult result = rpm.getRepositoryForNamespace(namespace).prepareTupleQuery("select * {?s ?p ?o}").evaluate();
        ArrayList<BindingSet> resultList = new ArrayList<BindingSet>();

        while (result.hasNext()) resultList.add(result.next());

        return resultList;
    }

    public void computeStats(String namespaceId) throws Exception {
        ArrayList<BindingSet> triples = listNamespaceTriples(namespaceId);

        RdfStats stats = new RdfStats();

        HashMap<String, Integer> inDegree = new HashMap<>();
        HashMap<String, Integer> outDegree = new HashMap<>();
        HashMap<String, Boolean> viz = new HashMap<>();

        for (int i=0; i<triples.size(); ++i){

            String s = triples.get(i).getBinding("s").getValue().stringValue();
            String p = triples.get(i).getBinding("p").getValue().stringValue();
            String o = triples.get(i).getBinding("o").getValue().stringValue();

            stats.setNrTriples(stats.getNrTriples()+1);
            if (p.endsWith("type")) stats.setNrType(stats.getNrType()+1);
            stats.setSize(stats.getSize()+s.length()+p.length()+o.length()+3);

            Integer idg = inDegree.get(o);
            int add=0;
            if (idg==null) {
                if (viz.get(o)==null) {
                    stats.setNrNodes(stats.getNrNodes()+1);
                    viz.put(o,true);
                    add=1;
                }
                else add=0;

                inDegree.put(o,new Integer(1));

                try {
                    new URL(o);
                    stats.setNrURINodes(stats.getNrURINodes()+add);
                } catch (Exception e) {

                    if (!triples.get(i).getBinding("o").getValue().toString().startsWith("_:"))
                         stats.setNrLiterals(stats.getNrLiterals()+add);
                    else stats.setNrBlankNodes(stats.getNrBlankNodes()+add);
                }
            }
            else inDegree.put(o,new Integer(idg+1));

            Integer odg = outDegree.get(s);
            add=0;
            if (odg==null) {
                if (viz.get(s)==null) {
                    stats.setNrNodes(stats.getNrNodes()+1);
                    viz.put(s,true);
                    ++add;
                }
                else add=0;

                outDegree.put(s,new Integer(1));

                try {
                    new URL(s);
                    stats.setNrURINodes(stats.getNrURINodes()+add);
                } catch (Exception e) {
                    if (!triples.get(i).getBinding("s").getValue().toString().startsWith("_:"))
                         stats.setNrLiterals(stats.getNrLiterals()+add);
                    else stats.setNrBlankNodes(stats.getNrBlankNodes()+add);
                }
            }
            else outDegree.put(s,odg+1);
        }

        Iterator it = inDegree.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String node = (String) pair.getKey();
            Integer inDg = (Integer) pair.getValue();

            if (inDg > stats.getMaxInDegree()) {
                stats.setMaxInDegree(inDg);
                stats.setMaxInDegreeNode(node);
            }

            Integer totDegree = inDg + (outDegree.get(node)!=null?outDegree.get(node):0);

            if (totDegree > stats.getMaxDegree()) {
                stats.setMaxDegree(totDegree);
                stats.setMaxDegreeNode(node);
            }
        }

        it = outDegree.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String node = (String) pair.getKey();
            Integer outDg = (Integer) pair.getValue();

            if (outDg > stats.getMaxOutDegree()) {
                stats.setMaxOutDegree(outDg);
                stats.setMaxOutDegreeNode(node);
            }

            Integer totDegree = outDg + (inDegree.get(node)!=null?inDegree.get(node):0);

            if (totDegree > stats.getMaxDegree()) {
                stats.setMaxDegree(totDegree);
                stats.setMaxDegreeNode(node);
            }
        }

        //save stats
        stats.setNamespaceID(namespaceId);
        String updateStatement = RDFDataCubeConverter.statsToDataCube(stats);

        createNamespace(namespaceId+"_stats", null);
        insertTriples(namespaceId+"_stats", IOUtils.toInputStream(updateStatement),RDFFormat.TURTLE);
    }

    public RdfStats getStats(String namespaceId) throws Exception {
        String queryStr = "select * \n" +
                "WHERE {\n" +
                "  ?s <http://example.org/ns#NumericalValue> ?o\n" +
                "} ";

        ArrayList<BindingSet> triples = executeCustomQuery(namespaceId+"_stats",queryStr);

        return RDFDataCubeConverter.triplesToStats(triples);
    }

    public void clearNamespace(String namespace) throws Exception {
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();

        rpm.getRepositoryForNamespace(namespace).prepareUpdate("DELETE { ?s ?p ?o } where { ?s ?p ?o }").evaluate();
    }

    public ArrayList<BindingSet> executeCustomQuery(String namespace, String query) throws Exception {
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();

        //if the number of triples is big this needs to be paginated
        TupleQueryResult result = rpm.getRepositoryForNamespace(namespace).prepareTupleQuery(query).evaluate();
        ArrayList<BindingSet> resultList = new ArrayList<BindingSet>();

        while (result.hasNext()) resultList.add(result.next());

        return resultList;
    }

    public void executeCustomUpdate(String namespace, String update) throws Exception {
        RemoteRepositoryManager rpm = RdfStorageHelper.getRemoteRepositoryManager();
        rpm.getRepositoryForNamespace(namespace).prepareUpdate(update).evaluate();
    }

    public static void main(String[] args) throws Exception {

        //Properties properties = (Properties) getDefaultRepoProperties().clone();

        RdfStorageHelper helper = new RdfStorageHelper();

        helper.computeStats("test1");

        System.out.println(helper.getStats("test1"));

        //helper.createNamespace("test1",null);

        //helper.deleteNamespace("test");

        /*
        //Insert using statements
        URIImpl subject = new URIImpl("http://blazegraph.com/Blazegraph");
        URIImpl predicate = new URIImpl("http://blazegraph.com/says");
        Literal object = new LiteralImpl("hello");
        Statement stmt = new StatementImpl(subject, predicate, object);
        ArrayList<Statement> stmts = new ArrayList<Statement>();
        stmts.add(stmt);

        helper.insertTriples("test",stmts);
        */


        //Insert using input stream in n3 or nquads formats
        //File f = new File("C:\\Users\\catavlas\\web-project\\DaCA\\app\\DaCAServices\\DaCASparqlProcessor\\tioman.n3");

        //helper.insertTriples("test1", new FileInputStream(f),RDFFormat.NQUADS);

        /*
        //list triples from namespace
        ArrayList<BindingSet> lista = helper.listNamespaceTriples("test");

        for (int i=0; i<lista.size(); ++i) {
            log.info(lista.get(i));
        }
        */

        /*
         helper.clearNamespace("test");
        */

        /*
        String customQuery = "select * where { ?s rdf:type ?o }";

        ArrayList<BindingSet> lista = helper.executeCustomQuery("test",customQuery);

        for (int i=0; i<lista.size(); ++i) {
            log.info(lista.get(i));
        }*/
    }


}
