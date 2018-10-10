# Building Docker Images

> 介紹幾個打包 Docker Images 的重點

## Base Images

1. Use Official repository on [hub.docker.com](https://hub.docker.com/), ex: [maven](https://hub.docker.com/_/maven/), [openjdk](https://hub.docker.com/_/openjdk/)
1. Minimal images: `alpine`, `slim`
1. Specific tags, ex: `maven:3.5-jdk-8-alpine`, `openjdk:8-jre-alpine`
1. Don't install any remote access tool, ex: *ssh*, *vnc*

## Builder Pattern

![](https://images.contentstack.io/v3/assets/blt300387d93dabf50e/blt74e61076b2d5fa9b/5b8462e30cdef43e0b861e35/download)

> https://www.weave.works/blog/kubernetes-best-practices

### A Builder Pattern Dockerfile

```
FROM {base-image-with-compiler-tool} As build-env
RUN {build commands}

FROM {runtime-base-image}
COPY --from=build-env {/path/from/build/container} {/path/in/runtime/container}
ENTRYPOINT ["/bin/sh", "-c", "{run commands}"]
```

## References

- SoftLeader Docker Hub - [https://hub.docker.com/u/softleader/](https://hub.docker.com/u/softleader/)
- softleader/dockerfile - [https://github.com/softleader/dockerfile](https://github.com/softleader/dockerfile)
- Building Small Containers (Kubernetes Best Practices) - [https://youtu.be/wGz_cbtCiEA](https://youtu.be/wGz_cbtCiEA)
- Building a Secure Docker Application - [https://www.youtube.com/watch?v=LmUw2H6JgJo](https://www.youtube.com/watch?v=LmUw2H6JgJo)
