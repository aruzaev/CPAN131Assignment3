public class SampleDistanceMatrix extends DistanceCalculator { // inheriting the DistanceCalculator class to get the methods
    private String[][] data;
    private double[][] distanceMatrix;

    public SampleDistanceMatrix(String[][] data) { // constructor which takes in a 2d array
        this.data = data;
        this.distanceMatrix = new double[data.length][data.length]; // making a distance matrix with given lengths
    }

    public void calculateDistanceMatrix() {
        // loop through each sample
        for (int i = 0; i < data.length; i++) {
            // calculate distance to every other sample
            for (int j = 0; j < data.length; j++) {
                if (i == j) { // the distance from a sample to itself is alwas 0
                    distanceMatrix[i][j] = 0;
                } else {
                    // calculate distance using the distance calculator inherited method
                    distanceMatrix[i][j] = calculateDistance(data[i], data[j]);
                }
            }
        }
    }

    public double[][] getDistanceMatrix() { // getter for our matrix
        return distanceMatrix;
    }

    public String[] predictDiagnosis(int N) {
        String[] predictions = new String[data.length];

        // iterating over each sample to make a prediction
        // i is the current sample that is being made for the preiction
        for (int i = 0; i < data.length; i++) { // iterating over the row index aka the vertical axis of the array
            double[] distancesCopy = new double[distanceMatrix[i].length]; // temp array to sort distances
            String[] diagnoses = new String[data.length]; // array to store diagnoses corresponding to sorted distances

            // copying the distance from distance matrix to a temporary array for sorting
            for (int k = 0; k < distanceMatrix[i].length; k++) { // k iterates horizontally along the columns of the ith row and copying each element
                distancesCopy[k] = distanceMatrix[i][k];
            }

            // filling in the diagnosis array according to the distance array
            for (int j = 0; j < diagnoses.length; j++) { // j goes through each row of the data 2d array and gets the diagnosis for each sample
                diagnoses[j] = data[j][1]; // moving vertically down the rows but always looking at the same column (index 1) to find the diagnosis string
                // diagnosis is in the second column
            }

            // sorting the distancesCopy array in ascending order from bottom to top
            for (int j = 0; j < distancesCopy.length; j++) { // j selects the start point for each pass of the sorting algorithm
                for (int k = j + 1; k < distancesCopy.length; k++) { // going ahead by one index to avoid redundancy
                    if (distancesCopy[j] > distancesCopy[k]) { // check if current distance is greater than the compared distance
                        // if true then they have to be swapped

                        // swapping the distances
                        double tempDistance = distancesCopy[j]; // store current distance in a temporary variable
                        distancesCopy[j] = distancesCopy[k]; // assign the compared distance to the current position
                        distancesCopy[k] = tempDistance; // move the distance from the temporary variable to the compared posistion

                        // swap corresponding diagnosis
                        String tempDiagnosis = diagnoses[j]; // store the diagnosis associated with the current distance in a temporary variable
                        diagnoses[j] = diagnoses[k]; // assign the diagnosis associated with the compared sitance to the current position
                        diagnoses[k] = tempDiagnosis; // move the diagnosis from temp variable to compared posisiotn

                    }
                }
            }

            // counting the frequency of the TOP N closes diagnoses
            int countM = 0;  // cout of malignant diagnosis
            int countB = 0; // count of benign diagnoses
            for (int j = 1; j <= N; j++) {  // the purpose of j is used to now count the number of diagnosis amongst the top N closest samples, sstarting from 1 to avoid 0th distance (to itself)
                if (diagnoses[j].equals("M")) { // if malignant is found, increase it
                    countM++;
                } else if (diagnoses[j].equals("B")) { // if benign is found, increase the count
                    countB++;
                }
            }

            predictions[i] = (countM > countB) ? "M" : "B"; // if the amount of malignant cancers is larger than benign, set it to M else B
        }

        return predictions;
    }

    public double calculateAccuracy(String[] predictions) {
        int correctPredictions = 0;
        // loop through each sample in the dataset
        // i is  each sample, going from 0 to totalNumberSample - 1
        for (int i = 0; i < data.length; i++) {
            if (data[i][1].equals(predictions[i])) { // if actual diagnosis on the 1st index matched the predicted diagnosis
                correctPredictions++; // incrememnt the amount of correct predictions
            }
        }

        return correctPredictions * 100.0 / data.length; // make it into a percentage
    }

}
