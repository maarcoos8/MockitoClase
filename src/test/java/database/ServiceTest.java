package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    

    @Test
    @DisplayName("El metodo query devuelve el true con una consulta")
    public void query_SelectAll_ReturnTrue(){

        // Arrange
        Database database = mock(Database.class);
        Service service = new Service( database );
        when( database.isAvailable()).thenReturn(true);

        // Act
        boolean ok = service.query("SELECT * FROM users");

        // Assert
        assertTrue( ok , "El metodo query deberia devolver true, en este caos hubo un error en el método");
    }

    @Test
    @DisplayName("El metodo toString")
    public void toString_User42_ReturnCorrectString(){
        
        // Arrange
        int number = 42;
        String resultExpected = "Users in the database : " + number;
        Database database = mock(Database.class);
        Service service = new Service( database );
        when( database.getNumberofUsers()).thenReturn(number);

        // Act
        String result = service.toString();

        // Assert
        assertEquals(resultExpected, result);
    }
}
