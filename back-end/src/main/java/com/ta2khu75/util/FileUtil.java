package com.ta2khu75.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	private FileUtil() {
		throw new IllegalStateException("Utility class");
	}
	public static String save(String uploadDir, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		String fileName = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(fileName);
		
		String newFileName = String.format("%s.%s", UUID.randomUUID().toString(), fileExtension);
		Path filePath = uploadPath.resolve(newFileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		return newFileName;
	}

	public static void save(String uploadDir, String fileName, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	}
	public static boolean isNonEmpty(MultipartFile file) {
		return !(file == null || file.isEmpty());
	}

	public static StreamingResponseBody read(String pathFile) {
		return outputStream -> {
			try (InputStream inputStream = Files.newInputStream(Paths.get(pathFile))) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

}
