package com.example.cash.repo;

import com.example.mongo.DbIntegrationTests;
import com.example.mongo.model.Cash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
class CashRepositoryTest extends DbIntegrationTests {

    @Autowired
    private CashRepository cashRepository;

    @BeforeEach
    public void setUp() {
        cashRepository.deleteAll();
        cashRepository.save(Cash.of(1000));
    }

    @Test
    public void findAll() {
        List<Cash> cash = cashRepository.findAll();
        assertNotNull(cash);
    }

    @Test
    public void save() {
        List<Cash> cashList = cashRepository.findAll();

        Cash cash = cashList.get(0);
        long beforeBalanace = cash.getBalance();

        cash.setBalance(2000L);
        Cash beCash = cashRepository.save(cash);

        assertTrue(beCash.getBalance() > beforeBalanace);
    }

    @Test
    public void deleteOne() {

        List<Cash> cashList = cashRepository.findAll();
        cashRepository.delete(cashList.get(0));

        cashList = cashRepository.findAll();
        assertEquals(cashList.size(), 0);
    }
}