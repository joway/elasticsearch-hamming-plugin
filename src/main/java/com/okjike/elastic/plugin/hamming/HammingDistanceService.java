package com.okjike.elastic.plugin.hamming;

import java.math.BigInteger;

public class HammingDistanceService {

    public static int hammingDistance(String lhs, String rhs) {
        return new BigInteger(lhs, 16).xor(new BigInteger(rhs, 16)).bitCount();
    }

    public static int hammingDistanceScore(String lhs, String rhs) {
        return Math.max(lhs.length(), rhs.length()) * 4 - hammingDistance(lhs, rhs);
    }
}
