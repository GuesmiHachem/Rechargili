package com.example.hachem.rechargilitn.Structure;

/**
 * Created by Hachem on 23/03/2016.
 */
public class UssdExterne
{

    public String operateur;
    public String categorie;
    public String code;
    public String service;


    public UssdExterne()
    {
        super();
    }
    public UssdExterne(String operateur, String categorie, String code, String service)
    {
        super();

        this.code=code;
        this.operateur=operateur;
        this.service=service;
        this.categorie=categorie;



    }
    //*******************************************
    public String getCategorie()
    {
        return categorie;
    }
    public void  setCategorie(String categorie)
    {
        this.categorie=categorie;
    }
    //*******************************************
    public String getOperateur()
    {
        return operateur;
    }
    public void  setOperateur(String operateur)
    {
        this.operateur=operateur;
    }
    //*******************************************
    public String getService()
    {
        return service;
    }
    public void  setService(String service)
    {
        this.service=service;
    }
    //*******************************************
    public String getCode()
    {
        return code;
    }
    public void  setCode(String code)
    {
        this.code=code;
    }

    @Override
    public String toString()
    {
        String ch;
        ch=operateur+"\t"+categorie+"\n"+code+"\t"+service+"\t";
        return ch;
    }
}
