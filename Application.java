import com.opencsv.CSVWriter;
import com.sun.tools.jdeprscan.CSV;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Application {

    private enum Algorithm {
        BUBBLE, QUICK, MERGE
    }

    private static class InputArgumentException extends Exception {
        InputArgumentException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws  IOException {

        try {
            String fileName = args[0];

            if(fileName == null || fileName.equals("")) {
                throw new InputArgumentException("Enter a .log file path");
            }


            if(!fileName.endsWith(".log")) {
                throw new InputArgumentException("Input file is not a log file");
            }


            File file = new File(fileName);

            executeSort(file);

        }
        catch(InputArgumentException e) {
            e.printStackTrace();
        }



    }

    public static void executeSort(File file) throws IOException {

        String[] stripPath = file.toString().split("/");
        String fileName = stripPath[stripPath.length - 1].substring(0, stripPath[stripPath.length - 1].length() - 4);
        String outputFile = fileName + "_Output.csv";
        CSVWriter csvWriter = new CSVWriter(
                new FileWriter(outputFile),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
        );

        String[] headerRecord = {"Algorithm Name", "File Name", "File Size", "Load Time", "Sort Time", "Load + Sort Time"};
        csvWriter.writeNext(headerRecord);

        Algorithm[] algorithms = new Algorithm[]{Algorithm.BUBBLE, Algorithm.MERGE, Algorithm.QUICK};
        LogSort logSort = new LogSort();
        LogFileReader logFileReader = new LogFileReader();

        for(Algorithm algorithm : algorithms) {

            // 1. Read file here and store in a list of logs
            long startLoad = System.nanoTime();
            List<Log> logs = logFileReader.readFile(file);
            long endLoad = System.nanoTime();
            long loadTime = (endLoad - startLoad) / 1000l;

            // 2. For each algorithm calculate execution time
            long startSort = 0l;
            long endSort = 0l;
            long sortTime = 0l;


            if(algorithm == Algorithm.QUICK) {

                startSort = System.nanoTime();
                logSort.quickSort(logs);
                endSort = System.nanoTime();
                sortTime = (endSort - startSort) / 1000l;

            }

            else if (algorithm == Algorithm.MERGE ) {

                startSort = System.nanoTime();
                logSort.mergeSort(logs);
                endSort = System.nanoTime();
                sortTime = (endSort - startSort) / 1000l;

            }

            else {
                startSort = System.nanoTime();
                logSort.bubbleSort(logs);
                endSort = System.nanoTime();
                sortTime = (endSort - startSort) / 1000l;
            }

            // 3. Write data to output.csv using csvWriter object
            csvWriter.writeNext(new String[]{
                    String.valueOf(algorithm),
                    fileName,
                    String.valueOf(logs.size()),
                    String.valueOf(loadTime),
                    String.valueOf(sortTime),
                    String.valueOf(loadTime + sortTime)
            });

        }

        csvWriter.close();

    }
}
