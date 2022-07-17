package business;

import data.ContoCorrente;
import data.ContoCorrenteDAO;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class BancomatEJB implements BancomatEJBLocal {

	private ContoCorrenteDAO ccDAO;

	public BancomatEJB() {
	}

	public static final int TUTTO_OK = 1;
	public static final int QTA_MINORE_0 = -1;
	public static final int SALDO_INSUFF = -2;

	public int controllaOperazione(String operazione, int idconto, float quantita) {
		if (quantita < 0) {
			return QTA_MINORE_0;
		}
		ContoCorrente contoControllo = ccDAO.getContoCorrente(idconto);

		if (contoControllo.getSaldo() < quantita) {
			return SALDO_INSUFF;
		}
		return TUTTO_OK;
	}

	public boolean preleva(int idconto, float quantita) {
		return ccDAO.preleva(idconto, quantita);
	}

	public boolean versa(int numero, float quantita) {
		return ccDAO.versa(numero, quantita);
	}

	public float saldo(int numero) {
		return ccDAO.getContoCorrente(numero).getSaldo();
	}

	public boolean esisteCc(int numero) {
		return ccDAO.getContoCorrente(numero) != null;
	}

	public ContoCorrenteDAO getCcDAO() {
		return ccDAO;
	}

	public void setCcDAO(ContoCorrenteDAO ccDAO) {
		this.ccDAO = ccDAO;
	}

}
