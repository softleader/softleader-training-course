## Docker Compose

![](https://cdn-images-1.medium.com/max/2000/1*_NQN6_YnxS29m8vFzWYlEg.png)

### Define services in a Compose file

Create a file called `docker-compose.yml` and paste the following:

```
version: '3'
services:
  mytomcat:
    image: mytomcat
    ports:
     - "8081:8080"
    volumes:
     - .:/logs
```

> [https://docs.docker.com/compose/gettingstarted/](https://docs.docker.com/compose/gettingstarted/)

### Start services with Compose

```sh
# Create and start all containers in the background
docker-compose up -d

# Connect to tomcat inside the container
curl localhost:8081
```

### 常用指令:

```sh
# Create and start all containers in the background
docker-compose up -d

# Stop all running containers
docker-compose stop

# Stop and remove all containers, networks, images, and volumes
docker-compose down

# Follow logs for all containers
docker-compose logs --follow
```

### Quiz 3

將 `index.html` mount 出來的版本也改成 compose 吧

## Next steps

- Learn about [What's more](./more.md)
