# Image Comparison with Java

A Java-based image comparison tool that analyzes and compares two images based on their RGB color values.

## What This Project Does

This application compares two images using advanced pixel-by-pixel analysis:
1. **Loads two image files** from disk with proper error handling
2. **Extracts RGB values** from every pixel in both images
3. **Performs pixel-by-pixel comparison** to detect exact matches
4. **Calculates detailed similarity metrics** including:
   - Exact match detection (100% identical)
   - Similarity percentage (% of matching pixels)
   - Mean Squared Error (MSE) for quantitative difference measurement
5. **Validates image dimensions** to ensure proper comparison
6. **Provides comprehensive analysis** with multiple similarity thresholds

The enhanced algorithm uses proper pixel-by-pixel comparison instead of the flawed total RGB sum approach.

## Project Structure

```
image-comparison-with-java/
├── src/
│   └── com/
│       └── imagecomparison/
│           ├── cli/
│           │   └── Main.java                    # Entry point of the application
│           ├── comparison/
│           │   ├── Compare.java                 # Main comparison logic coordinator
│           │   ├── CompareHandler.java          # Handles the actual RGB comparison
│           │   └── ComparisonResult.java        # Detailed comparison results
│           └── processing/
│               └── ImageSpider.java             # Unified image processing class
├── assets/                                      # Sample images for testing
├── build/                                       # Compiled classes (ignored by git)
└── .gitignore                                   # Git ignore file
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any text editor or Java IDE (project is now IDE-agnostic)

### Using Command Line
```bash
# Compile the Java files (from project root)
mkdir -p build/classes
javac -d build/classes src/com/imagecomparison/*/*.java

# Run the application
cd build/classes
java com.imagecomparison.cli.Main

# Or run from project root with classpath
java -cp build/classes com.imagecomparison.cli.Main
```

## Usage Examples

### Automatic Asset Detection (Recommended)
The application now automatically detects the project's assets folder:
```java
// The program automatically finds assets relative to the project root
Compare compare = new Compare("assets/1.jpg", "assets/2.jpg");
```

### Cross-Platform Path Handling
No more hardcoded Windows paths! The enhanced Main.java uses:
```java
String basePath = getProjectBasePath(); // Automatically detects project root
String image1Path = basePath + "assets" + File.separator + "1.jpg";
String image2Path = basePath + "assets" + File.separator + "2.jpg";
```

### Command Line Usage
Pass image paths as arguments for flexible comparison:
```bash
# From project root
java -cp build/classes com.imagecomparison.cli.Main path/to/image1.jpg path/to/image2.jpg

# Using relative paths
java -cp build/classes com.imagecomparison.cli.Main assets/1.jpg assets/2.jpg
```

## Remaining Limitations

**Minor remaining issues:**

1. **Basic Algorithm**: Uses exact pixel matching - could be enhanced with perceptual comparison algorithms
2. **Memory Usage**: Stores all pixel data in memory - could be optimized for very large images
3. **No Build System**: Could benefit from Maven or Gradle for dependency management

## Enhanced Features

**New Capabilities:**

- **Comprehensive Analysis**: Get detailed similarity reports with multiple metrics
- **Command Line Support**: Pass image paths as arguments for flexible usage
- **Cross-Platform**: Works on Windows, macOS, and Linux
- **Error Resilience**: Robust error handling with informative messages
- **Multiple Comparison Methods**: Exact matching, similarity thresholds, MSE analysis
- **Rich Output**: Clear text-based results with detailed statistics

## Future Enhancement Ideas

- **Perceptual Hashing**: Add structural similarity algorithms (SSIM, pHash)
- **Image Preprocessing**: Automatic resizing/normalization for different sized images  
- **Performance Optimization**: Streaming comparison for very large images
- **GUI Interface**: Desktop application with drag-and-drop functionality
- **Batch Processing**: Compare multiple image pairs at once
- **Export Results**: Save comparison reports to JSON/CSV formats
- **Advanced Metrics**: Histogram comparison, color distribution analysis

## Sample Assets

The `assets/` folder contains sample images (`1.jpg`, `2.jpg`, `test1.jpg`, `test2.jpg`) that can be used for testing the comparison functionality.
