package SistemaBancario.logica;

import SistemaBancario.dominio.CuentaChequeraElectronica;
import SistemaBancario.dominio.CuentaCorriente;
import SistemaBancario.logica.*;
import SistemaBancario.dominio.*;
public class ListaCuenta {
	private Cuenta [] lc;
	private int max;
	private int cant;
	//
	
	public ListaCuenta(int max) {
		lc = new Cuenta[max];
		cant = 0;
		this.max = max;
		
	}
	public boolean IngresarCuenta(Cuenta cuenta) {
		if (cant < max) {
			lc[cant] = cuenta;
			cant++;
			return true;
		}
		else{
			return false;
		}
	}
	public Cuenta getCuenta(int i) {
		if(i >=0 && i < cant) {
			return lc[i];
		}
		else {
			return null;
		}
	}
	public int GetCantidadCuentas() {
		return cant;
	}
	public Cuenta BuscarCuenta(String numero) {
		int i = 0;
		for(i = 0;i < cant ; i++) {
			if(lc[i].getNumeroCuenta().equals(numero)) {
				break;
			}
		}
		if(i == cant) {
			return null;
		}
		return lc[i];
	}
	
	
	
	public boolean EliminarCuenta(String numero) {
		int i = 0;
		for(i = 0; i< cant ; i++){
			if(lc[i].getNumeroCuenta().equals(numero)) {
				break;
			}
		}
		if(i == cant) {
			return false;
		}
		else {
			for(int k = i ; k<cant-1 ;k++) {
				lc[k] = lc[k+1];
			}
			cant--;
			return true;
		}
	}
	
	
	@Override
	public String toString() {
		String salida = "";
		int j = 0;
		for(j = 0; j < cant ; j++) {
			Cuenta cuenta = getCuenta(j);
			
			if (cuenta instanceof CuentaCorriente ) {
				CuentaCorriente cuentacorriente = (CuentaCorriente) cuenta;
				salida +="\n"+cuentacorriente.toString();
			}
			else{
				CuentaChequeraElectronica chequera = (CuentaChequeraElectronica) cuenta;
				salida += chequera.toString();
			}
			
		}
		return salida;
		
	}

	



}
