package SistemaBancario.logica;

import SistemaBancario.dominio.*;

public class SistemaBancarioImpl implements SistemaBancario {
	private ListaPersona listaPersonas;
	private ListaCuenta listaCuentas;
	public SistemaBancarioImpl() {
		listaPersonas=new ListaPersona(100);
		listaCuentas=new ListaCuenta(100);

	}
	/**
	 * this method create persons object for insert peopleList general.
	 */
	public boolean ingresarPersona(String rut,String nombre,String apellido,String contraseña, int matriz[][]) {
		Persona persona=new Persona(rut,nombre,apellido,contraseña,matriz);
		return listaPersonas.IngresarPersona(persona);
	}
	/*
	 * this method search if account is general list and associate account with persons. 
	 */
	public boolean ingresarCuentaCorriente(String numeroCuenta,String rutTitular,String contraseña,long saldo) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rutTitular);
		if(cuenta == null) {
			Cuenta cuentaC=new CuentaCorriente(numeroCuenta,rutTitular,contraseña,saldo);
			persona.getListaCuenta().IngresarCuenta(cuentaC);
			return listaCuentas.IngresarCuenta(cuentaC);
		}
		else{
			throw new NullPointerException("Posible Hackeo de la cuenta " +cuenta.getNumeroCuenta()+" con propietario "+cuenta.getRutTitular());
		}
	}
	/**
	 * this method search if account is general list and associate account with persons
	 */
	public boolean ingresarCuentaChequeraElectronica(String numeroCuenta,String rutTitular,String contraseña,long saldo) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rutTitular);
		if(cuenta == null) {
			Cuenta cuentaChequeraE=new CuentaChequeraElectronica(numeroCuenta,rutTitular,contraseña,saldo);
			persona.getListaCuenta().IngresarCuenta(cuentaChequeraE);
			return listaCuentas.IngresarCuenta(cuentaChequeraE);
		}
		else {
			throw new NullPointerException("Posible Hackeo");
		}
		
	}
	/**
	 * this method check if the login password and the person is in the general list.
	 */
	public boolean verificarCuentaInicioSesion(String rut ,String contraseñaInicio) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(persona==null) {
			throw new NullPointerException("la persona no existe");
		
		}
		else {
			if(persona.getContraseñaInicio().equals(contraseñaInicio)) {
				return true;
			}
			else {
				throw new IllegalArgumentException("contraseña incorrecta");
			}
				
		}
	}
	/**
	 * This method  must request the amount and the account number to which
		you want to deposit
	 */
	public boolean Depositar(long monto,String rut, String numeroCuenta) {
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		if(rut.equals(cuenta.getRutTitular())) {
			if(cuenta instanceof CuentaCorriente) {
				CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
				if(cuentaC.getEstado()) {//if account is enable
					if((cuentaC.getSaldo()+monto)<cuentaC.LimiteDinero()) {
						cuentaC.setSaldo(monto+(cuentaC.getSaldo()));
						return true;
					}
					return false;
				}
				else {
					throw new IllegalArgumentException("tarjeta bloqueada");
					
				}
				
			}
			else {
				CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
				if(cuentaE.getEstado()) {
					cuentaE.setSaldo(monto+(cuentaE.getSaldo()));
					return true;
				}
				else {
					throw new IllegalArgumentException("Tarjeta Bloqueada");
				}
			}
			
		}
		else {
			throw new IllegalArgumentException("cuenta no es del propietario"); 
		}
		
	}
	/**
	 * The user must choose the account to make the turn, and 
		request the amount and password for verify.
	 */
	public boolean Girar(long monto ,String numeroCuenta ,String contraseña,String rut) {
        Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
        String rutTitular1 = cuenta.getRutTitular();
        String contraseñaCuenta = cuenta.getContraseñaCuenta();
        if(cuenta!=null && rutTitular1.equals(rut)){
        	if(cuenta instanceof CuentaCorriente) {
        		CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
        		if(cuentaC.getEstado()) {//if account is enable
		        	if(contraseñaCuenta.equals(contraseña)) {
		        		if(monto<cuenta.getSaldo()&&monto>cuentaC.getMinimoMontoTrans()) {
		        			cuenta.setSaldo(cuenta.getSaldo()-(monto+(cuenta.getMontoGiro())));
		        			return true;
		        		}
		        		return false;
		        	}
		        	else {
		        		throw new IllegalArgumentException("contraseña de cuenta incorrecta");
		        	}
        		}
        		else {
        			throw new IllegalArgumentException("Cuenta Bloqueada ");
        		}
        	}
        	else {
        		CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
        		if(cuentaE.getEstado()) {
        			if(contraseñaCuenta.equals(contraseña)) {
		        		if(monto<cuenta.getSaldo()&&monto>cuentaE.getMinimoMontoTrans()) {
		        			cuenta.setSaldo(cuenta.getSaldo()-(monto+(cuenta.getMontoGiro())));
		        			return true;
		        		}
		        		return false;
		        	}
		        	else {
		        		throw new IllegalArgumentException("contraseña de cuenta incorrecta");
		        	}
        		}
        		else {
        			throw new IllegalArgumentException("Cuenta Bloqueada");
        		}
        		
        	}
        	
        }
        else {
        	if(!rutTitular1.equals(rut)) {
        		throw new IllegalArgumentException("cuenta no es del propietario");	
        	}
        	else {
        		throw new NullPointerException("Cuenta no existe");	
        	}
        	
        }
        	
    }
	/**
	 * this method get amount of money in the account entered.
	 */
	public long obtenerSaldo(String numeroCuenta) {
		 Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		 if(cuenta==null) {
			 throw new NullPointerException("cuenta no existe");
		 }
		 else {
			 return cuenta.getSaldo();
		 }
	}
	/***
	 * The user must choose the account to make the transfer,
       Once verified, the request amount and account number and check the numbers
        from dynamic transfer key.
	 */
	public boolean Transferir(long monto,String numeroCuentaOrigen, String numeroCuentaDesti,int C1 ,int F1,int numero1,
			int C2,int F2,int numero2,int C3,int F3,int numero3) {
		 Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuentaDesti);
		 Cuenta cuentaO = listaCuentas.BuscarCuenta(numeroCuentaOrigen);
		 Persona persona=listaPersonas.BuscarPersona(cuentaO.getRutTitular());
		 if(cuenta==null||cuentaO==null) {
			 throw new NullPointerException("cuenta de origen y/o destinatario no existe");
		 }
		 else {
			 int [][]tarjetaCoordenadas=persona.getTarjetaCoordenadas();//get 
			 if(tarjetaCoordenadas[F1][C1]==numero1&&tarjetaCoordenadas[F2][C2]==numero2&&tarjetaCoordenadas[F3][C3]==numero3) {
				if(cuenta instanceof CuentaCorriente) {
					CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
					if(cuentaC.getEstado()) {//if account is enable
						if((cuentaC.getSaldo()+monto)<cuentaC.LimiteDinero()&&(cuentaO.getSaldo()-monto)>0&&monto>cuentaC.getMinimoMontoTrans()) {
							cuentaC.setSaldo(monto+(cuentaC.getSaldo()));
							cuentaO.setSaldo(cuentaO.getSaldo()-monto);
							return true;
						}
						return false;
					}
					else {
						throw new IllegalArgumentException("cuenta Bloqueada");
					}
				}
				else {
					CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
					if(cuentaE.getEstado()) {
						if((cuentaO.getSaldo()-monto)>0&&monto>cuentaE.getMinimoMontoTrans()) {
							cuentaE.setSaldo(monto+(cuentaE.getSaldo()));//add money to addressee account
							cuentaO.setSaldo(cuentaO.getSaldo()-monto);//discount this amount the money
							return true;
						}
						else {
							throw new IllegalArgumentException("saldo insuficiente y/o monto inferior a $5000");
						}
					}
					else {
						throw new IllegalArgumentException("Cuenta Bloqueada");
					}
					
				}
						
						
			}
			else {
				throw new IllegalArgumentException("coordenadas incorrectas");
				 
			 }
		 }
		 
	
	}
	/**
	 * this method get account information from the persons entered for rut.
	 */
	public String ObtenerInformacionCuenta(String rut) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		return persona.getListaCuenta().toString();
	}
	/**
	 * this method block account entered for number account,also verify the passwords is correct.
	 */
	public boolean BloquearCuentas(String rut,String numeroCuenta,String contraseñaInicio ,String contraseñaCuenta) {
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(cuenta==null||!cuenta.getRutTitular().equals(rut)) {
			throw new NullPointerException("cuenta no exise y/o cuenta no es de su propiedad");
		}
		else {
			if((contraseñaInicio.equals(persona.getContraseñaInicio()))&&(cuenta.getContraseñaCuenta().equals(contraseñaCuenta))) {
				if(cuenta instanceof CuentaCorriente) {
					CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
					cuentaC.setEstado(false);//change the account status.
					return true;
				}
				else {
					CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
					cuentaE.setEstado(false);//change the account status.
					return true;
				}
			}
			else {
				return false;
			}
		}
	}
	/**
	 * this method change the login password
	 */
	public boolean ActualizarContraseñaInicioSesion(String rut,String contraseñaActual,String contraseñaNueva) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(persona.getContraseñaInicio().equals(contraseñaActual)) {
			persona.setContraseñaInicio(contraseñaNueva);
			return true;
		}
		else {
			return false;
		}	
	}
	/**
	 * this method change the account password
	 */
	public boolean ActualizarContraseñaCuenta(String rut,String numeroCuenta,String contraseñaActual,String contraseñaNueva) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		if(cuenta!=null&&cuenta.getRutTitular().equals(persona.getRut())) {
			if(cuenta instanceof CuentaCorriente) {
				CuentaCorriente cuentaCC=(CuentaCorriente)cuenta;
				if(cuentaCC.getEstado()) {//if account is enable 
					if(cuenta.getContraseñaCuenta().equals(contraseñaActual)) {
						cuenta.setContraseñaCuenta(contraseñaNueva);
						return true;
					}
					return false;
				}
				else {
					throw new IllegalArgumentException("la cuenta esta bloqueada");
				}
			}
			else {
				CuentaChequeraElectronica cuentaEE=(CuentaChequeraElectronica)cuenta;
				if(cuentaEE.getEstado()) { 
					if(cuenta.getContraseñaCuenta().equals(contraseñaActual)) {
						cuenta.setContraseñaCuenta(contraseñaNueva);
						return true;
					}
					return false;
				}
				else {
					throw new IllegalArgumentException("la cuenta esta bloqueada");
				}
				
			}
		}
		else {
			throw new NullPointerException("cuenta ingresada no existe y/o cuenta no es propia");
		}
	}

	


       
	

}
