

public class Parser {
    Lexer l1;
    Token tok;


    //tok.getType()


    //A constructor which creates a Lexer as a data member
    public Parser(Lexer l1){
        this.l1 = l1;
        tok = l1.getNextToken();

    }

    //this method parses an expression, i.e., the right-hand-side of an assignment.
    // Note that expressions can include + signs, e.g., Y+3+4
    public boolean parseExpression(){
  
        System.out.println(tok);


        //is it id or int
        while (!tok.getType().equals("\n")){
            if(!tok.getType().equals("Integer")){
                System.out.println(tok + " is not integer");
                System.out.println("Invalid");
                return false;
            }
            tok = l1.getNextToken();
            System.out.println(tok);

            if (!tok.getType().equals("PlusOp")){
                System.out.println(tok + " is not identifier");
                System.out.println("Invalid");
                return false;
            }
            tok = l1.getNextToken();
        }



            return true;
        }




    //this method parses a single assignment operator
    public boolean parseAssignOp(){
        //l1.getNextToken();

        if(tok.getType().equals("AssignOp")){
            System.out.println("=");
            tok = l1.getNextToken();

            return true;
        }else{
            return false;
        }


    }

    //this method parses a single identifier
    public boolean parseId() {

        System.out.println(tok);
        l1.index++;
        //tok = l1.getNextToken();
        if (tok.getType().equals("Identifier")) {
            tok = l1.getNextToken();
            return true;
        }else {
            System.out.println("Not an Identifier");
        }

        return false;
    }

    // this method should parse a single assignment statement.
    // It should call parseId, parseAssignmentOp, and parseExpression.
    public void parseAssignment(){
        //l1.getNextToken();
        parseId();
        parseAssignOp();
        parseExpression();

    }

    //this method drives the process and parses an entire program.
    //This method should call parseAssignment within a loop.
    public void parseProgram(){

        while(tok.getType() != "End of File"){
            parseAssignment();
            break;

        }

    }

    public static void main(String [] args){


        Lexer l1 = new Lexer("test.txt");
        Parser par = new Parser(l1);
        par.parseProgram();









    }
}
