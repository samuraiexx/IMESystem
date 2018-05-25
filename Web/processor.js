var sqlConsult = require('./sqlConsult');


exports.process = function(request) {
    var superUser = "superUser" in request && request["superUser"] == true;

    var valid = sqlConsult.login(request["user"], request["password"], superUser);


    if(!("query" in request) || !valid)
        return "{'valid' : " + valid + "}";

    var query = request["query"];
    var filter = "";
    if("filter" in request) filter = request["filter"];

    if(query in ["alunos", "disciplinas", "notas"] && !superUser)
        return "{'valid' : false}";

    return sqlConsult[query](filter);
};
