package SistemaBancario.dominio;

public class CuentaCorriente extends Cuenta{
	private long limiteDinero=2000000;
	private static boolean estado=true;
	
	
	
	public CuentaCorriente(String numeroCuenta, String rutTitular, String contraseñaCuenta, long saldo) {
		super(numeroCuenta,rutTitular,contraseñaCuenta,saldo);
	}//hola
	/**
	 * @return the estado
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	
	
	/**
	 * @return the limiteDinero
	 */
	public long LimiteDinero() {
		return limiteDinero;
	}

	/**
	 * @param limiteDinero the limiteDinero to set
	 */
	public void setLimiteDinero(long limiteDinero) {
		this.limiteDinero = limiteDinero;
	}


	public String toString() {
		String r="";
		if(estado) {
			r+="CuentaCorriente\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Activo ContraseñaCuenta:"
					+getContraseñaCuenta()+" Saldo:"+getSaldo();
		}
		else {
			r+="\n CuentaCorriente\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Bloqueada ContraseñaCuenta:"
					+getContraseñaCuenta()+" Saldo:"+getSaldo();
		}
		return r;
	
	}

}
