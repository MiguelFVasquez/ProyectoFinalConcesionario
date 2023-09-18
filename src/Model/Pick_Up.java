package Model;

import java.io.Serializable;

public class Pick_Up extends Vehiculo implements Serializable{

    private String  num_pasajeros;
    private String num_Puertas;
    private String num_Bolsas;
    private String capacidadCarga;
    private boolean aire_Acondicionado;
    private boolean cam_Reversa;
    private boolean vel_Crucero;
    private boolean abs;
    private boolean esCuatroPorCuatro;

    public Pick_Up(){
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
	 * @param capacidadCarga
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param vel_Crucero
	 * @param abs
	 * @param esCuatroPorCuatro
	 */
	public Pick_Up(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String num_Bolsas, String capacidadCarga, boolean aire_Acondicionado,
			boolean cam_Reversa, boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia,
				tiempoPromedioCarga, esEnchufable, esHibridoLigero);

		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.num_Bolsas = num_Bolsas;
		this.capacidadCarga = capacidadCarga;
		this.aire_Acondicionado = aire_Acondicionado;
		this.cam_Reversa = cam_Reversa;
		this.vel_Crucero = vel_Crucero;
		this.abs = abs;
		this.esCuatroPorCuatro = esCuatroPorCuatro;
	}

	/**
     * CONSTRUCTOR CON LAS VARIABLES PROPIAS
     * @param num_pasajeros
     * @param num_Puertas
     * @param num_Bolsas
     * @param capacidadCarga
     * @param aire_Acondicionado
     * @param cam_Reversa
     * @param vel_Crucero
     * @param abs
     * @param esCuatroPorCuatro
     */
	public Pick_Up(String  num_pasajeros, String num_Puertas, String num_Bolsas, String capacidadCarga, boolean aire_Acondicionado,
            boolean cam_Reversa, boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.num_Bolsas = num_Bolsas;
        this.capacidadCarga = capacidadCarga;
        this.aire_Acondicionado = aire_Acondicionado;
        this.cam_Reversa = cam_Reversa;
        this.vel_Crucero = vel_Crucero;
        this.abs = abs;
        this.esCuatroPorCuatro = esCuatroPorCuatro;
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

	public String getCapacidadCarga() {
		return capacidadCarga;
	}

	public void setCapacidadCarga(String capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
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

    public boolean isEsCuatroPorCuatro() {
        return esCuatroPorCuatro;
    }

    public void setEsCuatroPorCuatro(boolean esCuatroPorCuatro) {
        this.esCuatroPorCuatro = esCuatroPorCuatro;
    }


    @Override
    public String toString() {
        return super.toString()+"Pick_Up \nNumero de pasajeros" + num_pasajeros + "\nNumero de puertas" + num_Puertas + "\nCantidads de bolsas de aire" + num_Bolsas + "\nCapacidad de carga: " + capacidadCarga + "\nAire acondicionado" + aire_Acondicionado + "\nCamara de reversa: " + cam_Reversa + "\nTiene velocidad de crucero" + vel_Crucero + "\nABS: " + abs + "\nEs 4x4: "+ esCuatroPorCuatro;
    }




}