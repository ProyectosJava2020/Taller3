package SistemaBancario.logica;
import SistemaBancario.dominio.Cuenta;
import SistemaBancario.dominio.CuentaCorriente;
import ucn.*;

import java.io.IOException;
import java.util.Random;

public class App {
	/**
     * method for read personas.txt and enter to the system
     * @param sistema
     * @throws IOException
     */
	public static void LeerPersonas(SistemaBancario sistema) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("Personas.txt");
		
		while(!arch1.isEndFile()) {//d
			
			Registro reg = arch1.getRegistro();
	
			String rut = reg.getString();
			String nombre = reg.getString();
			String apellido = reg.getString();
			String contraseña = reg.getString();		
			int [][] matriz = new int [5][10];
			for(int i = 0 ; i<5 ;i++ ) {
				reg = arch1.getRegistro();
				for(int j = 0 ;j <10 ;j++) {
					matriz[i][j] = reg.getInt();
				}	
				
			}
			if(!sistema.ingresarPersona(rut, nombre, apellido, contraseña, matriz)){
				System.out.println("No se pudo ingresar la persona (Falta espacio) ");	
			}
				
		}		
		arch1.close();	
	}
	/**
     * method for read cuentas.txt and enter to the system
     * @param sistema
     * @throws IOException
     */
	public static void  LeerCuenta(SistemaBancario sistema) throws IOException {
		ArchivoEntrada arch2 = new ArchivoEntrada("Cuentas.txt");
		
		while(!arch2.isEndFile()) {
			Registro reg = arch2.getRegistro();
			
			String numeroCuenta = reg.getString();
			
			String tipo = reg.getString();
			String rut = reg.getString();
			String contraseña = reg.getString();
			long saldo = reg.getLong();
			
			if (tipo.equals("CE") ) {
				try {
					if(!sistema.ingresarCuentaChequeraElectronica(numeroCuenta, rut, contraseña, saldo)) {
						System.out.println("No hay espacio para ingresar la cuenta ");
					}
				}
				catch
					(NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
					
				
				
				
			}
			else {
				try {
					if(!sistema.ingresarCuentaCorriente(numeroCuenta, rut, contraseña, saldo)) {
						System.out.println("No hay espacio para ingresar la cuenta ");
					}
					
				}
				catch
					(NullPointerException ex) {
					System.out.println(ex.getMessage());
					
				}
			}
		}
		arch2.close();
	}
	/**
     * display the start Menu
     */
	public static void MenuInicio() {
		System.out.println("1)Iniciar Sesion");
		System.out.println("2)Cerrar Sistema");
	
	}
	/**
     * display the  Main Menu containing options
     */
	public static void Menu(){
		System.out.println("1) Depositar");
		System.out.println("2) Girar");
		System.out.println("3) Transferencia");
		System.out.println("4) Informacion Cuenta");
		System.out.println("5) Actualizar contraseña");
		System.out.println("6) Bloquear Cuenta");
		System.out.println("7) Cerrar sesion");
	}
	/**
     * method to get random numbers and this save in the list
     * @param vector2
     * @param vector3
     * @throws IOException
     */
	public static void posicionesAleatoria(int[]vector2,int[]vector3)throws IOException{
		for(int j=0;j<3;j++) {
			double X=Math.random()*9;
			double i= Math.random()*4;
			int f=(int)i;
			int C=(int)X;
			vector2[j]=f;
			vector3[j]=C;
		}
	}
	/**
	 * display second menu,this menu is allowed to come back to the main menu.
	 * @return chosen option
	 */
	public static String MenuSecundario() {
		System.out.print("Volver a menu principal(si/no): ");
		String eleccion=StdIn.readString();
		while(!eleccion.equalsIgnoreCase("si")) {
			System.out.print("Volver a menu principal(si/no): ");
			eleccion=StdIn.readString();
		}
		return eleccion;
	}
	
	
	/**
	 * enter information and it accessed to each menu.
	 * @param sistema
	 * @throws IOException
	 */
	public static void Datos(SistemaBancario sistema) throws IOException{
		StdOut.println("Bienvenido a Banco BCI-DEUDAS ");
		MenuInicio();
		System.out.print("Ingrese opcion: ");
		int eleccion=StdIn.readInt();
		while(eleccion!=2) {
			StdOut.print("ingrese rut: ");
			String rut=StdIn.readString();
			StdOut.print("Ingrese contraseña: ");
			String contraseñaI=StdIn.readString();
			try {
				if(sistema.verificarCuentaInicioSesion(rut, contraseñaI)) {
					Menu();
					System.out.print("ingrese opcion a realizar: ");
					int opcione=StdIn.readInt();
					while(opcione!=7) {
						while(true) {//this cycle is allowed to come back to the main menu
							if(opcione==1) {
								System.out.print("Ingrese numero de su cuenta: ");
								String numeroCuenta=StdIn.readString();
								System.out.print("Ingrese monto a depositar: ");
								long monto=StdIn.readLong();
								while(monto<0) {
									System.out.println("ingrese monto valido");
									System.out.print("Ingrese monto a depositar: ");
									monto=StdIn.readLong();
								}
								try {
									if(sistema.Depositar(monto, rut, numeroCuenta)) {
										System.out.println("Deposito hecho exitosamente");
									}
									else {
										System.out.println("Su cuenta Corriente alcanzo el maximo valor de saldo permitido");
									}
								}catch(IllegalArgumentException ex){
									System.out.println(ex.getMessage());
								}
								String volverMenuP=MenuSecundario();
								if(volverMenuP.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==2) {
								System.out.print("ingrese su cuenta (Numero Cuenta): ");
								String cuentaP=StdIn.readString();
								System.out.print("ingrese contraseña Cuenta: ");
								String contraC=StdIn.readString();
								System.out.print("ingrese monto a girar: ");
								long montoG=StdIn.readLong();
								while(montoG<0) {
									System.out.println("monto ingresado incorrecto");
									System.out.print("ingrese monto a girar: ");
									montoG=StdIn.readLong();
								}
								try {
									if(sistema.Girar(montoG, cuentaP, contraC, rut)) {
										System.out.println("Giro completado");
										System.out.println("Saldo:$"+sistema.obtenerSaldo(cuentaP));
										
									}
									else {
										System.out.println("saldo insuficiente y/o monto inferior a 5000");
									}
								}catch(IllegalArgumentException ex) {
									System.out.println(ex.getMessage());
								}catch(NullPointerException ex) {
									System.out.println("cuenta no existe");
								}
								String volverMenuP1=MenuSecundario();
								if(volverMenuP1.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==3) {
								System.out.print("ingrese su cuenta (Numero Cuenta): ");
								String cuentaT=StdIn.readString();
								System.out.print("ingrese Monto: ");
								int montoT=StdIn.readInt();
								while(montoT<0) {
									System.out.println("monto invalido");
									System.out.println("ingrese Monto: ");
									montoT=StdIn.readInt();
								}
								String []vector= {"A","B","C","D","E","F","G","H","I","J"};
								String []vector1= {"1","2","3","4","5"};
								int []vector2=new int[5];//row
								int []vector3=new int[10];//column
								
								System.out.print("ingrese cuenta Destinatario: ");
								String cuentaD=StdIn.readString();
								posicionesAleatoria(vector2,vector3);
								int p=0;
								System.out.print("ingrese cordenada "+vector[vector3[p]]+vector1[vector2[p]]+": ");//display the coordinates saved in vector2-vector3
								int numero1=StdIn.readInt();
								p++;
								System.out.print("ingrese cordenada "+vector[vector3[p]]+vector1[vector2[p]]+": ");
								int numero2=StdIn.readInt();
								p++;
								System.out.print("ingrese cordenada "+vector[vector3[p]]+vector1[vector2[p]]+": ");
								int numero3=StdIn.readInt();
								try {
									if(sistema.Transferir(montoT, cuentaT, cuentaD, vector3[0],vector2[0], numero1,vector3[1],vector2[1], numero2, vector3[2], 
											vector2[2],numero3)){
										System.out.println("transferencia exitosa");
									}
									else {
										System.out.println("saldo insuficiente o bien cuenta destinatario no tiene cupo o monto ingresado es inferior a 5000");
				
									}
								}catch(NullPointerException ex) {
									System.out.println(ex.getMessage());
								}catch(IllegalArgumentException ex1) {
									System.out.println(ex1.getMessage());
								}
								String volverMenuP2=MenuSecundario();
								if(volverMenuP2.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==4) {
								System.out.println(sistema.ObtenerInformacionCuenta(rut));
								String volverMenuP3=MenuSecundario();
								if(volverMenuP3.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==5) {
								System.out.println("Indique contraseña a actualizar");
								System.out.println("1)Contraseña Inicio Sesion");
								System.out.println("2)Contraseña Cuenta");
								System.out.println("ingrese opcion: ");
								int opcion=StdIn.readInt();
								if(opcion==1) {
									System.out.print("ingrese contraseña actual: ");
									String contraseñaAA=StdIn.readString();
									System.out.print("Ingrese contraseña nueva: ");
									String contraseñaNew0=StdIn.readString();
									System.out.print("Vuelva ingresar contraseña nueva: ");
									String contraseñaNew=StdIn.readString();
									while(!contraseñaNew.equals(contraseñaNew0)) {
										System.out.println("las contraseñas no coinciden");
										System.out.print("Ingrese contraseña nueva: ");
										contraseñaNew0=StdIn.readString();
										System.out.print("Vuelva ingresar contraseña nueva: ");
										contraseñaNew=StdIn.readString();
									}
									if(sistema.ActualizarContraseñaInicioSesion(rut, contraseñaAA, contraseñaNew)) {
										System.out.println("Contraseña de Inicio Actualizada");
									}
									else {
										System.out.println("error contraseña actual incorrecta");
									}
								}
								if(opcion==2) {
									System.out.print("ingrese su numero de cuenta: ");
									String numeroCuenta23=StdIn.readString();
									System.out.print("ingrese contraseña Actual: ");
									String contraActual=StdIn.readString();
									System.out.print("ingrese contraseña Nueva: ");
									String contraNueva0=StdIn.readString();
									System.out.print("vuelva ingresar contraseña Nueva: ");
									String contraNueva=StdIn.readString();
									while(!contraNueva.equals(contraNueva0)) {
										System.out.println("contraseñas no coinciden");
										System.out.print("ingrese contraseña Nueva: ");
										contraNueva0=StdIn.readString();
										System.out.print("vuelva ingresar contraseña Nueva: ");
										contraNueva=StdIn.readString();
									}
									try {
										if(sistema.ActualizarContraseñaCuenta(rut, numeroCuenta23, contraActual, contraNueva)) {
											System.out.println("contraseña actualizada");
										}
										else {
											System.out.println("error contraseña actual incorrecta");
										}
									}catch (IllegalArgumentException ex) {
										System.out.println(ex.getMessage());
									}catch (NullPointerException ex1) {
										System.out.println(ex1.getMessage());
									}
									
								}
								String volverMenuP4=MenuSecundario();
								if(volverMenuP4.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==6) {
								System.out.print("ingrese numero cuenta:");
								String numeroCB=StdIn.readString();
								System.out.print("ingrese contraseña Inicio Sesion: ");
								String contraInicio=StdIn.readString();
								System.out.print("ingrese contraseña Cuenta: ");
								String contraBanco=StdIn.readString();
								try {
									if(sistema.BloquearCuentas(rut, numeroCB, contraInicio, contraBanco)) {
										System.out.println("Cuenta Bloqueada Exitosamente");
									}
									else {
										System.out.println("contraseña de inicio de sesion y/o contraseña de cuenta Incorrecta");
									}
								}catch (NullPointerException ex) {
									System.out.println(ex.getMessage());
								}
								String volverMenuP5=MenuSecundario();
								if(volverMenuP5.equalsIgnoreCase("si")) {
									break;
								}
							}
					    }
						Menu();
						System.out.print("ingrese opcion a realizar: ");
						opcione=StdIn.readInt();
					}
				}
				
			}catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}catch (IllegalArgumentException ex1) {
				System.out.println(ex1.getMessage());
			}
			MenuInicio();
			System.out.print("Ingrese opcion: ");
			eleccion=StdIn.readInt();
		}
		
	}
	

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		SistemaBancario sistema=new SistemaBancarioImpl();
		LeerPersonas(sistema);
		LeerCuenta(sistema);
		Datos(sistema);
	}

}
