# Image Comparison with Java

A Java-based image comparison tool that analyzes and compares two images based on their RGB color values.

## What This Project Does

This application compares two images by:
1. Loading two image files from disk
2. Extracting RGB (Red, Green, Blue) color values from every pixel in both images
3. Calculating the total sum of each color channel (R, G, B) for each image
4. Comparing the total sums to determine if the images are "the same"

The comparison is based on whether the total RGB values of both images match exactly.

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

## Usage Example

Currently, the image paths are hardcoded in `Main.java`:
```java
Compare Compare = new Compare(
    "C:\\Users\\usuario\\Downloads\\image-comparison-with-java\\assets\\1.jpg",
    "C:\\Users\\usuario\\Downloads\\image-comparison-with-java\\assets\\2.jpg"
);
```

You need to modify these paths to point to your actual image files.

## Current Limitations and Issues

⚠️ **Important Limitations:**

1. **Crude Comparison Algorithm**: The tool only compares total RGB sums, not actual pixel-by-pixel differences. This means:
   - Different images could have the same total RGB values and be considered "the same"
   - It cannot detect structural differences, only gross color differences

2. **Code Duplication**: `ImageSpider1` and `ImageSpider2` are nearly identical classes with minimal differences

3. **Hardcoded Paths**: File paths are hardcoded and use Windows-style paths, making it non-portable

4. **Poor Error Handling**: Limited exception handling and error reporting

5. **Unused Code**: The entire `Percentage` package is empty and unused

6. **No Similarity Metrics**: Only provides binary same/different results, no similarity percentages or detailed analysis

## Potential Improvements

- Implement proper pixel-by-pixel comparison
- Add similarity percentage calculations
- Create a unified ImageSpider class to eliminate duplication
- Add command-line argument support for dynamic file paths
- Implement proper error handling and validation
- Add support for different image formats
- Create meaningful functionality for the Percentage classes
- Add unit tests and better documentation

## Sample Assets

The `assets/` folder contains sample images (`1.jpg`, `2.jpg`, `test1.jpg`, `test2.jpg`) that can be used for testing the comparison functionality.
