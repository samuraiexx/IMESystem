var mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'naru10',
  database: 'dummy',
});

exports.exec = function execQuery(query, callback){
    connection.query(query, function(err, rows, fields)
    {
        if (err) throw err;
        callback(JSON.parse(JSON.stringify(rows)));
    });
};

