package main.webapp.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;
import main.services.persistence.PersistenceFacade;

public class ProfileServlet extends MyServlet {

	public ProfileServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");
		String name = null;
		if (email != null) {
			// Se nella sessione esiste la mail, mi salvo tutte le info e carico il profilo
			Customer c = pf.getUser(email);
			name = c.getName();
			String surname = c.getSurname();
			// Da sostituire
			List<Configuration> conf;
			conf = pf.getConfigurationByEmail(email);
			response.getWriter().write(Rythm.render("profile.html", name, surname, email, conf, request));
		} else {
			// altrimenti reindirizzo al login
			response.sendRedirect("/login");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute("email");
		ServletController controller = new ServletController();

		if (request.getPathInfo().equals("/remove")) {
			remove(request, response, controller);
		} else if (request.getPathInfo().equals("/rename")) {
			rename(request, response, controller);
		}
	}

	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		int confId = Integer.parseInt(request.getParameter("id"));
		// controller.removeConfiguration(confId);

		if(controller.removeConfiguration(confId)) {
			System.out.println("Configurazione rimossa");
		}else {
			System.out.println("Configurazione non rimossa");
		}
		
		String json = "";
		if (controller.removeConfiguration(confId)) {
			json = JsonMessages.getJsonRemoveConfigurationResponse(confId);
		} else {
			json = JsonMessages.getJsonNotOkResponse();
		}
		response.getWriter().write(json);
		// System.out.println(json);
	}
	
	private void rename(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		int confId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		
		PersistenceFacade pf = PersistenceFacade.getIstance();
		Customer customer = pf.getUser((String) request.getSession().getAttribute("email"));
		Configuration configuration = pf.getConfiguration(confId);
		configuration.setName(name);
		pf.changeConfName(confId, name);
	}
}
