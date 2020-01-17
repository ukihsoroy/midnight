# alpha-commons

## sdk version

- `jdk`: 8u231
- `scala`: 2.11.12

## package

- `common-bigdata`: 大数据连接等
- `common-boot`: spring boot 
- `common-utils`: tools合集
- `common-web`: web server

## build

```shell script
mvn clean package
```

## deploy

```shell script
mvn deploy
```

## install

```xml
<repositories>
    <repository>
        <id>snapshots</id>
        <name>Nexus SnapshotRepository</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
    <repository>
      <id>releases</id>
      <name>Nexus ReleaseRepository</name>
      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
    </repository>
</repositories>
```