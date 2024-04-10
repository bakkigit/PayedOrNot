package model;

public class Person implements Comparable<Person> {
    private String name;
    private String nr;
    private String betalt;


    public Person(String name, String nr, String betalt) {
        this.name = name;
        this.nr = nr;
        this.betalt = betalt;
    }

    public String getName() {
        return name;
    }

    public String getNr() {
        return nr;
    }

    public String getBetalt() {
        return betalt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public void setBetalt(String betalt) {
        this.betalt = betalt;
    }

    @Override
    public String toString() {
        return "Navn: "+name+"   Nummer: "+nr+"   Betalt? "+betalt;
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}
