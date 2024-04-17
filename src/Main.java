public class Main {
    public static void main(String[] args) {
        String[][] loadedData = CSVLoader.loadCSV("src/Experiment.csv");
        SampleDistanceMatrix distanceMatrixCalculator = new SampleDistanceMatrix(loadedData);
        distanceMatrixCalculator.calculateDistanceMatrix();

        int[] N_values = {3, 5, 7, 11, 13, 560};
        for (int N : N_values) {
            String[] predictions = distanceMatrixCalculator.predictDiagnosis(N);
            double accuracy = distanceMatrixCalculator.calculateAccuracy(predictions);
            System.out.println("Accuracy for N=" + N + ": " + accuracy + "%");
        }
    }
}
