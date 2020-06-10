package com.example.library.database.src.team.library.demo;

public class TypeInfo {
    public int rank;
    public String typename;
    public int reservetime=0;

    public void AddReserve()
    {
        reservetime++;
    }
    public TypeInfo(String name,int rank)
    {
        typename=name;
        this.rank=rank;
    }

    public void setrank(int rank)
    {
        this.rank=rank;
    }

    public String getTypeName() {
        return this.typename;
    }
}
