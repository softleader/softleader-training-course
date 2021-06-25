# TOC

Table Of Content 產生器

## Developer

### Environment Requirements

- Golang 1.12+

### Build

輸入 `make help` 可以查看打包指令:

```
Usage:
  make <target>

General
  help             Display this help.
  build-all        Build compiles the packages for linux, macos and windows
  build-linux      Build compiles the packages for linux
  build-macos      Build compiles the packages for macos
  build-windows    Build compiles the packages for windows
  bootstrap        Bootstrap project
```

## Usage

輸入 `toc --help` 可以查看完整說明:

```
Table Of Content Generator

	$ toc PATH

PATH 用來指定從哪層目錄 (相對於工作目錄) 開始爬文, 如: '.' 代表當前目錄

	$ toc .

傳入 '--workdir' 指定工作目錄, 可為絕對路徑或相對於當前目錄的路徑, 預設執行指令的當前目錄

	$ toc . --workdir ../../
	$ toc . --workdir /tmp

傳入 '--template' 指定 template 位置 (相對於工作目錄)

	$ toc . --template templates/my.tpl

傳入 '--output' 指令輸出的檔案 (相對於工作目錄)

	$ toc . --template templates/my.tpl --output my.file

Usage:
  toc [flags]

Flags:
  -h, --help              help for toc
  -o, --output string     output file name relative to workdir (default "./README.md")
  -t, --template string   template file to use relative to workdir (required)
      --workdir string    working directory (default current directory)
```
