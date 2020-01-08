import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private static final int x = 10;
    private static final int y = 10;
    private JButton [][]b = new JButton[x][y];
    private JLabel label;
    private JPanel game;
    private int counter = 0;
    private String s = "";
    private String winner;


    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.setVisible(true);
    }


    public TicTacToe(){
        setSize(WIDTH,HEIGHT);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        label = new JLabel("Good luck.");
        add(label, BorderLayout.NORTH);

        game = new JPanel();
        game.setLayout(new GridLayout(x,y));

        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++){
                b[i][j] = new JButton();
                b[i][j].addActionListener(new AddingListener(b[i][j]));
                game.add(b[i][j]);
            }

        add(game);

    }

    private class AddingListener implements ActionListener{
        private JButton theButton;

        public AddingListener(JButton b){
            theButton = b;
        }

        public void actionPerformed(ActionEvent e){
            if (counter % 2 != 0)
                theButton.setText("O");
            else
                theButton.setText("X");

            theButton.setEnabled(false);

            counter++;

            if (checkWin() == true)
                label.setText("Winner is " + winner + ".");
            else if (counter == x*y)
                label.setText("There is a tie.");

        }

    }

    public boolean checkWin() {
        if ((checkRows() == true) || (checkColons() == true) || (checkOneDiagonal() == true) || (checkAnotherDiagonal() == true)) {
            for (int i = 0; i < x; i++)
                for (int j = 0; j < y; j++)
                    b[i][j].setEnabled(false);
            return true;
        }
        else
            return false;
    }


    public boolean checkRows() {
        boolean win = false;
        int count;
        for (int i = 0; i < x; i++) {
            count = 0;
            for (int j = 0; (j+1) < y; j++) {
                if (b[i][j].getText().equals(s)) {
                    count++;
                    break;
                }
                else if (!(b[i][j].getText().equals(b[i][j+1].getText()))){
                    count++;
                    break;
                }

            }
            if (count == 0) {
                win = true;
                winner = b[i][0].getText();
                break;
            }
        }
        return win;
    }

    public boolean checkColons() {
        boolean win = false;
        int count;
        for (int j = 0; j < y; j++) {
            count = 0;
            for (int i = 0; (i+1) < x; i++) {
                if (b[i][j].getText().equals(s)) {
                    count++;
                    break;
                }
                else if (!(b[i][j].getText().equals(b[i+1][j].getText()))){
                    count++;
                    break;
                }

            }
            if (count == 0) {
                win = true;
                winner = b[0][j].getText();
                break;
            }
        }

        return win;
    }

    public boolean checkOneDiagonal() {
        boolean win = false;
        int count = 0 ;
        for (int i = 0; (i+1) < x; i++) {
            count = 0;
            if (b[i][i].getText().equals(s)) {
                count++;
                break;
            }
            else if (!(b[i][i].getText().equals(b[i+1][i+1].getText()))){
                count++;
                break;
            }

        }

        if (count == 0) {
            winner = b[0][0].getText();
            win = true;
        }

        return win;
    }

    public boolean checkAnotherDiagonal() {
        boolean win = false;
        int count = 0 ;
        for (int i = 0; (i+1) < x; i++) {
            for (int j = y; (j-1) >= 0; j--) {
                if (!(i+j==x-1)){
                    count++;
                    break;
                }
                else if (!(b[i][j].getText().equals(b[i+1][j-1].getText()))){
                    count++;
                    break;
                }

            }
            if (count == 0) {
                winner = b[0][x].getText();
                win = true;
            }
        }
        return win;
    }





}

