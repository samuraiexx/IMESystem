var express = require('express');
var processor = require('./processor');

var app = express();

app.use(express.json());

app.post('/', function(request, response){
    console.log(request.body);      // your JSON

    //var answer = request.body;
    processor.process(request.body, function (answer) {
        console.log(answer);
        response.send(answer);
    });
});

app.listen(3000);
