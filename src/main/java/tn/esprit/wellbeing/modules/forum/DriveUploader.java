package tn.esprit.wellbeing.modules.forum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DriveUploader {
	
	public static  void uploadStatisticsCommentsFile() {
		//create file with statistics
		
		//upload
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("public_FW25ask4REtJuv5hY81G2qPhctb1");
		headers.setContentType(MediaType.IMAGE_PNG);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		String fooResourceUrl = "https://api.upload.io/v1/files/basic";
		HttpEntity<byte[]> entity = null;
		try {
			entity = new HttpEntity<>(Files.readAllBytes(Paths.get("statsComments.png")), headers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<String> response = restTemplate
		  .postForEntity(fooResourceUrl, entity, String.class);
	}
	
	public static  void uploadStatisticsPostsFile() {
		//create file with statistics
		
		//upload
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("public_FW25ask4REtJuv5hY81G2qPhctb1");
		headers.setContentType(MediaType.IMAGE_PNG);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		String fooResourceUrl = "https://api.upload.io/v1/files/basic";
		HttpEntity<byte[]> entity = null;
		try {
			entity = new HttpEntity<>(Files.readAllBytes(Paths.get("statsPosts.png")), headers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<String> response = restTemplate
		  .postForEntity(fooResourceUrl, entity, String.class);
	}

}
