#!/bin/sh

docker build -t softleader/nginx-training:1.15.10 -f Dockerfile-15 .
docker push softleader/nginx-training:1.15.10

docker build -t softleader/nginx-training:1.14.2 -f Dockerfile-14 .
docker push softleader/nginx-training:1.14.2
