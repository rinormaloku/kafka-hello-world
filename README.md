## A hello-world for kafka

1. Start kafka and zookeeper
```
docker-compose up -d
```

2. Start the applications

## Running in kubernetes

1. Install strimzi operator
```
helm repo add strimzi https://strimzi.io/charts  

helm install my-strimzi-kafka-operator strimzi/strimzi-kafka-operator --version 0.26.0
```

2. Apply the files in the k8s dir
```
kubectl apply -f ./k8s/
```


## Testing tools

**Check messages with kafkacat**

```
kubectl run busybox -i --tty --image=confluentinc/cp-kafkacat --restart=Never --rm -- sh

kafkacat -b localhost:9092 -t mytopic
```

**Check message headers**

```
kafkacat -b kafka-broker:9092 -t mytopic -C \
  -f '\nKey (%K bytes): %k
  Value (%S bytes): %s
  Timestamp: %T
  Partition: %p
  Offset: %o
  Headers: %h\n'
```
