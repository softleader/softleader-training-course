## Deployment

![](https://godleon.github.io/blog/images/kubernetes/k8s-deployment.png)

| Objects | Short name |
|---|---|
| namespace | ns |
| deployment | deploy |
| replicaset | rs |
| pod | po |

### Create Namepsace

```sh
kubectl create namespace k8s-training

# 切換 namespace
kubens k8s-training
```

### Apply a Deployment

編輯 YAML - [./yamls/deployment.yaml](./yamls/deployment.yaml)

使用以下指令部署:

```sh
# --record record current kubectl command in the resource annotation
kubectl apply -f ./yamls/deployment.yaml --record
```

檢查結果:

```sh
kubectl get deployment

# 檢視 deployment
kubectl describe deploy <NAME>
```

繼續往下看 ReplicaSet:

```sh
# 檢視 rs, 名稱將會是 [DEPLOYMENT-NAME]-[POD-TEMPLATE-HASH-VALUE]
kubectl get replicaset
kubectl describe rs <NAME>

# 檢視 pod
kubectl get pod
kubectl describe po <NAME>
```

### Update a Deployment

```sh
# 修改 replicas=3
vim ./yamls/deployment.yaml

kubectl apply -f ./yamls/deployment.yaml --record
```

查看所有 Kubernetes event

```sh
kubectl get event
```

### Rollout History

> 只有 `.spec.template` 變更時才會有 revision 記錄，因此改變 replica 數量就不會產生新的 revision

```sh
kubectl rollout history deploy nginx

kubectl rollout undo deploy --to-revision=1
```

## Next

- Learn about [Service & Ingress](./service_ingress.md)
