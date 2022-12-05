# Homework: REST Remote Access Tool Web Service

## Technology Enablers
- Web Service definition language: OpenAPI
    - The OpenAPI Specification, previously known as the Swagger Specification, is a specification for machine-readable interface files for describing, producing, consuming and visualizing RESTful web services.
- Protocol: REST over HTTP 
    - REST refers to a set of attributes of a particular architectural style, while HTTP is a well-defined protocol that happens to exhibit many features of a RESTful system.
- Provider programming language: Java (SpringBoot Application)
    - Spring Boot makes it easy to create stand-alone, production-grade Spring-based Applications that you can run. We take an opinionated view of the Spring platform and third-party libraries, so that you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration.
- Consumer programming languages: JavaScript (axios module)
    - Axios is a promise-based HTTP Client for node.js and the browser. It is isomorphic (= it can run in the browser and nodejs with the same codebase). On the server-side it uses the native node.js http module, while on the client (browser) it uses XMLHttpRequests.


## Development Process: Java-first / Code-first
### Java Provider class
- Rat service business implementation is the following: ma.aui.sse.paradigms.integration.rs.rat.provider.RemoteAccessUtility
- Provider main class creates an instance of the rat business implementation (RemoteAccessUtility class) and publishes it as a web service under `http://localhost:8080/rat` using SpringBoot Application.

### Java server stub code
- The code is built using the following command:
  - **./gradlew build**

### Running the Provider
- **./gradlew run**

### Python consumer
- Install Zeep package using pip:
  - pip install zeep
- run the consumer from the src folder:
  - python src/main/python/consumer.py

### JavaScript consumer
- Install all packages:
  - npm install
- Run the consumer:
  - npm start

## Design of the Web Service Functionalities
The web service RAT does three main functions:
- List all processes running remote server
    - If the client wants to get a list of all processes running on the remote server, they should call the procedure "getProcesses()" remotely.
    - Returns a list of strings, where each string specifies a specific process running on the remote process
- Screenshot remote server
    - If the client wants to take a screenshot of the remote system, they should call the procedure "screenshot()" remotely.
    - Returns a Base64 string representation of a png image, which is the screenshot grabbed.
- Reboot remote server
    - If the client wants to reboot the system, they should call the procedure "reboot()" remotely. 
    - Reboots remote server, and returns true or false as a status code if the rebooting is successful.
