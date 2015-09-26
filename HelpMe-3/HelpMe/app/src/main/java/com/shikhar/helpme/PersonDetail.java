package com.shikhar.helpme;

import java.io.Serializable;

/**
 * Created by guneet pc on 25-04-2015.
 */
public class PersonDetail implements Serializable
{
    String name;
    int charge;
    String profession;
    int age;
    String gender;
    String address;
    String phone;
    PersonDetail(String name,int charge,String profession)
    {
        this.name=name;
        this.charge=charge;
        this.profession=profession;
    }

}
