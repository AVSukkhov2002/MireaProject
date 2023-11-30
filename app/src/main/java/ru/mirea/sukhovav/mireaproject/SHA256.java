package ru.mirea.sukhovav.mireaproject;

import java.math.BigInteger;
import java.util.Arrays;

public class SHA256 {
    static long h0 = 0x6a09e667;
    static long h1 = 0xbb67ae85;
    static long h2 = 0x3c6ef372;
    static long h3 = 0xa54ff53a;
    static long h4 = 0x510e527f;
    static long h5 = 0x9b05688c;
    static long h6 = 0x1f83d9ab;
    static long h7 = 0x5be0cd19;

    private static final long[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

    static String newStr;
    static StringBuilder answer;

    public String getHash(String message) {
        newStr = stringToBinary(message);
        String newpass = hash(newStr);
        return newpass;
    }

    public static String stringToBinary(String s) {

        answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            answer.append(Integer.toBinaryString(c));
        }

        answer.append("1");
        int n = 512;
        int k = answer.length();

        int m = n*((k / n) + 1) - k;
        String zero = "0";
        String zerosForAnswer;

        if (m < 64){
            zerosForAnswer = zero.repeat(m + 512 - 64);
        }
        else
        {
            zerosForAnswer = zero.repeat(m - 64);
        }

        String binK = Integer.toBinaryString(k);
        String zerosForK = zero.repeat(64 - binK.length());


        String newAnswer = answer + zerosForAnswer + zerosForK + binK;

        //int res = Integer.parseInt(newAnswer, 2);
        //String convert = Integer.toHexString(res).toUpperCase();
        System.out.println(newAnswer);

        return newAnswer;
    }

    public static String hash(String mesage){
        char[] charArray = mesage.toCharArray();

        for (int i = 0; i * 512 <  charArray.length; i = i + 1){


            char[] newArray =  Arrays.copyOfRange(charArray, i * 512, i * 512 + 512);

            char[][] num = new char[64][32];
            long[] W = new long[64];


            String binary;



            for (int k = 0; k  <  16; k = k + 1){
                num[k] = Arrays.copyOfRange(newArray, k*32, k*32 + 32);

                W[k] = Long.parseLong(new String(num[k]), 2);
                //System.out.println("k = " + W[k]);
                //binary = Long.toBinaryString(W[k]);
                //System.out.println(k + "= " +  binary);
            }

            for (int k = 16; k < 64 ; k = k + 1){
                //System.out.println(k);
                W[k] = Sigma1(W[k - 2]) + W[k - 7] + Sigma0(W[k - 15]) + W[k - 16];
                binary = Long.toBinaryString(W[k]);
                System.out.println(k + "= " +   W[k]);
            }

            System.out.println("@@@@@@@@@@@@@@@@@@");


            long sigma0;
            long Ma;
            long t2;
            long sigma1;
            long Ch;
            long t1;

            long a = h0;
            long b = h1;
            long c = h2;
            long d = h3;
            long e = h4;
            long f = h5;
            long g = h6;
            long h = h7;

            // operate on TEMP
            for (int t = 0; t < W.length; ++t) {
                sigma0 = bigSig0(a);
                Ma = (a & b) ^ (a & c) ^ (b & c);
                t2 = foo(sigma0 + Ma);
                sigma1 = bigSig1(e);
                Ch = (e & f) ^ (~e & g);
                t1 = foo(h + sigma1 + Ch + K[t] + W[t]);

                h = g;
                g = f;
                f = e;
                e = d + t1;
                d = c;
                c = b;
                b = a;
                a = t1 + t2;

            }

            // add values in TEMP to values in H
            String h0new = getStr(h0 + a);
            System.out.println("h0 = " + h0new);

            String h1new = getStr(h1 + b);
            System.out.println("h1 = " + h1new);

            String h2new = getStr(h2 + c);
            System.out.println("h2 = " + h2new);

            String h3new = getStr(h3 + d);
            System.out.println("h3 = " + h3new);

            String h4new = getStr(h4 + e);
            System.out.println("h4 = " + h4new);

            String h5new = getStr(h5 + f);
            System.out.println("h5 = " + h5new);

            String h6new = getStr(h6 + g);
            System.out.println("h6 = " + h6new);

            String h7new = getStr(h7 + h);
            System.out.println("h7 = " + h7new);






            System.out.println("h1 = " + h0new.length());
            System.out.println("h2 = " + h1new.length());
            System.out.println("h3 = " + h2new.length());
            System.out.println("h4 = " + h3new.length());
            System.out.println("h5 = " + h4new.length());
            System.out.println("h6 = " + h5new.length());
            System.out.println("h7 = " + h6new.length());
            System.out.println("h8 = " + h7new.length());


            String res = h0new + h1new + h2new + h3new + h4new + h5new + h6new + h7new;


            BigInteger bi = new BigInteger(res, 2);
            String result = bi.toString(16);

            System.out.println("hash = " + result);
            return result;
        }
        return "0";
    }

    public static long foo(long num){
        String a = Long.toBinaryString(num);

        if (a.length() > 32){
            StringBuffer sb = new StringBuffer(a);
            sb.replace(0, a.length() - 32, "");

            a = sb.toString();
        }

        long number = Long.parseLong(removeZero(a), 2);

        return number;
    }

    public static String getStr(long num){
        String a = Long.toBinaryString(num);

        if (a.length() > 32){
            StringBuffer sb = new StringBuffer(a);
            sb.replace(0, a.length() - 32, "");

            a = sb.toString();
        }

        char[] charArray;
        if (a.length() != 32){
            charArray = new char[32];
            int k = 0;
            for (int i = 0; i < 32 - a.length(); i = i + 1 ) {
                charArray[i] = '0';
            }

            for (int i = 32 - a.length(); i < 32 ; i = i + 1 ) {
                charArray[i] = '0';
            }
        }
        else{
            charArray = a.toCharArray();
        }

        String str = new String(charArray);

        while ((32 != str.length()))
            str = "0" + str;
        return str;
    }

    private static long bigSig0(long x) {
        return fooRotateRight(x, 2)
                ^ fooRotateRight(x, 13)
                ^ fooRotateRight(x, 22);
    }

    private static long bigSig1(long x) {
        return fooRotateRight(x, 6)
                ^ fooRotateRight(x, 11)
                ^ fooRotateRight(x, 25);
    }

    private static long Sigma0(long x) {
        return fooRotateRight(x, 7)
                ^ fooRotateRight(x, 18)
                ^ (x >>> 3);
    }

    private static long Sigma1(long x) {
        return fooRotateRight(x, 17)
                ^ fooRotateRight(x, 19)
                ^ (x >>> 10);
    }


    public static long fooRotateRight(long num, int step){
        String a = Long.toBinaryString(num);

        if (a.length() > 32){
            StringBuffer sb = new StringBuffer(a);
            sb.replace(0, a.length() - 32, "");

            a = sb.toString();
        }


        char[] charArray;
        if (a.length() != 32){
            charArray = new char[32];
            int k = 0;
            for (int i = 0; i < 32 - a.length(); i = i + 1 ) {
                charArray[i] = '0';
            }

            for (int i = 32 - a.length(); i < 32 ; i = i + 1 ) {
                charArray[i] = '0';
            }
        }
        else{
            charArray = a.toCharArray();
        }

        char[] b = new char[charArray.length];


        System.arraycopy(charArray, 0, b, step, charArray.length - step);
        System.arraycopy(charArray, charArray.length - step, b, 0, step);

        long number = Long.parseLong(removeZero(new String(b)), 2);



        return number;
    }

    public static String removeZero(String str){

        int i = 0;

        while ((i < str.length() - 1) && (str.charAt(i) == '0'))
            i++;

        StringBuffer sb = new StringBuffer(str);


        sb.replace(0, i, "");

        return sb.toString();
    }
}
