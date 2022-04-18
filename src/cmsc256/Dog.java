package cmsc256;

public class Dog implements Comparable<Dog> {
    private String dogName;
    private int count;

    //constructors
    public Dog(String dogName, int count){
        this.dogName = dogName;
        this.count = count;
    }
    public Dog(){
    }

    //getters and setters
    public void setDogName(String dogName){
        this.dogName = dogName;
    }
    public String getDogName(){
        return dogName;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
    }

    //equals class compares two dog objects
    public boolean equals(Dog o){
        if(!dogName.equals(o.getDogName())){
            return false;
        }
        if(count != o.getCount()){
            return false;
        }
        return true;
    }

    //toString method
    public String toString(){
        return dogName + " " + count;
    }

    @Override
    public int compareTo(Dog o) {
        return this.dogName.compareTo(o.getDogName());
    }
}
