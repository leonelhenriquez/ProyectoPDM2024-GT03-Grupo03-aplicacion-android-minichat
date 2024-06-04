plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

android {
	namespace = "com.example.minichat"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.example.minichat"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildFeatures {
		viewBinding = true
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
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	viewBinding {
		enable = true
	}
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")

	// OkHttp
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.squareup.okhttp3:mockwebserver:4.12.0")

	// Retrofit
	implementation("com.squareup.retrofit2:retrofit:2.11.0")
	implementation("com.squareup.retrofit2:converter-gson:2.11.0")

	// Gson
	implementation("com.google.code.gson:gson:2.11.0")
	implementation("io.socket:socket.io-client:2.1.0")

	// Dagger
	annotationProcessor("com.google.dagger:dagger-compiler:2.51.1")
	ksp("com.google.dagger:dagger-compiler:2.51.1")

  // Material Switch
  implementation ("com.google.android.material:material:1.12.0")

    // Room
	val roomVersion = "2.6.1"
	implementation("androidx.room:room-runtime:$roomVersion")
	annotationProcessor("androidx.room:room-compiler:$roomVersion")

	// To use Kotlin Symbol Processing (KSP)
	ksp("androidx.room:room-compiler:$roomVersion")

	// optional - Kotlin Extensions and Coroutines support for Room
	implementation("androidx.room:room-ktx:$roomVersion")

	// Toasty
	implementation("com.github.GrenderG:Toasty:1.5.2")

	// Scarlet
	implementation("com.tinder.scarlet:scarlet:0.1.12")
	implementation("com.tinder.scarlet:websocket-okhttp:0.1.12")
	implementation("com.tinder.scarlet:stream-adapter-rxjava2:0.1.12")


	//RX
	implementation("io.reactivex.rxjava2:rxjava:2.2.21")
	implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
	implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

	// Lifecycle
	implementation("androidx.core:core-ktx:1.13.1")
	implementation("androidx.appcompat:appcompat:1.7.0")
	implementation("com.google.android.material:material:1.12.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("com.makeramen:roundedimageview:2.3.0")
	implementation("com.github.bumptech.glide:glide:4.16.0")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}