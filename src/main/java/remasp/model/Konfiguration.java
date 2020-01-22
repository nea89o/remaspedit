package remasp.model;

import java.util.ArrayList;
import java.util.List;

public class Konfiguration {
	private int bz;
	private ArrayList<Long> registers;
	private List<Befehl> befehlsSpeicher;
	private boolean isNegativeAllowed = false;
	
	public Konfiguration() {
		setBz(0);
		this.registers = new ArrayList<>(1000);
		for (int i = 0; i < 101; i++) {
			this.registers.add(i, Long.valueOf(0L));
		}
		this.befehlsSpeicher = new ArrayList<>(1000);
	}

	int findeIndexVonBefehlMitLabel(String lName) {
		int gesuchterIndex = -1;
		for (int i = 0; i < this.befehlsSpeicher.size(); i++) {
			if (((Befehl) this.befehlsSpeicher.get(i)).getLabel().equals(lName)) {
				gesuchterIndex = i;
				break;
			}
		}
		return gesuchterIndex;
	}
	
	public boolean isNegativeAllowed() {
		return isNegativeAllowed;
	}
	
	public void allowNegative() {
		isNegativeAllowed = true;
	}
	
	public long coerce(long value) {
		if(!isNegativeAllowed)
			if(value < 0)
				return 0;
		return value;
	}

	public Befehl getAktuelllerBefehl() {
		return this.befehlsSpeicher.get(this.bz);
	}

	public List<Befehl> getBefehlsSpeicher() {
		return this.befehlsSpeicher;
	}

	public void setBz(int wert) {
		this.bz = wert;
	}

	public int getBz() {
		return this.bz;
	}

	public void incBz() {
		setBz(getBz() + 1);
	}

	public long getRegister(int i) {
		return ((Long) this.registers.get(i)).longValue();
	}

	public void setRegister(int i, long wert) {
		this.registers.set(i, coerce(Long.valueOf(wert)));
	}

	public ArrayList<Long> getRegisters() {
		return this.registers;
	}
}
