package xmltransformator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Daniele
 */
public class XMLTransformator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource(new File("ACMTrento.xsl"));
            Transformer transformer = factory.newTransformer(xslt);            
            StreamSource text = new StreamSource(new File("ACMTrento.xml"));
            transformer.transform(text, new StreamResult(new FileOutputStream("output.csv")));
        } catch (FileNotFoundException | TransformerException ex) {
            System.err.println(ex);
        }   
    }   
}