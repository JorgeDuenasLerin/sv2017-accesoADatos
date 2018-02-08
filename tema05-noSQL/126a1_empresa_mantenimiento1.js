//Antonio Sevila
use empMantenimiento;

//Inserts operarios

db.operarios.save({codigo:"111", 
    nombre: "Antonio Sevila", 
    especialidades: ["Fontanero"]});

db.operarios.save({codigo:"222", 
    nombre: "Nacho Cabanes", 
    especialidades: ["Fontanero","Pintor"]});

db.operarios.save({codigo:"333", 
    nombre: "Abilio Reig", 
    especialidades: ["Albañil"]});

//Encontrar usuarios cuyo nombre contenga "a"

db.operarios.find({nombre: {$regex: /a/i}});

//Inserts incidencias

db.incidencias.save({
    codigo: "111", 
    fechahora: new Date(),
    nomcliente: "Pepe", 
    telefono: "(+34) 111222333", 
    direccion: "C/ de Pepe", 
    descripcion: "Problemas con una tuberia", 
    especialidades: ["Fontanero"]});
    
db.incidencias.save({
    codigo: "222", 
    fechahora: new Date(),
    nomcliente: "Juan", 
    telefono: "(+34) 444333222", 
    direccion: "C/ de Juan", 
    descripcion: "Problemas con la fachada", 
    especialidades: ["Albañil","Pintor"]});

// Buscar las incidencias en las que aparezca exactamente una espec.
db.incidencias.find({especialidades: ["Fontanero"]});
    
// Buscar las incidencias en las que aparezca una espec. (y quizá otras)
db.incidencias.find({especialidades: "Fontanero"});

// Guardar, modificar, borrar
db.incidencias.save({codigo: "333", nomcliente: "borrar"});
db.incidencias.update({codigo:"333"}, {$set: {fechahora: new Date()}});
db.incidencias.remove({codigo:"333"});

// Visitas
db.visitas.save({
    codigooperario:"222", 
    codigoIncidencia:"222", 
    fechaHoraVisita: new Date(), 
    descripcionTareas: "cosas hechas"});

db.visitas.save({codigooperario:"333", 
    codigoIncidencia:"222", 
    fechaHoraVisita: new Date(), 
    descripcionTareas: "cosas hrchas2"});

db.visitas.update({codigoIncidencia:"222"},{$set:{fechaFinalizacion: new Date()}});

