package SistemaBancario.logica;

public interface SistemaBancario {
	
	
	
	public boolean ingresarPersona(String rut,String nombre,String apellido,String contraseña, int matriz[][]);
	
	public boolean ingresarCuentaCorriente(String numeroCuenta,String rutTitular,String contraseña,long saldo);

	public boolean ingresarCuentaChequeraElectronica(String numeroCuenta,String rutTitular,String contraseña,long saldo);
	
	public boolean verificarCuentaInicioSesion(String rut ,String contraseñaInicio);
	
	public boolean Depositar(long monto,String rut, String numeroCuenta);
	
	public boolean Girar(long monto ,String numeroCuenta ,String contraseña,String rut);
	
	public long obtenerSaldo(String numeroCuenta);
	
	public boolean Transferir(long monto,String numeroCuentaOrigen, String numeroCuentaDesti,int C1 ,int F1,int numero1,
			int C2 ,int F2,int numero2,int C3 ,int F3,int numero3);
	
	public String ObtenerInformacionCuenta(String rut);
	
	public boolean BloquearCuentas(String rut,String numeroCuenta,String contraseñaInicio ,String contraseñaCuenta);
	
	public boolean ActualizarContraseñaInicioSesion(String rut,String contraseñaActual,String contraseñaNueva);
	
	public boolean ActualizarContraseñaCuenta(String rut,String numeroCuenta,String contraseñaActual,String contraseñaNueva);
	

}
