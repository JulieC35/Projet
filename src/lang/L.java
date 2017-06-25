/**
 * Allows to retrieve informations about current localization
 */

package lang;

import java.util.*;
import library.*;

public class L {
    private static HashMap<String, String> languageTable;

    /**
     * @param code The identifier of the translation to retrieve
     * @return The corresponding translation
     */
    public static String get(String code){
        return ( code != null && languageTable != null && languageTable.get(code) != (null) ) ? languageTable.get(code) : code;
    }

    public static void initialize(String lang){
        if ( lang != null ){
            try{
                Language tempLanguage = Language.valueOf(lang);
                L.languageTable = FilesHandler.xmlToMap("lang/" + tempLanguage + ".xml");
            } catch (IllegalArgumentException ex){
                System.out.println("Language initialization error: " + ex.getMessage());
                L.languageTable = FilesHandler.xmlToMap("lang/ENGLISH.xml");
            }
        }
    }
}