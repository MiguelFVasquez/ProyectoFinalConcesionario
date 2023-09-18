package Model;

import java.io.Serializable;

public class Deportivo extends Vehiculo implements Serializable {
    private String num_pasajeros;
    private String num_Puertas;
    private String num_Bolsas;
    private String num_Caballos_Fuerza;
    private String tiempo_en_100KM;



    /**
     * CONSTRUCTOR CON ATRIBUTOS PROPIO
     * @param num_pasajeros
     * @param num_Puertas
     * @param num_Bolsas
     * @param num_Caballos_Fuerza
     * @param tiempo_en_100KM
     */
    public Deportivo(String num_pasajeros, String num_Puertas, String num_Bolsas, String num_Caballos_Fuerza,
            String tiempo_en_100KM) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.num_Bolsas = num_Bolsas;
        this.num_Caballos_Fuerza = num_Caballos_Fuerza;
        this.tiempo_en_100KM = tiempo_en_100KM;
    }

	/** CONSTRUCTOR CON ATRIBUTOS DE LA SUPER CLASE + LOS ATRIBUTOS PROPIOS
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
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param num_Bolsas
	 * @param num_Caballos_Fuerza
	 * @param tiempo_en_100KM
	 */
	public Deportivo(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String num_Bolsas, String num_Caballos_Fuerza, String tiempo_en_100KM) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia,
				tiempoPromedioCarga, esEnchufable, esHibridoLigero);
		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.num_Bolsas = num_Bolsas;
		this.num_Caballos_Fuerza = num_Caballos_Fuerza;
		this.tiempo_en_100KM = tiempo_en_100KM;
	}



    public String getNum_pasajeros() {
		return num_pasajeros;
	}

	public void setNum_pasajeros(String num_pasajeros) {
		this.num_pasajeros = num_pasajeros;
	}

	public String getNum_Puertas() {
		return num_Puertas;
	}

	public void setNum_Puertas(String num_Puertas) {
		this.num_Puertas = num_Puertas;
	}

	public String getNum_Bolsas() {
		return num_Bolsas;
	}

	public void setNum_Bolsas(String num_Bolsas) {
		this.num_Bolsas = num_Bolsas;
	}

	public String getNum_Caballos_Fuerza() {
		return num_Caballos_Fuerza;
	}

	public void setNum_Caballos_Fuerza(String num_Caballos_Fuerza) {
		this.num_Caballos_Fuerza = num_Caballos_Fuerza;
	}

	public String getTiempo_en_100KM() {
		return tiempo_en_100KM;
	}

	public void setTiempo_en_100KM(String tiempo_en_100KM) {
		this.tiempo_en_100KM = tiempo_en_100KM;
	}

	@Override
    public String toString() {
        return super.toString()+"Deportivo \nNumero de pasajeros:" + num_pasajeros + ", \nNumero de puertas:" + num_Puertas + "\nNumero de bolsas"+ num_Bolsas + "\nCaballos de fuerza" + num_Caballos_Fuerza + "\nTiempo en el que alcanza los 100 km/h" + tiempo_en_100KM;
    }




}
