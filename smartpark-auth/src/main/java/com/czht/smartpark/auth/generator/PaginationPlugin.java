package com.czht.smartpark.auth.generator;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;

import java.util.List;

/**
 * The class Pagination plugin.
 *
 * @author paascloud.net@gmail.com
 */
public class PaginationPlugin extends PluginAdapter {

	/**
	 * Validate boolean.
	 *
	 * @param warnings the warnings
	 *
	 * @return the boolean
	 */
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	private static void generate() {
		try {

			String config = PaginationPlugin.class.getClassLoader().getResource("generator/generator-config.xml").getFile();
			String[] arg = {"-configfile", config, "-overwrite"};
			ShellRunner.main(arg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		generate();
	}
}