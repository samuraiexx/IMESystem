var mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'naru10',
  database: 'teste',
});

exports.exec = function execQuery(query, callback){
    connection.connect();
    connection.query(query, function(err, rows, fields)
    {
        connection.end();
        if (err) throw err;
        callback(JSON.parse(JSON.stringify(rows)));
    });
};

