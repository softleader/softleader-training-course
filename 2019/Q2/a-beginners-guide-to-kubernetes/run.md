## Run

> 開始前必須先 login kubectl

## Create Namepsace

```sh
kubectl create namespace k8s-training

# 切換 namespace
kubens k8s-training
```

## Just Run

```sh
kubectl run nginx --image=softleader/nginx-training:1.15.10 --restart=Never
```

| Generated Resource | Flags |
|---|---|
| Pod | --restart=Never |
| Deployment | --restart=Always |
| Job | --restart=OnFailure |
| Cron Job | --schedule=<cron> |

> more details about [generators](https://kubernetes.io/docs/reference/kubectl/conventions/#generators)

### 常用指令

```sh
# 取得 Pod 資訊
kubectl get pod

# 檢視 Pod 內容
kubectl describe pod POD_NAME

# 在 Pod 中執行指令
kubectl exec POD_NAME COMMAND

# 取得 Pod 的 shell
kubectl exec -it POD_NAME sh

# 刪除 Pod
kubectl delete pod POD_NAME
```

## Next

- Learn about [Deployment](./deploy.md)