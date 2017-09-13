package pe.edu.cibertec.proyemp.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JpaTest {
	
	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	
	public static void main(String[] args) {
		
		// Patron factory para obtener el EntityManagerFactory
		EntityManagerFactory factory 
		=Persistence.createEntityManagerFactory("JPA_Clase01_eclipselink");
			//= Persistence.createEntityManagerFactory("persistenceUnit");
		
		// Se extrae el EntityManager del factory
		EntityManager em = factory.createEntityManager();
		
		// Inyeccion de dependencias
		JpaTest test = new JpaTest(em);
		
		// Se obtiene el objeto EntityTransaction para definir la transaccion
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
			// inserts, updates y deletes
		//	test.crearEmpleados();
			test.crearEmpleadosEnCascada();
		tx.commit();
			
			test.listarEmpleados();
	}

	private void listarEmpleados() {
		
		List<Empleado> empleados = 
				manager.createQuery("select e from Empleado e",
						Empleado.class).getResultList();
		
		for (Empleado emp : empleados) {
//			System.out.println("Empleado: " + emp.getId() 
//											+ " , " + emp.getNombre());
			System.out.println(emp);
		}
		
	}

	private void crearEmpleadosEnCascada() {
		
		Departamento java = new Departamento("java");
		
		Empleado emp1 = new Empleado("Bob", java);	
		Empleado emp2 = new Empleado("Maria", java);
	
		List<Empleado> empleados = Arrays.asList(emp1,emp2);
				
//		List<Empleado> empleados = new ArrayList<Empleado>();
//		empleados.add(emp1);
//		empleados.add(emp2);
	
		java.setEmpleados(empleados);
		
		manager.persist(java);
		
	}

	private void crearEmpleados() {

		Departamento java = new Departamento("java");
		// se realiza el insert departamento
		manager.persist(java);
		
		Departamento net = new Departamento("net");
		// se realiza el insert departamento
		manager.persist(net);
		
		Empleado emp1 = new Empleado("Bob", java);
		// se realiza el insert empleado
		manager.persist(emp1);
		
		Empleado emp2 = new Empleado("Maria", java);
		// se realiza el insert empleado
		manager.persist(emp2);
		
		Empleado emp3 = new Empleado("Mike", net);
		// se realiza el insert empleado
		manager.persist(emp3);
		
		Empleado emp4 = new Empleado("Mario", net);
		// se realiza el insert empleado
		manager.persist(emp4);
	}
	
	

}
