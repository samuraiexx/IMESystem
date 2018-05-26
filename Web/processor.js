var sqlConsult = require('./sqlConsult');


exports.process = function(request, callback) {
    if("newUser" in request && request["newUser"] == true)
        return sqlConsult.newUser(request, callback);


    var superUser = "superUser" in request && request["superUser"] == true;

    sqlConsult.login(request["user"], request["password"], superUser, function (valid) {
        if (!("query" in request) || !valid)
            return callback(valid);

        var query = request["query"];
        var filter = "";
        if ("filter" in request) filter = request["filter"];

        if (query in ["alunos", "disciplinas", "notas"] && !superUser)
            return false;


        sqlConsult[query](filter, callback);
    });
};
