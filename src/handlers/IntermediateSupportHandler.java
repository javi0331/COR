package handlers;

import models.SupportRequest;

public class IntermediateSupportHandler extends SupportHandler {
    
    @Override
    protected boolean puedeManejar(SupportRequest solicitud) {
        return solicitud.getPrioridad() == SupportRequest.Priority.INTERMEDIATE;
    }
    
    @Override
    protected void manejar(SupportRequest solicitud) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      🔧 NIVEL 2: SOPORTE INTERMEDIO (TÉCNICO)              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n✅ Solicitud procesada por Soporte Técnico");
        System.out.println("👤 Agente: Nivel 2 - Especialista Técnico");
        System.out.println("📋 " + solicitud);
        System.out.println("\n💬 Respuesta: Nuestro equipo técnico está trabajando en su caso.");
        System.out.println("⏱️  Tiempo estimado de resolución: 2 horas\n");
    }
}