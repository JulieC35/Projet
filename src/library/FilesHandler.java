/**
 * Allows To read or write in files. Contains solely static methods.<br>
 * Calling FilesHandler.xmlToMap("myFile.xml") will attempt to add to find xml tags of this shape : 
 * <element code="" value="" /> and use the values of the attributes code and value as 
 * key and value of a HashMap<String,String>
 */

package library;

import library.entities.*;
import lang.*;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public abstract class FilesHandler {
    /**
     * Allows to get a map from an xml file
     * @param location The xml file's path (absolute or relative)
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

    /**
     * Allows to get an ArrayList of objects from an objects file
     * @param location the location of the file
     * @return a list of objects
     */
    public static ArrayList<Object> fileToList(String location){
        ArrayList<Object> ret = new ArrayList<Object>();
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(location));
            Object obj = null;
            
            obj = in.readObject();
            int i = 0;
            while(obj != null){
                ret.add(obj);
                obj = in.readObject();
                i++;
            }

        } catch(Exception e) { // To handle throwing of ClassNotFoundException and IOException
            System.err.println(e.getMessage());
        } finally {
            try {
                if ( in != null )
                    in.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * Allows to save an ArrayList of objects into a file
     * @param list The list of objects to save
     * @param location the location of the file
     */
    public static void listToFile(ArrayList<? extends Object> list, String location){
        if ( list != null && location != null ) {
            ObjectOutputStream out = null;
            try{
                out = new ObjectOutputStream(new FileOutputStream(location));
                for(Object obj : list){
                    out.writeObject(obj);
                }
            }
            catch (IOException e){
                System.err.println(e.getMessage());
            } finally {
                try {
                    if ( out != null )
                        out.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
