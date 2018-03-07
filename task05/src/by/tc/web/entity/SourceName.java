package by.tc.web.entity;

import java.io.File;

public final class SourceName {
	private static final File sourceName = new File("E:\\eclipse\\Java EE\\task05\\WebContent\\WEB-INF\\resources\\file.xml");

	public static File get() {
		return sourceName;
	}
}
