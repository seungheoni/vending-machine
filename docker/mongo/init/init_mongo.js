db.createUser(
    {
        user: "seongheon",
        pwd: "qwer1234",
        roles: [ { role: "readWrite", db: "vending-machine" } ]
    }
);
db.createCollection("drink");

