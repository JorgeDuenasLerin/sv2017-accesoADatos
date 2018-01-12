use ropa
db.pantalones.save( { tipo: "vaqueros azules", talla: 44 })
db.pantalones.save( { tipo: "chandal", marca: "adidas", talla: 42 })
db.pantalones.update( 
    { tipo: "chandal", marca: "adidas", talla: 42 } , 
    {tipo: "chandal", marca: "nike", talla: 42 } )
db.pantalones.find({talla: 44})
