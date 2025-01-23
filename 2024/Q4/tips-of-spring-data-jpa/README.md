# Spring Data JPA 各種雷

忘了錄影QQ

[SampleService.java](src/main/java/tw/com/softleader/example/training_20241129/SampleService.java)
[SampleTest.java](src/test/java/tw/com/softleader/example/training_20241129/SampleTest.java)

> Demo project

This project uses Kapok, the Java framework tailored for SoftLeader standards.

If you want to learn more about Kapok, please visit [https://github.com/softleader/kapok/](https://github.com/softleader/kapok/).

## Getting Started

Frequently used commands for development or maintenance are documented in the `Makefile`,
it's recommended to review the associated documentation available at [this link](https://github.com/softleader/kapok/wiki/Makefile) for more details.

### Running the Application

To run the application, use the following command:

```shell
make run
```

### Packaging the Container Image

You can build the optimized container image with:

```shell
make build
```

Or build the image directly to a Docker daemon with:

```shell
make build-docker
```

Alternatively, you can build and save the image to disk as a tarball with:

```shell
make build-tar
```

For more comprehensive instructions, please refer to [this link](https://github.com/softleader/kapok/wiki/Packaging-the-Container-Image).

### Deploying to Kubernetes

To deploy to Kubernetes cluster specified in `~/.kube/config`, use the following command:

```shell
make deploy
```
## Documentation

### References

For further reference, please consider the following sections:

* [Kapok](https://github.com/softleader/kapok)
* [Kapok Issues](https://github.com/softleader/kapok/issues)
* [Kapok Documentation](https://kapok.cloud.softleader.com.tw)
* [Spotless](https://github.com/diffplug/spotless)
* [SoftLeader Kubernetes Engine (SLKE)](https://slke.cloud.softleader.com.tw)

### Guides

The following guides illustrate how to use some features concretely:

* [Kapok Module Stacks](https://kapok.cloud.softleader.com.tw/docs/modules/)

