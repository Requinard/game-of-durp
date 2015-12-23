package matrix;

/**
 * Created by David on 12/23/2015.
 */
public class Node {
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    private boolean nextState;

    public Node(boolean alive) {
        this.alive = alive;
        this.nextState = nextState;
    }

    public void setNextState(boolean val){
        this.nextState = val;
    }

    public boolean Propagate(){
        return this.alive = nextState;
    }

    @Override
    public String toString() {
        if(alive) return "X";
        else return "O";
    }
}
