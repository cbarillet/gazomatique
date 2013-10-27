/**
 * 
 */
package com.cyrilBarillet.gazomatique.common.model.valueObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Explain where to find lawn information.
 * 
 * @author cyrilbarillet
 *
 */
public abstract class LawnInformationVO {
	
	/*
	 * Type of the resource which contains lawn information.
	 */
	private TypeResourceEnum typeResource;
	
	/*
	 * Data to use to read lawn information.
	 */
	private Map<String, Object> data;

	/**
	 * @param typeResource
	 * @param data
	 */
	protected LawnInformationVO(final TypeResourceEnum typeResource) {
		super();
		this.typeResource = typeResource;
		this.data = new HashMap<>();
	}
	
	/**
	 * @return the typeResource
	 */
	public TypeResourceEnum getTypeResource() {
		return typeResource;
	}

	/**
	 * @return the data
	 */
	protected Map<String, Object> getData() {
		return data;
	}
}
