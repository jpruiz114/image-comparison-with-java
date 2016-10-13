package org.Compare;

import org.ImageSpiders.ImageSpider1;
import org.ImageSpiders.ImageSpider2;

public class CompareHandler {

    public boolean CompareHandler(ImageSpider1 ImageSpider1, ImageSpider2 ImageSpider2) {
        boolean Same = (ImageSpider1.getRed() == ImageSpider2.getRed()) && (ImageSpider1.getGreen() == ImageSpider2.getGreen()) && (ImageSpider1.getBlue() == ImageSpider2.getBlue());
        return Same;
    }
}
