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

		Evento trovatoID = ev.findDataById(UUID.fromString("30b5481f-bd73-4be9-af74-9a0a2d441020"));
		System.out.println(trovatoID);

		ev.delete(UUID.fromString("6b576885-7adc-43a1-ab17-b713b4ba6351"));

		ev.refresh(UUID.fromString("a73e9825-964a-4c37-9635-539f71490259"));

		em.close();
		emf.close();

	}

}
