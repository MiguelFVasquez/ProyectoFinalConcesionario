package Model;

import java.io.Serializable;
/**
 *
 */
public class Camioneta extends Sedan  implements Serializable{
    private boolean esCuatroxCuatro;

    //constructor vacio
    public Camioneta(){
    }
    //Constructor con atributos propios
    public boolean isEsCuatroxCuatro() {
        return esCuatroxCuatro;
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
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param num_Bolsas
	 * @param abs
	 * @param sen_Colision
	 * @param sen_Trafico_Cruzado
	 * @param asistente_Carril
	 * @param esCuatroxCuatro
	 */
	public Camioneta(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril,
			boolean esCuatroxCuatro) {


		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia,
				tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero,
				aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril);
		this.esCuatroxCuatro = esCuatroxCuatro;
	}



    public void setEsCuatroxCuatro(boolean esCuatroxCuatro) {
		this.esCuatroxCuatro = esCuatroxCuatro;
	}
	@Override
    public String toString() {
        return super.toString()+"\nEs 4x4: " + esCuatroxCuatro;
    }


}
