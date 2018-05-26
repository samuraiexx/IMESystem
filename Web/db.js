var mysql = require('mysql');
var express = require('express');
var app = express();
var router = express.Router();


var fs = require('fs');
var obj = JSON.parse(fs.readFileSync('req.json', 'utf8'));

console.log(obj);

var filters = obj[0]["filter"];
console.log(filters);


var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '11235813',
  database: 'teste',
});

function execQuery(data,filter){
  var q = "SELECT " + data + " FROM aluno ";
  var slice = false;
  if (Object.keys(filter).length) {
    q+= " WHERE ";
    slice = true;
  }
  var s = "";
  var x
  for (x in filter){
    if ( filter.hasOwnProperty(x) ){
      s+=  x + " = " + filter[x] + " AND ";
      console.log(x);
    }
  }
  var q = (!slice? (q+s) : ((q+s).slice(0,-4)));
  return q;
}

var query = execQuery(obj[0]["data"],filters[0]);

connection.connect();

connection.query(query, function(err, rows, fields)
{
  if (err) throw err;

  console.log(rows[0]);
});

connection.end();