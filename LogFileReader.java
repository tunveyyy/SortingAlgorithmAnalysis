import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LogFileReader {
    public List<Log> readFile(File filename){

        List<Log> logs = new ArrayList<>();

        try{
            Scanner scanFile =  new Scanner(filename);
            while(scanFile.hasNext()){
                Log log = new Log(scanFile.nextLine());
                logs.add(log);
            }

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return logs;

     }

}
