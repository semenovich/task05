package by.tc.web.entity;

import java.io.File;

public final class SourceName {

    private static final String SOURCE_PATH = "E:\\eclipse\\Java EE\\task05\\WebContent\\WEB-INF\\resources\\file.xml";
	private static final File SOURCE_FILE = new File(SOURCE_PATH);

	public static File get() {
		return SOURCE_FILE;
	}
}
