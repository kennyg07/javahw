import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;


public class Lexer {

    final int MAXSIZE = 3000;
    char buffer[];  // this will hold the chars of the file
    int bufSize = 0;  // how many chars their actually are
    int index = 0;    // the location within buffer as we scan


    public Lexer(String fileName) {
        this.buffer = new char[MAXSIZE];
        getInput(fileName);

    }


    private void getInput(String fileName) {
        File inputFile = new File(fileName);
        try (FileReader reader = new FileReader(inputFile)) {
            this.bufSize = reader.read(this.buffer, 0, 1000);
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found.");
            System.out.println(fnf.getMessage());
            System.exit(1);
        } catch (IOException exc) {
            System.out.println("IO Exception");
            System.out.println(exc.getMessage());
            System.exit(1);
        }

    }

    //creating tokens that will be extracted into an identifier or integer
    public Token getNextToken() {


        //if(index == 0){
           // index++;
            //return getNextToken();
        //}
       // System.out.println();

        //going through the line and when it hits new line to go to the next
        if (buffer[index] == '\n') {
            index++;
            //return (new Token("\n", "\n"));
        }


        //going through line to check for white space
        if (buffer[index] == ' ')
            index++;

        //iterating through the loop while index is less than the amount of chars that there or or before it hits the end of file
        while (this.index < this.bufSize || this.buffer[index] != '-') {

            if (Character.isDigit(this.buffer[index])) {

                return getInteger();

            } else if (Character.isLetterOrDigit(this.buffer[index])) {
                return getIdentifier();

            } else if (this.buffer[index] == '+') {
                index++;
                return (new Token("PlusOp", "+"));

            } else if (this.buffer[index] == '=') {
                index++;
                return (new Token("AssignOp", "="));

            } else if (this.buffer[index] == '#') {
                index++;
                return (new Token("Unknown", "#"));

            }else if (this.buffer[index] == '$'){
                index++;
                return (new Token("Unknown", "$"));

            } else if (this.buffer[index] == '-') {
             return (new Token("End of File", "-"));
            }

        }
        return getNextToken();

    }


    // just print out buffer, mainly for debugging
    public String toString() {
        int i = 0;
        String s = "";
        while (i < this.bufSize) {
            s = s + this.buffer[i];
            i++;
        }
        return s;
    }


    //helper function for retrieving the next identifier
    private Token getIdentifier() {
        String str = "";
        while ((this.index < this.bufSize) && Character.isLetterOrDigit(this.buffer[index])) {
            str += this.buffer[index];
            index++;
        }
        return (new Token("Identifier", str));
    }

    //helper function for retrieving the next integer
    private Token getInteger() {
        String str = "";

        while ((this.index < this.bufSize) && Character.isDigit(this.buffer[index])) {
            str += this.buffer[index];
            index++;

        }
        return (new Token("Integer", str));
    }


    public static void main(String[] args) {
        Lexer lexer = new Lexer("test.txt");
        //System.out.println(lexer);


        System.out.println("Token Type    Token Value\n");
        Token tok = lexer.getNextToken();
        System.out.println(tok);


        while (!tok.getType().equals("End of File")) {
            tok = lexer.getNextToken();
            System.out.println(tok);


        }

    }

}

