package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Create a JUnit test class to test various methods of the GrayscaleImage class.
class GrayscaleImageTest {

    // Declare private member variables to store test instances of images.
    private GrayscaleImage smallSquare; // Used for testing non-wide image operations.
    private GrayscaleImage smallWide;   // Used for testing operations that may behave differently on non-square images.

    /**
     * Set up common test images used in the test cases.
     * This method is run before each test to ensure that each test has a fresh set of images.
     */
    @BeforeEach
    void setUp() {
        // Create a 2x2 image 'smallSquare' suitable for testing non-wide operations.
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        // Create a 2x3 image 'smallWide' suitable for testing operations that may behave differently on non-square images.
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
    }

    /**
     * Tests the 'getPixel' method to ensure it correctly returns pixel values at the edges of the image.
     * The edges are critical cases for image processing algorithms.
     */
    @Test
    void testGetPixelBoundaryConditions() {
        // Group boundary tests using assertAll to ensure all conditions are checked even if one fails.
        assertAll("Boundary Conditions",
                () -> assertEquals(1, smallSquare.getPixel(0, 0), "Top-left pixel"),
                () -> assertEquals(2, smallSquare.getPixel(1, 0), "Top-right pixel"),
                () -> assertEquals(3, smallSquare.getPixel(0, 1), "Bottom-left pixel"),
                () -> assertEquals(4, smallSquare.getPixel(1, 1), "Bottom-right pixel")
        );
    }

    /**
     * Verifies that requesting pixel values outside the valid image area throws an exception.
     * This is important to prevent accessing invalid array indices.
     */
    @Test
    void testGetPixelOutOfBounds() {
        // Test exception throwing for out-of-bounds coordinates.
        assertThrows(IllegalArgumentException.class, () -> smallSquare.getPixel(2, 0), "X coordinate out of bounds");
        assertThrows(IllegalArgumentException.class, () -> smallSquare.getPixel(0, 2), "Y coordinate out of bounds");
    }

    /**
     * Checks if the 'equals' method correctly identifies non-equal objects.
     * A GrayscaleImage should only be equal to another GrayscaleImage with the same pixel data.
     */
    @Test
    void testEqualsWithDifferentObjects() {
        // Comparing a GrayscaleImage object to an instance of a different class should return false.
        assertNotEquals(smallSquare, new Object(), "GrayscaleImage should not be equal to a different type of object");
    }

    /**
     * Ensures that the 'equals' method differentiates between images with similar but not identical pixel values.
     */
    @Test
    void testEqualsWithSimilarImages() {
        // Creating an image that is almost the same as 'smallSquare' except for a slight variation in one pixel.
        GrayscaleImage almostEqual = new GrayscaleImage(new double[][]{{1, 2.001}, {3, 4}});
        // The 'equals' method should detect even small differences in pixel values.
        assertNotEquals(smallSquare, almostEqual, "Images with similar but not identical pixels should not be equal");
    }

    /**
     * Tests that the 'averageBrightness' method calculates the correct average for images with fractional pixel values.
     */
    @Test
    void testAverageBrightnessFractional() {
        // Using an image with fractional pixel values to check the average brightness calculation.
        GrayscaleImage fractionalImage = new GrayscaleImage(new double[][]{{1.5, 2.5}, {3.5, 4.5}});
        // The expected average is the sum of the pixel values divided by the number of pixels.
        assertEquals(3.0, fractionalImage.averageBrightness(), "Average brightness of fractional values should be calculated correctly");
    }

    /**
     * Confirms that 'averageBrightness' returns the pixel value itself when the image contains a single pixel.
     */
    @Test
    void testAverageBrightnessSinglePixel() {
        // Single pixel image for testing average brightness in a trivial case.
        GrayscaleImage singlePixelImage = new GrayscaleImage(new double[][]{{150}});
        // The average brightness of a single pixel image is the value of the pixel itself.
        assertEquals(150, singlePixelImage.averageBrightness(), "Average brightness of a single pixel image should be the pixel's value");
    }

    /**
     * Tests the 'normalized' method to ensure it does not alter an image that already has the target average brightness.
     */
    @Test
    void testNormalizedAlreadyNormalized() {
        // Creating an image where every pixel has the target average brightness value.
        GrayscaleImage normalizedImage = new GrayscaleImage(new double[][]{{127, 127}, {127, 127}});
        // Normalizing an already normalized image should result in no change.
        GrayscaleImage result = normalizedImage.normalized();
        assertEquals(127, result.averageBrightness(), "Normalizing an already normalized image should not change the brightness");
    }

    /**
     * Checks if the 'mirrored' method correctly reverses the pixels in a single row image.
     */
    @Test
    void testMirroredSingleRow() {
        // Single row image to test the mirroring operation.
        GrayscaleImage singleRowImage = new GrayscaleImage(new double[][]{{1, 2, 3}});
        // The expected result is the reverse of the original row.
        GrayscaleImage expected = new GrayscaleImage(new double[][]{{3, 2, 1}});
        assertEquals(expected, singleRowImage.mirrored(), "Mirroring a single row image should reverse the row");
    }

    /**
     * Tests the 'mirrored' method for an image with a single column.
     * Mirroring a single column should not change the image as the order of pixels remains the same.
     */
    @Test
    void testMirroredSingleColumn() {
        // Create a single column image to test the mirroring operation.
        GrayscaleImage singleColumnImage = new GrayscaleImage(new double[][]{{1}, {2}, {3}});
        // The expected result should be identical to the original image.
        GrayscaleImage expected = new GrayscaleImage(new double[][]{{1}, {2}, {3}});
        assertEquals(expected, singleColumnImage.mirrored(), "Mirroring a single column image should not change the image");
    }

    /**
     * Tests the 'cropped' method to ensure it returns the correct single pixel when cropping a one-pixel area.
     * This is a boundary case for the cropping functionality.
     */
    @Test
    void testCroppedSinglePixel() {
        // Crop a single pixel from the top-left corner of the small square image.
        GrayscaleImage result = smallSquare.cropped(0, 0, 1, 1);
        // The expected result should be a new image containing only that single pixel.
        assertEquals(new GrayscaleImage(new double[][]{{1}}), result, "Cropping to a single pixel should return that pixel");
    }

    /**
     * Tests the 'cropped' method to ensure it throws an exception when given invalid dimensions.
     * This checks the method's robustness against erroneous input.
     */
    @Test
    void testCroppedInvalidDimensions() {
        // Attempt to crop an area larger than the small square image, which should throw an exception.
        assertThrows(IllegalArgumentException.class, () -> smallSquare.cropped(0, 0, 3, 3), "Cropping with invalid dimensions should throw an exception");
    }

    /**
     * Tests the 'squarified' method on an image that is already square.
     * A square image should not change when the 'squarified' method is applied.
     */
    @Test
    void testSquarifiedAlreadySquare() {
        // Use a square image to test that the 'squarified' method does not alter it.
        GrayscaleImage alreadySquare = new GrayscaleImage(new double[][]{{1, 2}, {3, 4}});
        assertEquals(alreadySquare, alreadySquare.squarified(), "Squarifying an already square image should not change the image");
    }

    /**
     * Tests the 'squarified' method on an image that is taller than it is wide.
     * The resulting image should only include the central row(s) to form a square.
     */
    @Test
    void testSquarifiedHeightGreaterThanWidth() {
        // Create a tall image to test the 'squarified' method's behavior on non-square images.
        GrayscaleImage tallImage = new GrayscaleImage(new double[][]{{1}, {2}, {3}});
        // The expected result is a square image containing the central row of the original.
        GrayscaleImage expected = new GrayscaleImage(new double[][]{{2}});
        assertEquals(expected, tallImage.squarified(), "Squarifying an image taller than wide should result in the central row");
    }

    /**
     * Tests the 'getPixel' method on a wide image to ensure it returns correct pixel values.
     * This test specifically checks pixels at the corners of the image.
     */
    @Test
    void testGetPixelOnWideImage() {
        // Group tests for each corner pixel in the wide image to ensure all corners are checked.
        assertAll("Pixels in wide image",
                () -> assertEquals(1, smallWide.getPixel(0, 0), "Top-left pixel of wide image"),
                () -> assertEquals(3, smallWide.getPixel(2, 0), "Top-right pixel of wide image"),
                () -> assertEquals(4, smallWide.getPixel(0, 1), "Bottom-left pixel of wide image"),
                () -> assertEquals(6, smallWide.getPixel(2, 1), "Bottom-right pixel of wide image")
        );
    }

    /**
     * Tests the 'normalized' method on a wide image to check if the average brightness is approximately 127.
     * This test validates the normalization process for images with more columns than rows.
     */
    @Test
    void testNormalizedOnWideImage() {
        // Normalize the small wide image and check if the average brightness is close to the target.
        GrayscaleImage normalizedWide = smallWide.normalized();
        // Check that the average brightness is approximately 127
        assertEquals(127, normalizedWide.averageBrightness(), 127 * 0.001, "Normalized wide image should have average brightness close to 127");
    }

    /**
     * Tests the 'mirrored' method on a wide image to ensure it correctly reverses the order of pixels in each row.
     * This test checks the mirroring functionality for images with more columns than rows.
     */
    @Test
    void testMirroredWideImage() {
        // The expected mirrored image should have rows reversed from the original wide image.
        GrayscaleImage expectedMirroredWide = new GrayscaleImage(new double[][]{{3, 2, 1}, {6, 5, 4}});
        // Check if the mirrored image matches the expected result.
        GrayscaleImage mirroredWide = smallWide.mirrored();
        assertEquals(expectedMirroredWide, mirroredWide, "Mirroring a wide image should reverse each row");
    }

    /**
     * Tests the 'cropped' method on a wide image to ensure it returns the correct section of pixels.
     * This test validates cropping functionality on images with more columns than rows.
     */
    @Test
    void testCroppedFromWideImage() {
        // Crop a section from the bottom row of the wide image.
        GrayscaleImage croppedWide = smallWide.cropped(0, 1, 2, 1);
        // The expected result should match the section of pixels that were cropped.
        GrayscaleImage expectedCroppedWide = new GrayscaleImage(new double[][]{{2, 3}});
        assertEquals(expectedCroppedWide, croppedWide, "Cropping a section from a wide image should return the correct pixels");
    }

    /**
     * Tests the 'squarified' method on a wide image


     /**
     * Tests the 'squarified' method on a wide image to confirm it results in a square image.
     * The test checks that the process correctly crops the rightmost columns if necessary.
     */
    @Test
    void testSquarifiedWideImage() {// Squarify the small wide image and verify the result is square by removing extra columns.

        GrayscaleImage squaredWide = smallWide.squarified();
        // Expected result should be a square image, cropping the rightmost column from smallWide
        GrayscaleImage expectedSquaredWide = new GrayscaleImage(new double[][]{{1, 2}, {4, 5}});
        assertEquals(expectedSquaredWide, squaredWide, "Squarifying a wide image should result in a square image with the rightmost columns cropped");
    }

}