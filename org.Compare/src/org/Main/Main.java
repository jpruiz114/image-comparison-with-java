package org.Main;

import org.Compare.Compare;
import org.Compare.ComparisonResult;
import org.ImageSpiders.ImageSpider;
import java.io.File;

/**
 * Enhanced Main class demonstrating the improved image comparison capabilities
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        // Use relative paths that work on any operating system
        String basePath = getProjectBasePath();
        String image1Path = basePath + "assets" + File.separator + "1.jpg";
        String image2Path = basePath + "assets" + File.separator + "2.jpg";
        String testImage1Path = basePath + "assets" + File.separator + "test1.jpg";
        String testImage2Path = basePath + "assets" + File.separator + "test2.jpg";
        
        System.out.println("=== Enhanced Image Comparison Tool ===\n");
        
        // Test 1: Compare original images
        System.out.println("Test 1: Comparing 1.jpg and 2.jpg");
        performComparison(image1Path, image2Path);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 2: Compare test images
        System.out.println("Test 2: Comparing test1.jpg and test2.jpg");
        performComparison(testImage1Path, testImage2Path);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 3: Compare same image with itself
        System.out.println("Test 3: Comparing 1.jpg with itself (should be identical)");
        performComparison(image1Path, image1Path);
        
        // Test 4: Demonstrate command line arguments if provided
        if (args.length >= 2) {
            System.out.println("\n" + "=".repeat(50) + "\n");
            System.out.println("Test 4: Comparing user-provided images");
            System.out.println("Image 1: " + args[0]);
            System.out.println("Image 2: " + args[1]);
            performComparison(args[0], args[1]);
        } else {
            System.out.println("\nTip: You can provide two image paths as command line arguments to compare your own images:");
            System.out.println("java org.Main.Main path/to/image1.jpg path/to/image2.jpg");
        }
    }
    
    /**
     * Performs comprehensive image comparison and displays results
     */
    private static void performComparison(String path1, String path2) {
        try {
            // Create comparison object
            Compare compare = new Compare(path1, path2);
            
            // Check if comparison completed successfully
            if (!compare.isDone()) {
                System.out.println("ERROR: Comparison failed to complete.");
                return;
            }
            
            // Get detailed results
            ComparisonResult result = compare.getDetailedResult();
            
            // Display comprehensive results
            System.out.println(result.toString());
            
            // Additional analysis
            System.out.println("\n--- Analysis Summary ---");
            
            if (result.isExactMatch()) {
                System.out.println("✅ Images are IDENTICAL");
            } else if (result.isSimilar(95.0)) {
                System.out.println("✅ Images are VERY SIMILAR (>95% match)");
            } else if (result.isSimilar(80.0)) {
                System.out.println("⚠️  Images are SOMEWHAT SIMILAR (>80% match)");
            } else if (result.isSimilar(50.0)) {
                System.out.println("⚠️  Images are SLIGHTLY SIMILAR (>50% match)");
            } else {
                System.out.println("❌ Images are VERY DIFFERENT (<50% match)");
            }
            
            // Performance info
            ImageSpider spider1 = compare.getImageSpider1();
            ImageSpider spider2 = compare.getImageSpider2();
            System.out.println("Image 1 dimensions: " + spider1.getWidth() + "x" + spider1.getHeight());
            System.out.println("Image 2 dimensions: " + spider2.getWidth() + "x" + spider2.getHeight());
            
            // Legacy compatibility demo
            System.out.println("\n--- Legacy Compatibility ---");
            System.out.println("Legacy isTheSame(): " + compare.isTheSame());
            
        } catch (Exception e) {
            System.err.println("❌ Error during comparison: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the project base path in a cross-platform way
     */
    private static String getProjectBasePath() {
        // Try to find the project root by looking for assets folder
        String currentDir = System.getProperty("user.dir");
        File assetsDir = new File(currentDir, "assets");
        
        if (assetsDir.exists()) {
            return currentDir + File.separator;
        }
        
        // If we're in the build directory, go up to find assets
        File parentDir = new File(currentDir).getParentFile();
        if (parentDir != null) {
            assetsDir = new File(parentDir, "assets");
            if (assetsDir.exists()) {
                return parentDir.getAbsolutePath() + File.separator;
            }
            
            // Try going up one more level (for nested directory structures)
            File grandParentDir = parentDir.getParentFile();
            if (grandParentDir != null) {
                assetsDir = new File(grandParentDir, "assets");
                if (assetsDir.exists()) {
                    return grandParentDir.getAbsolutePath() + File.separator;
                }
            }
        }
        
        // Fallback to current directory
        System.out.println("Warning: Could not find assets directory. Using current directory.");
        return currentDir + File.separator;
    }
}
