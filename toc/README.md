# TOC

Table Of Content 產生器

## Usage

```
Table Of Content Generator

	$ toc PATH

PATH 用來指定從哪層目錄 (相對於工作目錄) 開始爬文, 如: '.' 代表當前目錄

	$ toc .

傳入 '--workdir' 指定工作目錄, 預設執行指令的當前目錄, 如: 從上一層爬文

	$ toc . --workdir ../

傳入 '--template' 指定 template 位置 (相對於工作目錄)

	$ toc . --template templates/my.tpl

傳入 '--output' 指令輸出的檔案

	$ toc . --template templates/my.tpl --output my.file

Usage:
  toc [flags]

Flags:
  -h, --help              help for toc
  -o, --output string     output file name relative to workdir (default "./README.md")
  -t, --template string   template file to use relative to workdir (required)
      --workdir string    working directory (default current directory)
```
