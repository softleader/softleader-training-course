## How docker changes the way we develop

### No more installations

```sh
docker run --rm -d -p 6379:6379 redis
```

> [Redis Docker Hub](https://hub.docker.com/_/redis/)

```sh
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=<YourStrong!Passw0rd>' \
   -p 1433:1433 --name sql1 \
   -d microsoft/mssql-server-linux:2017-latest
```

> [Run the SQL Server 2017 container image with Docker](https://docs.microsoft.com/en-us/sql/linux/quickstart-install-connect-docker?view=sql-server-linux-2017)


```sh
docker run -d postgres
```

> [Postgres Docker Hub](https://hub.docker.com/_/postgres/)

### Cross-platform commands!

```sh
docker run --rm \
    -v "$(pwd)":/data \
    -v ~/.m2:/root/.m2 \
    softleader/freesia-playbooks \
    pack.yml -vvv
```

> [softleader-freesia playbooks](https://github.com/softleader/softleader-freesia/tree/playbooks)

```sh
docker run --rm -it softleader/cli whereis ${your.name} -l 100 -d _1M..today
```

> [softleader/softleader-cli](https://github.com/softleader/softleader-cli)

```sh
docker run -it --entrypoint=bash -v /var/run/docker.sock:/var/run/docker.sock --rm softleader/docker -c "docker images | grep mytomcat"
```

> [docker in docker](https://github.com/softleader/dockerfile/tree/master/docker)


## Congratulations

你已經學會了基本款的 docker 應用了, 期待之後的 [Kubernetes Tutorials](https://kubernetes.io/docs/tutorials/) 吧!

- [返回第一頁](./README.md)


### References

- SoftLeader Docker Hub - [https://hub.docker.com/u/softleader/](https://hub.docker.com/u/softleader/)
- softleader/dockerfile - [https://github.com/softleader/dockerfile](https://github.com/softleader/dockerfile)
- 開發人員必須知道的 Kubernetes 核心技術 - [https://www.slideshare.net/WillHuangTW/things-to-know-about-kubernetes-for-developers/WillHuangTW/things-to-know-about-kubernetes-for-developers](https://www.slideshare.net/WillHuangTW/things-to-know-about-kubernetes-for-developers/WillHuangTW/things-to-know-about-kubernetes-for-developers)
- Docker Official Getting Started Guide - [https://docs.docker.com/get-started/](https://docs.docker.com/get-started/)

