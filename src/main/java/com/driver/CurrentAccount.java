package com.driver;

import java.util.HashMap;
import java.util.Map;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    private static double minBal = 5000;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,minBal);
        if(balance < minBal){
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean isValid = true;
        int n = tradeLicenseId.length();
        for(int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                isValid = false;
                break;
            }
        }
        if(!isValid){
            int maxFreq = 0;
            Map<Character,Integer> freqMap = new HashMap<>();
            for(int i=0;i<n;i++){
                char ch = tradeLicenseId.charAt(i);
                freqMap.put(ch,freqMap.getOrDefault(ch,0)+1);
                maxFreq = Math.max(maxFreq,freqMap.get(ch));
            }
            if(maxFreq > (n+1)/2){
                throw new Exception("Valid License can not be generated");
            }
            else {
                 char []arr = new char[n];
                String temp = "";
                int i = 0;
                for(Map.Entry<Character,Integer> map: freqMap.entrySet()){
                    char ch = map.getKey();
                    int t = map.getValue();
                    while(t != 0 && i < n){
                        arr[i] = ch;
                        t--;
                        i = i+2;
                        if(i >= n){
                            i = 1;
                        }
                    }
                }
                String rearrangeLicense = new String(arr);
                this.tradeLicenseId = rearrangeLicense;
            }
        }

    }

}
