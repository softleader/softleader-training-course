
## Container
 
### Networking

```sh
# Start the container in background
docker run -d --publish-all mytomcat

# List currently running docker containers
docker ps

# Connect to tomcat inside the container
curl localhost:$publishe_port

# Stop the container
docker stop $container_id
```

| Flag value	| Description |
|------------|--------------|
| `-p 8080:80` |	Map TCP port 80 in the container to port 8080 on the Docker host. |
| `-p 192.168.1.100:8080:80` |	Map TCP port 80 in the container to port 8080 on the Docker host for connections to host IP 192.168.1.100. |
| `-p 8080:80/udp` |	Map UDP port 80 in the container to port 8080 on the Docker host.|
`-p 8080:80/tcp -p 8080:80/udp` |	Map TCP port 80 in the container to TCP port 8080 on the Docker host, and map UDP port 80 in the container to UDP port 8080 on the Docker host.

> [https://docs.docker.com/config/containers/container-networking/](https://docs.docker.com/config/containers/container-networking/)

```sh
docker run -d -p 8081:8080 mytomcat

docker ps

curl localhost:8081
```

### Storage

![](https://docs.docker.com/storage/images/types-of-mounts.png)

> [https://docs.docker.com/storage/](https://docs.docker.com/storage/)

```sh
# Start the container in background, $(pwd) 取代成當前的目錄
docker run -d -p 8081:8080 -v "$(pwd)":/logs mytomcat
```

檢查當前目錄:

```sh
.
├── catalina.2018-06-26.log
├── host-manager.2018-06-26.log
├── localhost.2018-06-26.log
├── localhost_access_log..2018-06-26.txt
└── manager.2018-06-26.log
```

### 常用指令

```sh
# List currently running docker containers
docker ps

# List all docker containers (running and stopped)
docker ps -a

# Start a container from an image
docker run -it <IMAGE>

# Start a container
docker start <CONTAINER>

# Stop a container
docker stop <CONTAINER>

# Remove a stopped container
docker rm <CONTAINER>

# Fetch and follow the logs of a container
docker logs -f <CONTAINER>

# Get a shell inside of a container
docker exec -it <CONTAINER> bash
```

### Quiz 2

直接將 `$TOMCAT_HOME/webapps/ROOT` mount 出來讓工程師可以直接修改吧!

## Next steps

- Learn about [Docker Compose](./docker-compose.md)
