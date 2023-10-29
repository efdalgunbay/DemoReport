package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetSS {
    public  void getScreenSS(){
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenshot = robot.createScreenCapture(screenRect);
            File output = new File("src/main/java/screenshot/screenshot.png");
            ImageIO.write(screenshot, "png", output);
            System.out.println("Screenshot saved as screenshot.png");
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}
