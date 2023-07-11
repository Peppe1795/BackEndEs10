package App;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Evento;
import entities.EventoDAO;
import entities.TipoEvento;
import util.JpaUtil;

public class App {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		Evento primo = new Evento("Concerto musicale", LocalDate.now(), "inzio ore 15 e fine ore 20",
				TipoEvento.PUBBLICO, 450);
		Evento secondo = new Evento("Mostra Picasso", LocalDate.now(), "inzio ore 8 e fine ore 23", TipoEvento.PRIVATO,
				50);

		EventoDAO ev = new EventoDAO(em);

		ev.save(primo);
		ev.save(secondo);

		Evento trovatoID = ev.findDataById(UUID.fromString("1f20c832-aa78-41b8-9d06-5f63f3dfe65b"));
		System.out.println(trovatoID);

		ev.delete(UUID.fromString("1f20c832-aa78-41b8-9d06-5f63f3dfe65b"));

		ev.refresh(UUID.fromString("7a6a8d06-2374-452f-b8fe-e3c77cd81779"));

		em.close();
		emf.close();

	}

}
