var sqlConsult = require('./sqlConsult');

login = function(user, pass, super_user){
    return true;
};

exports.process = function(request) {
    var super_user = "super-user" in request && request["super-user"] == true;

    var valid = sqlConsult.login(request["user"], request["password"], super_user);


    if(!("query" in request) || !valid)
        return "{'valid' : " + valid + "}";

    var query = request["query"];
    var filter = "";
    if("filter" in request) filter = request["filter"];

    if(query in ["alunos", "disciplinas", "notas"] && !super_user)
        return "{'valid' : false}";

    return sqlConsult[query](filter);
};
