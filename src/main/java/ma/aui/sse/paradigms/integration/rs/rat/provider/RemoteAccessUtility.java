package ma.aui.sse.paradigms.integration.xs.rat.provider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;

/**
 * This class provides a web service that allows remote access to various system-level functions on a
 * computer running the Java Virtual Machine (JVM).
 */
@WebService
public class RemoteAccessUtility {

    /**
     * Returns a list of all running processes on the system, along with some details about each process.
     *
     * @return an ArrayList of strings containing details about each process
     */
    public ArrayList<String> getProcesses() {
        ArrayList<String> listOfAllProcesses = new ArrayList<String>();
        ProcessHandle.allProcesses().forEach(
            process -> listOfAllProcesses.add(processDetails(process)) 
        );
        return listOfAllProcesses;
    }

    /**
     * Takes a screenshot of the entire screen and returns the screenshot as a base64-encoded string.
     *
     * @return a base64-encoded string representing the screenshot
     */
    public String screenshot() {
        try {
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenshot = new Robot().createScreenCapture(screenRect);
            String extension = "png";
            return encodeToString(screenshot, extension);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Reboots the system.
     *
     * @return true if the reboot was successful, false otherwise
     */
    public boolean reboot() {
        try {
            ProcessBuilder builder = System.getProperty("os.name").toLowerCase().contains("win")
                    ? new ProcessBuilder("shutdown", "-r", "-t", "1")
                    : new ProcessBuilder("shutdown", "-r", "now");
            Process process = builder.start();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Encodes a BufferedImage object as a base64-encoded string.
     *
     * @param image the BufferedImage object to be encoded
     * @param type the file type of the image (e.g. "png")
     * @return a base64-encoded string representing the image
     */
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
