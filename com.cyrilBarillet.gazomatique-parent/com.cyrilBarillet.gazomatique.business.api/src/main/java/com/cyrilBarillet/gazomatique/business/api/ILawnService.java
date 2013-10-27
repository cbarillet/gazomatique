package com.cyrilBarillet.gazomatique.business.api;

import com.cyrilBarillet.gazomatique.model.valueObject.LawnInformationVO;

public interface ILawnService {
	void Mow(LawnInformationVO information);
	
	void addFinishMowingEventListener(FinishMowingEventListener listener);
	
    void removeFinishMowingEventListener(FinishMowingEventListener listener);
}
