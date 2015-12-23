package matrix;

import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.*;

/**
 * Created by David on 12/23/2015.
 */
public class GameOfLifeTest {
    @Test
    public void constructorTest(){

    }

    @Test
    public void testIterate() throws Exception {
        GameOfLife gameOfLife = new GameOfLife(10,10, 15);
        gameOfLife.setOutput(true);
        gameOfLife.iterate(10);
    }

    @Test
    public void testIterateThroughNode() throws Exception {
        GameOfLife gameOfLife = new GameOfLife(10, 10, 50);
        Consumer<Node> functor = (Node node) -> node.setNextState(false);

        gameOfLife.iterateThroughNode(functor);
    }
}