import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static String fileName = "simInputValid.txt";
//    private static String fileName = "simInputInvalid.txt";
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

            while(inputScan.hasNextLine()){
                inputParse = inputScan.nextLine();
                fileContents.add(inputParse);
            }
        }
        catch (FileNotFoundException e) {
            setFileGood(false);
            return null;
        }

//        System.out.println(fileContents);

        return fileContents;
    }

    public boolean writeFile(String[] fileContents){

        if(isFileGood()) {

            try (PrintWriter printer = new PrintWriter(inputFile)) {
                for(int i=0; i<fileContents.length; i++){
                    printer.print(fileContents[i]);
                    if(i!=fileContents.length-1){
                        printer.print('\n');
                    }
                }

                setFileGood(true);
            }catch (FileNotFoundException e){
                setFileGood(false);

                if(createFile(new File(getFileName()))) {
                    setFileGood(writeFile(fileContents));
                }
            }

        }else{
            if(inputFile.delete()){
                if(createFile(new File(getFileName()))){
                    setFileGood(writeFile(fileContents));
                }
            }else{setFileGood(false);}
        }

        return isFileGood();
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

