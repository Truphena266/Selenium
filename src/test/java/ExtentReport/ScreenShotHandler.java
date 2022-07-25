package ExtentReport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

public class ScreenShotHandler extends atsqaReport {
	public static void takeScreenShotOnFailure() {
		SimpleDateFormat dateName = new SimpleDateFormat("yyyyMMddhhmmss");
	TakesScreenshot ts = ((TakesScreenshot)driver);
		File source = ts.getScreenshotAs(OutputType.FILE);
		//After execution, you could see a folder "FailedTestsScreenshots" under scr folder
		//String destination = System.getProperty("user.dir") + "/screenshots/" + dateName + ".png";
		String destination = "C:\\Users\\hp\\OneDrive\\Desktop\\automated testing\\Maven1\\screenshots"+"dateName" + ".png";
		 try {
			FileUtils.copyFile(source,new File(destination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

