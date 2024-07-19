package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servico implements BaseModel{
	private int id;
	private double valor;
	private String nome;
	private int tempo;
	private int id_cargo;
	ArrayList<Servico> servicos = new ArrayList<Servico>();
	public Servico(int id, double valor, String nome, int tempo, int id_cargo) {
		this.id = id;
		this.id_cargo = id_cargo;
		this.valor = valor;
		this.nome = nome;
		this.tempo = tempo;
	}
	public Servico() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public int getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(int id_cargo) {
		this.id_cargo = id_cargo;
	}
	public ArrayList<Servico> listarServicos() {
		try {
	        String sql = "SELECT * FROM Servico";
	        PreparedStatement stmt = DAO.getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        ArrayList<Servico> servicos = new ArrayList<Servico>();
	        while (rs.next()) {
	        	Servico servico = new Servico();
	        	servico.setNome(rs.getString("nome"));
	        	servico.setId(rs.getInt("id"));
	        	servicos.add(servico);
	        }
	        return servicos;
	    } catch (Exception e) {
	        System.out.println("Ocorreu um erro: " + e.getMessage());
	        return null;
	    }
	}
	public Servico encontrarServicoPorId(int id) {
		try {
	        String sql = "SELECT * FROM Servico WHERE id = ?";
	        PreparedStatement stmt = DAO.getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        Servico servico = new Servico();
	        if (rs.first()) {
	        	servico.setNome(rs.getString("nome"));
	        	servico.setId(rs.getInt("id"));
	        }
	        return servico;
	    } catch (Exception e) {
	        System.out.println("Ocorreu um erro: " + e.getMessage());
	        return null;
	    }
	}
	@Override
	public int salvar() throws SQLException {
		try {
    		String sql = "INSERT INTO Servico (nome, id_cargo, valor, tempo) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement stmt = DAO.getConnection().prepareStatement(sql);
        	stmt.setString(1, this.getNome());
            stmt.setInt(2, this.getId_cargo());
            stmt.setDouble(3, this.getValor());
            stmt.setInt(4, this.getTempo());
        	return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
			DAO.getConnection().close();
			return 0;
		}
	}
	@Override
	public int deletar(int id) throws SQLException {
		try {
    		String sql = "DELETE FROM Servico WHERE id = ?";
        	PreparedStatement stmt = DAO.getConnection().prepareStatement(sql);
        	stmt.setInt(1, this.getId());
        	return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
			DAO.getConnection().close();
			return 0;
		}
	}
	@Override
	public int editar(int id) throws SQLException {
		try {
    		String sql = "UPDATE Servico SET nome = ?, id_cargo = ?, valor = ?, tempo = ? WHERE id = ?";
        	PreparedStatement stmt = DAO.getConnection().prepareStatement(sql);
        	stmt.setString(1, this.getNome());
            stmt.setInt(2, this.getId_cargo());
            stmt.setDouble(3, this.getValor());
            stmt.setInt(4, this.getTempo());
        	return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
			DAO.getConnection().close();
			return 0;
		}
	}
}
