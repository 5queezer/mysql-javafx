package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class Artikel {
	private IntegerProperty id;
	private StringProperty bezeichnung;
	private StringProperty einheit;
	private ObjectProperty<BigDecimal> preis;
	
	public Artikel(Integer id, String name, String adresse, BigDecimal preis) {
		this.id = new SimpleIntegerProperty(id);
		this.bezeichnung = new SimpleStringProperty(name);
		this.einheit = new SimpleStringProperty(adresse);
		this.preis = new SimpleObjectProperty<BigDecimal>(preis);
	}

	public IntegerProperty getIdProperty() {
		return id;
	}

	public StringProperty getNameProperty() {
		return bezeichnung;
	}

	public StringProperty getAdresseProperty() {
		return einheit;
	}
	
	public ObjectProperty<BigDecimal> getPreisPropery() {
		return preis;
	}

	public void setId(Integer id) {
		this.id.set(id);
	}
	
	public void setBezeichnung(String name) {
		this.bezeichnung.set(name);
	}
	
	public void setEinheit(String adresse) {
		this.einheit.set(adresse);
	}
	
	public void setPreis(BigDecimal preis) {
		this.preis.set(preis);
	}
	
	public Integer getId() {
		return this.id.get();
	}
	
	public String getBezeichnung() {
		return this.bezeichnung.get();
	}
	
	public String getEinheit () {
		return this.einheit.get();
	}
	
	public BigDecimal getPreis() {
		return this.preis.get();
	}
}
