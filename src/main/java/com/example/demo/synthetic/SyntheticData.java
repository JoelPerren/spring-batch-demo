package com.example.demo.synthetic;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SyntheticData {
	
	String id;
	String geometry;
	boolean someBoolean;
	LocalDate someDate;
	int someNumber;
	ChangeType changeType;
	
	public String toString() {
		return String.format(
				"%s,%s,%s,%s,%s,%s\n", 
				this.id, 
				this.geometry, 
				this.someBoolean, 
				this.someDate,
				this.someNumber,
				this.changeType
				);
	}

}

enum ChangeType {
	insert,
	update,
	delete
}
