package com.cyrilBarillet.gazomatique.dataAccess.api;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

public interface ILawnDAO {

	LawnEntity loadData(LawnInformationVO information);
	
}
