package datosUsuario;

public class DatosUsuario {
	private int id;	
	private String busca;
	private String sexo;
	private String fechanacimiento;
	private String lugardondevive;
	
	
	
	public DatosUsuario(int id, String busca, String sexo, String fechanacimiento, String lugardondevive) {
		super();
		this.id = id;
		this.busca = busca;
		this.sexo = sexo;
		this.fechanacimiento = fechanacimiento;
		this.lugardondevive = lugardondevive;
	}

	public DatosUsuario(String busca, String sexo, String fechanacimiento, String lugardondevive) {
		super();
		this.busca = busca;
		this.sexo = sexo;
		this.fechanacimiento = fechanacimiento;
		this.lugardondevive = lugardondevive;
	}
	
	

	public int getId() {
		return id;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getLugardondevive() {
		return lugardondevive;
	}

	public void setLugardondevive(String lugardondevive) {
		this.lugardondevive = lugardondevive;
	}
	
	

}
