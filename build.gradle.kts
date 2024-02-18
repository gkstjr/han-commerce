plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.toy"
version = "0.0.1-SNAPSHOT"
var queryDslVersion = "5.0.0"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	dependencies {
		implementation("io.jsonwebtoken:jjwt-api:0.11.5")
		runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
		runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	}

	// QueryDSL Implementation
	implementation ("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

/**
 * QueryDSL Build Options
 */
val querydslDir = "src/main/generated"

sourceSets {
	getByName("main").java.srcDirs(querydslDir)
}

tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory = file(querydslDir)

}

tasks.named("clean") {
	doLast {
		file(querydslDir).deleteRecursively()

	}
}