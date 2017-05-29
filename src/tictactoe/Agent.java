/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Dhaval
 */
public class Agent {

    private static HashMap<String, Integer> table = new HashMap<>();
    public final int MAX_REWARD = 1000;
    public final int MIN_REWARD = 0;
    public final int DEFAULT_REWARD = 500;
    int type;
    int epsilon;
    double alpha;
    Environment environment;
    
    public Agent(int type, int epsilon, double alpha) {
        this.type = type;
        this.epsilon = epsilon;
        this.alpha = alpha;
        
    }
    
    public int takeAction() {
        environment = Environment.getInstance();
        int possibleActions[] = environment.getPossibleActions();
        int action;
        if(generateRandom(1, 10) <= epsilon) {
            action = possibleActions[generateRandom(0, possibleActions.length-1)];
        }
        else if(type == Environment.CROSS){
            int max = -2000;
            action = possibleActions[generateRandom(0, possibleActions.length-1)];
            for(int i = 0; i < possibleActions.length; i++) {
                int state[] = environment.getState();
                int a = possibleActions[i];
                state[a] = type;                
                String iState = convertArrayToKey(state);
                Integer value = null;
                if(table.get(iState) != null) value = table.get(iState);
                if(value == null) {
                    value = DEFAULT_REWARD;
                    table.put(iState,value);
                }
                if(value > max) {
                    max = value;
                    action = possibleActions[i];
                }
            }
        }
        else {
            int min = 2000;
            action = possibleActions[generateRandom(0, possibleActions.length-1)];
            for(int i = 0; i < possibleActions.length; i++) {
                int realState[] = environment.getState();
                int state[] = new int[9];
                for(int j = 0; j < 9; j++) state[j] = realState[j];
                int a = possibleActions[i];
                state[a] = type;  
                String iState = convertArrayToKey(state);
                Integer value = null;
                if(table.get(iState) != null) value = table.get(iState);
                if(value == null) {
                    value = DEFAULT_REWARD;
                    table.put(iState,value);
                }
                if(value < min) {
                    min = value;
                    action = possibleActions[i];
                }
            }
        }
        int nState[] = environment.getState();
        nState[action] = type;
        updateTable(environment.getState(), nState);
        return action;
    }
    
    public void updateTable(int currentState[], int nextState[]) {
        environment = Environment.getInstance();
        String cState = convertArrayToKey(currentState);
        String nState = convertArrayToKey(nextState);
        Integer valueC = table.get(cState);
        Integer valueN = table.get(nState);
        if(valueC == null) {
            valueC = DEFAULT_REWARD;
            table.put(cState,valueC);
        }
        if(valueN == null){
            valueN = DEFAULT_REWARD;
            table.put(nState,valueN);
        }
        
        int winner = environment.gameStatus(nextState);
        if(winner == Environment.CROSS){
            valueN = MAX_REWARD;
            table.put(nState,valueN);
        }
        else if(winner == Environment.CIRCLE) {
            valueN = MIN_REWARD;
            table.put(nState,valueN);
        }
        int newValue = (int) (valueC + alpha*(valueN - valueC));
        table.put(cState, newValue);
    }
    
    public Integer[] convertToIntegerArray(int[] array) {
        Integer[] result = new Integer[array.length];
        for(int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public String convertArrayToKey(int[] array) {
        String result = "";
        for(int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }
    
    public int generateRandom(int lower, int upper) {
        Random random = new Random();
        return random.nextInt(upper - lower + 1) + lower;
    }

    
    
}
