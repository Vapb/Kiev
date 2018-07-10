package cin;

import java.util.Date;

public class OnibusEvent {
	private String unidade;
	private String nomeempresa;
	private String codinterno;
	private Date tempo;
	private int codx;
	private int cody;

	
	public OnibusEvent(String unidade, String nomeempresa, String codinterno, Date tempo, int codx, int cody) {
		this.unidade = unidade;
		this.nomeempresa = nomeempresa;
		this.codinterno = codinterno;
		this.tempo = tempo;
		this.codx = codx;
		this.cody = cody;
	
	}
	
	// Gets 
	
	public String getUnidade(){
		return this.unidade;
	}
	public String getNomeempresa() {
		return this.nomeempresa;
	}
	public String getCodinterno() {
		return this.codinterno;
	}
	public Date getTempo() {
		return this.tempo;
	}
	public int getCodx() {
		return this.codx;
	}
	public int getCody() {
		return this.cody;
	}


}
