package ma.aui.sse.paradigms.integration.rs.rat.provider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rat")
public class RemoteAccessUtility {

    @GetMapping("/processes")
    public ArrayList<String> getProcesses() {

        ArrayList<String> listOfAllProcesses = new ArrayList<String>();
            
        StringBuilder processes = new StringBuilder("");

        ProcessHandle.allProcesses().forEach(
            process -> listOfAllProcesses.add(processDetails(process)) 
        );

        return listOfAllProcesses;

    }


    @GetMapping("/screenshot")
    public String screenshot() {

        try {

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenshot = new Robot().createScreenCapture(screenRect);

            String extension = "png";

            return encodeToString(screenshot, extension);

        } 
        catch (Exception ex) {
            
            return "";

        }
    }

    // @GetMapping(value = "/screenshot", produces = MediaType.IMAGE_JPEG_VALUE)
    // public byte[] screenshot() {

    //     try {

    //         Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    //         BufferedImage capture = new Robot().createScreenCapture(screenRect);

    //         ByteArrayOutputStream temp = new ByteArrayOutputStream();
    //         ImageIO.write(capture, "jpg", temp);

    //         return temp.toByteArray();

    //     } 
    //     catch (Exception ex) {
            
    //         return new byte[0];

    //     }
    // }

    @GetMapping("/reboot")
    public boolean reboot() {

        try {

            ProcessBuilder builder = System.getProperty("os.name").toLowerCase().contains("win")
                    ? new ProcessBuilder("shutdown", "-r", "-t", "1")
                    : new ProcessBuilder("shutdown", "-r", "now");

            Process process = builder.start();

            return true;

        } 
        catch (Exception ex) {
            
            return false;

        }
							
    }

    private static String processDetails(ProcessHandle process) {

		return String.format("%8d %8s %10s %-40s",
				process.pid(),
				text(process.parent().map(ProcessHandle::pid)),
				text(process.info().user()),
                text(process.info().commandLine()));
                
	}
	
    private static String text(Optional<?> optional) {

        return optional.map(Object::toString).orElse("-");

    }


    private static String encodeToString(BufferedImage image, String type) {
         
        String imageString = null;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  

        try {
            
            ImageIO.write(image, type, bos);  
            byte[] imageBytes = bos.toByteArray();  
            imageString = Base64.getEncoder().encodeToString(imageBytes);  
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return imageString;
        
    } 
    
}