package com.B1team.b01;

import com.B1team.b01.entity.BOM;
import com.B1team.b01.entity.Facility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest

class B01ApplicationTestSSS {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/*
	@Test

	void contextLoads() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String name = "발주" + entityManager.createNativeQuery("SELECT bom_seq.nextval FROM DUAL").getSingleResult();

		BOM bom  = new BOM();

		bom.setVolume(10L);
		bom.setMtrId(0L);
		bom.setProductId(0L);
		bom.setMtrName(name);

		entityManager.persist(bom);

		System.out.println("test1111112" + bom.getId());
		System.out.println("test1111111" + bom.getMtrName());


	}

	 */

}
