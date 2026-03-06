package handlers;

import models.SupportRequest;

public class CriticalSupportHandler extends SupportHandler {
    
    @Override
    protected boolean puedeManejar(SupportRequest solicitud) {
        return solicitud.getPrioridad() == SupportRequest.Priority.CRITICAL;
    }
    
    @Override
    protected void manejar(SupportRequest solicitud) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     🚨 NIVEL 3: SOPORTE CRÍTICO (INGENIERÍA)              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n✅ Solicitud ESCALADA a Ingeniería");
        System.out.println("👤 Agente: Nivel 3 - Ingeniero Senior");
        System.out.println("📋 " + solicitud);
        System.out.println("\n💬 Respuesta: PRIORIDAD MÁXIMA - Equipo de ingeniería asignado.");
        System.out.println("⏱️  Tiempo estimado de resolución: Inmediato - 30 minutos\n");
    }
}