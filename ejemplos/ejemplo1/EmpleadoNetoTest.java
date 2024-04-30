package empleados;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
//la clase engloba la funcionalidad, hay atributos y dentro guardamos valores
public class EmpleadoNetoTest {
    
    private float entrada;
    private float salida;

    public EmpleadoNetoTest (float entrada, float salida) {       
        this.entrada = entrada;
        this.salida = salida;
    }

    @Parameters
    public static Collection<Object[]> valores() {
        return Arrays.asList (new Object [][]{
            {2000, 1640}, 
            {1500, 1230},
            {1499.99f, 1259.9916f},
            {1250, 1050},
            {1000, 840},
            {999.9f, 999.9f},
            {500, 500},
            {0, 0}
        });
    }


    @Test
    public void CalculaSalarioNE() throws BRException {
        System.out.println("El Salario Neto es de " + salida);        
        EmpleadoNeto instance = new EmpleadoNeto();
        float result = instance.calculaSalarioNeto(entrada);
        assertEquals(salida, result, 0.01);
    }
    
}