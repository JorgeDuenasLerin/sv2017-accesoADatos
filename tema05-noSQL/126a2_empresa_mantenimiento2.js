//Sergio Garcia Balsas

use empresa

//Añadir 3 operarios
db.operario.save({
    codigo:1, 
    nombre:"Sergio", 
    especialidad:[{profesion:"fontaneria"}] } )

db.operario.save({
    codigo:2, 
    nombre:"Fernando", 
    especialidad:[{profesion:"albañil"}] } )

db.operario.save({
    codigo:3, 
    nombre:"Juan", 
    especialidad:[{profesion:"fontaneria"}, {profesion:"pintor"}]})

//Buscar operarios cuyo nombre contega la a: 
db.operario.find({nombre:{$regex:/a/}})

//Añadir incidencias:
db.incidencia.save({
    codigo:1, fechaHora: new Date(), 
    nombreCliente:"Nacho", 
    telefono:680942937, 
    direccion:"Calle Falsa 123", 
    descripcion:"Humedad en pared", 
    especialidad:[{profesion:"albañil"}]})
 
//Incidencia con dos especialidades: 
db.incidencia.save({codigo:2, 
    fechaHora: new Date(), 
    nombreCliente:"Jose", 
    telefono:680942936, 
    direccion:"Calle Verdadera 123", 
    descripcion:"Rotura de baño",
    especialidad: [ { "profesion": "fontaneria" } , 
        { "profesion": "albañil"}] } )

// Buscar una especialidad
  db.incidencias.find({"especialidad.profesion":"fontaneria"})
  
//Añade otra incidencia con codigo y nombre de clietne
db.incidencias.save({codigo:3, nombreCliente:"Paco"})
//Luego añadele fechaHora
db.incidencias.update({codigo:3}, 
    {$set:{fechaHora: new Date()} } )
//Borrala
db.incidencias.remove({codigo:3})

//Visitas
db.visitas.save({
    codigoOperario:1, 
    codigoIncidencia:2, 
    fechaFinalizacion: new Date()})

db.visitas.save({
    codigoOperario:2, 
    codigoIncidencia:2, 
    fechaFinalizacion: new Date()})
