package org.Compare;

import org.ImageSpiders.ImageSpider;

/**
 * Enhanced Compare class with improved comparison algorithms and backward compatibility
 */
public class Compare {

    // Image path information
    protected String imagePath1;
    protected String imagePath2;
    protected ImageSpider imageSpider1;
    protected ImageSpider imageSpider2;

    // Comparison results
    protected boolean same = false;
    protected boolean done = false;
    protected ComparisonResult detailedResult;

    /**
     * Constructor that performs image comparison
     * @param path1 Path to first image
     * @param path2 Path to second image
     * @throws Exception If images cannot be loaded or compared
     */
    public Compare(String path1, String path2) throws Exception {
        this.imagePath1 = path1;
        this.imagePath2 = path2;
        
        try {
            // Load images using the new unified ImageSpider
            imageSpider1 = new ImageSpider(path1);
            imageSpider2 = new ImageSpider(path2);
            
            // Perform comparison using enhanced algorithm
            CompareHandler compareHandler = new CompareHandler();
            detailedResult = compareHandler.compareImages(imageSpider1, imageSpider2);
            
            // Set legacy boolean result for backward compatibility
            same = detailedResult.isExactMatch();
            done = true;
            
        } catch (Exception e) {
            done = false;
            throw new Exception("Failed to compare images: " + e.getMessage(), e);
        }
    }

    // === NEW ENHANCED METHODS ===
    
    /**
     * Get detailed comparison results
     * @return ComparisonResult object with comprehensive analysis
     */
    public ComparisonResult getDetailedResult() {
        return this.detailedResult;
    }
    
    /**
     * Get similarity percentage between images
     * @return Similarity percentage (0-100)
     */
    public double getSimilarityPercentage() {
        return detailedResult != null ? detailedResult.getSimilarityPercentage() : 0.0;
    }
    
    /**
     * Get Mean Squared Error between images
     * @return MSE value (lower is more similar)
     */
    public double getMeanSquaredError() {
        return detailedResult != null ? detailedResult.getMeanSquaredError() : Double.MAX_VALUE;
    }
    
    /**
     * Check if images are similar within a threshold
     * @param threshold Minimum similarity percentage (0-100)
     * @return true if images meet similarity threshold
     */
    public boolean isSimilar(double threshold) {
        return detailedResult != null && detailedResult.isSimilar(threshold);
    }

    // === BACKWARD COMPATIBILITY METHODS ===
    
    /**
     * @deprecated Use getImageSpider1() instead
     */
    @Deprecated
    public ImageSpider getImageSpiderObject1() {
        return this.imageSpider1;
    }

    /**
     * @deprecated Use getImageSpider2() instead
     */
    @Deprecated
    public ImageSpider getImageSpiderObject2() {
        return this.imageSpider2;
    }
    
    /**
     * Get first ImageSpider object
     * @return ImageSpider for first image
     */
    public ImageSpider getImageSpider1() {
        return this.imageSpider1;
    }

    /**
     * Get second ImageSpider object
     * @return ImageSpider for second image
     */
    public ImageSpider getImageSpider2() {
        return this.imageSpider2;
    }

    /**
     * Get path of first image
     * @return Path to first image
     */
    public String getImagePath1() {
        return this.imagePath1;
    }

    /**
     * Get path of second image
     * @return Path to second image
     */
    public String getImagePath2() {
        return this.imagePath2;
    }

    /**
     * Check if images are exactly the same (backward compatibility)
     * @return true if images are identical
     */
    public boolean isTheSame() {
        return this.same;
    }

    /**
     * Check if comparison is complete
     * @return true if comparison finished successfully
     */
    public boolean isDone() {
        return this.done;
    }
    
    /**
     * Get a formatted string with detailed comparison results
     * @return Formatted comparison results
     */
    @Override
    public String toString() {
        if (detailedResult != null) {
            return detailedResult.toString();
        } else {
            return "Comparison not completed or failed.";
        }
    }
}
