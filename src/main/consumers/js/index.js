const axios = require('axios');

const serverUrl = "http://localhost:8080/rat";

const endpoints = {
    list_processes: serverUrl + "/processes",
    screenshot: serverUrl + "/screenshot",
    reboot: serverUrl + "/reboot",
};

axios.get(endpoints.list_processes)
    .then((response) => {
        console.log("The processes running on the remote server are: ")
        response.data.map(proc => console.log(proc))
    })
    .catch(err => {
        console.log("An error occurred while trying to fetch the processes.")
        console.log(err);
    });

axios.get(endpoints.screenshot)
    .then((response) => {
        console.log("The screenshot file encoded in base64 is: ")
        console.log(response.data)
        if (response.data != "") {
            var base64Data = response.data.replace(/^data:image\/png;base64,/, "");
            require("fs").writeFile("output.png", base64Data, 'base64', function(err) {
                console.log(err);
            });
        }
    })
    .catch(err => {
        console.log("An error occurred while trying to fetch the screenshot.")
        console.log(err);
    });

axios.get(endpoints.reboot)
    .then((response) => {
        if (response.data)
            console.log("The remote server is successfully rebooting!")
        else
            console.log("The remote server has not successfully rebooted!")
    })
    .catch(err => {
        console.log("An error occurred while trying to reboot remotely.")
        console.log(err);
    });