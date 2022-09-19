db.createUser(
    {
        user: "seongheon",
        pwd: "qwer1234",
        roles: [ { role: "readWrite", db: "vending-machine" } ]
    }
);
db.createCollection("drink");
db.createCollection("display");
db.createCollection("cash");
db.createCollection("transaction");
