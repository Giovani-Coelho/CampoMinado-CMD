import com.giovani.error.ExplosionException;
import com.giovani.model.Campo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    Assert.assertTrue(result);
  }

  @Test
  public void testNeighborDistance1Right() {
    Campo neighbor = new Campo(3,4);
    boolean result = field.addNeighbor(neighbor);

    Assert.assertTrue(result);
  }

  @Test
  public void testNeighborDistance1Up() {
    Campo neighbor = new Campo(2,3);
    boolean result = field.addNeighbor(neighbor);

    Assert.assertTrue(result);
  }

  @Test
  public void testNeighborDistance1Down() {
    Campo neighbor = new Campo(4,3);
    boolean result = field.addNeighbor(neighbor);

    Assert.assertTrue(result);
  }

  @Test
  public void testNeighborDistance2() {
    Campo neighbor = new Campo(2,2);
    boolean result = field.addNeighbor(neighbor);

    Assert.assertTrue(result);
  }

  @Test
  public void isNotNeighbor() {
    Campo neighbor = new Campo(1,1);
    boolean result = field.addNeighbor(neighbor);

    Assert.assertFalse(result);
  }

  @Test
  public void testStandardMarkup() {
    Assert.assertFalse(field.isMarked());
  }

  @Test
  public void testToggleTwoCallsMarkup() {
    field.toggleMarking();
    field.toggleMarking();
    Assert.assertFalse(field.isMarked());
  }

  @Test
  public void testOpenNotUnderminedNotMarked() {
    Assert.assertTrue(field.openField());
  }

  @Test
  public void testOpenNotUnderminedMarked() {
    field.toggleMarking();
    Assert.assertFalse(field.openField());
  }

  @Test
  public void testOpenUndermineMarked() {
    field.toggleMarking();
    field.undermine();
    Assert.assertFalse(field.openField());
  }

  @Test
  public void testOpenUndermineNotMarked() {
    field.undermine();
    // se esse metodo chama essa excecao
    Assert.assertThrows(ExplosionException.class, () -> field.openField());
  }

  @Test
  public void testOpenSafeNeighbor() {
    Campo neighbor1 = new Campo(1,1);
    Campo neighbor2 = new Campo(2,2);

    neighbor2.addNeighbor(neighbor1);

    field.addNeighbor(neighbor2);
    field.openField();

    Assert.assertTrue(neighbor2.isOpen() && neighbor1.isOpen());
  }
}
