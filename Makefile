BUILD := $(CURDIR)
BINARY := toc
HAS_GLIDE := $(shell command -v glide;)

.PHONY: install
all: bootstrap linux macos windows

.PHONY: linux
linux:
	GOOS=linux GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-linux -a -tags netgo

.PHONY: macos
macos:
	GOOS=darwin GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-macos -a -tags netgo

.PHONY: windows
windows:
	GOOS=windows GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-windows.exe -a -tags netgo

.PHONY: bootstrap
bootstrap:
ifndef HAS_GLIDE
	go get -u github.com/Masterminds/glide
endif
	glide install --strip-vendor