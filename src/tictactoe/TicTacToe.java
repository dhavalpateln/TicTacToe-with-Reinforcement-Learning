/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dhaval
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Environment environment = Environment.getInstance();
        environment.setVisible();
        int games = 0;
        int circleWins = 0;
        int crossWins = 0;
        int draw = 0;
        //Agent circleAgent = new Agent(Environment.CIRCLE, 2, 0.1);
        //Agent crossAgent = new Agent(Environment.CROSS, 1, 0.1);
        AgentN circleAgent = new AgentN(Environment.CIRCLE, 2, 0.1);
        AgentN crossAgent = new AgentN(Environment.CROSS, 1, 0.1);
        while(games < 500000) {
            environment.reset();
            while(environment.gameStatus() == 0) {
                
                if(environment.getTurn() == Environment.CIRCLE) {
                    int action = circleAgent.takeAction();
                    environment.action(action);
                }
                else {
                    int action = crossAgent.takeAction();
                    environment.action(action);
                }
                
            }
            switch (environment.gameStatus()) {
                case Environment.CIRCLE:
                    circleWins++;
                    break;
                case Environment.CROSS:
                    crossWins++;
                    break;
                default:
                    draw++;
                    break;
            }
            games++;
            System.out.println("Game "+games+"\tCircle = "+circleWins+"\tCross = "+crossWins+"\tDraw = "+draw);
        }
        while(games < 10010) {
            environment.reset();
            while(environment.gameStatus() == 0) {
                
                if(environment.getTurn() == Environment.CIRCLE) {
                    int action = circleAgent.takeAction();
                    environment.action(action);
                }
                else {
                    int action = crossAgent.takeAction();
                    environment.action(action);
                }
                
            }
            switch (environment.gameStatus()) {
                case Environment.CIRCLE:
                    circleWins++;
                    break;
                case Environment.CROSS:
                    crossWins++;
                    break;
                default:
                    draw++;
                    break;
            }
            games++;
            System.out.println("Game "+games+"\tCircle = "+circleWins+"\tCross = "+crossWins+"\tDraw = "+draw);
        }
        environment.reset();
        playWithHuman();
        
    }
    
    public static void playWithHuman() {
        Environment environment = Environment.getInstance();
        //Agent circleAgent = new Agent(Environment.CIRCLE, 0, 1);
        AgentN circleAgent = new AgentN(Environment.CIRCLE, 0, 1);
        if(environment.getTurn() == Environment.CIRCLE){
            int action = circleAgent.takeAction();
            environment.action(action);
        }
        environment.getBoard().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int y = me.getX()/100;
                int x = me.getY()/100;
                if(environment.getTurn() == Environment.CROSS){
                environment.action(x,y);
                int[] s = environment.getState();
                for(int i = 0; i < 9; i++) {
                    System.out.print(s[i]+" ");
                    if(i % 3 == 2) System.out.println();
                }
                if(environment.gameStatus() != 0) {
                    if(environment.gameStatus() == Environment.CIRCLE) {
                        System.out.println("Circle wins");
                        
                    }
                    else if(environment.gameStatus() == Environment.CROSS){
                        System.out.println("Cross wins");
                    }
                    else {
                        System.out.println("Draw");
                    }
                    sleep();
                    environment.reset();
                    sleep();
                    
                }
                sleep();
                //Comp action
                int action = circleAgent.takeAction();
                environment.action(action);
                if(environment.gameStatus() != 0) {
                    if(environment.gameStatus() == Environment.CIRCLE) {
                        System.out.println("Circle wins");
                        
                    }
                    else if(environment.gameStatus() == Environment.CROSS){
                        System.out.println("Cross wins");
                    }
                    else {
                        System.out.println("Draw");
                    }
                    sleep();
                    environment.reset();
                    sleep();
                    
                }
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
    }
    
    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
