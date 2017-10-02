package es.monguerapps.ireferendum.enums;

public enum EstadoConsulta {
	ELIMINADA("Eliminada", null), FINALIZADA("Finalizada", ELIMINADA), EN_CURSO("En Curso",
			FINALIZADA), PLANTEADA("Planteada", EN_CURSO), BORRADOR("Borrador", PLANTEADA);

	private String nombreEstado;
	private EstadoConsulta estadoSiguiente;

	private EstadoConsulta(String nombreEstado, EstadoConsulta estadoSiguiente) {
		this.setNombreEstado(nombreEstado);
		this.setEstadoSiguiente(estadoSiguiente);
	}

	public static EstadoConsulta fromString(String text) {
		for (EstadoConsulta b : EstadoConsulta.values()) {
			if (b.nombreEstado.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public EstadoConsulta getEstadoSiguiente() {
		if (this == ELIMINADA) return ELIMINADA;
		return estadoSiguiente;
	}

	public void setEstadoSiguiente(EstadoConsulta estadoSiguiente) {
		this.estadoSiguiente = estadoSiguiente;
	}
}
