docker 執行 redis

```docker run --rm -d -p 6379:6379 redis```

找 redis 的 container id

```docker ps```

進入redis中

```docker exec -it a8f1548ace0e(containerId) redis-cli```

找所有在redis中的key

```keys *```

清除所有在redis中的key

```flushdb```

示範url

```localhost:8181/index/ERIC```

```localhost:8181/setSession/很帥```

```localhost:8787/getSession```
