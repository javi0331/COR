package handlers;

import models.SupportRequest;

public abstract class SupportHandler {
    
    protected SupportHandler siguienteHandler;
    
    public void setSiguienteHandler(SupportHandler handler) {
        this.siguienteHandler = handler;
    }
    
    public void procesarSolicitud(SupportRequest solicitud) {
        if (puedeManejar(solicitud)) {
            manejar(solicitud);
        } else if (siguienteHandler != null) {
            System.out.println("⏩ Escalando al siguiente nivel...\n");
            siguienteHandler.procesarSolicitud(solicitud);
        } else {
            System.out.println("❌ No hay handler disponible para esta solicitud\n");
        }
    }
    
    protected abstract boolean puedeManejar(SupportRequest solicitud);
    
    protected abstract void manejar(SupportRequest solicitud);
}