package com.worldpay.offers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.worldpay.exception.OfferNotFoundException;

@RestController
@RequestMapping("/offers")
public class OfferController {
	
	@Autowired
	private OfferService offerService;
	
	@RequestMapping(value="/{productId}",method=RequestMethod.GET)
	public Offer view(@PathVariable Long productId) {
		Offer offer = offerService.findOffer(productId);
		if (offer == null)
			throw new OfferNotFoundException(productId);
		else {
			return offer;
		}
		
	}
	@RequestMapping(value="",method=RequestMethod.PUT)
	public Offer update(Offer offer) {
		return offerService.updateOffer(offer);
	}
	
	@RequestMapping(value="/{productId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable Long productId) {
		//TODO change to update with active flag.
		
		if(productId == null ) {
			throw new IllegalArgumentException();
		}
		offerService.deleteOffer(productId) ;	
		return productId + " has just been deleted.";
	}
	
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public Offer register(Offer offer) {
		
		return offerService.registerOffer(offer);
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<Offer> list() {
		List<Offer> offers = offerService.findOffers();
		return offers;
	}
}
