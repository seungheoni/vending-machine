db.createUser(
    {
        user: "seongheon",
        pwd: "qwer1234",
        roles: [ { role: "readWrite", db: "vending-machine" } ]
    }
);
db.createCollection("drink");
db.drink.insert([{ "_id" : ObjectId() , "name": "콜라" , "price": 2000 , "quantity" : 5, "displays": [{"position": 1}, {"position": 2}, {"position": 3}]},
    { "_id": ObjectId() , "name" : "식혜" , "price": 3000 , "quantity" : 5, "displays": [{"position": 4}, {"position": 5}, {"position": 6}]},
    { "_id": ObjectId() , "name": "사이다" , "price": 1000 , "quantity" : 5, "displays": [{"position": 7}, {"position": 8}]}]);




