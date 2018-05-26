var fs = require('fs');

var db = require('./db.js');

function queryHelper(row, filter, boolop) {
  var s = "(";
  for (x in filter) {
    s += row + " = '" + filter[x] + "' " + boolop + " ";
  }
  return s.slice(0, -4) + ")";
}


class sqlConsult {
  login(user, password, superUser) {
    if (user == "samuraiexx" &&
      password == "naru10") return superUser;
    return false;
  }

  // SELECT alunoId, anoGrad from aluno WHERE
  alunos(filter,callback, callback) {
    const query = "SELECT alunoId,nome as nomeAluno, anoGrad from aluno;"
    db.exec(query,callback);
  }
  //SELECT distinct disciplina from (SELECT aluno.alunoId, nota.disciplina, nota.periodo from aluno join nota on aluno.alunoId = nota.alunoId) t where periodo = 6;
  disciplinas(filter,callback) {
    const query = "SELECT distinct disciplina from (SELECT aluno.alunoId," +
      " nota.disciplina, nota.periodo from aluno join nota on" +
      " aluno.alunoId = nota.alunoId) t where periodo = " + filter["periodo"] + ";"

    db.exec(query,callback);
  }
  // { id : [15416,15419], periodo : 6, disciplinas}
  notas(filter,callback) {
    var clause = queryHelper("alunoId",filter["id"],"OR") + " AND " + queryHelper("disciplina", filter["disciplina"],"OR") + " AND periodo = " + filter["periodo"];
    var table =  "(select a.alunoId,disciplina,periodo, (VE+VC)*0.25+VF*0.5 as media from aluno a join nota b on a.alunoId = b.alunoId order by disciplina) t"
    var query =  "select alunoId,disciplina,media from " +table+" where" + clause + ";";

    db.exec(query,callback);
  }
  appNotas(filter,callback, user) {
    return '{"VC" : 10, "VE" : 5, "VF" : 4}';
  }
  appFaltas(filter,callback, user) {
    return '{"pontos" : 119}';
  }
}

module.exports = new sqlConsult();
