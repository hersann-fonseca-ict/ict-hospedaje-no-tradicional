package com.ict.alojamiento.models;

import java.util.ArrayList;
import java.util.List;

import com.ict.commons.entity.Alojamiento;

public class AlojamientoModel {

	public AlojamientoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlojamientoModel(Alojamiento name, List<AlojamientoModel> children) {
		super();
		this.name = name;
		this.children = children;
	}

	private Alojamiento name = new Alojamiento();
	private List<AlojamientoModel> children = new ArrayList<>();

	public Alojamiento getName() {
		return name;
	}

	public void setName(Alojamiento name) {
		this.name = name;
	}

	public List<AlojamientoModel> getChildren() {
		return children;
	}

	public void setChildren(List<AlojamientoModel> children) {
		this.children = children;
	}

}
