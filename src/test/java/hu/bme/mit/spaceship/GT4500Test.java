package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTorpedoStore1;
  private TorpedoStore mockTorpedoStore2;

  @BeforeEach
  public void init(){
    this.mockTorpedoStore1 = mock(TorpedoStore.class);
    this.mockTorpedoStore2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTorpedoStore1,mockTorpedoStore2);
  }

  @Test
  public void fireTorpedo_Single_Success_Second(){
    // Arrange
    when(mockTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockTorpedoStore2.isEmpty()).thenReturn(false);
    when(mockTorpedoStore2.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTorpedoStore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_First(){
    // Arrange
    when(mockTorpedoStore1.isEmpty()).thenReturn(false);
    when(mockTorpedoStore2.isEmpty()).thenReturn(true);
    when(mockTorpedoStore1.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTorpedoStore1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Empty(){
    // Arrange
    when(mockTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockTorpedoStore2.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Second_Time_Empty(){

    ship.fireTorpedo(FiringMode.SINGLE);

    // Arrange
    when(mockTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockTorpedoStore2.isEmpty()).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Second_Time_First(){
    ship.fireTorpedo(FiringMode.SINGLE);

    // Arrange
    when(mockTorpedoStore1.isEmpty()).thenReturn(false);
    when(mockTorpedoStore2.isEmpty()).thenReturn(true);
    when(mockTorpedoStore1.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTorpedoStore1, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_Second_Time_Second(){
    ship.fireTorpedo(FiringMode.SINGLE);

    // Arrange
    when(mockTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockTorpedoStore2.isEmpty()).thenReturn(false);
    when(mockTorpedoStore2.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTorpedoStore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
