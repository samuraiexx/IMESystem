var fs = require('fs');

class sqlConsult{
    login(user, password, superUser) {
        if (user == "samuraiexx" &&
            password == "naru10") return superUser;
        return false;
    }

    alunos(filter){
        return '[' +
            '{"AlunoId" : 15419, "nomeAluno" : "MateusC", "anoGrad" : 2019}, ' +
            '{"AlunoId" : 15420, "nomeAluno" : "Matheus", "anoGrad" : 2019}, ' +
            '{"AlunoId" : 15421, "nomeAluno" : "Matheus", "anoGrad" : 2019}, ' +
            ']';
    }
    disciplinas(filter){
        return '[' +
            ' {"DisciplinaId: 1, "disciplinaNome":"Algelin"}, ' +
            ' {"DisciplinaId: 2, "disciplinaNome":"MecII"}, ' +
            ' {"DisciplinaId: 3, "disciplinaNome":"VeganInitiation"}, ' +
            ']';
    }
    notas(filter){
        return '[' +
            '{"nomeDisciplina" : "Algelin", notasDisciplina : [' +
                '{"nomeAluno" : "fabinho", "notaAluno" : 10, "media" : 9.9}, ' +
                '{"nomeAluno" : "fabao", "notaAluno" : 9.9, "media" : 9.8}, ' +
                '{"nomeAluno" : "suamae", "notaAluno" : 9.9, "media" : 9.8}, ]' +
            '}, ' +
            '{"nomeDisciplina" : "Algelin2", notasDisciplina : [' +
                '{"nomeAluno" : "fabinho", "notaAluno" : 5, "media" : 9.9}, ' +
                '{"nomeAluno" : "fabao", "notaAluno" : 9.9, "media" : 9.8}, ' +
                '{"nomeAluno" : "suamae", "notaAluno" : 9.9, "media" : 9.8}, ]' +
            '}, ' +
        ']';
    }
    appNotas(filter, user){
        return '{"VC" : 10, "VE" : 5, "VF" : 4}';
    }
    appFaltas(filter, user){
        return '{"pontos" : 119}';
    }
}

module.exports = new sqlConsult();
