package com.alevilla86.firebase.security.test.firebase_security_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class FirebaseSecurityTestApplication {

	public static void main(String[] args) {

		FileInputStream serviceAccount;
		try {

			ClassLoader classLoader = FirebaseSecurityTestApplication.class.getClassLoader();
			File file = new File(classLoader.getResource("firebase-adminsdk-secret.json").getFile());
			serviceAccount = new FileInputStream(file.getAbsolutePath());
		
			@SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build();

			FirebaseApp.initializeApp(options);


		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} 

		SpringApplication.run(FirebaseSecurityTestApplication.class, args);
	}

}
