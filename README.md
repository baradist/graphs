# graphs

## Short manual

## Build

You supposed to have jdk8+ installed

`git clone https://github.com/baradist/graphs.git`

`cd graphs`

`./gradlew build (or gradlew.bat build on Windows)`

## Run
`java -jar <graphMatrixFilename> <fromIndex> <toIndex>`

For instance: 

`java -jar build/libs/dijkstra.jar src/test/resources/dijkstra-example.txt 1 6`
(on Windows: `java -jar build\libs\dijkstra.jar src\test\resources\dijkstra-example.txt 1 6`)
