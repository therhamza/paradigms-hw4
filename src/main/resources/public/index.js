const RxHRBuffer = rhr.RxHR.getBuffer('http://localhost:8080/rat/screenshot');

const button = document.getElementById('screenshotBtn');
const image = document.getElementById('img');
var intervalId = null;

function onClick () {
    
    let n = document.getElementsByTagName('input')[0].value;

    if (n <= 0) {
        alert("Invalid input! Please input a positive number");
        return;
    }

    if (intervalId) {
        clearInterval(intervalId);
        intervalId = null;
        button.textContent = "Start";
        return;
    }

    RxHRBuffer.subscribe(data => {
        image.src ='data:image/jpeg;base64,' + data.body;
    });

    intervalId = setInterval(() => {
        RxHRBuffer.subscribe(data => {
            image.src = 'data:image/jpeg;base64,' + data.body;
        });
    }, 1000 * n);

    button.textContent = "Stop";


}