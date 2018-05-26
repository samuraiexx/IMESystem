var fs = require('fs');

var db = require('./db.js');

function queryHelper(row, filter, boolop) {
  var s = "(";
  for (x in filter) {
    s += row + " = '" + filter[x] + "' " + boolop + " ";
  }
  return s.slice(0, -4) + ")";

}

function isEmpty(obj) {
  for(var key in obj) {
    if(obj.hasOwnProperty(key))
      return false;
  }
  return true;
}

class sqlConsult {
  /*
        {
          "newUser" : true,
          "name" : "Mateus de Castro",
          "user" : "samuraiexx",
          "password" : "naru10",
          "anoGrad" : 2019,
          "alunoId" : 15419
        }
        */
  newUser(userData, callback){
    var query = "SELECT user FROM aluno WHERE user ="+userData["user"] + ";";
    db.exec(query, function (ans){
      if (!isEmpty(ans)) callback(false);
      else {
        query = "insert into aluno values ("+
                ans["alunoId"]  + ", '"+
                ans["user"]     + "', '"+
                ans["password"] + "', '"+
                ans["name"]     + "', "+
                ans["anoGrad"]  + ");";
        var smthng;
        db.exec(query,smthng);
        callback(true);
      }
    )
    }
  }

  /*
  */
    login(user, password, superUser, callback) {
      const table = (superUser?"admin":"aluno");
      const query = "SELECT user, password FROM " + table + " WHERE user = " + user + ";";
      const ans = db.exec(query, function(ans){
        ans = ans[0];
        if("password" in ans) callback(ans["password"] == password);
        else callback(false);
      });
    }

  // SELECT alunoId, anoGrad FROM aluno WHERE
    alunos(filter,callback) {
        const query = "SELECT alunoId,nome as nomeAluno, anoGrad FROM aluno;"
        db.exec(query,callback);
    }
  //SELECT distinct disciplina FROM (SELECT aluno.alunoId, nota.disciplina, nota.periodo FROM aluno JOIN nota on aluno.alunoId = nota.alunoId) t WHERE periodo = 6;
    disciplinas(filter,callback) {
        const query = "SELECT distinct disciplina as disciplinaNome FROM (SELECT aluno.alunoId," +
            " nota.disciplina, nota.periodo FROM aluno JOIN nota on" +
            " aluno.alunoId = nota.alunoId) t WHERE periodo = " + filter["periodo"] + ";";

        db.exec(query,callback);
    }
  // { id : [15416,15419], periodo : 6, disciplinas}
    notas(filter,callback) {
        const clause = queryHelper("alunoId",filter["id"],"OR") + " AND " + queryHelper("disciplina", filter["disciplinas"],"OR") + " AND periodo = " + filter["periodo"];
        const table =  "(SELECT a.alunoId,disciplina,periodo, (VE+VC)*0.25+VF*0.5 as media FROM aluno a JOIN nota b on a.alunoId = b.alunoId order by disciplina) t"
        const query =  "SELECT alunoId,disciplina,media FROM " +table+" WHERE " + clause + ";";

        db.exec(query,callback);
    }
  /*
        {
            "user" : "samuraiexx",
            "password" : "naru10",
            "query" : "appNotas",
            "filter" : {
                "periodo" : 4,
                "disciplina" : "algelin"
                }
            }
        }
        */
  //SELECT VE,VC,VF FROM aluno JOIN nota ON aluno.alunoId = nota.alunoId
  //  WHERE periodo = 6 AND disciplina = 'LAB PROG II' AND usuario = 'lucbarr';
  appNotas(filter,callback, user) {
    const query = "SELECT VE,VC,VF FROM aluno JOIN nota on aluno.alunoId = nota.alunoId "+
                  "WHERE periodo = " + filter["periodo"]    + "  and " +
                  "disciplina = '"   + filter["disciplina"] + "' and "+
                  "usuario = '"      + user       + "';";
    db.exec(query,callback);
  }
  /*
        {
            "user" : "samuraiexx",
            "password" : "naru10",
            "query" : "appFaltas",
            "filter" : {
                "periodo" : 4
            }
        }
        */
  //SELECT pontos FROM aluno JOIN falta ON aluno.alunoId = falta.alunoId
  //  WHERE usuario = 'samuraiexx' AND periodo = 7;
  appFaltas(filter,callback, user) {
    const query = "SELECT pontos FROM aluno JOIN falta ON aluno.alunoId = falta.alunoId WHERE "+
                  "usuario = '" + user + "' AND periodo = " + filter["periodo"] + ";";
    db.exec(query,callback);
    //callback('{"pontos" : 119}');
  }
}

module.exports = new sqlConsult();
