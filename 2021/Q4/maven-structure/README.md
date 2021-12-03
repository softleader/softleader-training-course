# Maven

## Pom結構-1
### information
https://semver.org/lang/zh-TW/
### properties
### dependencies
Intellij Plugin: `Maven Helper`

## 常用指令
### clean
清理target
### compile
包出class
### test
執行測試
### package
包出jar/war/ear
`mvn clean pakcage -DskipTests`
### install
package+把包出來的東西放到m2
### deploy
package+把包出來的東西放到遠端倉庫

## Pom結構-2 Sub Module
### parent
### bom
### dependencyManagement
### pluginManagement
### plugin

## Pom結構-3
### profiles
### build

## 這部分沒講
> ## 結合CICD  
> ### 強制更新dependencies: -U  
> ### 程式碼排版檢查: git status  
> ### docker build vs jib vs buildpack  