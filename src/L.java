/**
 * Allows to retrieve informations about current localization
 */

import java.util.*;

public class L {
    private static Language currentLanguage = Language.FRENCH;
    private static HashMap<String, String> languageTable = FilesHandler.xmlToMap("lang/" + currentLanguage + ".xml");

    /**
     * @param code The identifier of the translation to retrieve
     * @return The corresponding translation
     */
    public static String get(String code){
        return ( code != null && languageTable.get(code) != (null) ) ? languageTable.get(code) : code;
    }
}