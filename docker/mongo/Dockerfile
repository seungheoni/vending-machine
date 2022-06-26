FROM mongo:latest

ENV MONGO_INITDB_ROOT_USERNAME seongheon
ENV MONGO_INITDB_ROOT_PASSWORD qwer1234
ENV MONGO_INITDB_DATABASE vending-machine

COPY init/init_mongo.js /docker-entrypoint-initdb.d/

EXPOSE 27017