// Comenzar a usar una base de datos en MongoDB
use polos

// Añadir un dato
db.dehielo.save({nombre:"Calippo", precio: 1.1})

// Mostrar todos
db.dehielo.find()

// Buscar uno concreto
db.dehielo.find( {nombre: "Calippo" } )

// Añadir otro
db.dehielo.save({nombre:"Capitán Cola", marca: "Frigo"})

// Modificar uno, a partir de todos sus datos
> db.dehielo.update(
  {nombre:"Calippo", precio: 1.1},
  {nombre:"Calippo", precio: 1.1, marca: "Frigo"}
)

// A continuación, sigue un volcado completo de la ejecución

/*
> show dbs
admin   0.000GB
config  0.000GB
local   0.000GB
> db
test
> use polos
switched to db polos
> db
polos
> show dbs
admin   0.000GB
config  0.000GB
local   0.000GB
> db.dehielo.save({nombre:"Calippo", precio: 1.1})
WriteResult({ "nInserted" : 1 })
> db.dehielo.find()
{ "_id" : ObjectId("5a54fe05da856f0243946d83"), "nombre" : "Calippo", "precio" :
 1.1 }
> db.dehielo.find( {nombre: "Calippo" } )
{ "_id" : ObjectId("5a54fe05da856f0243946d83"), "nombre" : "Calippo", "precio" :
 1.1 }
> db.dehielo.save({nombre:"Capitán Cola", marca: "Frigo"})
WriteResult({ "nInserted" : 1 })
> db.dehielo.find()
{ "_id" : ObjectId("5a54fe05da856f0243946d83"), "nombre" : "Calippo", "precio" :
 1.1 }
{ "_id" : ObjectId("5a55009ada856f0243946d84"), "nombre" : "Capitán Cola", "marc
a" : "Frigo" }
> db.dehielo.update(
...     {nombre:"Calippo", precio: 1.1},
...     {nombre:"Calippo", precio: 1.1, marca: "Frigo"}
... )
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.dehielo.find()
{ "_id" : ObjectId("5a54fe05da856f0243946d83"), "nombre" : "Calippo", "precio" :
 1.1, "marca" : "Frigo" }
{ "_id" : ObjectId("5a55009ada856f0243946d84"), "nombre" : "Capitán Cola", "marc
a" : "Frigo" }
>
*/
