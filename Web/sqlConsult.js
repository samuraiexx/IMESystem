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
    var query = "insert into aluno values ("+
      userData["alunoId"]  + ", '"+
      userData["user"]     + "', '"+
      userData["password"] + "', '"+
      userData["name"]     + "', "+
      userData["anoGrad"]  + ");";
    db.exec(query, function(ans) {
      callback(ans != false)
    });
  }

  /*
  */
  login(user, password, superUser, callback) {
    var table = (superUser?"admin":"aluno");
    var query = "SELECT usuario, senha FROM " + table + " WHERE usuario = '" + user + "';";
    db.exec(query, function(ans){
      if(ans.length && "senha" in ans[0])
        callback(ans[0]["senha"] == password);
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
  notas(filter, callback) {
    const clause = queryHelper("alunoId",filter["id"],"OR") + " AND " + queryHelper("disciplina", filter["disciplinas"],"OR") + " AND periodo = " + filter["periodo"];
    const table =  "(SELECT nome, a.alunoId,disciplina,periodo, (VE+VC)*0.25+VF*0.5 as media FROM aluno a JOIN nota b on a.alunoId = b.alunoId) t"
    const query =  "SELECT nome as nomeAluno, disciplina, media as notaAluno FROM " +table+" WHERE " + clause + " order by disciplina;";
    db.exec(query, function(result) {
        var disciplina = "";
        var ans = [];
        var notasDisciplina = [];
        
        for(var i = 0; i < result.length; i++){
          var t = result[i];
          if(t["disciplina"] != disciplina){
            if(disciplina != ""){
              ans.push({ nomeDisciplina: disciplina, notasDisciplina: notasDisciplina});
              notasDisciplina = [];
            }
            disciplina = t["disciplina"];
          }
          var nota = {nomeAluno: t["nomeAluno"], notaAluno: t["notaAluno"]};
          notasDisciplina.push(nota);
        }
        if(result.length > 0)
          ans.push({ nomeDisciplina: disciplina, notasDisciplina: notasDisciplina});

        callback(ans)
      });

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
     */
  //SELECT VE,VC,VF FROM aluno JOIN nota ON aluno.alunoId = nota.alunoId
  //  WHERE periodo = 6 AND disciplina = 'LAB PROG II' AND usuario = 'lucbarr';
  appNotas(filter,callback, user) {
    const query = "SELECT VE,VC,VF FROM aluno JOIN nota on aluno.alunoId = nota.alunoId "+
      "WHERE periodo = " + filter["periodo"]    + "  and " +
      "disciplina = '"   + filter["disciplina"] + "' and "+
      "usuario = '"      + user       + "';";
    db.exec(query, function(ans) {
      if(Object.keys(ans).length == 0)
        callback("{ VE: -1, VC : -1, VF: -1 }");
      else callback(ans[0]);
    });
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
    db.exec(query, function(ans) {
      if(Object.keys(ans).length == 0) callback("{ pontos: 0 }");
      else callback(ans[0]);
    });
  }
}

module.exports = new sqlConsult();
