package com.example.demo.batch;

import org.springframework.batch.item.ItemProcessor;

public class CsvToSyntheticDataProcessor implements ItemProcessor<String[], SyntheticData> {

	@Override
	public SyntheticData process(String[] item) throws Exception {
		SyntheticData syntheticDataEntity = new SyntheticData();
		
		syntheticDataEntity.setId(item[0]);
		syntheticDataEntity.setGeometry(item[1]);
		syntheticDataEntity.setSomeBoolean(item[2]);
		syntheticDataEntity.setSomeDate(item[3]);
		syntheticDataEntity.setSomeNumber(item[4]);
		syntheticDataEntity.setChangeType(item[5]);
		
		return syntheticDataEntity;
	}

}
