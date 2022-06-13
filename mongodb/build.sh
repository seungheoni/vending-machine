#!/bin/bash

docker build -f Dockerfile -t drink_mongo .
docker save drink_mongo:latest > build/libs/drink_mongo.tar