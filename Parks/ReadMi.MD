##About Parks

Application helps to manage work in Parks. Plant / heal / cut the plants.

## How to set up

Environment:
- Java 8
- MySQL 5.7

## How to use

To use applicaiton you should run application using following pattern
```java
java app.jar <entity> <command> <field 1> ... <field n>
```

Where:
- entity can have following values:
    - park
    - gardener
- command can have following values:
    - create
    - update
    - read 
    - delete
    - readAll

For example if you want to create a new park you can use following command:

```java
java app.jar park create NewPark 0.0 NewLocation
```