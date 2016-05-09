package common.helpers;

import fr.ybonnel.csvengine.adapter.AdapterCsv;

public class DefenseHelper extends AdapterCsv<Double> {
	@Override
	public Double parse(String chaine) {

		chaine = chaine.replace(',', '.');
		return new Double(chaine);
	}

	@Override
	public String toString(Double value) {
		return value.toString();
	}
}
