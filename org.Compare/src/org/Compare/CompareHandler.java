package org.Compare;

import org.ImageSpiders.ImageSpider;

/**
 * Enhanced CompareHandler with multiple comparison algorithms
 */
public class CompareHandler {

    /**
     * Backwards compatibility method - uses the old total RGB sum comparison
     * @deprecated Use compareImages() for better comparison results
     */
    @Deprecated
    public boolean CompareHandler(ImageSpider spider1, ImageSpider spider2) {
        return (spider1.getRed() == spider2.getRed()) && 
               (spider1.getGreen() == spider2.getGreen()) && 
               (spider1.getBlue() == spider2.getBlue());
    }

    /**
     * Comprehensive image comparison using multiple algorithms
     * @param spider1 First image spider
     * @param spider2 Second image spider
     * @return ComparisonResult with detailed analysis
     */
    public ComparisonResult compareImages(ImageSpider spider1, ImageSpider spider2) {
        if (!spider1.isJobDone() || !spider2.isJobDone()) {
            return new ComparisonResult(false, 0.0, Double.MAX_VALUE, 0, 0, false,
                "Error", "One or both images failed to load properly");
        }

        // Check if dimensions match
        boolean sameDimensions = (spider1.getWidth() == spider2.getWidth()) && 
                                (spider1.getHeight() == spider2.getHeight());
        
        if (!sameDimensions) {
            return new ComparisonResult(false, 0.0, Double.MAX_VALUE, 0, 
                spider1.getWidth() * spider1.getHeight(), false,
                "Dimension Mismatch", 
                String.format("Image 1: %dx%d, Image 2: %dx%d", 
                    spider1.getWidth(), spider1.getHeight(),
                    spider2.getWidth(), spider2.getHeight()));
        }

        return performPixelByPixelComparison(spider1, spider2);
    }

    /**
     * Performs detailed pixel-by-pixel comparison
     */
    private ComparisonResult performPixelByPixelComparison(ImageSpider spider1, ImageSpider spider2) {
        int width = spider1.getWidth();
        int height = spider1.getHeight();
        int totalPixels = width * height;
        int matchingPixels = 0;
        double totalSquaredError = 0.0;
        
        // Compare each pixel
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int r1 = spider1.getRedPixel(x, y);
                int g1 = spider1.getGreenPixel(x, y);
                int b1 = spider1.getBluePixel(x, y);
                
                int r2 = spider2.getRedPixel(x, y);
                int g2 = spider2.getGreenPixel(x, y);
                int b2 = spider2.getBluePixel(x, y);
                
                // Check for exact pixel match
                if (r1 == r2 && g1 == g2 && b1 == b2) {
                    matchingPixels++;
                }
                
                // Calculate squared error for this pixel
                double pixelError = Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2, 2);
                totalSquaredError += pixelError;
            }
        }
        
        // Calculate metrics
        double similarityPercentage = (double) matchingPixels / totalPixels * 100.0;
        double meanSquaredError = totalSquaredError / (totalPixels * 3); // Divide by 3 for RGB channels
        boolean exactMatch = matchingPixels == totalPixels;
        
        String details = String.format("Analyzed %dx%d images with %d total pixels", 
                                     width, height, totalPixels);
        
        return new ComparisonResult(exactMatch, similarityPercentage, meanSquaredError,
                                  matchingPixels, totalPixels, true, 
                                  "Pixel-by-Pixel Analysis", details);
    }

    /**
     * Quick comparison method that only checks if images are exactly identical
     * @param spider1 First image spider
     * @param spider2 Second image spider
     * @return true if images are exactly identical
     */
    public boolean areIdentical(ImageSpider spider1, ImageSpider spider2) {
        ComparisonResult result = compareImages(spider1, spider2);
        return result.isExactMatch();
    }

    /**
     * Checks if images are similar within a given threshold
     * @param spider1 First image spider
     * @param spider2 Second image spider
     * @param threshold Similarity threshold (0-100)
     * @return true if similarity percentage meets or exceeds threshold
     */
    public boolean areSimilar(ImageSpider spider1, ImageSpider spider2, double threshold) {
        ComparisonResult result = compareImages(spider1, spider2);
        return result.isSimilar(threshold);
    }
}
