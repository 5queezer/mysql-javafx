package application.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Bestellung {
	private IntegerProperty artikelid;
	private IntegerProperty kundenid;
	private IntegerProperty stueckzahl;
	private ObjectProperty<Date> bestelldatum;
	private ObjectProperty<Date> lieferdatum;
	
	public Bestellung(Integer artikelid, Integer kundenid, Integer stueckzahl, Date bestelldatum, Date lieferdatum) {
		this.artikelid = new SimpleIntegerProperty(artikelid);
		this.kundenid = new SimpleIntegerProperty(kundenid);
		this.stueckzahl = new SimpleIntegerProperty(stueckzahl);
		this.bestelldatum = new SimpleObjectProperty<Date>(bestelldatum);
		this.lieferdatum = new SimpleObjectProperty<Date>(lieferdatum);
	}

	public IntegerProperty getArtikelidProperty() {
		return artikelid;
	}

	public IntegerProperty getKundenidProperty() {
		return kundenid;
	}

	public IntegerProperty getStueckzahlProperty() {
		return stueckzahl;
	}

	public ObjectProperty<Date> getBestelldatumProperty() {
		return bestelldatum;
	}

	public ObjectProperty<Date> getLieferdatumProperty() {
		return lieferdatum;
	}
	
	public void setArtikelid(Integer value) {
		this.artikelid.set(value);
	}
	
	public void setKundenid(Integer value) {
		this.kundenid.set(value);
	}
	
	public void setStueckzahl(Integer value) {
		this.stueckzahl.set(value);
	}
	
	public void setBestelldatum(Date value) {
		this.bestelldatum.set(value);
	}
	
	public void setLieferdatum(Date value) {
		this.lieferdatum.set(value);
	}
	
	public Integer getArtikelid() {
		return this.artikelid.get();
	}
	
	public Integer getKundenid() {
		return this.kundenid.get();
	}
	
	public Integer getStueckzahl() {
		return this.stueckzahl.get();
	}
	
	public Date getBestelldatum() {
		return this.bestelldatum.get();
	}
	
	public Date getLieferdatum() {
		return this.lieferdatum.get();
	}
}
