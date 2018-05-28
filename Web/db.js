var mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'YOURPASSWORD',
  database: 'dummy',
});

exports.exec = function execQuery(query, callback){
    connection.query(query, function(err, rows, fields)
    {
        if(err) callback(false);
        else callback(JSON.parse(JSON.stringify(rows)));
    });
};

