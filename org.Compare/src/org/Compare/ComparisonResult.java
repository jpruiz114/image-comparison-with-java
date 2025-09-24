package org.Compare;

/**
 * Holds detailed results of image comparison analysis
 */
public class ComparisonResult {
    
    private final boolean exactMatch;
    private final double similarityPercentage;
    private final double meanSquaredError;
    private final int matchingPixels;
    private final int totalPixels;
    private final boolean sameDimensions;
    private final String comparisonMethod;
    private final String details;
    
    /**
     * Constructor for ComparisonResult
     * @param exactMatch Whether images are exactly identical
     * @param similarityPercentage Percentage of matching pixels (0-100)
     * @param meanSquaredError MSE value for image differences
     * @param matchingPixels Number of exactly matching pixels
     * @param totalPixels Total number of pixels compared
     * @param sameDimensions Whether images have same dimensions
     * @param comparisonMethod Method used for comparison
     * @param details Additional details about the comparison
     */
    public ComparisonResult(boolean exactMatch, double similarityPercentage, 
                           double meanSquaredError, int matchingPixels, 
                           int totalPixels, boolean sameDimensions,
                           String comparisonMethod, String details) {
        this.exactMatch = exactMatch;
        this.similarityPercentage = similarityPercentage;
        this.meanSquaredError = meanSquaredError;
        this.matchingPixels = matchingPixels;
        this.totalPixels = totalPixels;
        this.sameDimensions = sameDimensions;
        this.comparisonMethod = comparisonMethod;
        this.details = details;
    }
    
    // Getter methods
    public boolean isExactMatch() {
        return exactMatch;
    }
    
    public double getSimilarityPercentage() {
        return similarityPercentage;
    }
    
    public double getMeanSquaredError() {
        return meanSquaredError;
    }
    
    public int getMatchingPixels() {
        return matchingPixels;
    }
    
    public int getTotalPixels() {
        return totalPixels;
    }
    
    public boolean hasSameDimensions() {
        return sameDimensions;
    }
    
    public String getComparisonMethod() {
        return comparisonMethod;
    }
    
    public String getDetails() {
        return details;
    }
    
    /**
     * Backwards compatibility method - returns true if similarity is 100%
     * @return true if images are exactly the same
     */
    public boolean isTheSame() {
        return exactMatch;
    }
    
    /**
     * Determine if images are "similar" based on a threshold
     * @param threshold Minimum similarity percentage (0-100)
     * @return true if similarity meets or exceeds threshold
     */
    public boolean isSimilar(double threshold) {
        return similarityPercentage >= threshold;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Image Comparison Results ===\n");
        sb.append("Comparison Method: ").append(comparisonMethod).append("\n");
        sb.append("Exact Match: ").append(exactMatch ? "Yes" : "No").append("\n");
        sb.append("Similarity: ").append(String.format("%.2f", similarityPercentage)).append("%\n");
        sb.append("Matching Pixels: ").append(matchingPixels).append(" / ").append(totalPixels).append("\n");
        sb.append("Same Dimensions: ").append(sameDimensions ? "Yes" : "No").append("\n");
        sb.append("Mean Squared Error: ").append(String.format("%.2f", meanSquaredError)).append("\n");
        if (details != null && !details.isEmpty()) {
            sb.append("Details: ").append(details).append("\n");
        }
        sb.append("===============================");
        return sb.toString();
    }
}
