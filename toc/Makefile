BUILD := $(CURDIR)
BINARY := toc

##@ General

help: ## Display this help.
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

build-all: bootstrap build-linux build-macos build-windows  ## Build compiles the packages for linux, macos and windows

build-linux: bootstrap  ## Build compiles the packages for linux
	GOOS=linux GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-linux -a -tags netgo

build-macos: bootstrap  ## Build compiles the packages for macos
	GOOS=darwin GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-macos -a -tags netgo

build-windows: bootstrap  ## Build compiles the packages for windows
	GOOS=windows GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-windows.exe -a -tags netgo

bootstrap:  ## Bootstrap project
	go mod download
