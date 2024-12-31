package com.ericlam.mc.groovier;

import com.google.inject.Module;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

/**
 * groovier addon
 */
public interface GroovierAddon {
	
	/**
	 * install guice module
	 * @param module module
	 */
	void installModule(Module module);

	/**
	 * extract file from jar
	 * @param jar jar class
	 * @param sourceFolder source folder
	 * @param targetFolder target folder
	 * @throws URISyntaxException uri syntax exception
	 * @throws IOException io exception
	 */
	void extractFromJar(Class<?> jar, String sourceFolder, String targetFolder) throws URISyntaxException, IOException;

}
