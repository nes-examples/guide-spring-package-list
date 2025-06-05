# Generating a List of Spring Dependencies

This guide walks you through generating a list of Spring dependencies that can be used for more accurate pricing of HeroDevs NES for Spring. There are 2 general ways that you can generate this list:

1. Using Maven or Gradle commands, optionally with a filter to only include Spring dependencies.
2. Generating a Software Bill of Materials (SBOM) using the `sbom` plugin for Maven or Gradle, which will include all dependencies in the project.

# Other Dependencies

In addition to NES for Spring, HeroDevs offers support for other end of life Open Source Software (OSS) projects. You can find more information about these projects on the [HeroDevs website](https://herodevs.com). This guide provides instructions how to filter the dependency list for only Spring projects, however it may be worth considering that afull dependency graph could help remediate other vulnerabilities in non-Spring code as well.

# Build System Dependency Tree

This repository contains a Spring Boot application that is used to demonstrate how to identify all Spring packages used by an application. Both Maven and Gradle build systems are supported.

Note that the commands below use the Maven and Gradle wrappers that are included in this project. When you replicate these commands in your own project, you can use the Maven and Gradle configurations that you already have in place.

The commands below will work for any Maven or Gradle project.

## Maven

To generate a list of all dependencies using Maven, you can run the following command:
```bash
./mvnw dependency:tree
```

To filter this output to only include Spring dependencies, you can use the following command:
```bash 
./mvnw dependency:tree | grep 'org.springframework'
```

For either command, you can redirect the output by appending ` > hd_nes_for_spring.txt`. This is the file that HeroDevs will use to generate a quote for NES for Spring.

If you are using a Windows machine, use the Windows wrapper `mvnw.cmd` instead of `mvnw`.

## Gradle

To generate a list of all dependencies using Maven, you can run the following command:
```bash
./gradlew dependencies --configuration testRuntimeClasspath
```

To filter this output to only include Spring dependencies, you can use the following command:
```bash 
./gradlew dependencies --configuration testRuntimeClasspath | grep 'org.springframework'
```

For either command, you can redirect the output by appending ` > hd_nes_for_spring.txt`. This is the file that HeroDevs will use to generate a quote for NES for Spring.

If you are using a Windows machine, use the Windows wrapper `gradlew.bat` instead of `gradlew`.

# Software Bill of Materials (SBOM)

The most complete way to generate a list of dependencies is to create a Software Bill of Materials (SBOM). This will include all dependencies in the project, not just Spring dependencies. HeroDevs can use this SBOM to generate a quote for NES for Spring.

## Spring Boot 2.7

The following instructions are required for Spring Boot 2.7 applications. Spring Boot 3.x applications have built-in support for generating an SBOM, so these steps are not required.

### Maven

To generate an SBOM for a Spring Boot 2.7 project using Maven, you can use the `sbom` plugin. First, add the following plugin to your `pom.xml` file in the `<build><plugins>` section:

```xml
<!-- Configure the CycloneDX Maven plugin to generate the SBOM -->
<plugin>
    <groupId>org.cyclonedx</groupId>
    <artifactId>cyclonedx-maven-plugin</artifactId>
    <version>2.9.1</version>
    <!-- Run the makeAggregateBom goal during the package phase -->
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>makeAggregateBom</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

After adding the plugin, you can generate the SBOM by running the following command:
```bash
./mvnw org.cyclonedx:cyclonedx-maven-plugin:makeAggregateBom
```

This will create a `bom.xml` and `bom.json` file in the `target` directory. These are the files you can send to HeroDevs for evaluation.

### Gradle

To generate an SBOM for a Spring Boot 2.7 project using Gradle, you can use the `sbom` plugin. First, add the following plugin to your `build.gradle` file:

```groovy
plugins {
    id 'org.cyclonedx.bom' version '1.8.1'
}
```

And configure the plugin in the `dependencies` block:

```groovy
cyclonedxBom {
	includeConfigs = ['testRuntimeClasspath']
}
```

After adding the plugin, you can generate the SBOM by running the following command:
```bash
./gradlew cyclonedxBom
```
This will create a `bom.xml` and `bom.json` file in the `build/reports/` directory. These are the files you can send to HeroDevs for evaluation.