// Declare the package for the class
package ma.aui.sse.paradigms.integration.rs.rat.provider;

// Import the IOException class
import java.io.IOException;

// Import the SpringApplication and SpringBootApplication classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation to enable Spring Boot auto-configuration and component scanning
@SpringBootApplication
public class Provider {

  // The main method is the entry point of the application
  public static void main(String[] args) {
    // Set the java.awt.headless property to false
    System.setProperty("java.awt.headless", "false");
    // Run the SpringApplication, passing in the Provider class and the command line arguments
    SpringApplication.run(Provider.class, args);
  }
}
