## Service & Ingress

### Create Service

編輯 YAML - [./yamls/service.yaml](./yamls/service.yaml)

使用以下指令部署:

```sh
kubectl apply -f ./yamls/service.yaml

kubectl describe service nginx
```

### Create Ingress

編輯 YAML - [./yamls/ingress.yaml](./yamls/ingress.yaml)

使用以下指令部署:

```sh
kubectl apply -f ./yamls/ingress.yaml

kubectl describe ingress nginx
```

### Take a Peek

- inside the Pod

```sh
kubectl get po

# 在 Pod 中執行指令
kubectl exec -it NAME curl localhost
```

- inside the Pod through Service

```sh
kubectl get svc
kubectl get po

# 在 Pod 中執行指令
kubectl exec -it NAME curl SERVICE_PORT
```

- from NodePort

```
curl HOST:NODEPORT
```

- from Ingress

```
curl HOST/INGRESS_PATH -k
```

## Next

- Learn about [IBM Cloud Private](./icp.md)
