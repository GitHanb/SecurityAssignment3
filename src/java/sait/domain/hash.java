/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sait.db.UsersDB;

/**
 *
 * @author hanzh
 */
public class hash
{

    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String hashAdam = UsersDB.hashPassword("adamURDqzTnZRD9mCBv/ivMyVXgPqusBTklNBXK1gUJfA9U=");
        String hashBill = UsersDB.hashPassword("billtQwg3jFMCEAQfJ1Umc6AvNeqIzN3XLVeHLDL84/+lh4=");
        
        System.out.println(hashAdam);
        System.out.println(hashBill);
        
    }
}
