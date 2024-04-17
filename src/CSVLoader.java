import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVLoader {
    public static String[][] loadCSV(String path) {
        // setting the rows and columns so that we have an accurate size
        int numOfRows = 0;
        int numOfColumns = 0;

        // counting number of rows and columns in order to create a sized 2d array
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line; // empty string
            while ((line = br.readLine()) != null) {
                numOfRows++; // increase the amount of rows
                if (numOfColumns == 0) {
                    numOfColumns = line.split(",").length; // getting the number of columns and splitting by comma
                    // we split because CSVs are comma separated so that we need to get rid of the delimiter
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // creating a new 2d array with the appropriate size
        String[][] data = new String[numOfRows - 1][numOfColumns]; // substracting 1 row to accomodate for header row

        // populating the newly created array
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // skipping the header line
            String line;
            int row = 0; // reader flag starting from the first row
            // while each line is valid and the reader row is within the total row range
            while ((line = br.readLine()) != null && row < numOfRows) {
                String[] cells = line.split(","); // splitting each cell of the csv into a separate array
                data[row] = cells; // copying the split line to the row in the main data array
                row++; // going to the next row
             }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data; // returning the populated 2d array
    }
}
