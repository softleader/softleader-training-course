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

- ldapsearch

```
docker run -it --rm \
    -e HOST=softleader.com.tw \
    -e PORT=10388 \
    softleader/ldap-client search dc=softleader,dc=example,dc=com
```

- [curl](https://curl.haxx.se/download.html) + [jq](https://github.com/stedolan/jq)

```sh
docker run -t --rm softleader/curl-jq \
  "-sS https://icp.softleader.com.tw/eureka-jasmine-uat/registry | jq .application "
```

## Congratulations

你已經學會了基本款的 docker 應用了, 期待之後的 [Kubernetes Tutorials](https://kubernetes.io/docs/tutorials/) 吧!

- [返回第一頁](./README.md)

### References

- SoftLeader Docker Hub - [https://hub.docker.com/u/softleader/](https://hub.docker.com/u/softleader/)
- softleader/dockerfile - [https://github.com/softleader/dockerfile](https://github.com/softleader/dockerfile)
- 開發人員必須知道的 Kubernetes 核心技術 - [https://www.slideshare.net/WillHuangTW/things-to-know-about-kubernetes-for-developers/WillHuangTW/things-to-know-about-kubernetes-for-developers](https://www.slideshare.net/WillHuangTW/things-to-know-about-kubernetes-for-developers/WillHuangTW/things-to-know-about-kubernetes-for-developers)
- Docker Official Getting Started Guide - [https://docs.docker.com/get-started/](https://docs.docker.com/get-started/)

