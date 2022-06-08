package Testing;

import Controller.Cell;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCell extends Cell {

    Cell cell;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        cell = new Cell();
    }

    @Test
    public void visitCell()
    {
        cell.isVisited = true;
        cell.SetIsObstacle(false);
        assertEquals(true, cell.isVisited);
        assertEquals(false, cell.isObstacle());
    }

    @Test
    public void IsCellObstacle()
    {
        cell.SetIsObstacle(true);
        assertEquals(true, cell.isObstacle());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}