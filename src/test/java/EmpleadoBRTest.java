package empleados;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

public class EmpleadoBRTest {

    private TipoEmpleado tipo;
    private float ventaMes;
    private float horasExtra;
    private float expResult; // variable para el resultado esperado

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

    public static Stream<Arguments> valores() {
        return Stream.of(
            Arguments.of(TipoEmpleado.vendedor, 2000, 8, 1360.0f),
            Arguments.of(TipoEmpleado.vendedor, 1500, 3, 1260.0f),
            Arguments.of(TipoEmpleado.vendedor, 1499.99f, 0, 1100.0f),
            Arguments.of(TipoEmpleado.encargado, 1250, 8, 1760.0f),
            Arguments.of(TipoEmpleado.encargado, 1000, 0, 1600.0f),
            Arguments.of(TipoEmpleado.encargado, 999.9f, 3, 1560.0f),
            Arguments.of(TipoEmpleado.encargado, 500, 0, 1500.0f),
            Arguments.of(TipoEmpleado.encargado, 0, 8, 1660.0f)
        );
    }

    @ParameterizedTest
    @MethodSource("valores")
    public void CalculaSalarioBruto(TipoEmpleado tipo, float ventaMes, float horasExtra, float expResult) throws BRException {
        System.out.println("El Salario Bruto es de " + expResult);
        EmpleadoBR instance = new EmpleadoBR();
        float result = instance.calculaSalarioBruto(tipo, ventaMes, horasExtra);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void CalculaSalarioNulo() {
        EmpleadoBR instance = new EmpleadoBR();
        assertThrows(BRException.class, () -> {
            instance.calculaSalarioBruto(null, 1500, 8);
        });
    }
}
