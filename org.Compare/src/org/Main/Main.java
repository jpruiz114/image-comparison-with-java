package org.Main;

import org.Compare.Compare;
import org.ImageSpiders.ImageSpider1;
import org.ImageSpiders.ImageSpider2;

public class Main
{
    public static void main(String[] args) throws InterruptedException, Exception
    {
        Compare Compare = new Compare(
        		"C:\\Users\\usuario\\Downloads\\image-comparison-with-java\\assets\\1.jpg",
        		"C:\\Users\\usuario\\Downloads\\image-comparison-with-java\\assets\\2.jpg"
        		);
        ImageSpider1 ImageSpider1 = Compare.getImageSpiderObject1();
        ImageSpider2 ImageSpider2 = Compare.getImageSpiderObject2();
        if (Compare.isDone() == true && Compare.isTheSame() == true) {
            System.out.printf("Image are the same!\n");
        } else if (Compare.isDone() == true && Compare.isTheSame() != true) {
            System.out.printf("Image are not the same ;(\n");
        }
    }
}
