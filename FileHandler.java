import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static String fileName = "simInputInvalid.txt";
    private boolean fileGood;
    private final File inputFile;

    // Constructor
    FileHandler() {

        inputFile = new File(fileName);

        setFileGood(false);

        if(inputFile.exists()){
            if(inputFile.canRead()){
                setFileGood(true);}
            else{
                setFileGood(inputFile.setReadable(true, true));}
        }
        else{
            setFileGood(createFile(inputFile));}

    }

    // Validity Checks
    public boolean isFileGood() {
        return fileGood;
    }
    private void setFileGood(boolean fileGood){
        this.fileGood = fileGood;
    }

    // File Management
    public ArrayList<String> readFile(){

        ArrayList<String> fileContents = new ArrayList<>();
        String inputParse;
        Scanner inputScan;
        try{
            inputScan = new Scanner(inputFile);

            while(inputScan.hasNext()){
                inputParse = inputScan.next();
                fileContents.add(inputParse);
            }
        }
        catch (FileNotFoundException e) {
            setFileGood(false);
            return null;
        }

        return fileContents;
    }

    public boolean writeFile(ArrayList<String> fileContents){

        if(fileGood) {

            try (PrintWriter printer = new PrintWriter(inputFile)) {

                for (String line : fileContents) {
                    printer.println(line);
                }

            }catch (FileNotFoundException e){setFileGood(false);}

        }else{return false;}

        return true;
    }

    private boolean createFile(File inputFile){

        boolean success;

        try{success = inputFile.createNewFile();}
        catch(IOException e){success = false;}

        return success;

    }

    // File Name Get/Set
    public static String getFileName() {
        return fileName;
    }

    public static boolean setFileName(String fileName) {
        if(fileName!=null && !fileName.isEmpty()){
            FileHandler.fileName = fileName;
            return true;
        }else{return false;}
    }



}

