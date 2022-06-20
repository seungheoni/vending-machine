#!/bin/bash

docker build -f Dockerfile -t drink_mongo .
docker save drink_mongo:latest > image/drink_mongo.tar
