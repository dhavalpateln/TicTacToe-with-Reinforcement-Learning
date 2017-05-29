/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dhaval
 */
public class Environment {
    
    private static Environment environment = null;
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int CIRCLE = 2;
    public static final int DRAW = 3;
    
    ImageIcon crossIcon;
    ImageIcon circleIcon;
    
    int turn = 0;
    
    JFrame frame;
    JPanel board;
    JLabel grid[][] = new JLabel[3][3];
    int state[] = new int[9];
    
    private Environment() {
        
        crossIcon = new ImageIcon("images\\x.png");
        circleIcon = new ImageIcon("images\\o.png");
        
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        board.setPreferredSize(new Dimension(300, 300));
        initBoard();
        
        frame.add(board);
    }
    
    public void initBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j] = new JLabel("");
                board.add(grid[i][j]);
            }
        }
    }
    
    public void action(int i) {
        if(state[i] == 0) {
            int x = i/3;
            int y = i%3;
            if(getTurn() == CIRCLE) {
                grid[x][y].setIcon(circleIcon);
                state[i] = CIRCLE;
            }
            else {
                grid[x][y].setIcon(crossIcon);
                state[i] = CROSS;
            }
            turn++;
        }
    }
    
    public void action(int x, int y) {
        //System.out.println(x+" - "+y+" = "+(y*3+x));
        action(x*3 + y);
    }
    
    public int gameStatus() {
        //Straight Lines
        for(int i = 0; i < 3; i++) {
            if((state[i] != 0) && (state[i] == state[i+3]) && (state[i] == state[i+6])) {
                return state[i];
            }
        }
        //Horizontal lines
        for(int i = 0; i < 3; i++) {
            if((state[i*3] != 0) && (state[i*3] == state[i*3+1]) && (state[i*3] == state[i*3+2])) {
                return state[i*3];
            }
        }
        //Diagonal 1
        if((state[4] != 0) && (((state[4] == state[0]) && (state[4] == state[8])) || ((state[4] == state[2]) && (state[4] == state[6])))) {
            return state[4];
        }
        // running
        for(int i = 0; i < state.length; i++) {
            if(state[i] == 0) {
                return 0;
            }
        }
        
        //draw
        return DRAW;
    }
    
    public int gameStatus(int state[]) {
        //Straight Lines
        for(int i = 0; i < 3; i++) {
            if((state[i] != 0) && (state[i] == state[i+3]) && (state[i] == state[i+6])) {
                return state[i];
            }
        }
        //Horizontal lines
        for(int i = 0; i < 3; i++) {
            if((state[i*3] != 0) && (state[i*3] == state[i*3+1]) && (state[i*3] == state[i*3+2])) {
                return state[i*3];
            }
        }
        //Diagonal 1
        if((state[4] != 0) && (((state[4] == state[0]) && (state[4] == state[8])) || ((state[4] == state[2]) && (state[4] == state[6])))) {
            return state[4];
        }
        // running
        for(int i = 0; i < state.length; i++) {
            if(state[i] == 0) {
                return 0;
            }
        }
        
        //draw
        return DRAW;
    }
    
    public int[] getPossibleActions() {
        ArrayList<Integer> actions = new ArrayList<>();
        for(int i = 0; i < state.length; i++) {
            if(state[i]==0) {
                actions.add(i);
            }
        }
        int a[] = new int[actions.size()];
        for(int i = 0; i < a.length; i++) {
            a[i] = actions.get(i);
        }
        return a;
    }
    public int[] getPossibleActions(int state[]) {
        ArrayList<Integer> actions = new ArrayList<>();
        for(int i = 0; i < state.length; i++) {
            if(state[i]==0) {
                actions.add(i);
            }
        }
        int a[] = new int[actions.size()];
        for(int i = 0; i < a.length; i++) {
            a[i] = actions.get(i);
        }
        return a;
    }
    
    public void reset() {
        state = new int[9];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j].setIcon(null);
            }
        }
    }
    
    public int[] getState() {
        int dupState[] = new int[9];
        System.arraycopy(state, 0, dupState, 0, 9);
        return dupState;
    }
    
    public void setState(int s[]) {
        for(int i = 0; i < s.length; i++) {
            state[i] = s[i];
        }
    }
    
    public Integer[] getIntegerState() {
        Integer[] s = new Integer[state.length];
        for(int i = 0; i < state.length; i++) {
            s[i] = state[i];
        }
        return s;
    }
    
    public int getTurn() {
        return turn % 2 == 0 ? CIRCLE : CROSS;
    }
    
    public JPanel getBoard() {
        return board;
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public void setVisible() {
        frame.setVisible(true);
    }
    
    public static Environment getInstance() {
        if(environment == null) {
            environment = new Environment();
        }
        return environment;
    }
}
