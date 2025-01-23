# Options

## VERSION defines the release version FOB the bundle.
VERSION ?=
## JAVA defines the Java version to compile, test or install the source code.
JAVA ?=
## PL defines the main module of the application to run.
PL ?= 

# Functions

define run_module
$(if $(filter-out "",$(PL)),-pl=$(PL),)
endef

define java_version
$(if $(filter-out "",$(JAVA)),-D'java.version=$(JAVA)',)
endef

.PHONY: help format clean compile test install run version new-version release build build-docker build-tar deploy undeploy redeploy

##@ General

FORMATTING_BEGIN_YELLOW = \033[0;33m
FORMATTING_BEGIN_BLUE = \033[36m
FORMATTING_END = \033[0m

.PHONY: help
help: ## Display this help.
	@printf -- "${FORMATTING_BEGIN_BLUE}%s${FORMATTING_END}\n" \
	"" \
	"   __ _____   ___  ____  __ ______ " \
	"  / //_/ _ | / _ \/ __ \/ //_/ / / " \
	" / ,< / __ |/ ___/ /_/ / ,< < < < " \
	"/_/|_/_/ |_/_/   \____/_/|_| \_\_\ " \
	""
	@awk 'BEGIN {\
	    FS = ":.*##"; \
	    printf                "Usage: ${FORMATTING_BEGIN_BLUE}OPTION${FORMATTING_END}=<value> make ${FORMATTING_BEGIN_YELLOW}<target>${FORMATTING_END}\n"\
	  } \
	  /^[a-zA-Z0-9_-]+:.*?##/ { printf "  ${FORMATTING_BEGIN_BLUE}%-35s${FORMATTING_END} %s\n", $$1, $$2 } \
	  /^.?.?##~/              { printf "   %-35s${FORMATTING_BEGIN_YELLOW}%-35s${FORMATTING_END}\n", "", substr($$1, 6) } \
	  /^##@/                  { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Develop

format: ## Format the source code.
	mvn validate

clean: ## Remove files generated at build-time.
	mvn clean -e

compile: clean  ## Clean and compile the source code.
	mvn compile -e $(call java_version)

test: clean ## Clean and test the compiled code.
	mvn test -e $(call java_version)

install: clean ## Install project to local repository w/o unit testing.
	mvn install -e -DskipTests -Prelease $(call java_version)

run: compile ## Run the application.
	mvn spring-boot:run $(call java_version) $(call run_module)

##@ Integration

kapok-version: ## Get current Kapok version.
	@mvn help:evaluate -Dexpression=kapok.version -DforceStdout -q

bump-kapok: ## Bump Kapok and Spring version.
ifeq ($(strip $(KAPOK)),)
	$(error KAPOK is required)
endif
ifeq ($(strip $(BOOT)),)
	$(error BOOT is required)
endif
	mvn versions:set-property -Dproperty=kapok.version -DnewVersion=$(KAPOK)
	mvn versions:set-property -Dproperty=spring-boot.version -DnewVersion=$(BOOT)
	mvn versions:commit

bump-deps: ## Bump dependencies to the latest version (excluding Kapok & Spring).
	mvn versions:update-properties -DexcludeProperties=kapok.version,spring-boot.version
	mvn versions:commit

##@ Delivery

version: ## Get current project version.
	@mvn help:evaluate -Dexpression=project.version -DforceStdout -q

new-version: ## Update project version.
ifeq ($(strip $(VERSION)),)
	$(error VERSION is required)
endif
	mvn versions:set -DnewVersion=$(VERSION)
	mvn versions:commit

release: ## Pack w/o unit testing, and release jar to remote repository.
	mvn deploy -e -DskipTests -Prelease $(call java_version)

build: clean ## Build image w/o unit testing to remote image repository.
	mvn package jib:build -e -DskipTests $(call java_version)

build-docker: clean ## Build image w/o unit testing to local docker daemon.
	mvn package jib:dockerBuild -e -DskipTests $(call java_version)

build-tar: clean ## Build image w/o unit testing to tar file.
	mvn package jib:buildTar -e -DskipTests $(call java_version)

##@ Deploy

deploy: ## Deploy to Kubernetes specified in ~/.kube/config.
	kubectl apply -k ./deploy

undeploy: ## Undeploy from Kubernetes specified in ~/.kube/config.
	kubectl delete -k ./deploy --ignore-not-found

redeploy: undeploy deploy ## Redeploy to Kubernetes specified in ~/.kube/config.

