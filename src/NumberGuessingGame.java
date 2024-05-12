import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class NumberGuessingGame extends JFrame{

    private int randomNumber;
    private int attemptsLeft;
    private final int maxAttempts=10;

    private JTextField guessInput; //a text field where user enters their guesses.
    private JButton guessButton; //A button that the user clicks to submit their guess.
    private JLabel messageLabel; //A label that shows message to the user
    private JLabel attemptsLabel; //A label that shows how many guesses the user has left.


    public NumberGuessingGame(int maxNumber){
        randomNumber = new Random().nextInt(maxNumber) + 1;
        attemptsLeft = maxAttempts;
        createUI();
        setupFrame();
    }

    private void createUI(){
        guessInput=new JTextField(10);
        guessButton=new JButton("Guess");
        messageLabel=new JLabel("Enter your Guess:");
        attemptsLabel=new JLabel("Attempts left:" + attemptsLeft);
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    int guess=Integer.parseInt(guessInput.getText());
                    if(guess<1 || guess>100){
                        JOptionPane.showMessageDialog(null,"Please Enter a number between 1 and 100.");
                        return;
                    }
                    checkGuess(guess);
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null,"Please enter a valid Number.");
                }

            }
        });

        setLayout(new FlowLayout());
        add(messageLabel);
        add(guessInput);
        add(guessButton);
        add(attemptsLabel);
    }

    private void checkGuess(int guess){
        attemptsLeft--;
        if(guess==randomNumber){
            JOptionPane.showMessageDialog(null,"You guesses the right Number! You Win!");
            resetGame();
        }else if(attemptsLeft==0){
            JOptionPane.showMessageDialog(null,"No attempts left! You Lose! The number was" + randomNumber);

        }else if(guess<randomNumber){
            messageLabel.setText("Too low! Try Again:");
        }else {
            messageLabel.setText("Too High! Try again:");
        }
        attemptsLabel.setText("Attempts left: "+attemptsLeft);
    }
    private void resetGame(){
        randomNumber=new Random().nextInt(100)+1;
        attemptsLeft=maxAttempts;
        messageLabel.setText("Enter your Guess:");
        attemptsLabel.setText("Attempts left: "+attemptsLeft);
        guessInput.setText("");
    }
    private void setupFrame(){
        setTitle("Number Guessing Game:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setVisible(true);
    }
    public static void main(String[] args){
        new NumberGuessingGame(100);
    }
}