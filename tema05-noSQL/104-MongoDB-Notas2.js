// Alumnos con el número 10 o mayor

db.alumnos.find( { num : { $gte : 10 }})

// Alumnos con el número 10, 12 o 14

db.alumnos.find( { num : { $in : [10,12,14] }})

// Alumnos cuyo apellido contiene una "o"

db.alumnos.find( { apellidos : { $regex : /o/ }})

// Alumnos cuyo apellido termina en "o"

db.alumnos.find( { apellidos : { $regex : /o$/ }})

// Alumnos cuyo apellido contiene una "o" o una "O"

db.alumnos.find( { apellidos : { $regex : /[oO]/ }})
db.alumnos.find( { apellidos : { $regex : /o/i }})

db.alumnos.find( 
    { $or :
        [ 
           {apellidos : { $regex : /o/ }},
           {apellidos : { $regex : /O/ }}
        ]
    }
)

// Alumnos cuyo apellido contiene una "s" o cuyo numero es < 9

db.alumnos.find( 
    { $or :
        [ 
           {apellidos : { $regex : /s/ }},
           { num : { $lt : 9 }}
        ]
    }
)

// Añadir un nuevo alumno

db.alumnos.save(
    {
        nombre: "Antonio",
        apellidos: "Sevila",
        num: 7,
        notas:
        [
            {asignatura: "Sistemas",
            nota: 8}
        ]
    }
)

// Cambiar el código de un alumno

db.alumnos.update(
    {apellidos: "Sevila"},
    {$set: {num: 17 }}
)

// Lista de alumnos, ordenados por apellidos

db.alumnos.find().sort( { apellidos : 1 } );

// Lista de alumnos, sólo nombre y apellidos

db.alumnos.find({}, { nombre: 1, apellidos : 1 } );

// Para forzar a que no aparezca la "id":

db.alumnos.find({}, { _id: 0, nombre: 1, apellidos : 1 } );

// Solo nombre y apellidos, ordenado

db.alumnos.find(
    {}, { _id: 0, nombre: 1, apellidos : 1 } 
    ).sort( { apellidos : 1 } );

// Alumnos con dos o más notas

db.alumnos.find(
    { $where : "this.notas.length >= 2" });

// Primera nota de cada alumno

db.alumnos.find({}, { notas:{$slice : 1}} );

db.alumnos.find({}, { _id: 0, nombre: 1, apellidos : 1,
    notas:{$slice : 1} } );
