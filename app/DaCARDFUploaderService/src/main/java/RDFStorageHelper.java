import com.bigdata.rdf.sail.webapp.SD;
import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import org.apache.log4j.Logger;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.rio.RDFFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

//this need to be wrapped with a restful web service
//here is a spring example
//in order to start the blazegraph database we can use blazegraph.jar
//ex: C:\Users\catavlas>java -server -Xmx4g -jar C:\Users\catavlas\Downloads\blazegraph.jar
public class RDFStorageHelper {

    private static final Logger log = Logger.getLogger(RDFStorageHelper.class);
    private static final String serviceURL = "http://localhost:9999/blazegraph";
    private static RemoteRepositoryManager rpm;
    private static Properties defaultNamespaceProperties;
    private static RDFStorageHelper instance;

    public static RDFStorageHelper getInstance() {
        if (instance == null) instance = new RDFStorageHelper();
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
            defaultNamespaceProperties.setProperty("com.bigdata.journal.AbstractJournal.file","default_blazegraph.jnl");
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

    //each data-set must have a different namespace, suggested format is: user_id-dataset_id
    //this will method create a namespace if it doesn't exists already
    public String createNamespace(String namespace, Properties properties) throws Exception {

        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();

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
            if (properties==null) properties = getDefaultRepoProperties();
            rpm.createRepository(namespace,properties);
        }

        return null;
    }

    public void deleteNamespace(String namespace) throws Exception {
        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();

        rpm.deleteRepository(namespace);
    }

    public void insertTriples(String namespace,ArrayList<Statement> stmts) throws Exception {
        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();

        rpm.getRepositoryForNamespace(namespace).add(new RemoteRepository.AddOp(stmts));
    }

    public void insertTriples(String namespace, InputStream tripleIS, RDFFormat rdfFormat) throws IOException {
        if (tripleIS == null) {
            throw new IOException("null input stream");
        }
        try {
            RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();
            rpm.getRepositoryForNamespace(namespace).add(new RemoteRepository.AddOp(tripleIS, rdfFormat));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tripleIS.close();
        }
    }

    public ArrayList<BindingSet> listNamespaceTriples(String namespace) throws Exception {
        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();

        //if the number of triples is big this needs to be paginated
        TupleQueryResult result = rpm.getRepositoryForNamespace(namespace).prepareTupleQuery("select * {?s ?p ?o}").evaluate();
        ArrayList<BindingSet> resultList = new ArrayList<BindingSet>();

        while (result.hasNext()) resultList.add(result.next());

        return resultList;
    }

    public void clearNamespace(String namespace) throws Exception {
        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();

        rpm.getRepositoryForNamespace(namespace).prepareUpdate("DELETE { ?s ?p ?o } where { ?s ?p ?o }").evaluate();
    }

    public ArrayList<BindingSet> executeCustomQuery(String namespace, String query) throws Exception {
        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();

        //if the number of triples is big this needs to be paginated
        TupleQueryResult result = rpm.getRepositoryForNamespace(namespace).prepareTupleQuery(query).evaluate();
        ArrayList<BindingSet> resultList = new ArrayList<BindingSet>();

        while (result.hasNext()) resultList.add(result.next());

        return resultList;
    }

    public void executeCustomUpdate(String namespace, String update) throws Exception {
        RemoteRepositoryManager rpm = RDFStorageHelper.getRemoteRepositoryManager();
        rpm.getRepositoryForNamespace(namespace).prepareUpdate(update).evaluate();
    }

    public static void main(String[] args) throws Exception {

        //Properties properties = (Properties) getDefaultRepoProperties().clone();

        RDFStorageHelper helper = new RDFStorageHelper();

        helper.createNamespace("test",null);

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


        /*
        //Insert using input stream in n3 or nquads formats
        File f = new File("C:\\Users\\catavlas\\Desktop\\tioman_virus.n3");

        helper.insertTriples("test",new FileInputStream(f),RDFFormat.NQUADS);
        */

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
