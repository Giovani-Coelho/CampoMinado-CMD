import com.giovani.model.Campo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CampoTest {
  private Campo field;

  @Before
  public void startField() {
    field = new Campo(3,3);
  }

  @Test
  public void testNeighborDistance1Left() {
    Campo neighbor = new Campo(3,2);
    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void testNeighborDistance1Right() {
    Campo neighbor = new Campo(3,4);
    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void testNeighborDistance1Up() {
    Campo neighbor = new Campo(2,3);
    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void testNeighborDistance1Down() {
    Campo neighbor = new Campo(4,3);
    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void testNeighborDistance2() {
    Campo neighbor = new Campo(2,2);
    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void isNotNeighbor() {
    Campo neighbor = new Campo(1,1);
    boolean result = field.addNeighbor(neighbor);

    assertFalse(result);
  }
}
