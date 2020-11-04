package SistemaBancario.logica;
import ucn.*;
import SistemaBancario.dominio.*;
public class ListaPersona {
	private Persona [] lp;
	private int max;
	private int cant;
	
	
	public ListaPersona(int max) {
		lp = new Persona[max];
		cant = 0;
		this.max = max;
		
	}
	public boolean IngresarPersona(Persona persona) {
		if (cant < max) {
			lp[cant] = persona;
			cant++;
			return true;
		}
		else{
			return false;
		}
	}
	public Persona getPersona(int i) {
		if(i >=0 && i < cant) {
			return lp[i];
		}
		else {
			return null;
		}
	}
	public int GetCantidadPersona() {
		return cant;
	}
	public Persona BuscarPersona(String rut) {
		int i = 0;
		for(i = 0;i < cant ; i++) {
			if(lp[i].getRut().equals(rut)) {
				break;
			}
		}
		if(i == cant) {
			return null;
		}
		return lp[i];
	}
	@Override
	public String toString() {
		return "ListaPersona [max=" + max + ", cant=" + cant + "]";
	}

	
}
