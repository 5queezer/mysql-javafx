package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kunde {
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty adresse;
	
	public Kunde (Integer id, String name, String adresse) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.adresse = new SimpleStringProperty(adresse);
	}

	public IntegerProperty getIdProperty() {
		return id;
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public StringProperty getAdresseProperty() {
		return adresse;
	}
	
	public void setId(Integer id) {
		this.id.set(id);
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	
	public Integer getId() {
		return this.id.get();
	}
	
	public String getName() {
		return this.name.get();
	}
	
	public String getAdresse () {
		return this.adresse.get();
	}
}
