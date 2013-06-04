package br.com.michelon.softimob.tela.widget;

import de.ralfebert.rcputils.tables.format.StringValueFormatter;

public class ColumnProperties {

	private String label;
	
	private String atributo;
	
	private Integer tamanho;
	
	private StringValueFormatter formatter;

	public ColumnProperties(String label, String atributo, Integer tamanho, StringValueFormatter formatter) {
		this.label = label;
		this.atributo = atributo;
		this.tamanho = tamanho;
		this.formatter = formatter;
	}

	public ColumnProperties(String label, String atributo, Integer tamanho) {
		this(label, atributo, tamanho, null);
	}

	
	public ColumnProperties(String label, String atributo) {
		this(label, atributo, null);
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public StringValueFormatter getFormatter() {
		return formatter;
	}
	
	public void setFormatter(StringValueFormatter formatter) {
		this.formatter = formatter;
	}
	
}
