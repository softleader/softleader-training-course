## IBM Cloud Private

## What is ICP

IBM Cloud Private is an application platform for developing and managing on-premises, containerized applications. It is an integrated environment for managing containers that includes the container orchestrator Kubernetes, a private image registry, a management console, and monitoring frameworks.

> More details about [IBM Cloud Private Overview](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.3/getting_started/introduction.html)

### TL;DR

![](https://www.ibm.com/blogs/think/tw-zh/wp-content/uploads/sites/13/2018/01/Think-blog-02.jpg)

## ICP Architecture

- Boot node - 進行安裝, 配置, 更新 ICP Cluster
- Master node - 負責管理, 控制 Worker nodes
- Worker node - 負責 Container 的運行

![](https://www.ibm.com/support/knowledgecenter/SSBS6K_2.1.0.3/images/architecture.jpg)

## Recipes 

- Single-node

| - | Boot node | Master node |  Worker node |
|---|------|--------|-------|
| host1 | V | V | V |

- Three-node cluster

| - | Boot node | Master node |  Worker node |
|---|------|--------|-------|
| host1 | V | V | |
| host2 |  |  | V |
| host3 |  |  | V |

- Five-node cluster

| - | Boot node | Master node | Worker node |
|---|------|--------|-------|
| host1 | V | V | |
| host2 |  | V |  |
| host3 |  | V |  |
| host4 |  |  | V |
| host5 |  |  | V |


## Reference

- [Kubernetes Best Practices](https://www.youtube.com/playlist?list=PLIivdWyY5sqL3xfXz5xJvwzFW_tlQB_GB)
- [Kubernetes explained in pictures: the theme park analogy](https://danlebrero.com/2018/07/09/kubernetes-explained-in-pictures-the-theme-park-analogy/)
- [ICP Architecture](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.3/getting_started/architecture.html)
- [5張圖告訴你:什麼是 IBM Cloud Private](https://www.ibm.com/blogs/think/tw-zh/2018/01/17/5%E5%BC%B5%E5%9C%96%E5%91%8A%E8%A8%B4%E4%BD%A0%E4%BB%80%E9%BA%BC%E6%98%AF-ibm-cloud-private/)