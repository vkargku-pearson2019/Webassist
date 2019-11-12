package webassist.util;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {

    private WebDriver wd;

    public CaptureScreenshot(WebDriver wd){
        this.wd = wd;
    }

    public String takeFullScreenshot(){
        Screenshot fullScreenshot = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(1000)).
                takeScreenshot(wd);
        String currentTimeStamp = DateTimeFormat.getFormattedDateTime();
        String dest = System.getProperty("user.dir") + "/reports/screenshots/Screenshot_"+ currentTimeStamp +".png";
        try {
            ImageIO.write(fullScreenshot.getImage(),"PNG",
                    new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
