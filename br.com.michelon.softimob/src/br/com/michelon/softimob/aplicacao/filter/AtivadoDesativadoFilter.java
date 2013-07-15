package br.com.michelon.softimob.aplicacao.filter;

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class AtivadoDesativadoFilter extends ViewerFilter {

	public enum AtivadoDesativado {

		ATIVADOS("Ativados"), DESATIVADOS("Desativados"), TODOS("Todos");

		private String nome;

		AtivadoDesativado(String nome) {
			this.nome = nome;
		}

		@Override
		public String toString() {
			return nome;
		}

	}

	private AtivadoDesativado estado = AtivadoDesativado.ATIVADOS;

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		try {
			List<Field> fields = ReflectionHelper.getAtributoAtivoDesativado(element);

			if (fields != null && !fields.isEmpty()) {
				if (estado.equals(AtivadoDesativado.TODOS))
					return true;
				else {
					boolean ativo;
					ativo = (Boolean) ReflectionHelper.getAtribute(element, fields.get(0).getName());

					if (estado.equals(AtivadoDesativado.ATIVADOS))
						return ativo;
					if (estado.equals(AtivadoDesativado.DESATIVADOS))
						return !ativo;
				}
			}

		} catch (Exception e) {}
		
		return false;
	}

	public void setEstado(AtivadoDesativado estado) {
		this.estado = estado;
	}

}
