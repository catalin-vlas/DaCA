package com.wade.daca.sparql.impl;

import com.wade.daca.sparql.api.SparqlService;
import com.wade.daca.sparql.dataobjects.RdfStats;
import com.wade.daca.sparql.dataobjects.RdfTriple;
import com.wade.daca.sparql.helper.RdfStorageHelper;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.rio.RDFFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SparqlServiceImpl implements SparqlService {

    RdfStorageHelper rdfStorageHelper;

    @Autowired
    public SparqlServiceImpl() {
        rdfStorageHelper = RdfStorageHelper.getInstance();
    }

    @Override
    public List<String> getNamespaces() {
        List<String> namespaces = new ArrayList<>();

        try {
            namespaces = rdfStorageHelper.getNamespaces();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return namespaces;
    }

    @Override
    @RequestMapping(value = "/namespace/{namespaceId}", method = RequestMethod.POST)
    public String createNamespace(@PathVariable("namespaceId") String namespaceId) {
        String result = "";

        try {
            result = rdfStorageHelper.createNamespace(namespaceId, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/namespace/{namespaceId}", method = RequestMethod.DELETE)
    public String deleteNamespace(@PathVariable("namespaceId") String namespaceId) {
        String result = "Success";

        try {
            rdfStorageHelper.deleteNamespace(namespaceId);
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure";
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/triples/{namespaceId}", method = RequestMethod.GET)
    public List<RdfTriple> getTriples(@PathVariable("namespaceId") String namespaceId) {
        ArrayList<RdfTriple> result = new ArrayList<>();

        try {
            ArrayList<BindingSet> values = rdfStorageHelper.listNamespaceTriples(namespaceId);

            for (BindingSet value : values) {
                RdfTriple rdfTriple = new RdfTriple(value.getValue("s").stringValue(),
                        value.getValue("p").stringValue(),
                        value.getValue("o").stringValue());

                result.add(rdfTriple);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/triples/stats/{namespaceId}", method = RequestMethod.GET)
    public RdfStats getTriplesStats(@PathVariable("namespaceId") String namespaceId) throws Exception {
        return rdfStorageHelper.getStats(namespaceId);
    }

    @Override
    @RequestMapping(value = "/triples/{namespaceId}", method = RequestMethod.POST)
    public String addTriples(@PathVariable("namespaceId") String namespaceId,
                             @RequestBody ArrayList<RdfTriple> triples) {
        String result = "Success";

        try {
            List<Statement> statements = new ArrayList<>();

            // build list of Statements
            for (RdfTriple rdfTriple : triples) {
                URIImpl subject = new URIImpl(rdfTriple.getSubject());
                URIImpl predicate = new URIImpl(rdfTriple.getPredicate());
                Literal object = new LiteralImpl(rdfTriple.getObject());
                Statement stmt = new StatementImpl(subject, predicate, object);
                statements.add(stmt);
            }

            rdfStorageHelper.insertTriples(namespaceId, statements);
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure";
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/triples/{namespaceId}", method = RequestMethod.PUT)
    public String addTriplesFromFile(@PathVariable("namespaceId") String namespaceId,
                                     @RequestParam("file") MultipartFile file) {
        String result = "Success";

        try {
            // TODO: If the format of the file isn't NQUADS, return an error!
            rdfStorageHelper.insertTriples(namespaceId, file.getInputStream(), RDFFormat.NQUADS);
            rdfStorageHelper.computeStats(namespaceId);
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure";
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/sparql/query", method = RequestMethod.GET)
    public List<RdfTriple> executeSparqlQuery(@RequestParam(value = "namespaceId", required = true) String namespaceId,
                                              @RequestParam(value = "query", required = true) String query) {
        ArrayList<RdfTriple> result = new ArrayList<>();

        try {
            ArrayList<BindingSet> values = rdfStorageHelper.executeCustomQuery(namespaceId, query);

            for (BindingSet value : values) {
                RdfTriple rdfTriple = new RdfTriple(value.getValue("s").stringValue(),
                        value.getValue("p").stringValue(),
                        value.getValue("o").stringValue());

                result.add(rdfTriple);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/sparql/query", method = RequestMethod.POST)
    public String executeSparqlUpdate(@RequestParam(value = "namespaceId", required = true) String namespaceId,
                                      @RequestParam(value = "query", required = true) String query) {
        String result = "Success";

        try {
            rdfStorageHelper.executeCustomUpdate(namespaceId, query);
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure";
        }

        return result;
    }
}
