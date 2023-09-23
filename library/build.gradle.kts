plugins {
    id("org.jetbrains.kotlin.jvm")
    //id("com.android.library")
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
                version = "0.1.1"
            }
        }

        repositories {
            maven {
                url = uri("https://jitpack.io")
            }
        }
    }
}