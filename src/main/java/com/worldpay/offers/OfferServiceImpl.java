package com.worldpay.offers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worldpay.exception.OfferNotFoundException;

@Service
public class OfferServiceImpl implements OfferService {
	
	
	public OfferServiceImpl() {
		super();
	}
	
	
	
	public OfferServiceImpl(OfferRepository offerRepository) {
		super();
		this.offerRepository = offerRepository;
	}
	 
	@Resource
	private OfferRepository offerRepository;
	
	
	@Override
	public Offer registerOffer(Offer offer) {
		if(offer==null || offer.getCurrency() ==null) {
			throw new IllegalArgumentException("a offer is invalid");
		}
		offer=offerRepository.save(offer);
		return offer;
	}
	@Override
	public List<Offer> findOffers() {
		
		return (List<Offer>) offerRepository.findAll();
	}
	@Override
	public Offer findOffer(Long productId) {
		return offerRepository.findOne(productId);
	}

	@Override
	public Offer updateOffer(Offer offer) {
		if(offer==null || offer.getProductId() ==null) {
			throw new IllegalArgumentException("a offer is invalid");
		}
		Offer existence = offerRepository.findOne(offer.getProductId());
		
		if(existence==null) {
			throw new OfferNotFoundException(offer.getProductId());
		}
		
		offer=offerRepository.save(offer);
		return offer;
	}



	@Override
	public void deleteOffer(Long productId) {
		if(productId==null) {
			throw new IllegalArgumentException("a offer is invalid");
		}
		offerRepository.delete(productId);
		
	}
}