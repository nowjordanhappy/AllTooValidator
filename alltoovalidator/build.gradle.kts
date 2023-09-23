plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}


dependencies {
    implementation("androidx.annotation:annotation:1.6.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["kotlin"])

                groupId = "com.github.nowjordanhappy"
                artifactId = "alltoovalidator"
                version = "0.1.3+1"
            }
        }
    }
}