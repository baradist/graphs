# graphs

## Short manual
This tool, using *Dijkstra*'s algorithm, finds a shortest path in a given graph from one vertex to another.
The graph supposed to be represented as a matrix like in an example https://github.com/baradist/graphs/blob/master/src/test/resources/dijkstra-example.txt

## Build

You supposed to have jdk8+ installed

`git clone https://github.com/baradist/graphs.git`

`cd graphs`

`./gradlew build (or gradlew.bat build on Windows)`
## Test
Tests aren't test anything they are added just for demonstration purposes.

To see few built-in examples, run 

`./gradlew test -i`

## Run
`java -jar <graphMatrixFilename> <fromIndex> <toIndex>`

For instance: 

`java -jar build/libs/dijkstra.jar src/test/resources/dijkstra-example.txt 0 5`

(on Windows: `java -jar build\libs\dijkstra.jar src\test\resources\dijkstra-example.txt 0 5`)
