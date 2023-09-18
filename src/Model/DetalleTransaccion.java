package Model;

public class DetalleTransaccion {
	private String codigo;
	private double subTotal;
	private int cantidad;
	/**
	 * @param codigo
	 * @param subTotal
	 * @param cantidad
	 */
	public DetalleTransaccion(String codigo, double subTotal, int cantidad) {
		super();
		this.codigo = codigo;
		this.subTotal = subTotal;
		this.cantidad = cantidad;
	}
	/**
	 *
	 */
	public DetalleTransaccion() {
		super();
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "DetalleTransaccion \nCodigo: " + codigo + ", subTotal=" + subTotal + ", cantidad=" + cantidad + "]";
	}

}
