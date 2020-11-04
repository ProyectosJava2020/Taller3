package SistemaBancario.dominio;

public  class Cuenta  {
	private String numeroCuenta;
	private String rutTitular;
	private String contraseñaCuenta;
	private long saldo;
	private static int minimoMontoTrans=5000;
	private static int montoGiro=500;
	
	
	public Cuenta(String numeroCuenta, String rutTitular, String contraseñaCuenta, long saldo) {
		this.numeroCuenta = numeroCuenta;
		this.rutTitular = rutTitular;
		this.contraseñaCuenta = contraseñaCuenta;
		this.saldo = saldo;
	}
	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	
	
		

	/**
	 * @return the minimoMontoTrans
	 */
	public static int getMinimoMontoTrans() {
		return minimoMontoTrans;
	}
	/**
	 * @param minimoMontoTrans the minimoMontoTrans to set
	 */
	public static void setMinimoMontoTrans(int minimoMontoTrans) {
		Cuenta.minimoMontoTrans = minimoMontoTrans;
	}
	/**
	 * @return the montoGiro
	 */
	public static int getMontoGiro() {
		return montoGiro;
	}
	/**
	 * @param montoGiro the montoGiro to set
	 */
	public static void setMontoGiro(int montoGiro) {
		Cuenta.montoGiro = montoGiro;
	}
	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	/**
	 * @return the rutTitular
	 */
	public String getRutTitular() {
		return rutTitular;
	}
	/**
	 * @param rutTitular the rutTitular to set
	 */
	public void setRutTitular(String rutTitular) {
		this.rutTitular = rutTitular;
	}
	/**
	 * @return the contraseñaCuenta
	 */
	public String getContraseñaCuenta() {
		return contraseñaCuenta;
	}
	/**
	 * @param contraseñaCuenta the contraseñaCuenta to set
	 */
	public void setContraseñaCuenta(String contraseñaCuenta) {
		this.contraseñaCuenta = contraseñaCuenta;
	}
	/**
	 * @return the saldo
	 */
	public long getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the estado
	 */
	
	//wena

}
