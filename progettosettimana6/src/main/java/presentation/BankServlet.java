package presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import business.BancomatEJB;
import data.ContoCorrente;
import data.ContoCorrenteDAO;

@WebServlet("/sportellobancomat")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankServlet() {
		super();
	}

	@EJB
	BancomatEJB bankService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContoCorrenteDAO ccDAO = new ContoCorrenteDAO();
		
		bankService.setCcDAO(ccDAO);
		
		
		int numero = Integer.parseInt(request.getParameter("numero"));
		float quantita = Float.parseFloat(request.getParameter("importo"));
		
		
		String tipo = request.getParameter("operazionerichiesta");
		
		if(!bankService.esisteCc(numero)) {
			System.out.println("Il conto è insesitente, operazione annullata");
			return;
		}		
		
		if(bankService.controllaOperazione(tipo, numero, quantita) != bankService.TUTTO_OK) {
			System.out.println("Operazione non consentita");
			return;
		}
		
		
		
		if(tipo.equalsIgnoreCase("preleva")) {			
						
			boolean risultato = bankService.preleva(numero, quantita);
			
			
			if(risultato) {
				System.out.println("Prelievo completato");
				}else {
					System.out.println("Prelievo non riuscito");
				}
			
		}else if(tipo.equalsIgnoreCase("deposita")) {
			boolean risultato = bankService.versa(numero, quantita);			
			
			if(risultato) {
				System.out.println("Deposito completato");
			}else {
				System.out.println("Deposito NON riuscito");
			}
			
		}else if(tipo.equalsIgnoreCase("saldo")) {
			
			float saldo = bankService.saldo(numero);
			
			System.out.println("Il saldo è " + saldo);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
