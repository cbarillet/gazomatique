/**
 * 
 */
package com.cyrilBarillet.gazomatique.common.model.valueObject;

/**
 * @author cyrilbarillet
 *
 */
public class TextFileLawnInformationVO extends LawnInformationVO {

	private static final String FILE_NAME_KEY = "FilePath";
	
	/**
	 * @param typeResource
	 * @param data
	 */
	 public TextFileLawnInformationVO(String filePath) {
		super(TypeResourceEnum.TEXT_FILE);
		this.getData().put(FILE_NAME_KEY, filePath);
	 }
	 
	 public String getFilePath()
	 {
		 return (String) this.getData().get(FILE_NAME_KEY);
	 }
}
