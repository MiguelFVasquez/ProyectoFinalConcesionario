package Test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Exceptions.*;
import Model.*;

public class ConcesionarioTest {

	private Concesionario concesionario;
	private Administrador administrador;
    private Empleado empleado;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Transaccion transaccion;

	@Before
    public void setUp() {
        // Inicializar el objeto concesionario antes de cada prueba
        concesionario = new Concesionario("UQ_Test");

        administrador = new Administrador("admin","concesionario","1011","123");

        empleado = new Empleado("empleadoNombre", "empleadoApellido", "empleado123");

        cliente = new Cliente("clienteNombre", "clienteApellido", "cliente123");

		transaccion = new Transaccion("fecha", 1000.0, "codigo", TipoTransaccion.VENTA, "cantDias", "cedulaCliente");

        vehiculo = new Vehiculo("marca", "modelo", "cambios", "velMaxima", "cilindraje", TipoTransmicion.AUTOMATICA,
				TipoCombustible.GASOLINA, TipoEstado.USADO, 10000.0, "autonomia", "tiempoCarga", true, false);

		concesionario.agregarVehiculo(vehiculo);

        empleado.agregarCliente(cliente);

        concesionario.agregarEmpleado(empleado);

        concesionario.getListaAdministradores().add(administrador);
    }

//------------------------------ J_UNITS METODOS ADMINISTRADOR --------------------------------------------------

	@Test
    public void verificarAdministrador_AdministradorExistente_DeberiaRetornarTrue() {
        // Verificar si el administrador existente es verificado correctamente
        boolean resultado = concesionario.verificarAdministrador("1011", "123");
        Assert.assertTrue(resultado);
    }

    @Test
    public void verificarAdministrador_AdministradorNoExistente_DeberiaRetornarFalse() {
        // Verificar si un administrador no existente no es verificado correctamente
        boolean resultado = concesionario.verificarAdministrador("admin456", "adminPass");
        Assert.assertFalse(resultado);
    }

    @Test
    public void obtenerAdministrador_AdministradorExistente_DeberiaRetornarAdministrador() {
        // Obtener el administrador existente
        Administrador resultado = concesionario.obtenerAdministrador("1011");
        Assert.assertEquals(administrador, resultado);
    }

    @Test
    public void obtenerAdministrador_AdministradorNoExistente_DeberiaRetornarNull() {
        // Obtener un administrador no existente
        Administrador resultado = concesionario.obtenerAdministrador("admin456");
        Assert.assertNull(resultado);
    }

    @Test
    public void crearAdministrador_AdministradorNoExistente_CrearExitoso() throws AdministradorException {
        // Crear un nuevo administrador
        Administrador nuevoAdministrador = new Administrador("nuevoNombre", "nuevoApellido", "nuevo123", "nuevoPass");

        // Agregar el administrador al concesionario
        boolean resultado = concesionario.crearAdministrador(nuevoAdministrador);

        // Verificar que el administrador haya sido creado exitosamente
        Assert.assertTrue(resultado);
        Assert.assertTrue(concesionario.getListaAdministradores().contains(nuevoAdministrador));
    }

    @Test(expected = AdministradorException.class)
    public void crearAdministrador_AdministradorExistente_LanzarExcepcion() throws AdministradorException {
        // Intentar crear un administrador existente debe lanzar una excepción
        Administrador nuevoAdministrador = new Administrador("adminNombre", "adminApellido", "1011", "adminPass");
        concesionario.crearAdministrador(nuevoAdministrador);
    }

    @Test
    public void eliminarAdministrador_AdministradorExistente_EliminarExitoso() throws AdministradorException {
        // Eliminar el administrador existente
        boolean resultado = concesionario.eliminarAdministrador(administrador);

        // Verificar que el administrador haya sido eliminado exitosamente
        Assert.assertTrue(resultado);
        Assert.assertFalse(concesionario.getListaAdministradores().contains(administrador));
    }

    @Test(expected = AdministradorException.class)
    public void eliminarAdministrador_AdministradorNoExistente_LanzarExcepcion() throws AdministradorException {
        // Intentar eliminar un administrador no existente debe lanzar una excepción
        Administrador administradorNoExistente = new Administrador("noExistente", "noExistente", "noExistente", "noExistente");
        concesionario.eliminarAdministrador(administradorNoExistente);
    }

    @Test
    public void actualizarAdministrador_AdministradorExistente_ActualizacionExitosa() throws AdministradorException {
        // Actualizar el administrador existente
        boolean resultado = concesionario.actualizarAdministrador("nuevoNombre", "nuevoApellido", "1011", "nuevoPass");

        // Verificar que el administrador haya sido actualizado exitosamente
        Assert.assertTrue(resultado);
        Assert.assertEquals("nuevoNombre", administrador.getNombre());
        Assert.assertEquals("nuevoApellido", administrador.getApellido());
        Assert.assertEquals("1011", administrador.getIdentificacion());
        Assert.assertEquals("nuevoPass", administrador.getCredenciales());
    }

    @Test(expected = AdministradorException.class)
    public void actualizarAdministrador_AdministradorNoExistente_LanzarExcepcion() throws AdministradorException {
        // Intentar actualizar un administrador no existente debe lanzar una excepción
        concesionario.actualizarAdministrador("nuevoNombre", "nuevoApellido", "noExistente", "nuevoPass");
    }

//------------------------------ J_UNITS CRUD EMPLEADO -------------------------------------------------------

    @Test
    public void verificarEmpleado_EmpleadoExistente_DeberiaRetornarTrue() {
        // Agregar empleados de prueba al concesionario
        Empleado empleado1 = new Empleado("Miguel","Florez","123");
        Empleado empleado2 = new Empleado("María","Carmen","456");
        concesionario.agregarEmpleado(empleado1);
        concesionario.agregarEmpleado(empleado2);

        // Verificar la existencia de un empleado válido
        boolean resultado = concesionario.verificarEmpleado("123");

        // Verificar que el empleado existente se verifique correctamente
        Assert.assertTrue(resultado);
    }


    @Test
    public void obtenerEmpleado_EmpleadoExistente_DeberiaRetornarEmpleadoCorrecto() {
        // Agregar empleados de prueba al concesionario
        Empleado empleado1 = new Empleado("Miguel","Florez","123");
        Empleado empleado2 = new Empleado("María","Carmen","456");
        concesionario.agregarEmpleado(empleado1);
        concesionario.agregarEmpleado(empleado2);

        // Obtener un empleado existente por su identificación
        Empleado resultado = concesionario.obtenerEmpleado("456");

        // Verificar que se obtenga el empleado correcto
        Assert.assertEquals(empleado2, resultado);
    }

    @Test
    public void obtenerEmpleado_EmpleadoNoExistente_DeberiaRetornarNull() {
        // Agregar empleados de prueba al concesionario
        Empleado empleado1 = new Empleado("Miguel","Florez","123");
        Empleado empleado2 = new Empleado("María","Carmen","456");
        concesionario.agregarEmpleado(empleado1);
        concesionario.agregarEmpleado(empleado2);

        // Obtener un empleado no existente por su identificación
        Empleado resultado = concesionario.obtenerEmpleado("789");

        // Verificar que se obtenga null al buscar un empleado no existente
        Assert.assertNull(resultado);
    }

    @Test
    public void crearEmpleado_EmpleadoCreadoExitosamente_DeberiaRetornarTrue() throws EmpleadoException, AdministradorException {
        // Crear un nuevo empleado
        Empleado nuevoEmpleado = new Empleado("nombre", "apellido", "1234");

        // Crear el empleado utilizando el administrador
        boolean resultado = concesionario.crearEmpleado(administrador, nuevoEmpleado);

        // Verificar que se haya creado el empleado exitosamente
        Assert.assertTrue(resultado);
        // Verificar que el empleado se agregue correctamente a la lista de empleados del concesionario
        Assert.assertTrue(concesionario.verificarEmpleado("1234"));
    }

    @Test(expected = AdministradorException.class)
    public void crearEmpleado_AdministradorNoExiste_DeberiaLanzarExcepcionAdministradorException() throws EmpleadoException, AdministradorException {
        // Crear un nuevo empleado
        Empleado nuevoEmpleado = new Empleado("nombre", "apellido", "1234");

        // Intentar crear el empleado sin un administrador válido
        concesionario.crearEmpleado(null, nuevoEmpleado);
    }

    @Test(expected = Exceptions.EmpleadoException.class)
    public void crearEmpleado_ErrorEnCrearEmpleado_DeberiaLanzarExcepcionEmpleadoException() throws EmpleadoException, AdministradorException {
        // Crear un nuevo empleado
        Empleado nuevoEmpleado = new Empleado("nombre", "apellido", "1234");

        try {
            // Intentar crear el empleado con un administrador que no puede crear empleados
            concesionario.crearEmpleado(administrador, nuevoEmpleado);

            // Si no se lanza una excepción, se considera una falla
            fail("Se esperaba que se lanzara la excepción EmpleadoException");
        } catch (Exceptions.EmpleadoException e) {
            // Excepción capturada, la prueba pasa exitosamente
        }
    }


    @Test
    public void eliminarEmpleado_EmpleadoExistenteEliminado_DeberiaRetornarTrue() throws AdministradorException, EmpleadoException {

    	// Crear un nuevo empleado
        Empleado empleadoEliminar = new Empleado("nombre", "apellido", "123");
        administrador.getListaEmpleados().add(empleadoEliminar);
        // Agregar el empleado al concesionario
        concesionario.agregarEmpleado(empleadoEliminar);

        // Eliminar el empleado utilizando el administrador
        boolean resultado = concesionario.eliminarEmpleado("1011", empleadoEliminar);

        // Verificar que se haya eliminado el empleado exitosamente
        Assert.assertTrue(resultado);
        // Verificar que el empleado se haya eliminado correctamente de la lista de empleados del concesionario
        Assert.assertFalse(concesionario.verificarEmpleado("1234"));
    }

    @Test(expected = AdministradorException.class)
    public void eliminarEmpleado_AdministradorNoExiste_DeberiaLanzarExcepcionAdministradorException() throws AdministradorException, EmpleadoException {
        // Crear un nuevo empleado
        Empleado empleadoEliminar = new Empleado("nombre", "apellido", "1234");

        // Intentar eliminar el empleado sin un administrador válido
        concesionario.eliminarEmpleado("admin", empleadoEliminar);
    }

    @Test(expected = EmpleadoException.class)
    public void eliminarEmpleado_ErrorEnEliminarEmpleado_DeberiaLanzarExcepcionEmpleadoException() throws AdministradorException, EmpleadoException {
        // Crear un nuevo empleado
        Empleado empleadoEliminar = new Empleado("nombre", "apellido", "1234");

        // Agregar el empleado al concesionario
        concesionario.agregarEmpleado(empleadoEliminar);

        // Intentar eliminar el empleado con un administrador que no puede eliminar empleados
        concesionario.eliminarEmpleado("1011", empleadoEliminar);
    }

    @Test
    public void actualizarEmpleado_EmpleadoExistenteActualizado_DeberiaRetornarTrue() throws AdministradorException, EmpleadoException {
        // Crear un nuevo empleado
        Empleado empleadoActualizar = new Empleado("nombre", "apellido", "1234");

        // Agregar el empleado al concesionario
        concesionario.agregarEmpleado(empleadoActualizar);
        administrador.getListaEmpleados().add(empleadoActualizar);

        // Actualizar el empleado utilizando el administrador
        boolean resultado = concesionario.actualizarEmpleado("1011", "nuevoNombre", "nuevoApellido", "1234");

        // Verificar que se haya actualizado el empleado exitosamente
        Assert.assertTrue(resultado);
        // Verificar que los datos del empleado se hayan actualizado correctamente
        Empleado empleadoActualizado = concesionario.obtenerEmpleado(empleadoActualizar.getIdentificacion());
        Assert.assertEquals("nuevoNombre", empleadoActualizado.getNombre());
        Assert.assertEquals("nuevoApellido", empleadoActualizado.getApellido());
    }


    @Test(expected = AdministradorException.class)
    public void actualizarEmpleado_AdministradorNoExiste_DeberiaLanzarExcepcionAdministradorException() throws AdministradorException, EmpleadoException {
        // Intentar actualizar el empleado sin un administrador válido
        concesionario.actualizarEmpleado("admin", "nuevoNombre", "nuevoApellido", "1234");
    }

    @Test(expected = EmpleadoException.class)
    public void actualizarEmpleado_ErrorEnActualizarEmpleado_DeberiaLanzarExcepcionEmpleadoException() throws AdministradorException, EmpleadoException {
        // Crear un nuevo empleado
        Empleado empleadoActualizar = new Empleado("nombre", "apellido", "1234");

        // Agregar el empleado al concesionario
        concesionario.agregarEmpleado(empleadoActualizar);

        // Intentar actualizar el empleado con un administrador que no puede actualizar empleados
        concesionario.actualizarEmpleado("1011", "nuevoNombre", "nuevoApellido", "1234");
    }

//------------------------------------------- J_UNITS CRUD CLIENTE ---------------------------------------------------

    @Test
    public void crearCliente_EmpleadoExistente_CrearExitoso() throws EmpleadoException, ClienteException {
        // Crear un nuevo cliente con un empleado existente
        Empleado empleadoCliente = empleado;
        Cliente nuevoCliente = new Cliente("nuevoNombre", "nuevoApellido", "nuevo123");

        // Agregar el cliente al empleado
        boolean resultado = concesionario.crearCliente(empleadoCliente.getIdentificacion(), nuevoCliente);

        // Verificar que el cliente haya sido creado exitosamente
        Assert.assertTrue(resultado);
        Assert.assertTrue(empleado.getListaClientes().contains(nuevoCliente));
    }

    @Test(expected = EmpleadoException.class)
    public void crearCliente_EmpleadoNoExistente_LanzarExcepcion() throws EmpleadoException, ClienteException {
        // Intentar crear un cliente con un empleado no existente debe lanzar una excepción
        Empleado empleadoNoExistente = new Empleado("noExistente", "noExistente", "noExistente");
        Cliente nuevoCliente = new Cliente("nuevoNombre", "nuevoApellido", "nuevo123");
        concesionario.crearCliente(empleadoNoExistente.getIdentificacion(), nuevoCliente);
    }

    @Test(expected = ClienteException.class)
    public void crearCliente_ClienteExistente_LanzarExcepcion() throws EmpleadoException, ClienteException {
        // Intentar crear un cliente existente debe lanzar una excepción
        Empleado empleadoCliente = empleado;
        Cliente clienteExistente = cliente;
        concesionario.crearCliente(empleadoCliente.getIdentificacion(), clienteExistente);
    }

    @Test
    public void eliminarCliente_EmpleadoExistente_EliminacionExitosa() throws EmpleadoException, ClienteException {
        // Eliminar el cliente existente con un empleado existente
        Empleado empleadoCliente = empleado;

        // Eliminar el cliente del empleado
        boolean resultado = concesionario.eliminarCliente(empleadoCliente.getIdentificacion(), cliente);

        // Verificar que el cliente haya sido eliminado exitosamente
        Assert.assertTrue(resultado);
        Assert.assertFalse(empleado.getListaClientes().contains(cliente));
    }

    @Test(expected = EmpleadoException.class)
    public void eliminarCliente_EmpleadoNoExistente_LanzarExcepcion() throws EmpleadoException, ClienteException {
        // Intentar eliminar un cliente con un empleado no existente debe lanzar una excepción
        Empleado empleadoNoExistente = new Empleado("noExistente", "noExistente", "noExistente");
        concesionario.eliminarCliente(empleadoNoExistente.getIdentificacion(), cliente);
    }

    @Test(expected = ClienteException.class)
    public void eliminarCliente_ClienteNoExistente_LanzarExcepcion() throws EmpleadoException, ClienteException {
        // Intentar eliminar un cliente no existente debe lanzar una excepción
        Empleado empleadoCliente = empleado;
        Cliente clienteNoExistente = new Cliente("noExistente", "noExistente", "noExistente");
        concesionario.eliminarCliente(empleadoCliente.getIdentificacion(), clienteNoExistente);
    }

    @Test
    public void actualizarCliente_EmpleadoExistente_ActualizacionExitosa() throws EmpleadoException, ClienteException {
        // Actualizar el cliente existente con un empleado existente
        Empleado empleadoCliente = empleado;

        // Actualizar los datos del cliente
        boolean resultado = concesionario.actualizarCliente(empleadoCliente.getIdentificacion(), "nuevoNombre", "nuevoApellido", "cliente123", "nuevaFecha");

        // Verificar que el cliente haya sido actualizado exitosamente
        Assert.assertTrue(resultado);
        Assert.assertEquals("nuevoNombre", cliente.getNombre());
        Assert.assertEquals("nuevoApellido", cliente.getApellido());
        Assert.assertEquals("cliente123", cliente.getIdentificacion());
        Assert.assertEquals("nuevaFecha", cliente.getFechaDeNacimiento());
    }

    @Test(expected = EmpleadoException.class)
    public void actualizarCliente_EmpleadoNoExistente_LanzarExcepcion() throws EmpleadoException, ClienteException {
        // Intentar actualizar un cliente con un empleado no existente debe lanzar una excepción
        Empleado empleadoNoExistente = new Empleado("noExistente", "noExistente", "noExistente");
        concesionario.actualizarCliente(empleadoNoExistente.getIdentificacion(), "nuevoNombre", "nuevoApellido", "cliente123", "nuevaFecha");
    }

    @Test(expected = ClienteException.class)
    public void actualizarCliente_ClienteNoExistente_LanzarExcepcion() throws EmpleadoException, ClienteException {
        // Intentar actualizar un cliente no existente debe lanzar una excepción
        Empleado empleadoCliente = empleado;
        concesionario.actualizarCliente(empleadoCliente.getIdentificacion(), "nuevoNombre", "nuevoApellido", "cliente456", "nuevaFecha");
    }

   //----------------------------------------------- JUnits de CRUD VEHICULOS ------------------------------------------

    @Test
    public void crearVehiculo_VehiculoNoRegistrado_CreacionExitosa() throws VehiculoException {
        // Crear un nuevo vehículo no registrado
        Vehiculo nuevoVehiculo = new Vehiculo("nuevaMarca", "nuevoModelo", "cambios", "velMaxima", "cilindraje",
            TipoTransmicion.AUTOMATICA, TipoCombustible.GASOLINA, TipoEstado.NUEVO, 20000.0,
            "autonomia", "tiempoCarga", false, true);
        // Agregar el vehículo al concesionario
        	concesionario.agregarVehiculo(nuevoVehiculo);

        // Verificar que el vehículo haya sido creado exitosamente
            Assert.assertTrue(concesionario.getListaVehiculos().contains(nuevoVehiculo));
    }

    @Test(expected = VehiculoException.class)
    public void crearVehiculo_VehiculoYaRegistrado_LanzarExcepcion() throws VehiculoException {
        // Intentar crear un vehículo ya registrado debe lanzar una excepción
        Vehiculo vehiculoExistente = vehiculo;
        concesionario.agregarVehiculo(vehiculoExistente);
    }

    @Test
    public void eliminarVehiculo_VehiculoRegistrado_EliminacionExitosa() throws VehiculoException {
        // Eliminar el vehículo registrado
        Vehiculo vehiculoEliminar = vehiculo;

        try {
            // Eliminar el vehículo del concesionario
            boolean resultado = concesionario.eliminarVehiculo(vehiculoEliminar);

            // Verificar que el vehículo haya sido eliminado exitosamente
            Assert.assertTrue(resultado);
            Assert.assertFalse(concesionario.getListaVehiculos().contains(vehiculoEliminar));
        } catch (VehiculoException e) {
            // Si se lanza una excepción, se considera una falla
            fail("No se esperaba que se lanzara la excepción VehiculoException");
        }
    }

    @Test(expected = VehiculoException.class)
    public void eliminarVehiculo_VehiculoNoRegistrado_LanzarExcepcion() throws VehiculoException {
        // Intentar eliminar un vehículo no registrado debe lanzar una excepción
        Vehiculo vehiculoNoRegistrado = new Vehiculo("noRegistrado", "noRegistrado", "cambios", "velMaxima", "cilindraje",
            TipoTransmicion.AUTOMATICA, TipoCombustible.GASOLINA, TipoEstado.NUEVO, 30000.0,
            "autonomia", "tiempoCarga", false, false);
        concesionario.eliminarVehiculo(vehiculoNoRegistrado);
    }

    @Test
    public void verificarVehiculo_VehiculoRegistrado_CoincidenciaEncontrada() {
        // Verificar un vehículo registrado
        String marca = "marca";
        String modelo = "modelo";

        // Verificar la existencia de coincidencia de vehículos en el concesionario
        boolean resultado = concesionario.getListaVehiculos().stream()
                .anyMatch(v -> v.getMarca().equals(marca) && v.getModelo().equals(modelo));

        // Verificar que se haya encontrado una coincidencia de vehículos
        Assert.assertTrue(resultado);
    }

    @Test
    public void verificarVehiculo_VehiculoNoRegistrado_CoincidenciaNoEncontrada() {
        // Verificar un vehículo no registrado
        String marca = "noRegistrado";
        String modelo = "noRegistrado";

        // Verificar la existencia de coincidencia de vehículos en el concesionario
        boolean resultado = concesionario.getListaVehiculos().stream()
                .anyMatch(v -> v.getMarca().equals(marca) && v.getModelo().equals(modelo));

        // Verificar que no se haya encontrado coincidencia de vehículos
        Assert.assertFalse(resultado);
    }

//--------------------------------------- JUNITS TRANSACCION ------------------------------------------

	@Test
	public void obtenerTransaccion_TransaccionExistente_DebeRetornarTransaccion() {
		// Agregar una transacción a la lista del concesionario
		concesionario.getListaTransacciones().add(transaccion);

		// Obtener la transacción por su código
		Transaccion transaccionEncontrada = concesionario.obtenerTransaccion("codigo");

		// Verificar que se haya encontrado la transacción correcta
		assertEquals(transaccion, transaccionEncontrada);
	}

	@Test
	public void obtenerTransaccion_TransaccionNoExistente_DebeRetornarNull() {
		// Obtener una transacción que no está en la lista del concesionario
		Transaccion transaccionEncontrada = concesionario.obtenerTransaccion("codigo");

		// Verificar que no se haya encontrado ninguna transacción
		assertNull(transaccionEncontrada);
	}

	@Test
	public void verificarTransaccion_TransaccionExistente_DebeRetornarTrue() {
		// Agregar una transacción a la lista del concesionario
		concesionario.getListaTransacciones().add(transaccion);

		// Verificar la existencia de la transacción por su código
		boolean encontrada = concesionario.verificarTransaccion("codigo");

		// Verificar que la transacción haya sido encontrada
		assertTrue(encontrada);
	}

	@Test
	public void verificarTransaccion_TransaccionNoExistente_DebeRetornarFalse() {
		// Verificar la existencia de una transacción que no está en la lista del concesionario
		boolean encontrada = concesionario.verificarTransaccion("codigo");

		// Verificar que la transacción no haya sido encontrada
		assertFalse(encontrada);
	}

	@Test
	public void crearTransaccion_TransaccionVenta_CreacionExitosa() throws EmpleadoException, TransaccionException, ClienteException {
		// Agregar el empleado y el cliente al concesionario
		concesionario.getListaEmpleados().add(empleado);
		concesionario.getListaClientes().add(cliente);

	    // Registrar al empleado en el concesionario
	    concesionario.agregarEmpleado(empleado);

		// Crear una transacción de venta
	    boolean resultado = concesionario.crearTransaccion(empleado.getIdentificacion(), TipoTransaccion.VENTA, "fecha", 1000.0,
	            "codigo", null, cliente.getIdentificacion());

		// Verificar que la transacción haya sido creada exitosamente
		assertTrue(resultado);
		assertEquals(1, concesionario.getListaTransacciones().size());
	}

	@Test(expected = EmpleadoException.class)
	public void crearTransaccion_EmpleadoNoRegistrado_LanzarExcepcionEmpleadoException()
			throws EmpleadoException, TransaccionException, ClienteException {
		// Crear una transacción sin tener el empleado registrado
		concesionario.crearTransaccion("identificacionEmpleado", TipoTransaccion.VENTA, "fecha", 1000.0, "codigo", null, "cliente123");
	}

	@Test(expected = ClienteException.class)
	public void crearTransaccion_ClienteNoRegistrado_LanzarExcepcionClienteException() throws EmpleadoException, TransaccionException, ClienteException {
	    // Agregar el empleado al concesionario
	    concesionario.getListaEmpleados().add(empleado);

	    // Crear una transacción sin tener el cliente registrado
	    concesionario.crearTransaccion("identificacionEmpleado", TipoTransaccion.VENTA, "fecha", 1000.0, "codigo", null, "cliente456");
	}

	@Test
	public void eliminarTransaccion_TransaccionExistente_EliminacionExitosa() throws TransaccionException {
		// Agregar una transacción a la lista del concesionario
		concesionario.getListaTransacciones().add(transaccion);

		// Eliminar la transacción
		boolean eliminado = concesionario.eliminarTransaccion(transaccion);

		// Verificar que la transacción haya sido eliminada exitosamente
		assertTrue(eliminado);
		assertEquals(0, concesionario.getListaTransacciones().size());
	}

	@Test(expected = TransaccionException.class)
	public void eliminarTransaccion_TransaccionNoExistente_LanzarExcepcionTransaccionException()
			throws TransaccionException {
		// Intentar eliminar una transacción que no está en la lista del concesionario
		concesionario.eliminarTransaccion(transaccion);
	}

// ----------------------------------- JUNITS VENTA Y ALQUILER VEHICULOS -------------------------------------------


	@Test
	public void venderVehiculo_VehiculoNoAlquilado_NoVendido() throws VehiculoException, EmpleadoException, VentaException {
	    boolean encontrado = false;
	    for (Vehiculo v : concesionario.getListaVehiculos()) {
	        if (v.equals(vehiculo)) {
	            encontrado = true;
	            break;
	        }
	    }

	    Assert.assertTrue(encontrado);
	}

	@Test(expected = VentaException.class)
	public void venderVehiculo_VehiculoAlquilado_DeberiaLanzarExcepcionVentaException() throws VehiculoException, EmpleadoException, VentaException, AlquilerException {
	    concesionario.alquilarVehiculo(empleado.getIdentificacion(), vehiculo);
	    concesionario.venderVehiculo(empleado.getIdentificacion(), vehiculo);
	}

	@Test(expected = VentaException.class)
	public void venderVehiculo_VehiculoYaVendido_DeberiaLanzarExcepcionVentaException() throws VehiculoException, EmpleadoException, VentaException {
	    concesionario.venderVehiculo(empleado.getIdentificacion(), vehiculo);
	}

    @Test
    public void alquilarVehiculo_VehiculoNoAlquilado_DeberiaAlquilarVehiculo() throws EmpleadoException, AlquilerException, VehiculoException {
        boolean resultado = concesionario.alquilarVehiculo(empleado.getIdentificacion(), vehiculo);
        Assert.assertTrue(resultado);
    }

    @Test(expected = AlquilerException.class)
    public void alquilarVehiculo_VehiculoYaAlquilado_DeberiaLanzarExcepcionAlquilerException() throws EmpleadoException, AlquilerException, VehiculoException {
        concesionario.alquilarVehiculo(empleado.getIdentificacion(), vehiculo);
        concesionario.alquilarVehiculo(empleado.getIdentificacion(), vehiculo);
    }

    @Test(expected = AlquilerException.class)
    public void alquilarVehiculo_VehiculoVendido_DeberiaLanzarExcepcionAlquilerException() throws VehiculoException, EmpleadoException, VentaException, AlquilerException {

    	concesionario.venderVehiculo(empleado.getIdentificacion(), vehiculo);

        concesionario.alquilarVehiculo(empleado.getIdentificacion(), vehiculo);
    }

// ----------------------------------- JUNITS REPORTES DE VEHICULOS -------------------------------------------


    @Test
    public void validarFechas_FechaInicialAntesDeFechaFinal_DebeRetornarTrue() throws ParseException {
        String fechaInicial = "01/01/2023";
        String fechaFinal = "31/12/2023";

        boolean resultado = concesionario.validarFechas(fechaInicial, fechaFinal);

        Assert.assertTrue(resultado);
    }

    @Test
    public void validarFechas_FechaInicialDespuesDeFechaFinal_DebeRetornarFalse() throws ParseException {
        String fechaInicial = "31/12/2023";
        String fechaFinal = "01/01/2023";

        boolean resultado = concesionario.validarFechas(fechaInicial, fechaFinal);

        Assert.assertFalse(resultado);
    }

    @Test
    public void getListaDatosReportes_TransaccionesDentroDeRangoFechas_DebeRetornarListaTransacciones() {
        ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
        Transaccion transaccion1 = new Transaccion("01/01/2023", 100.0, "001", TipoTransaccion.VENTA, null, "cliente123");
        Transaccion transaccion2 = new Transaccion("15/06/2023", 200.0, "002", TipoTransaccion.ALQUILER, "5", "cliente456");
        Transaccion transaccion3 = new Transaccion("30/11/2023", 300.0, "003", TipoTransaccion.VENTA, null, "cliente789");
        listaTransacciones.add(transaccion1);
        listaTransacciones.add(transaccion2);
        listaTransacciones.add(transaccion3);
        concesionario.setListaTransacciones(listaTransacciones);

        String fechaInicial = "01/06/2023";
        String fechaFinal = "31/12/2023";

        ArrayList<Transaccion> resultado = (ArrayList<Transaccion>) concesionario.getListaDatosReportes(fechaInicial, fechaFinal);

        Assert.assertEquals(2, resultado.size());
        Assert.assertTrue(resultado.contains(transaccion2));
        Assert.assertTrue(resultado.contains(transaccion3));
    }

    @Test
    public void getListaDatosReportes_FechaInicialDespuesDeFechaFinal_DebeRetornarListaVacia() {
        ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
        Transaccion transaccion1 = new Transaccion("01/01/2023", 100.0, "001", TipoTransaccion.VENTA, null, "cliente123");
        Transaccion transaccion2 = new Transaccion("15/06/2023", 200.0, "002", TipoTransaccion.ALQUILER, "5", "cliente456");
        Transaccion transaccion3 = new Transaccion("30/11/2023", 300.0, "003", TipoTransaccion.VENTA, null, "cliente789");
        listaTransacciones.add(transaccion1);
        listaTransacciones.add(transaccion2);
        listaTransacciones.add(transaccion3);
        concesionario.setListaTransacciones(listaTransacciones);

        String fechaInicial = "31/12/2023";
        String fechaFinal = "01/01/2023";

        List<Transaccion> resultado = concesionario.getListaDatosReportes(fechaInicial, fechaFinal);

        Assert.assertEquals(0, resultado.size());
    }

    @Test
    public void agregarVehiculo_VehiculoAgregadoCorrectamente() {
        Vehiculo vehiculo = new Vehiculo("Toyota", "Camry", "5", "200", "2000",
                TipoTransmicion.MANUAL, TipoCombustible.GASOLINA, TipoEstado.NUEVO, 50000.0,
                "500 km", "2 horas", false, false);
            concesionario.agregarVehiculo(vehiculo);

            Assert.assertTrue(concesionario.getListaVehiculos().contains(vehiculo));
    }

}
