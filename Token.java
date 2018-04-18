
//two private data members both of type String
public class Token {
    private String type;
    private String value;
    //private final EOF = '-';


    //constructor that takes two parameters
    public Token (String type, String value){
        this.type = type;
        this.value = value;

    }

    public String getType() {
        return type;
    }



    //toString method that prints tokens type and value
    public String toString(){
        return (type + "        " +value);
    }

    public boolean equals(Token other){
        return this.value == other.value;
    }


    public static void main(String[] args){


    }

}