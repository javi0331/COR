package models;

public class SupportRequest {
    
    private final String id;
    private final String cliente;
    private final String descripcion;
    private final Priority prioridad;
    
    public enum Priority {
        BASIC,
        INTERMEDIATE,
        CRITICAL
    }
    
    public SupportRequest(String id, String cliente, String descripcion, Priority prioridad) {
        this.id = id;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }
    
    public String getId() {
        return id;
    }
    
    public String getCliente() {
        return cliente;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public Priority getPrioridad() {
        return prioridad;
    }
    
    @Override
    public String toString() {
        return String.format("Ticket #%s | Cliente: %s | Prioridad: %s\nDescripción: %s", 
                           id, cliente, prioridad, descripcion);
    }
}