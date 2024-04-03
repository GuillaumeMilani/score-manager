plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.openapi.generator") version "7.2.0"
}

group = "com.github.guillaumemilani"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_19
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.10")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen:6.4.4.Final")
}

tasks.test {
    useJUnitPlatform()
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/specs/specs.yaml")

    outputDir.set(layout.buildDirectory.dir("generated/sources/openapi").get().asFile.path)
    apiPackage.set("com.github.guillaumemilani.scoremanagerbackend.api")
    modelPackage.set("com.github.guillaumemilani.scoremanagerbackend.api.model")
    modelNameSuffix.set("Dto")

    configOptions.set(mapOf(
            "dateLibrary" to "java8",
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "useSpringBoot3" to "true",
    ))
}

tasks.withType<JavaCompile> {
    dependsOn("openApiGenerate")
}

sourceSets {
    main {
        java {
            srcDir("build/generated/sources/openapi")
        }
    }
}