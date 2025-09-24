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
org.Compare/
├── src/
│   ├── org/
│   │   ├── Main/
│   │   │   └── Main.java              # Entry point of the application
│   │   ├── Compare/
│   │   │   ├── Compare.java           # Main comparison logic coordinator
│   │   │   └── CompareHandler.java    # Handles the actual RGB comparison
│   │   ├── ImageSpiders/
│   │   │   ├── ImageSpider1.java      # Processes first image
│   │   │   └── ImageSpider2.java      # Processes second image
│   │   └── Percentage/
│   │       ├── Percentage.java        # (Currently unused)
│   │       ├── ColorPercentage.java   # (Currently unused)
│   │       └── LightPercentage.java   # (Currently unused)
├── assets/                            # Sample images for testing
└── build.xml                          # NetBeans build configuration
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (recommended) or any Java IDE

### Using NetBeans
1. Open the project in NetBeans
2. Update the file paths in `Main.java` to point to your image files
3. Build and run the project

### Using Command Line
```bash
# Navigate to the source directory
cd org.Compare/src

# Compile the Java files
javac -d ../build/classes org/Main/*.java org/Compare/*.java org/ImageSpiders/*.java org/Percentage/*.java

# Run the application
cd ../build/classes
java org.Main.Main
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
java -cp org.Compare/build/classes org.Main.Main path/to/image1.jpg path/to/image2.jpg

# Using relative paths
java -cp org.Compare/build/classes org.Main.Main assets/1.jpg assets/2.jpg
```

## ✅ Recent Improvements (Algorithm Fixed!)

The project has been **significantly enhanced** with the following improvements:

✅ **Fixed Comparison Algorithm**: Now uses proper pixel-by-pixel comparison instead of flawed RGB sum comparison
✅ **Eliminated Code Duplication**: Replaced `ImageSpider1` and `ImageSpider2` with unified `ImageSpider` class  
✅ **Cross-Platform Compatibility**: Removed hardcoded Windows paths, now works on all operating systems
✅ **Enhanced Error Handling**: Comprehensive error handling with detailed error messages
✅ **Rich Similarity Metrics**: Provides similarity percentages, MSE calculations, and multiple analysis thresholds
✅ **Backward Compatibility**: All existing code continues to work with legacy methods

## Remaining Limitations

⚠️ **Minor remaining issues:**

1. **Unused Code**: The `Percentage` package classes are still empty placeholders
2. **Basic Algorithm**: Uses exact pixel matching - could be enhanced with perceptual comparison algorithms
3. **Memory Usage**: Stores all pixel data in memory - could be optimized for very large images

## Enhanced Features

🎯 **New Capabilities:**

- **Comprehensive Analysis**: Get detailed similarity reports with multiple metrics
- **Command Line Support**: Pass image paths as arguments for flexible usage
- **Cross-Platform**: Works on Windows, macOS, and Linux
- **Error Resilience**: Robust error handling with informative messages
- **Multiple Comparison Methods**: Exact matching, similarity thresholds, MSE analysis
- **Rich Output**: Color-coded results with emoji indicators and detailed statistics

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
