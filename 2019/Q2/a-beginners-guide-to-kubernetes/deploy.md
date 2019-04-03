## Deployment

![](https://godleon.github.io/blog/images/kubernetes/k8s-deployment.png)

| Resources | Short name |
|---|---|
| namespace | ns |
| deployment | deploy |
| replicaset | rs |
| pod | po |

### Apply a Deployment

編輯 YAML - [./yamls/deployment.yaml](./yamls/deployment.yaml)

使用以下指令部署:

```sh
# --record record current kubectl command in the resource annotation
kubectl apply -f ./yamls/deployment.yaml --record
```

檢查結果:

```sh
# 檢視 deployment
kubectl get deployment
kubectl describe deployment <DEPLOY_NAME>

# 檢視 ReplicaSet, 名稱將會是 [DEPLOYMENT-NAME]-[POD-TEMPLATE-HASH-VALUE]
kubectl get replicaset
kubectl describe rs <RS_NAME>

# 檢視 Pod
kubectl get pod
kubectl describe po <POD_NAME>
```

### Update a Deployment

```sh
# 修改 replicas=3
vim ./yamls/deployment.yaml

kubectl apply -f ./yamls/deployment.yaml --record
```

查看 Event

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
