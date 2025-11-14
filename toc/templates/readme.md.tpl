[![Build Status](https://travis-ci.org/softleader/softleader-training-course.svg?branch=master)](https://travis-ci.org/softleader/softleader-training-course)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/softleader/softleader-training-course/graphs/commit-activity)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

# 松凌科技教育訓練專區

[http://bit.ly/softleader-training-course](http://bit.ly/softleader-training-course)

<img src="./qr-code.svg" width="500">

![](./training.png)

## Directory Structure

請講師將上課教材，依照下述目錄結構擺放, **課程名稱不可以有空白!**

```sh
.
└── <年度>
    └── <季>
        ├── <課程A>
        ├── <課程B>
        └── ...
```

如:

```sh
.
└── 2016
    ├── Q1
    │   ├── spring-transaction
    │   └── stream-and-lambda
    └── Q2
        ├── design-patterns
        └── ...
```

在你的課程目錄中, 如果有放一個 *README.md*, 會自動擷取該檔案中第一個 header (`#`, `##` ... `######`) 作為課程標題

```sh
.
└── <年度>
    └── <季>
        └── <課程A>
             └── README.md
```

## TL;DR

[如何有序學習教育訓練內容](https://drive.google.com/file/d/1Pn5LfCCwIYd0bUr1Is1AlDO1zhsXCl4x/view?usp=sharing)

## Courses

{{.}}

## GitHub Action

本 Repo 設有 [GitHub Action](./.github/workflows) 在 Push 或 PR 時自動的觸發 *toc*
