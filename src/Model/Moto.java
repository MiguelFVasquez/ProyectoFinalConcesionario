package Model;

import java.io.Serializable;

public class Moto extends Vehiculo implements Serializable {

    public Moto() {
    }

	/**
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
	 */
	public Moto(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero) {
		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia,
				tiempoPromedioCarga, esEnchufable, esHibridoLigero);
		// TODO Auto-generated constructor stub
	}




}
