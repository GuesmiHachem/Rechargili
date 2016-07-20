package com.example.hachem.rechargilitn.Structure;

/**
 * Created by Hachem on 23/03/2016.
 */
public class
        ParametreStructure
{

    public String langue;
    public String son;


    public ParametreStructure()
    {
        super();
    }
    public ParametreStructure(String langue, String son)
    {
        super();
        this.langue=langue;
        this.son=son;
    }
    //*******************************************
    public String getLangue()
    {
        return langue;
    }
    public void  setLangue(String langue)
    {
        this.langue=langue;
    }
    //*******************************************
    public String getSon()
    {
        return son;
    }
    public void  setSon(String son)
    {
        this.son=son;
    }

    @Override
    public String toString()
    {
        String ch;
        ch=langue+"\n\t"+son+"\n";
        return ch;
    }
}
