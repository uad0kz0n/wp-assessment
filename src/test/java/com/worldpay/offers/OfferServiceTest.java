package com.worldpay.offers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.worldpay.exception.OfferNotFoundException;
import com.worldpay.offers.Offer;
import com.worldpay.offers.OfferRepository;

public class OfferServiceTest {
	private static final String OFFER_CURRENCY = "GBP";
	private OfferService offerService;
	private OfferRepository offerRepositoryMock;

	@Before
	public void setUp() throws Exception {
		offerRepositoryMock = new TestOfferRepository();
		offerService = new OfferServiceImpl(offerRepositoryMock);
		 
	}

	@Test
	public void register_a_offer() {
		Offer offer=new Offer("productName","productDescription",BigDecimal.ZERO,OFFER_CURRENCY);
		offer=offerService.registerOffer(offer);
		assertNotNull( offer.getProductId());
	}
	
	@Test(expected = OfferNotFoundException.class)
	public void update_a_offer_with_invalid_id() {
		Offer offer=new Offer("productName","productDescription",BigDecimal.ZERO,OFFER_CURRENCY);
		offer.setProductId(System.currentTimeMillis());
		offer=offerService.updateOffer(offer);
	}
	
	@Test
	public void delete_a_offer() {
		
		offerService.deleteOffer(123456789l);
		 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void registerInvalidOffer() {
		Offer noCurrencyOffer=new Offer("productName","productDescription",BigDecimal.ZERO,null);
		noCurrencyOffer=offerService.registerOffer(noCurrencyOffer);
	}
	
	@Test
	public void find_offer() {
		Offer offer=new Offer("productName","productDescription",BigDecimal.ZERO,OFFER_CURRENCY);
		offer=offerService.registerOffer(offer);
		assertNotNull( offer.getProductId());
		Offer offer2 =  offerService.findOffer( offer.getProductId());
		assertEquals(offer2.getProductDescription(), "productDescription");
		
		
	}
	@Test 
	public void find_all_Offers() {
		Offer offer=new Offer("productName","productDescription",BigDecimal.ZERO,OFFER_CURRENCY);
		offer=offerService.registerOffer(offer);
		List<Offer> list= offerService.findOffers();
		assertNotNull(list);
		 
	}
	
}
