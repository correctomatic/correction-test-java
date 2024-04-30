package empleados;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class EmpleadoBRTest {
    
    private TipoEmpleado tipo;
    private float ventaMes;
    private float horasExtra;
    private float expResult;//variable para el resultado esperado
    
    /**
     *
     * @param tipo
     * @param ventaMes
     * @param horasExtra
     */
    public EmpleadoBRTest (TipoEmpleado tipo, float ventaMes, float horasExtra, float expResult) {
        this.tipo = tipo;
        this.ventaMes = ventaMes;
        this.horasExtra = horasExtra;
        this.expResult = expResult;
    }   
    
    @Parameters
    public static Collection<Object[]> valores() {
        return Arrays.asList (new Object [][]{
            {TipoEmpleado.vendedor, 2000, 8, 1360.0f}, 
            {TipoEmpleado.vendedor, 1500, 3, 1260.0f},
            {TipoEmpleado.vendedor, 1499.99f, 0, 1100.0f},
            {TipoEmpleado.encargado, 1250, 8, 1760.0f},
            {TipoEmpleado.encargado, 1000, 0, 1600.0f},
            {TipoEmpleado.encargado, 999.9f, 3, 1560.0f},
            {TipoEmpleado.encargado, 500, 0, 1500.0f},
            {TipoEmpleado.encargado, 0, 8, 1660.0f}
        });
    }
    
    @Test
    public void CalculaSalarioBruto() throws BRException {
        System.out.println("El Salario Bruto es de " + expResult);        
        EmpleadoBR instance = new EmpleadoBR();
        float result = instance.calculaSalarioBruto(tipo, ventaMes, horasExtra);
        assertEquals(expResult, result, 0);

    }


    @Test(expected = Exception.class)
    public void CalculaSalarioNulo() throws BRException{
        EmpleadoBR instance = new EmpleadoBR();
        instance.calculaSalarioBruto (null, 1500, 8);
    }

}