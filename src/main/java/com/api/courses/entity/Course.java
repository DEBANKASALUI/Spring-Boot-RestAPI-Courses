package com.api.courses.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {

	@Id
	private long id;
	private String name;
	private String description;
	private double price;
	private int duration;

	public Course(long id, String name, String description, double price, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.duration = duration;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", duration=" + duration + "]";
	}

}
