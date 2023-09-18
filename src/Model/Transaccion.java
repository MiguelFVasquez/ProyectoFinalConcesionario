package Model;

import java.util.ArrayList;
import java.util.List;

public class Transaccion {
	private String fecha;
	private double total;
	private String codigo;
	private Vehiculo vehiculoTransaccion;
	private TipoTransaccion tipoTransaccion;
	private String cantDias;
	private String cedulaCliente;
	private List<DetalleTransaccion> listaDetalles= new ArrayList<>();

	/**
	 * @param fecha
	 * @param total
	 * @param codigo
	 * @param tipoTransaccion
	 */
	public Transaccion(String fecha, double total, String codigo, TipoTransaccion tipoTransaccion, String cantDias, String cedulaCliente) {
		super();
		this.fecha = fecha;
		this.total = total;
		this.codigo = codigo;
		this.tipoTransaccion = tipoTransaccion;
		this.cantDias= cantDias;
		this.cedulaCliente= cedulaCliente;
	}


	/**
	 * @param fecha
	 * @param total
	 * @param codigo
	 * @param vehiculoTransaccion
	 * @param tipoTransaccion
	 * @param cantDias
	 * @param cedulaCliente
	 */
	public Transaccion(String fecha, double total, String codigo, Vehiculo vehiculoTransaccion, TipoTransaccion tipoTransaccion, String cantDias, String cedulaCliente) {
		super();
		this.fecha = fecha;
		this.total = total;
		this.codigo = codigo;
		this.vehiculoTransaccion = vehiculoTransaccion;
		this.tipoTransaccion = tipoTransaccion;
		this.cantDias = cantDias;
		this.cedulaCliente = cedulaCliente;
	}


	/**
	 * @param fecha
	 * @param total
	 * @param codigo
	 * @param vehiculoPrestado
	 * @param listaDetalles
	 */
	public Transaccion(String fecha, double total, String codigo, Vehiculo vehiculoPrestado, List<DetalleTransaccion> listaDetalles) {
		super();
		this.fecha = fecha;
		this.total = total;
		this.codigo = codigo;
		this.vehiculoTransaccion = vehiculoPrestado;
		this.listaDetalles = listaDetalles;
	}


	/**
	 *
	 */
	public Transaccion() {
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Vehiculo getVehiculoTransaccion() {
		return vehiculoTransaccion;
	}

	public void setVehiculoTransaccion(Vehiculo vehiculoTransaccion) {
		this.vehiculoTransaccion = vehiculoTransaccion;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}


	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getCantDias() {
		return cantDias;
	}


	public void setCantDias(String cantDias) {
		this.cantDias = cantDias;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}


	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}


	public List<DetalleTransaccion> getListaDetalles() {
		return listaDetalles;
	}
	public void setListaDetalles(List<DetalleTransaccion> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}


	@Override
	public String toString() {
		return "Transaccion \nFecha: " + fecha + "\nTotal: " + total + "\nCodigo: " + codigo + "\nlista Detalles:"
				+ listaDetalles;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Transaccion other = (Transaccion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



}
