package com.example.demo.synthetic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class SyntheticDataService {
	
	private final List<ChangeType> CHANGE_TYPES = Collections.unmodifiableList(Arrays.asList(ChangeType.values()));
	private final int SIZE = CHANGE_TYPES.size();
	
	public void writeSyntheticData(int rowsToGenerate, String filePath) throws IOException {
		Writer writer = new FileWriter(filePath);
		writer.write("id,geometry,someBoolean,someDate,someNumber,changeType\n");
		
		for (int i = 0; i < rowsToGenerate; i++) {
			SyntheticData row = generateRow(i + 1);
			writer.write(row.toString());
		}
		
		writer.close();

	}
	
	private SyntheticData generateRow(int rowNumber) {
		SyntheticData row = new SyntheticData();
		
		row.setId(String.format("identifier%d", rowNumber));
		row.setGeometry(generateWktPoint());
		row.setSomeBoolean(ThreadLocalRandom.current().nextBoolean());
		row.setSomeDate(generateDate());
		row.setSomeNumber(ThreadLocalRandom.current().nextInt());
		row.setChangeType(generateRandomChangeType());
		
		return row;
		
	}
	
	private String generateWktPoint() {
		int x = ThreadLocalRandom.current().nextInt(700000);
		int y = ThreadLocalRandom.current().nextInt(700000);
		
		return String.format("POINT (%d %d)", x, y);
	}
	
	private LocalDate generateDate() {
		int hundredYears = 100 * 365;
		
		return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears));
	}
	
	private ChangeType generateRandomChangeType() {
		return CHANGE_TYPES.get(ThreadLocalRandom.current().nextInt(SIZE));
	}

}
