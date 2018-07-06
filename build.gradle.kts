buildscript {
  ext.kotlin_version = '1.2.51'
  ext.gdx_version = '1.9.8'

  repositories {
    jcenter()
  }
  dependencies {
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
  }
}

allprojects {
  apply plugin: "kotlin"

  repositories {
    jcenter()
  }
}

project(":desktop") {
  dependencies {
    compile project(":core")
    compile "com.badlogicgames.gdx:gdx-backend-lwjgl:$gdx_version"
    compile "com.badlogicgames.gdx:gdx-platform:$gdx_version:natives-desktop"
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
  }
}

project(":core") {
  dependencies {
    compile "com.badlogicgames.gdx:gdx:$gdx_version"
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
  }
}
