import java.util.Properties

plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	kotlin("plugin.serialization")
	id("com.google.gms.google-services")
	id("com.google.devtools.ksp")
}

val properties = Properties()
properties.load(File(projectDir, "secrets.properties").reader())

android {
	signingConfigs {
		// https://developer.android.com/studio/publish/app-signing#sign-auto
		all {
			keyAlias = "clam"
			// No Quotations in props
			storeFile = file(properties.getProperty("keystore-path"))
			storePassword = properties.getProperty("keystore-password")
			keyPassword = properties.getProperty("keystore-password")
		}
	}
	namespace = "io.gitlab.fsc_clam.fscwhereswhat"
	compileSdk = 34

	defaultConfig {
		applicationId = "io.gitlab.fsc_clam.fscwhereswhat"
		minSdk = 25
		targetSdk = 34
		versionCode = 1
		versionName = "0.0.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
		buildConfigField(
			"String",
			"mapboxAPI",
			properties.getProperty("mapboxAPI")

		)
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)

		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		buildConfig = true
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.3"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
	implementation("androidx.activity:activity-compose:1.8.1")
	implementation(platform("androidx.compose:compose-bom:2023.03.00"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("com.google.android.material:material:1.10.0")
	implementation("androidx.compose.material3:material3")
	implementation("com.google.firebase:firebase-database:20.3.0")
	implementation("com.github.skydoves:colorpicker-compose:1.0.5")
	implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
	implementation("androidx.work:work-runtime-ktx:2.9.0")
	implementation("com.google.android.gms:play-services-auth:20.7.0")

	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")
	coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

	val room_version = "2.6.1"
	implementation("androidx.room:room-common:$room_version")
	implementation("androidx.room:room-runtime:$room_version")
	implementation("androidx.room:room-ktx:$room_version")
	ksp("androidx.room:room-compiler:$room_version")

	val lifecycle_version = "2.6.2"
	// ViewModel
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
	// ViewModel utilities for Compose
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
	implementation("io.coil-kt:coil-compose:2.5.0")

	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

	// Firebase
	implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
	implementation("com.google.firebase:firebase-analytics")
	implementation("com.google.firebase:firebase-auth")

	val nav_version = "2.7.5"

	implementation("androidx.navigation:navigation-compose:$nav_version")
	//OSM
	implementation("org.osmdroid:osmdroid-android:6.1.17")
	implementation("tech.utsmankece:osm-androd-compose:+")

	val accompanist_version = "0.33.2-alpha"
	//Accompanist
	implementation("com.google.accompanist:accompanist-permissions:$accompanist_version")

	implementation("com.google.android.gms:play-services-ads:22.6.0")

}