# 為啥我部署到 Kubernetes 的程式被 OOMKilled 了!?

- [錄影檔](https://drive.google.com/file/d/1Mg8ieX40bZN-BqU4dvC2YwS6l4fCVmeY/view?usp=drive_link), 建議 1.25 倍速看
- [影片中畫的圖的素材](./material.drawio)
- [影片中的範例程式](./oom.zip)

## 影片中畫的圖

### Arch

![](./material-arch.drawio.svg)

### cgroup

![](./material-cgroup.drawio.svg)

### node

![](./material-node.drawio.svg)

### flow

![](./material-flow.drawio.svg)

## References

- [Reserve Compute Resources for System Daemons](https://kubernetes.io/docs/tasks/administer-cluster/reserve-compute-resources/#eviction-thresholds)
- [OOM Score configuration at the Nodes](https://github.com/kubernetes/design-proposals-archive/blob/main/node/resource-qos.md)
- [Flows leading to out-of-memory situations](https://mihai-albert.com/2022/02/13/out-of-memory-oom-in-kubernetes-part-4-pod-evictions-oom-scenarios-and-flows-leading-to-them/#flows-leading-to-out-of-memory-situations)