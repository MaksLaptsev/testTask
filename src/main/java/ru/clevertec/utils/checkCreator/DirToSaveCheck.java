package ru.clevertec.utils.checkCreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class DirToSaveCheck {
    private static final Logger logger = LoggerFactory.getLogger(DirToSaveCheck.class);
    public static String path;
    public static String directoryCreateFolder(){
        String folder = path+"\\RecipeFolder"+"\\Recipe.pdf";
        String regularExpression = "[a-zA-Z]:\\\\(?:([^<>:\"\\/\\\\|?*]*[^<>:\"\\/\\\\|?*.]\\\\|..\\\\)*([^<>:\"\\/\\\\|?*]*[^<>:\"\\/\\\\|?*.]\\\\?|..\\\\))?";

        try{
            if(!Pattern.matches(regularExpression,path)){
                throw new IOException();
            }
            Files.createDirectories(Paths.get(path+"\\RecipeFolder"));
            return folder;
        }catch (IOException e){
            logger.info("the path is specified incorrectly - "+path+"\n the receipt will be saved in the default folder");
            return System.getProperty("user.dir")+"\\Recipe.pdf";
        }
    }

    public static void checkPathInParams(String[] s){
        if(s == null || s.length == 0){
            path = System.getProperty("user.dir");
        }
        else {
            for (String str:s) {
                if(str.replace(" ","").split("-")[0].equals("pathToSave") &
                        str.replace(" ","").split("-").length>1){

                    path = str.replace(" ","").split("-")[1];
                }else {
                    path = System.getProperty("user.dir");
                }
            }
        }

    }
}
