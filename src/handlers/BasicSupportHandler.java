package handlers;

import models.SupportRequest;

public class BasicSupportHandler extends SupportHandler {
    
    @Override
    protected boolean puedeManejar(SupportRequest solicitud) {
        return solicitud.getPrioridad() == SupportRequest.Priority.BASIC;
    }
    
    @Override
    protected void manejar(SupportRequest solicitud) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         📞 NIVEL 1: SOPORTE BÁSICO                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n✅ Solicitud procesada por Soporte Básico");
        System.out.println("👤 Agente: Nivel 1 - Soporte General");
        System.out.println("📋 " + solicitud);
        System.out.println("\n💬 Respuesta: Su consulta ha sido resuelta por nuestro equipo básico.");
        System.out.println("⏱️  Tiempo estimado de resolución: 15 minutos\n");
    }
} 