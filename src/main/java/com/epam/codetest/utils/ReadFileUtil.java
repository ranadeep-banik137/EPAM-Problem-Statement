package com.epam.codetest.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileUtil {
	
	private File file;
	private Scanner scanner;
	
	public ReadFileUtil(final File file) {
		this.file = file;
		try {
			this.scanner = new Scanner(this.file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getStringListLineWiseFromFile() {
		List<String> list = new ArrayList<>();
		while (scanner.hasNext()) {
			list.add(scanner.next());
		}
		return list;
	}

}
