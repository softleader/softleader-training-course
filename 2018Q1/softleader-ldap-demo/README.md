# LDAP 基本介紹

## Prerequisite

[Eclipse Plugin](http://directory.apache.org/studio/installation-in-eclipse.html)

## Introduction

### Definition - What does Lightweight Directory Access Protocol (LDAP) mean?

> Lightweight Directory Access Protocol (LDAP) is a client/server protocol used to access and manage directory information. It reads and edits directories over IP networks and runs directly over TCP/IP using simple string formats for data transfer. It was originally developed as a front end to X.500 Directory Access Protocol.

> Lightweight Directory Access Protocol is also known as RFC 1777.

### Definition - What does X.500 mean?

> X.500 is a series of computer networking standards used to develop the equivalent of an electronic directory that is very similar to the concept of a physical telephone directory. Its purpose is to centralize an organization's contacts so that anyone within (and sometimes without) the organization who has Internet access can look up other people in the same organization by name or 

### Feature

	LDAP is characterized as a write-once-read-many-times service.
	LDAP directory servers stores data hierarchically.

## Ldap Server

Open Source:

- [ApacheDS](http://directory.apache.org/apacheds/)
- OpenLDAP
- OpenDJ
- 389 Directory Server

Commercial:

- Microsoft Active Directory

## 名詞介紹

- DN: Distinguished Name
> e.g.:  o=technology department,dc=softleader,dc=com

- RDN: Relative Distinguished Name
- objectClass
- Attributes
- [Commonly Used Attributes](http://www.zytrax.com/books/ldap/ape/)

## Reference

- [softleader-tree](https://github.com/softleader/softleader-wiki/wiki/Tree-Setup)
- [softleader-boot-starter-ldap](https://github.com/softleader/softleader-boot-starter/tree/master/softleader-boot-starter-ldap)
- [techopedia](https://www.techopedia.com/definition/2439/lightweight-directory-access-protocol-ldap)
- [zytrax](http://www.zytrax.com/books/ldap/ch2/index.html#history)