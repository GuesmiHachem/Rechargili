package com.example.hachem.rechargilitn.Structure;

/**
 * Created by Hachem on 23/03/2016.
 */
public class UssdLocale
{

    public String operateur;
    public String categorie;
    public String code;
    public String service;
    public int favoris;

    public UssdLocale()
    {
        super();
    }
    public UssdLocale(String operateur, String categorie, String code, String service,int favoris)
    {
        super();

        this.code=code;
        this.operateur=operateur;
        this.service=service;
        this.categorie=categorie;
        this.favoris=favoris;



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
    //*******************************************
    public int getFavoris()
    {
        return favoris;
    }
    public void  setFavoris(int favoris)
    {
        this.favoris=favoris;
    }

    @Override
    public String toString()
    {
        String ch;
        ch=operateur+"\n\t"+categorie+"\n"+service+"\n\t"+code;
        return ch;
    }
}
