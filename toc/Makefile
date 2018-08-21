BUILD := $(CURDIR)
BINARY := toc

.PHONY: install
install: bootstrap build

.PHONY: build
build:
	GOOS=linux GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-linux -a -tags netgo
	GOOS=darwin GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-macos -a -tags netgo
	GOOS=windows GOARCH=amd64 go build -o $(BUILD)/$(BINARY)-windows.exe -a -tags netgo

.PHONY: bootstrap
bootstrap:
	glide install --strip-vendor