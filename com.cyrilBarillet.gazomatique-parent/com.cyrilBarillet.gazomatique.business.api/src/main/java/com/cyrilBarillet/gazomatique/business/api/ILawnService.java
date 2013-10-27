package com.cyrilBarillet.gazomatique.business.api;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

public interface ILawnService {
	void Mow(LawnInformationVO information);
	
	void addFinishMowingEventListener(FinishMowingEventListener listener);
	
    void removeFinishMowingEventListener(FinishMowingEventListener listener);
    
    boolean loadData(String resourceName);
	
	boolean hasNext();
	
	LawnEntity nextCommand();
}
