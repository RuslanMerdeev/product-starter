import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("maven-publish")
}

group = "com.example"
version = "0.0.8"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.flowable:flowable-spring-boot-starter:6.8.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2:2.3.232")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    dependsOn("ktlintFormat")
}


tasks.register("incrementVersion") {
    doLast {
        val currentVersion = project.version.toString()
        val parts = currentVersion.split(".")
        val newVersion = "${parts[0]}.${parts[1]}.${parts[2].toInt() + 1}"

        println("Incrementing version: $currentVersion -> $newVersion")

        // Обновляем версию в build.gradle.kts
        val buildFile = file("build.gradle.kts")
        var content = buildFile.readText()
        content = content.replace(
            "version = \"$currentVersion\"",
            "version = \"$newVersion\""
        )
        buildFile.writeText(content)

        val result = providers.exec {
            commandLine("git", "tag", newVersion)
        }.result

        if (result.get().exitValue == 0) {
            println("✅ Git tag created: $newVersion")
        } else {
            println("❌ Failed to create Git tag")
        }

        println("✅ Version updated to: $newVersion")
        println("⚠️  Don't forget to push tags: git push --tags")
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/RuslanMerdeev/product-starter")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR") ?: ""
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

tasks.named("publish") {
    dependsOn("incrementVersion")
}