public class Main {
    public static void main(String[] args) {
        String[][] loadedData = CSVLoader.loadCSV("Experiment.csv");

        for (String[] row : loadedData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}