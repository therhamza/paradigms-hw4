// Import the RxHR module and use it to get a buffer for the specified URL
const RxHRBuffer = rhr.RxHR.getBuffer('http://localhost:8080/rat/screenshot');

// Get a reference to the 'screenshotBtn' and 'img' elements in the HTML document
const button = document.getElementById('screenshotBtn');
const image = document.getElementById('img');

// Declare a variable to store the interval ID
var intervalId = null;

// Set the onclick event for the button to call the onClick function
button.onclick = onClick;

// Define the onClick function, which is called when the button is clicked
function onClick() {
  
  // Get the value of the first input element on the page
  let n = document.getElementsByTagName('input')[0].value;

  // If the value is less than or equal to 0, display an alert and return
  if (n <= 0) {
    alert("Invalid input! Please input a positive number");
    return;
  }

  // If the interval ID is set, clear the interval and reset the button text
  if (intervalId) {
    clearInterval(intervalId);
    intervalId = null;
    button.textContent = "Start";
    return;
  }

  // Subscribe to the RxHRBuffer and update the image src with the data from the API
  RxHRBuffer.subscribe(data => {
    image.src = 'data:image/jpeg;base64,' + data.body;
  });

  // Set an interval to call the RxHRBuffer subscription every n seconds
  intervalId = setInterval(() => {
    RxHRBuffer.subscribe(data => {
      image.src = 'data:image/jpeg;base64,' + data.body;
    });
  }, 1000 * n);

  // Update the button text
  button.textContent = "Stop";
}
