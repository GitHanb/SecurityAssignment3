/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.domain;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 *
 * @author hanzh
 */
public class generateSalt
{
    public static void main(String[] args)
    {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        System.out.println(Base64.getEncoder().encodeToString(saltBytes));
    }
    
}
