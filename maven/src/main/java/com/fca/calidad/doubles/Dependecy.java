package com.fca.calidad.doubles;
public class Dependecy {
	private final SubDependecy subDependency;
	private double ultimoResultado;
	public double suma(double operando1, double operando2) {
		return ultimoResultado = operando1 + operando2;
	}
	public Dependecy(SubDependecy sub) {
		super();
		this.subDependency = sub;
	}
	
	public String getClassName() {
		return this.getClass().getSimpleName();
	}
	
	public String getSubdependencyClassName() {
		return subDependency.getClassName();
	}
	
	public int addTwo(int i) {
		return i + 2;
	}
	public String getClassNameUpperCase() {
		return getClassName().toUpperCase();
	}
	
}

