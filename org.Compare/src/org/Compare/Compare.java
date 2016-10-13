package org.Compare;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.ImageSpiders.ImageSpider1;
import org.ImageSpiders.ImageSpider2;

public class Compare {

    // this is path information about the images
    protected String ImagePath1;
    protected String ImagePath2;
    protected ImageSpider1 ImageSpider1;
    protected ImageSpider2 ImageSpider2;

    // this is information that tells if they we the same.
    protected boolean Same = false;
    protected boolean Done = false;

    public Compare(String path1, String path2) throws Exception {
        ImageSpider1 = new ImageSpider1(path1);
        ImageSpider2 = new ImageSpider2(path2);
        CompareHandler compareHandler = new CompareHandler();
        Same = compareHandler.CompareHandler(ImageSpider1, ImageSpider2);
        Done = true;
    }

    public ImageSpider1 getImageSpiderObject1() {
        return this.ImageSpider1;
    }

    public ImageSpider2 getImageSpiderObject2() {
        return this.ImageSpider2;
    }

    // returns the locations of each image path
    public String getImagePath1() {
        return this.ImagePath1;
    }

    // returns the locations of each image path
    public String getImagePath2() {
        return this.ImagePath2;
    }

    public boolean isTheSame() {
        return this.Same;
    }

    public boolean isDone() {
        return this.Done;
    }
}
