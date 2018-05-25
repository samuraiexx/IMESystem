var express = require('express');
var processor = require('./processor');

var app = express();

app.use(express.json());

app.post('/', function(request, response){
    console.log(request.body);      // your JSON

    var answer = processor.process(request.body);
    //var answer = request.body;
    response.send(answer);
    console.log(answer);
});

app.listen(3000);
