package Model;

import java.io.Serializable;

/**
 * Vehiculos es la clase padre de todo lo relacionado con los vehiculos
 */
public class Vehiculo implements Serializable{
    //Atributos
    private String marca;
    private String modelo;
    private String cambios;
    private String velMaxima;
    private String cilindraje;
    private TipoTransmicion transmicion;
    private TipoCombustible combustible;
    private TipoEstado estado;
    private Double precio;
	private TipoVehiculo tipoVehiculo;
    //Si es un vehiculo electrico
    private String autonomia;
    private String tiempoPromedioCarga;

    //Si es un vehiculo hibirido
    private boolean esEnchufable;
    //Si no es enchufable
    private boolean esHibridoLigero;


    public Vehiculo() {
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
	public Vehiculo(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero) {
		this.marca = marca;
		this.modelo = modelo;
		this.cambios = cambios;
		this.velMaxima = velMaxima;
		this.cilindraje = cilindraje;
		this.transmicion = transmicion;
		this.combustible = combustible;
		this.estado = estado;
		this.precio = precio;
		this.autonomia = autonimia;
		this.tiempoPromedioCarga = tiempoPromedioCarga;
		this.esEnchufable = esEnchufable;
		this.esHibridoLigero = esHibridoLigero;
	}






	public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }



    public String getVelMaxima() {
		return velMaxima;
	}

	public void setVelMaxima(String velMaxima) {
		this.velMaxima = velMaxima;
	}

	public String getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(String autonomia) {
		this.autonomia = autonomia;
	}

	public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }



    public TipoTransmicion getTransmicion() {
		return transmicion;
	}

	public void setTransmicion(TipoTransmicion transmicion) {
		this.transmicion = transmicion;
	}

	public TipoCombustible getCombustible() {
		return combustible;
	}

	public void setCombustible(TipoCombustible combustible) {
		this.combustible = combustible;
	}

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}




	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getAutonimia() {
		return autonomia;
	}

	public void setAutonimia(String autonimia) {
		this.autonomia = autonimia;
	}

	public String getTiempoPromedioCarga() {
		return tiempoPromedioCarga;
	}

	public void setTiempoPromedioCarga(String tiempoPromedioCarga) {
		this.tiempoPromedioCarga = tiempoPromedioCarga;
	}

	public boolean isEsEnchufable() {
		return esEnchufable;
	}

	public void setEsEnchufable(boolean esEnchufable) {
		this.esEnchufable = esEnchufable;
	}

	public boolean isEsHibridoLigero() {
		return esHibridoLigero;
	}

	public void setEsHibridoLigero(boolean esHibridoLigero) {
		this.esHibridoLigero = esHibridoLigero;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	@Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nCambios: " + cambios + "\nVelocidad maxima: " + velMaxima
                + "\nCilindraje: " + cilindraje+ "\nTipo de combustible: "+ combustible+ "\nTipo de transmicion: "+ transmicion
                + "\nEstado: "+ estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (modelo == null) {
            if (other.modelo != null)
                return false;
        } else if (!modelo.equals(other.modelo))
            return false;
        return true;
    }





}
