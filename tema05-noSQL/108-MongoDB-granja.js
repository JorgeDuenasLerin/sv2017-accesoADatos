// 1. Crea una base de datos para una granja. La primera colección, de animales, tendrá los siguientes datos (nombre, especie, peso):
// Lola, vaca, 800
// Luis, perro, 300
// Lucas, pato, 12
// Paca, vaca, 790

use granja;

db.animales.save({nombre:"Lola", especie:"vaca", peso:200})
db.animales.save({nombre:"Luis", especie:"perro", peso:30})
db.animales.save({nombre:"Lucas", especie:"pato", peso:12})
db.animales.save({nombre:"Paca", especie:"vaca", peso:790})

// 
// Consultas y modificaciones:
// 
// 2. La vaca Lola pesa 810 kilos
db.animales.update(
	{nombre:"Lola"}, 
	{$set:{peso:810}}
)

db.animales.update(
	{nombre:"Lola",especie:"vaca",peso:600},
	{$set:{peso:810}});

db.animales.update(
	{nombre:"Lola", tipo: "vaca", peso: 800},
	{nombre:"Lola", tipo: "vaca", peso: 810})

// 
// 3. Mostrar todos los animales
db.animales.find()

// 4. Mostrar las vacas
db.animales.find({especie:"vaca"})
 
// 5. Mostrar todos los animales que pesen menos de 100 kilos
db.animales.find({peso:{$lt:100}})

// 6. Mostrar los animales cuyo nombre empieza por L
db.animales.find({nombre:{$regex:/^L/}})
db.animales.find({nombre:{$regex:/^l/i}})

// 7. Mostrar los animales cuyo nombre tiene 4 letras
db.animales.find({$where:"this.nombre.length==4"});

// 8. Mostrar el nombre y especie de los animales, ordenado alfabéticamente por nombre.
db.animales.find(
	{},
	{ _id: 0, nombre:1, especie:1}).
		sort({nombre:1})

// 9. Animales que sean vacas o patos
db.animales.find(
	{$or: [ {especie:"vaca"}, 
		{especie:"pato"} ] } 
)

db.animales.find( 
	{ especie : 
		{ $in : ["vaca", "pato"] }})

db.animales.find(
	{$where:"this.especie == 'vaca' || this.especie == 'pato'"});
