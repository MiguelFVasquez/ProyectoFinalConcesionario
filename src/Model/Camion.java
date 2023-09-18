package Model;

import java.io.Serializable;

public class Camion extends Vehiculo implements Serializable {
    private boolean aire_Acondicionado;
    private boolean abs;
    private String  num_ejes;
    private String tipo_Camion;



    public Camion(){
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
	 * @param aire_Acondicionado
	 * @param abs
	 * @param num_ejes
	 * @param tipo_Camion
	 */
	public Camion(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			boolean aire_Acondicionado, boolean abs, String num_ejes, String tipo_Camion) {
		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia,
				tiempoPromedioCarga, esEnchufable, esHibridoLigero);
		this.aire_Acondicionado = aire_Acondicionado;
		this.abs = abs;
		this.num_ejes = num_ejes;
		this.tipo_Camion = tipo_Camion;
	}

	/**
     * CONSTRUCTOR CON ATRIBUTOS PROPIOS
     * @param aire_Acondicionado
     * @param abs
     * @param num_ejes
     * @param tipo_Camion
     */
    public Camion(boolean aire_Acondicionado, boolean abs, String num_ejes, String tipo_Camion) {
        this.aire_Acondicionado = aire_Acondicionado;
        this.abs = abs;
        this.num_ejes = num_ejes;
        this.tipo_Camion = tipo_Camion;
    }



	public boolean isAire_Acondicionado() {
        return aire_Acondicionado;
    }

    public void setAire_Acondicionado(boolean aire_Acondicionado) {
        this.aire_Acondicionado = aire_Acondicionado;
    }

    public boolean isAbs() {
        return abs;
    }

    public void setAbs(boolean abs) {
        this.abs = abs;
    }

    public String getNum_ejes() {
		return num_ejes;
	}

	public void setNum_ejes(String num_ejes) {
		this.num_ejes = num_ejes;
	}

	public String getTipo_Camion() {
        return tipo_Camion;
    }

    public void setTipo_Camion(String tipo_CamionM) {
        this.tipo_Camion = tipo_CamionM;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tipo_Camion == null) ? 0 : tipo_Camion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Camion other = (Camion) obj;
        if (tipo_Camion == null) {
            if (other.tipo_Camion != null)
                return false;
        } else if (!tipo_Camion.equals(other.tipo_Camion))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Camion \nAire acondicionado=" + aire_Acondicionado + "\n Tiene ABS: " + abs + "\n Cantidad de ejes: " + num_ejes + "\nTipo de camion=" + tipo_Camion;
    }



}
