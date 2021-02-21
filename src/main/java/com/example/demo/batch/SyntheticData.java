package com.example.demo.batch;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import lombok.Data;

@Entity
@Data
public class SyntheticData {
	
	@Id
	String id;
	Geometry geometry;
	boolean someBoolean;
	LocalDate someDate;
	int someNumber;
	String changeType;
	
	public void setGeometry(String wktGeometry) throws ParseException {
		WKTReader wktReader = new WKTReader(new GeometryFactory(new PrecisionModel(), 27700));
		Geometry geometry = wktReader.read(wktGeometry);
		this.geometry = geometry;
	}
	
	public void setSomeBoolean(String booleanString) {
		this.someBoolean = Boolean.parseBoolean(booleanString);
	}
	
	public void setSomeDate(String dateString) {
		this.someDate = LocalDate.parse(dateString);
	}
	
	public void setSomeNumber(String intString) {
		this.someNumber = Integer.parseInt(intString);
	}

}
