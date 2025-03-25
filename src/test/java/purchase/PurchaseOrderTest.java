package purchase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class PurchaseOrderTest {

  @Test
  public void shouldPurchaseWorkProperlyWhenThereAreEnoughProducts() {
    // STEP 1: create mock object
    Warehouse warehouse = mock(Warehouse.class) ;
    // STEP 2: define behavior
    when(warehouse.thereAreProducts("Beer", 20)).thenReturn(true) ;

    // STEP 3: execute
    PurchaseOrder purchaseOrder = new PurchaseOrder("Beer", 20) ;
    purchaseOrder.purchase(warehouse);

    // STEP 4: verify
    verify(warehouse).remove("Beer", 20);
    verify(warehouse, times(1)).remove("Beer", 20);
  }


  @Test
  public void purchase_ThereIsProduct_RemoveProductFromWarehouse(){
    //Arrange
    Warehouse almacen = mock(Warehouse.class);
    PurchaseOrder compra = new PurchaseOrder("mandarinas", 50);
    when(almacen.thereAreProducts("mandarinas", 50)).thenReturn(true);

    //Act
    compra.purchase(almacen);

    //Assert
    verify(almacen).remove("mandarinas", 50); //verificamos q se ha llamado a eso en el almacen
  }

  @Test
  public void purchase_ThereIsNOProduct_RemoveProductFromWarehouse(){
    //Arrange
    Warehouse almacen = mock(Warehouse.class);
    PurchaseOrder compra = new PurchaseOrder("mandarinas", 50);
    when(almacen.thereAreProducts("mandarinas", 50)).thenReturn(false);

    //Act
    compra.purchase(almacen);

    //Assert
    verify(almacen, never()).remove("mandarinas", 50); //verificamos q NO se llama a remove en el almacen
  }

  //En este caso no tendremos q usar el verify pq en search no tendremos q comprobar q hace un metodo ya q devuelve un valor
  @Test
  public void search_ThereIsProduct_ReturnsAmount(){
    //Arrange
    Warehouse almacen = mock(Warehouse.class);
    PurchaseOrder busqueda = new PurchaseOrder("cervezas", 50);
    when(almacen.contains("cervezas")).thenReturn(true);
    when(almacen.getAmount("cervezas")).thenReturn(100);

    //Act
    int result = busqueda.search("cervezas", almacen);

    //Assert
    assertEquals(100, result);
  }

}