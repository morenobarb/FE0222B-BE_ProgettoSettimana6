package data;

import java.sql.*;

public class ContoCorrenteDAO {
	
	private Connection conn;
	

	public ContoCorrenteDAO() {
		this.conn = ConnectionFactory.getConnection();
	}

	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ContoCorrente getContoCorrente(int numero) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContoCorrente ccc = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM contocorrente WHERE numero = ?");
			ps.setInt(1, numero);

			rs = ps.executeQuery();
			ccc = new ContoCorrente();

			if (rs.next()) {
				ccc.setNumero(rs.getInt("numero"));
				ccc.setIntestatario(rs.getString("intestatario"));
				ccc.setSaldo(rs.getFloat("saldo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Recupero info non riuscito!");
		}

		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ccc;
	}

	
	
	public boolean preleva(int numero, float importo) {
		PreparedStatement ps = null;
		
	
		ContoCorrente ccc = this.getContoCorrente(numero);
		
		
		float nuovoSaldo = ccc.getSaldo() - importo;
		
		
		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE numero = ?;");
			
			ps.setFloat(1, nuovoSaldo);
			ps.setInt(2, numero);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Impossibile prelevare dal conto! " +  e.getMessage());
			
			return false;
		}

		try {
			ps.close();
		} catch (SQLException e1) {e1.printStackTrace();}
	
		return true;
	}
	
	
	public boolean versa(int numero, float quantita) {
PreparedStatement ps = null;
		
		
		ContoCorrente ccc = this.getContoCorrente(numero);
		
		
		float nuovoSaldo = ccc.getSaldo() + quantita;
		
		
		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE numero = ?;");
			ps.setFloat(1, nuovoSaldo);
			ps.setInt(2, numero);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Impossibile depositare nel conto! " +  e.getMessage());
			return false;
		}

		try {
			ps.close();
		} catch (SQLException e1) {e1.printStackTrace();}
	
		return true;
	}
	
	
	
	
	public boolean movimentaConto(int numero, float importo) {
		PreparedStatement ps = null;
		
		
		ContoCorrente ccc = this.getContoCorrente(numero);
		
		
		float nuovoSaldo = ccc.getSaldo() + importo;
		
		
		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE numero = ?;");
			ps.setFloat(1, nuovoSaldo);
			ps.setInt(2, numero);
			
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Impossibile movimentare il conto! " +  e.getMessage());
			return false;
		}

		try {
			ps.close();
		} catch (SQLException e1) {e1.printStackTrace();}
	
		return true;
	}
	
	public boolean depositaInConto(int numero, float importo) {
		return movimentaConto(numero, importo);
	}
	
	public boolean prelevaDaConto(int numero, float importo) {
		return movimentaConto(numero,- importo);
	}
	
	

}