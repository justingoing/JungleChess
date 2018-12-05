package edu.colostate.cs.cs414.method_men.jungle.server;

public class DBRecord {
    private Long ID;
    private String string1;
    private String string2;
    private String string3;
    private String string4;

    public DBRecord(Long ID, String string1, String string2, String string3, String string4) {
        this.ID = ID;
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
        this.string4 = string4;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public String getString4() {
        return string4;
    }

    public void setString4(String string4) {
        this.string4 = string4;
    }

    @Override
    public String toString() {
        return "DBRecord{" +
                "ID=" + ID +
                ", string1='" + string1 + '\'' +
                ", string2='" + string2 + '\'' +
                ", string3='" + string3 + '\'' +
                ", string4='" + string4 + '\'' +
                '}';
    }


}
