use notas;

db.alumnos.save(
    {
        nombre: "Daniel",
        apellidos: "Ossa",
        num: 12,
        notas:
        [
            {asignatura: "Acceso a Datos",
            nota: 5},
            {asignatura: "Procesos",
            nota: 6}
        ]
    }
)

db.alumnos.save(
    {
        nombre: "Javier",
        apellidos: "Montejo",
        num: 11,
        notas:
        [
            {asignatura: "Acceso a Datos",
            nota: 6}
        ]
    }
)

db.alumnos.find();

db.alumnos.find( {num: 11 } );
