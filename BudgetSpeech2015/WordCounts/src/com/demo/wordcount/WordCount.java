package com.demo.wordcount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordCount {
	private final static String WRITE_TO_FILE = "/Users/agarwala/Desktop/BudgetSpeech2015_output_count_1.txt";
	private final static String READ_FROM_FILE = "/Users/agarwala/Desktop/BudgetSpeech2015.txt";

	public static void main(String[] args) throws IOException {
		Map<String, Integer> words = new HashMap<String, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(READ_FROM_FILE))){
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				words = processLine(sCurrentLine, words);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		writeHashMapToFile(words);
	}
	
	public static void writeHashMapToFile(Map<String, Integer> words) throws IOException {
		File file = new File(WRITE_TO_FILE);
		
		// if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (@SuppressWarnings("rawtypes") Map.Entry entry : words.entrySet()) {
			if((Integer)entry.getValue() > 10) {
				bw.write(entry.getKey() + ", " + entry.getValue() + "\n");
			}
		}
		
		bw.close();
		System.out.println("Done");
	}
	
	public static Map<String, Integer> processLine(final String line, Map<String, Integer> words) {
		final StringTokenizer tokenizer = new StringTokenizer(line," ");
		
		while(tokenizer.hasMoreTokens()) {
			String key = tokenizer.nextToken();
			if(words.get(key)==null)
				words.put(key, 1);
			else {
				Integer i = words.get(key);
				words.put(key, i.intValue() + 1);
			}
		}
		return words;
	}
}
