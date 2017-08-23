package com.worldpay.offers;

import java.util.List;

public interface OfferService {

	
	Offer registerOffer(Offer offer);

	List<Offer> findOffers();

	Offer findOffer(Long productId);

	Offer updateOffer(Offer offer);

	void deleteOffer(Long productId);

	 
}
