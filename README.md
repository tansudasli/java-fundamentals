# java-fundamentals

things to practise some java concepts

- core (java language specific staffs)
- oop
- functional programming
- generics
- multi-thread
- algorithms
- reactive

### Techs

- jdk 17, 
- gradle 7.x
- java micro benchmark 1.3.x

## How to Start

`git clone ...` then `cd ..`

- `./gradlew :fp:run`, or other modules
- to run,
  - Use IDE menu, chose class and right click,
  - or, `./gradlew jar` to build jar and 
    run class from the jar like `java -cp /build/libs/.....jar com.Basics`
  - or, `java src/main/java/com/core/fp/TestStream4.java`
- to run Java Micro benchmarks, use 
  - `./gradlew jmh` which runs all @Benchmarks annotated files
  - or install JMH plugin for intelliJ. so function based run and main based run is possible
  - or, Add main() function for jmh (where you gathered under jmh/src folder), 
    - run `./gradlew jmhJar`
    - and use ` java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArrayFrequencyBenchmark`
