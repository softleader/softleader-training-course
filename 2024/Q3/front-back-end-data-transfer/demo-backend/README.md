# 20240712

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
* [Kapok Wiki](https://github.com/softleader/kapok/wiki)
* [Kapok Issues](https://github.com/softleader/kapok/issues)
* [Jib](https://github.com/GoogleContainerTools/jib)
* [Spotless](https://github.com/diffplug/spotless)
* [SoftLeader Kubernetes Engine (SLKE)](https://github.com/softleader/slke)

### Guides

The following guides illustrate how to use some features concretely:

* [Kapok Web - Building REST endpoints and OpenAPI.](https://github.com/softleader/kapok/blob/3.x/extensions/web)
* [Calculating Java memory in container](https://github.com/softleader/kapok/wiki/Calculating-Java-Memory-in-Container)

