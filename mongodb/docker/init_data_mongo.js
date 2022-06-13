db.createUser(
    {
        user: "seongheon",
        pwd: "qwer1234",
        roles: [ { role: "readWrite", db: "vending-machine" } ]
    }
);
db.createCollection("drink");
db.drink.insert([{ "_id" : 0 , "name": "콜라" , "price": 2000 , "quantity" : 5},
    { "_id": 1 , "name" : "식혜" , "price": 3000 , "quantity" : 5},
    { "_id": 2 , "name": "사이다" , "price": 1000 , "quantity" : 5}]);





