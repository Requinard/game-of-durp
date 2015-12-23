package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by David on 12/23/2015.
 */
public class GameOfLife {
    Node[][] nodes;
    private List<Node> nodesList;
    private int width, height;
    private final Random random = new Random(System.currentTimeMillis());

    private boolean output = false;

    public void setOutput(boolean output) {
        this.output = output;
    }

    public GameOfLife(int width, int height, int fillChance) {
        // Check input params
        if (fillChance < 1 || fillChance > 100) {
            throw new IllegalArgumentException("Fill chance has to be between 1 and 100");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be higher then 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be higher then 0");
        }
        nodesList = new ArrayList<Node>(height * width);
        this.width = width;
        this.height = height;
        nodes = new Node[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int randval = (int) random.nextInt(100);
                Node node;
                if (randval <= fillChance) {
                    node = new Node(true);
                } else {
                    node = new Node(false);
                }

                nodes[i][j] = node;
            }
        }
    }

    /**
     * Iterates through a round of game of life
     */
    public void iterate() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assessState(i, j);
                builder.append(nodes[i][j].toString() + " ");
            }
            builder.append("\r\n");
        }

        if(output) System.out.println(builder.toString());
        propagateChanges();
    }

    public void iterate(int count){
        for(int i = 0; i != count; i++){
            iterate();
        }
    }

    private void assessState(int i, int j) {
        int aliveCount = 0;
        Node currentNode = nodes[i][j];

        for (int y = -1; y != 2; y++) {
            // Make y node transfer over array
            int nodeY = i + y;
            if (nodeY < 0) nodeY = (height-1);
            else if(nodeY >= width) nodeY = 0;

            for (int x = -1; x != 2; x++) {
                // Make x node transfer over array
                int nodeX = j + x;
                if (nodeX < 0) nodeX = (width-1);
                else if(nodeX >= width) nodeX = 0;


                if (nodes[nodeY][nodeX].isAlive() && (nodeX != i && nodeY != j)) {
                    aliveCount++;
                }
            }
        }

        if (currentNode.isAlive()) {
            if (aliveCount < 2 || aliveCount > 3) currentNode.setNextState(false);
            else currentNode.setNextState(true);
        } else if (aliveCount == 3) {
            currentNode.setNextState(true);
        }
    }

    /**
     * Propagates changes through all nodes
     */
    private void propagateChanges() {
        Consumer<Node> functor = (Node node) -> node.Propagate();

        iterateThroughNode(functor);
    }


    /**
     * Executes a functor on all nodes
     *
     * @param functor function to be executed
     */
    public void iterateThroughNode(Consumer<Node> functor) {
        for (int i = 0; i != height; i++) {
            for (int j = 0; j != width; j++) {
                functor.accept(nodes[i][j]);
            }
        }
    }
}
