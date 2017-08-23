package com.worldpay.offers;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestOfferRepository implements OfferRepository {
	
	Map <Long,Offer> mockDB= new ConcurrentHashMap<>();

	@Override
	public <S extends Offer> S save(S entity) {
		if(entity!=null) {
			entity.setProductId(System.currentTimeMillis());
			mockDB.put(entity.getProductId(), entity);
		}
		return entity;
	}

	@Override
	public <S extends Offer> Iterable<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public Offer findOne(Long id) {
		return mockDB.get(id);
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Offer> findAll() {
		return new LinkedList<>(mockDB.values());
	}

	@Override
	public Iterable<Offer> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		 
		return mockDB.values().size();
	}

	@Override
	public void delete(Long id) {
		mockDB.remove(id);
		
	}

	@Override
	public void delete(Offer entity) {
		delete(entity.getProductId());
		
	}

	@Override
	public void delete(Iterable<? extends Offer> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		mockDB.clear();
		
	}
	
	 
}
