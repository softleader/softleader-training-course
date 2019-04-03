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

- 在 Pod 中打自己查看

```sh
kubectl get po
kubectl exec POD_NAME curl localhost
```

- 在 Pod 中打 Service 查看

```sh
kubectl get svc
kubectl get po
kubectl exec -it POD_NAME curl SERVICE
```

- 在 k8s 外打 node port

```
curl K8S_HOST:NODEPORT
```

- 在 k8s 外打 ingress path

```
curl K8S_HOST/INGRESS_PATH -k
```

## Next

- Learn about [IBM Cloud Private](./icp.md)