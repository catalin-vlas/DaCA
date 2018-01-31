package com.wade.daca.sparql.helper;

import com.wade.daca.sparql.dataobjects.RdfStats;
import org.openrdf.query.BindingSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by catavlas on 1/30/2018.
 */
public class RDFDataCubeConverter {

    private static String TemplateLocation = "src\\main\\resources\\RDFDataCubeStatTemplate.txt";

    private static String Template = null;

    private static void loadTemplate() {
        if (Template == null){
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(TemplateLocation)))
            {

                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null)
                {
                    contentBuilder.append(sCurrentLine).append("\n");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Template = contentBuilder.toString();
        }
    }
    
    public static String statsToDataCube(RdfStats stats){
        loadTemplate();
        String cube = new String(Template);
        
        cube = cube.replaceFirst("\\{Size\\}",Integer.toString(stats.getSize()));
        cube = cube.replaceFirst("\\{NrTriples\\}",Integer.toString(stats.getNrTriples()));
        cube = cube.replaceFirst("\\{NrNodes\\}",Integer.toString(stats.getNrNodes()));
        cube = cube.replaceFirst("\\{NrBlankNodes\\}",Integer.toString(stats.getNrBlankNodes()));
        cube = cube.replaceFirst("\\{NrURINodes\\}",Integer.toString(stats.getNrURINodes()));
        cube = cube.replaceFirst("\\{NrLiteralNodes\\}",Integer.toString(stats.getNrLiterals()));
        cube = cube.replaceFirst("\\{NrTypeTriples\\}",Integer.toString(stats.getNrType()));
        cube = cube.replaceFirst("\\{MaxInDegree\\}",Integer.toString(stats.getMaxInDegree()));
        cube = cube.replaceFirst("\\{MaxOutDegree\\}",Integer.toString(stats.getMaxOutDegree()));
        cube = cube.replaceFirst("\\{MaxDegree\\}",Integer.toString(stats.getMaxDegree()));
        cube = cube.replaceAll("\\{namespaceId\\}",stats.getNamespaceID());

        return cube;
    }
    
    public static RdfStats triplesToStats(ArrayList<BindingSet> triples) {
        RdfStats stats = new RdfStats();

        for (int i=0; i<triples.size(); ++i) {
            String s = triples.get(i).getBinding("s").getValue().stringValue();
            String o = triples.get(i).getBinding("o").getValue().stringValue();

            switch (s) {
                case "http://example.org/ns#o1": stats.setSize(Integer.parseInt(o)); break;
                case "http://example.org/ns#o2": stats.setNrTriples(Integer.parseInt(o)); break;
                case "http://example.org/ns#o3": stats.setNrNodes(Integer.parseInt(o)); break;
                case "http://example.org/ns#o4": stats.setNrBlankNodes(Integer.parseInt(o)); break;
                case "http://example.org/ns#o5": stats.setNrURINodes(Integer.parseInt(o)); break;
                case "http://example.org/ns#o6": stats.setNrLiterals(Integer.parseInt(o)); break;
                case "http://example.org/ns#o7": stats.setNrType(Integer.parseInt(o)); break;
                case "http://example.org/ns#o8": stats.setMaxInDegree(Integer.parseInt(o)); break;
                case "http://example.org/ns#o9": stats.setMaxOutDegree(Integer.parseInt(o)); break;
                case "http://example.org/ns#o10": stats.setMaxDegree(Integer.parseInt(o)); break;
                default:
                    System.out.println("Invalid stat");
            }
        }

        return stats;
    }
    
}
