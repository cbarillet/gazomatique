package com.cyrilBarillet.gazomatique.business.api;

import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

public interface ILawnMowerService {
	void mow(LawnInformationVO information, LawnMowerEntity lawnMower);
}
