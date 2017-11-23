/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.okjike.elastic.plugin.hamming.HammingDistanceService;

/**
 *
 * @author PC
 */
public class HammingDistanceTest {
    
    public HammingDistanceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void maximumHammingDistance(){
        String hash1 = "0000000000000000";
        String hash2 = "FFFFFFFFFFFFFFFF";

        int diff = HammingDistanceService.hammingDistance(hash1, hash2);
        System.out.println(diff);
        assert(diff==64);
    }
}
