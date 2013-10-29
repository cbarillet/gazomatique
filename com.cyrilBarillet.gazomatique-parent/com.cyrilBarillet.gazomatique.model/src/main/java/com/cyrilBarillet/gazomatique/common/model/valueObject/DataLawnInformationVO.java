/**
 * 
 */
package com.cyrilBarillet.gazomatique.common.model.valueObject;

/**
 * @author cyrilbarillet
 *
 */
public class DataLawnInformationVO extends LawnInformationVO {

	private static final String DATA_KEY = "Data";
	
	/**
	 * @param typeResource
	 * @param data
	 */
	 public DataLawnInformationVO(String data) {
		super(TypeResourceEnum.DATA);
		this.getData().put(DATA_KEY, data);
	 }
	 
	 public String getDataInfomation()
	 {
		 return (String) this.getData().get(DATA_KEY);
	 }
	
}
