package empleados;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.ExtensionContext;

public class EmpleadoParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        // Check if the parameter type is supported
        return parameterContext.getParameter().getType().equals(EmpleadoBRTest.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        // Create and return an instance of EmpleadoBRTest with appropriate arguments
        // return new EmpleadoBRTest(TipoEmpleado.vendedor, 2000, 8, 1360.0f);
        return new EmpleadoBRTest();
    }
}
