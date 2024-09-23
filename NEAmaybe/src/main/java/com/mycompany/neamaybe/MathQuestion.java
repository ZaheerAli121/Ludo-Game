// Import necessary package
package com.mycompany.neamaybe;
import java.util.Random;

// MathQuestion class to generate random math questions
public class MathQuestion {

    // Method to generate a random math question
    public static MathQuestions generateRandomMathQuestion() {
        
        // Initialize variables for answer and question
        int answer = 0;
        String question = "";
        
        // Loop until a valid answer is generated
        while (answer == 0) {
            
            // Create a Random object
            Random random = new Random();
            
            // Generate random numbers for the math question
            int num1 = random.nextInt(12) + 1;
            int num2 = random.nextInt(12) + 1;
            
            // Ensure num2 is not 0
            while (num2 == 0) {
                num2 = random.nextInt(12) + 1;
            }
            
            // Choose a question type
            int questionType = random.nextInt(5) + 1;
            
            // For basic arithmetic operations
            if (questionType > 2) {
                // Choose a random operator
                int operatorChoice = random.nextInt(4);
                char operator;
                
                // Calculate the answer based on the operator
                switch (operatorChoice) {
                    case 0:
                        operator = '+';
                        answer = num1 + num2;
                        break;
                    case 1:
                        operator = '-';
                        answer = num1 - num2;
                        break;
                    case 2:
                        operator = '*';
                        answer = num1 * num2;
                        break;
                    case 3:
                        operator = '/';
                        answer = num1 / num2;
                        break;
                    default:
                        operator = '+';
                        answer = num1 + num2;
                }

                // Build the question string
                question = num1 + " " + operator + " " + num2;
            } 
            // For linear equations
            else {
                // Generate random coefficients and constants
                int a = random.nextInt(10) + 1; 
                int b = random.nextInt(50) + 1; 
                int ans = random.nextInt(50) + 1;
                int op = random.nextInt(3);
                
                // Initialize question and answer variables
                question = "";
                answer = 0;
                
                // Build the question string and calculate the answer
                if (op == 1) {
                    question = a + "x " + "+" + " " + b + " = " + (ans);
                    answer = (ans - b) / (a);
                } 
                else {
                    question = a + "x " + "-" + " " + b + "=" + ans;
                    answer = (ans + b) / (a);
                }
                
            }
        }
        
        // Return a new MathQuestions object
        return new MathQuestions(question, answer);
    }
}

// MathQuestions class to represent a math question
class MathQuestions {
    // Private fields for question and answer
    private String question;
    private int answer;

    // Constructor to initialize the question and answer
    public MathQuestions(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getter method for the question
    public String getQuestion() {
        return question;
    }

    // Getter method for the answer
    public int getAnswer() {
        return answer;
    }
}


/*
package com.mycompany.neamaybe;
import java.util.Random;

public class MathQuestion {
    public static MathQuestions generateRandomMathQuestion() {
        
    int answer = 0;
    String question = "";
    
    while(answer == 0){
        
    Random random = new Random();
    int num1 = random.nextInt(12) + 1;
    int num2 = random.nextInt(12) + 1;
    
    while(num2 == 0){
        num2 = random.nextInt(12)+1;
    }
    
    int questionType = random.nextInt(5) +1;
    
    if (questionType >2) {
        int operatorChoice = random.nextInt(4);
        char operator;
        switch (operatorChoice) {
            case 0:
                operator = '+';
                answer = num1 + num2;
                break;
            case 1:
                operator = '-';
                answer = num1 - num2;
                break;
            case 2:
                operator = '*';
                answer = num1 * num2;
                break;
            case 3:
                operator = '/';
                answer = num1 / num2;
                break;
            default:
                operator = '+';
                answer = num1 + num2;
        }

        question = num1 + " " + operator + " " + num2;
        return new MathQuestions(question, answer);
    } 
    else {
        int a = random.nextInt(10) + 1; 
        int b = random.nextInt(50) + 1; 
        int ans = random.nextInt(50) + 1;
        int op = random.nextInt(3);
        question = "";
        answer = 0;
        if(op == 1){
        question = a + "x " + "+" + " " + b + " = " + (ans);
        answer = (ans - b) / (a);
        }
        else{
            question = a + "x " + "-" + " " + b + "=" + ans;
            answer = (ans + b) / (a);
            
        }
       return new MathQuestions(question,answer);
    }
    }
    
    return new MathQuestions(question,answer);
}
    
    
}

class MathQuestions {
    private String question;
    private int answer;

    public MathQuestions(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }
}





/*
    public static MathQuestions generateRandomMathQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(12) + 1; 
        int num2 = random.nextInt(12) + 1;
        int questiontype = random.nextInt(4);
      
        int operatorChoice = random.nextInt(4);
        char operator;
        int answer;
        if(questiontype>1){
        switch (operatorChoice) {
            case 0:
                operator = '+';
                answer = num1 + num2;
                break;
            case 1:
                operator = '-';
                answer = num1 - num2;
                break;
            case 2:
                operator = '*';
                answer = num1 * num2;
                break;
            case 3:
                operator = '/';
                // Ensure that division result is an integer (no remainder)
                answer = num1 / num2;
                
                break;
            default:
                operator = '+';
                answer = num1 + num2;
        }

        String question = num1 + " " + operator + " " + num2;
        return new MathQuestions(question, answer);
    }
        else if(questiontype == 1){
            int a = random.nextInt(10) + 1; // Coefficient of x
        int b = random.nextInt(50) + 1; // Constant
        char operatorn = random.nextBoolean() ? '+' : '-';
        String question = a + "x " + operatorn + " " + b + " = " + (a + b);
        int answern = (a + b) / (a - 1);
        return new MathQuestions(question, answern);
            
        }
        
}
*/

