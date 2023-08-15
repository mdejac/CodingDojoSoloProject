package com.michaeld.baggers.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private final Path root = Paths.get("userImageUpload");
	
	public void init() throws IOException {
		if (!Files.exists(root)) {
			try {
				Files.createDirectory(root);
			} catch(IOException e) {
				throw new RuntimeException("Download Folder Not Initialized");
			}
		}
	}
	
	public String save(MultipartFile file) {
		try {
			Path savepath = root.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), savepath);
			return savepath.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Resource load(String fileName) {
		try {
			Path filePath = root.resolve(fileName);
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("File not readable");
			}
		} catch(MalformedURLException e) {
			throw new RuntimeException("Error: "+e.getMessage());
		}
	}
	
}
