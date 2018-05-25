var fs = require('fs');

class sqlConsult{
    login(user, password, super_user) {
        if (user == "samuraiexx" &&
            password == "naru10") return super_user;
        return false;
    }

    alunos(filter){
        return '{"a":1,"b":2,"s":"oi","obj2":{"c":4}}';
    }
    disciplinas(filter){
        return '{"a":1,"b":2,"s":"oi","obj2":{"c":4}}';
    }
    notas(filter){
        return '{"a":1,"b":2,"s":"oi","obj2":{"c":4}}';
    }
    appNotas(filter){
        return '{"a":1,"b":2,"s":"oi","obj2":{"c":4}}';
    }
    appFaltas(filter){
        return '{"a":1,"b":2,"s":"oi","obj2":{"c":4}}';
    }
}

module.exports = new sqlConsult();
