public class DistanceCalculator {
    public double calculateDistance(String[] sample1, String[] sample2) { // passing in the entire string of sample 2 and 1
        if (sample1.length != sample2.length) { // if the sample strings aren't the same length, meaning they don't have the same categories
            throw new IllegalArgumentException("Samples must have the same amount of fields.");
        }
        double sum = 0.0; // sum of the squared differences
        for (int i = 2; i < sample1.length; i++) { // start from the second index in order to skip the "id" and "diagnosis" columns
            try {
                double val1 = Double.parseDouble(sample1[i].trim()); // typecasting the samples into a doubles so that math can be used, trims whitespace
                double val2 = Double.parseDouble(sample2[i].trim());
                //    System.out.println("Comparing: " + val1 + " and " + val2); // Debug print
                sum += Math.pow(val1 - val2, 2); // finding the difference and squaring them
            } catch (NumberFormatException e) { // the string converted isn't a number
                System.err.println("NumberFormatException: " + e.getMessage());
            }


        }

        return Math.sqrt(sum); // returning the square root of the squared difference

    }
}
