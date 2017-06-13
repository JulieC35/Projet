/**
 * Allows To read or write in files. Contains solely static methods
 * Calling FilesHandler.xmlToMap("myFile.xml") will attempt to add to find xml tags of this shape : 
 * <element code="" value="" /> and use the values of the attributes code and value as 
 * key and value of a HashMap<String,String>
 * @author M.Lamy
 */

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class FilesHandler {
    /**
     * Allows to get a map from an xml file
     * @param filePath The xml file's path (absolute or relative)
     * @return The generated HashMap
     */
    public static HashMap<String,String> xmlToMap(String location) {
        HashMap<String,String> map = new HashMap<String,String>();

        try{
            // Initialization
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(location));
            Element root = doc.getDocumentElement();
            NodeList rootNodes = root.getChildNodes();

            for(int i = 0 ; i < rootNodes.getLength() ; i++){
                Node node = rootNodes.item(i);
                if ( node.getNodeType() == Node.ELEMENT_NODE ){
                    NamedNodeMap attributes = node.getAttributes();
                    Attr code = (Attr) attributes.item(0);
                    Attr value = (Attr) attributes.item(1);

                    map.put(code.getValue(), value.getValue());
                }
            }
        } catch ( Exception ex){
            System.err.println(ex.getMessage());
            map = null;
        }

        return map;
    }
}
