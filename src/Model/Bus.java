package Model;

import java.io.Serializable;

public class Bus extends Vehiculo implements Serializable{
    private String num_pasajeros;
    private String num_Puertas;
    private String cap_Maletero;
    private String num_ejes;
    private String num_salidas_emergencia;
    private String num_Bolsas;
    private boolean aire_Acondicionado;
    private boolean cam_Reversa;
    private boolean vel_Crucero;
    private boolean abs;


    /**
     *
     */
    public Bus(){
    }

    /**  CONSTRUCTOR CON ATRIBUTOS DE LA SUPER CLASE + LOS ATRIBUTOS PROPIOS
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
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param vel_Crucero
	 * @param num_Bolsas
	 * @param abs
	 * @param num_ejes
	 * @param num_salidas_emergencia
	 */
	public Bus(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			boolean vel_Crucero, String num_Bolsas, boolean abs, String num_ejes, String num_salidas_emergencia) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia,
				tiempoPromedioCarga, esEnchufable, esHibridoLigero);

		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.cap_Maletero = cap_Maletero;
		this.aire_Acondicionado = aire_Acondicionado;
		this.cam_Reversa = cam_Reversa;
		this.vel_Crucero = vel_Crucero;
		this.num_Bolsas = num_Bolsas;
		this.abs = abs;
		this.num_ejes = num_ejes;
		this.num_salidas_emergencia = num_salidas_emergencia;
	}





	/**
     * CONSTRUCTOR CON ATRIBUTOS PROPIOS
     * @param num_pasajeros
     * @param num_Puertas
     * @param cap_Maletero
     * @param aire_Acondicionado
     * @param cam_Reversa
     * @param vel_Crucero
     * @param num_Bolsas
     * @param abs
     * @param num_ejes
     * @param num_salidas_emergencia
     */
    public Bus(String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado,
    		boolean cam_Reversa, boolean vel_Crucero, String num_Bolsas, boolean abs, String num_ejes,
    		String num_salidas_emergencia) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.cap_Maletero = cap_Maletero;
        this.aire_Acondicionado = aire_Acondicionado;
        this.cam_Reversa = cam_Reversa;
        this.vel_Crucero = vel_Crucero;
        this.num_Bolsas = num_Bolsas;
        this.abs = abs;
        this.num_ejes = num_ejes;
        this.num_salidas_emergencia = num_salidas_emergencia;
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

	public String getCap_Maletero() {
		return cap_Maletero;
	}

	public void setCap_Maletero(String cap_Maletero) {
		this.cap_Maletero = cap_Maletero;
	}

	public String getNum_ejes() {
		return num_ejes;
	}

	public void setNum_ejes(String num_ejes) {
		this.num_ejes = num_ejes;
	}

	public String getNum_salidas_emergencia() {
		return num_salidas_emergencia;
	}

	public void setNum_salidas_emergencia(String num_salidas_emergencia) {
		this.num_salidas_emergencia = num_salidas_emergencia;
	}

	public String getNum_Bolsas() {
		return num_Bolsas;
	}

	public void setNum_Bolsas(String num_Bolsas) {
		this.num_Bolsas = num_Bolsas;
	}

	public boolean isAire_Acondicionado() {
		return aire_Acondicionado;
	}

	public void setAire_Acondicionado(boolean aire_Acondicionado) {
		this.aire_Acondicionado = aire_Acondicionado;
	}

	public boolean isCam_Reversa() {
		return cam_Reversa;
	}

	public void setCam_Reversa(boolean cam_Reversa) {
		this.cam_Reversa = cam_Reversa;
	}

	public boolean isVel_Crucero() {
		return vel_Crucero;
	}

	public void setVel_Crucero(boolean vel_Crucero) {
		this.vel_Crucero = vel_Crucero;
	}

	public boolean isAbs() {
		return abs;
	}

	public void setAbs(boolean abs) {
		this.abs = abs;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bus other = (Bus) obj;
        if (num_ejes != other.num_ejes)
            return false;
        if (num_salidas_emergencia != other.num_salidas_emergencia)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Bus \nNumero de pasajeros: " + num_pasajeros + "\nNumero de puertas: " + num_Puertas + "\nCapacidad del maletero: " + cap_Maletero + "\nAire acondicionado: " + aire_Acondicionado + "\nCamara de reversa: " + cam_Reversa + "Tiene velocidad de crucero?: "+ vel_Crucero + ", num_Bolsas=" + num_Bolsas + ", abs=" + abs + ", num_ejes=" + num_ejes + "\nCantidad de salidas de emergencia=" + num_salidas_emergencia;
    }



}
