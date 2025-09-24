package com.imagecomparison.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Unified ImageSpider class for processing and analyzing images
 * Replaces the duplicated ImageSpider1 and ImageSpider2 classes
 */
public class ImageSpider {

    // Image properties
    private int totalRed;
    private int totalGreen;
    private int totalBlue;
    private int totalAlpha;
    private String path;
    private int width;
    private int height;
    private boolean jobDone;
    private BufferedImage image;
    private int[][] redPixels;
    private int[][] greenPixels;
    private int[][] bluePixels;
    private int[][] alphaPixels;

    /**
     * Constructor that loads and processes an image from the given path
     * @param path The file path to the image
     * @throws Exception If the image cannot be loaded or processed
     */
    public ImageSpider(String path) throws Exception {
        this.jobDone = false;
        try {
            this.path = path;
            initiate();
            this.jobDone = true;
        } catch (Exception e) {
            jobDone = false;
            throw new Exception("Failed to load image from path: " + path + ". Error: " + e.getMessage());
        }
    }

    /**
     * Initializes the image processing by loading the image and extracting properties
     */
    private void initiate() throws IOException {
        File imageFile = new File(this.path);
        if (!imageFile.exists()) {
            throw new IOException("Image file does not exist: " + this.path);
        }
        
        image = ImageIO.read(imageFile);
        if (image == null) {
            throw new IOException("Unable to read image file. Unsupported format or corrupted file: " + this.path);
        }
        
        this.height = image.getHeight();
        this.width = image.getWidth();
        
        // Initialize pixel arrays
        this.redPixels = new int[width][height];
        this.greenPixels = new int[width][height];
        this.bluePixels = new int[width][height];
        this.alphaPixels = new int[width][height];
        
        extractProperties();
    }

    /**
     * Extracts color properties from each pixel and stores them in arrays
     * Also calculates total color sums for backward compatibility
     */
    private void extractProperties() {
        totalRed = 0;
        totalGreen = 0;
        totalBlue = 0;
        totalAlpha = 0;
        
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Color color = new Color(image.getRGB(w, h), true);
                
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int alpha = color.getAlpha();
                
                // Store individual pixel values
                redPixels[w][h] = red;
                greenPixels[w][h] = green;
                bluePixels[w][h] = blue;
                alphaPixels[w][h] = alpha;
                
                // Calculate totals
                totalRed += red;
                totalGreen += green;
                totalBlue += blue;
                totalAlpha += alpha;
            }
        }
    }

    // Getter methods for backward compatibility
    public int getRed() {
        return this.totalRed;
    }

    public int getGreen() {
        return this.totalGreen;
    }

    public int getBlue() {
        return this.totalBlue;
    }

    public int getAlpha() {
        return this.totalAlpha;
    }

    public boolean isJobDone() {
        return this.jobDone;
    }

    // New getter methods for enhanced functionality
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getPath() {
        return this.path;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Get the red value of a specific pixel
     * @param x X coordinate
     * @param y Y coordinate
     * @return Red value (0-255)
     */
    public int getRedPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return redPixels[x][y];
        }
        throw new IndexOutOfBoundsException("Pixel coordinates out of bounds: (" + x + ", " + y + ")");
    }

    /**
     * Get the green value of a specific pixel
     * @param x X coordinate
     * @param y Y coordinate
     * @return Green value (0-255)
     */
    public int getGreenPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return greenPixels[x][y];
        }
        throw new IndexOutOfBoundsException("Pixel coordinates out of bounds: (" + x + ", " + y + ")");
    }

    /**
     * Get the blue value of a specific pixel
     * @param x X coordinate
     * @param y Y coordinate
     * @return Blue value (0-255)
     */
    public int getBluePixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return bluePixels[x][y];
        }
        throw new IndexOutOfBoundsException("Pixel coordinates out of bounds: (" + x + ", " + y + ")");
    }

    /**
     * Get the alpha value of a specific pixel
     * @param x X coordinate
     * @param y Y coordinate
     * @return Alpha value (0-255)
     */
    public int getAlphaPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return alphaPixels[x][y];
        }
        throw new IndexOutOfBoundsException("Pixel coordinates out of bounds: (" + x + ", " + y + ")");
    }
}
