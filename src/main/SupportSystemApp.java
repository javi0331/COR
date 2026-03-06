package main;

import models.SupportRequest;
import models.SupportRequest.Priority;
import handlers.*;
import java.util.Scanner;

public class SupportSystemApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static SupportHandler cadenaDeResponsabilidad;
    private static int contadorTickets = 1;
    
    public static void main(String[] args) {
        
        imprimirBanner();
        construirCadena();
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion(1, 3);
            
            switch (opcion) {
                case 1:
                    crearYProcesarSolicitud();
                    break;
                case 2:
                    verEstructuraCadena();
                    break;
                case 3:
                    continuar = false;
                    break;
            }
            
            if (continuar) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ¡Gracias por usar el sistema! 🎯             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        scanner.close();
    }
    
    private static void construirCadena() {
        SupportHandler nivelBasico = new BasicSupportHandler();
        SupportHandler nivelIntermedio = new IntermediateSupportHandler();
        SupportHandler nivelCritico = new CriticalSupportHandler();
        
        nivelBasico.setSiguienteHandler(nivelIntermedio);
        nivelIntermedio.setSiguienteHandler(nivelCritico);
        
        cadenaDeResponsabilidad = nivelBasico;
        
        System.out.println("\n✅ Cadena de responsabilidad construida correctamente:");
        System.out.println("   BÁSICO → INTERMEDIO → CRÍTICO\n");
    }
    
    private static void mostrarMenu() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                      MENÚ PRINCIPAL                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  1. 🎫 Crear nueva solicitud de soporte");
        System.out.println("  2. 🔗 Ver estructura de la cadena");
        System.out.println("  3. 🚪 Salir");
        System.out.println();
        System.out.print("Seleccione una opción: ");
    }
    
    private static void crearYProcesarSolicitud() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("CREAR NUEVA SOLICITUD DE SOPORTE");
        System.out.println("═".repeat(60));
        
        System.out.print("\nNombre del cliente: ");
        String cliente = scanner.nextLine().trim();
        
        if (cliente.isEmpty()) {
            System.out.println("\n❌ El nombre del cliente no puede estar vacío");
            return;
        }
        
        System.out.println("\nDescripción del problema:");
        String descripcion = scanner.nextLine().trim();
        
        if (descripcion.isEmpty()) {
            System.out.println("\n❌ La descripción no puede estar vacía");
            return;
        }
        
        System.out.println("\nSeleccione la prioridad:");
        System.out.println("1. BÁSICO    - Consultas generales, preguntas simples");
        System.out.println("2. INTERMEDIO - Problemas técnicos, configuraciones");
        System.out.println("3. CRÍTICO    - Sistemas caídos, emergencias");
        System.out.print("\nOpción: ");
        
        int prioridadOpcion = leerOpcion(1, 3);
        Priority prioridad;
        
        switch (prioridadOpcion) {
            case 1:
                prioridad = Priority.BASIC;
                break;
            case 2:
                prioridad = Priority.INTERMEDIATE;
                break;
            case 3:
                prioridad = Priority.CRITICAL;
                break;
            default:
                prioridad = Priority.BASIC;
        }
        
        String ticketId = String.format("T-%04d", contadorTickets++);
        SupportRequest solicitud = new SupportRequest(ticketId, cliente, descripcion, prioridad);
        
        System.out.println("\n\n" + "═".repeat(60));
        System.out.println("PROCESANDO SOLICITUD EN LA CADENA");
        System.out.println("═".repeat(60) + "\n");
        
        cadenaDeResponsabilidad.procesarSolicitud(solicitud);
    }
    
    private static void verEstructuraCadena() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("ESTRUCTURA DE LA CADENA DE RESPONSABILIDAD");
        System.out.println("═".repeat(60));
        
        System.out.println("\n🔗 Flujo del patrón Chain of Responsibility:\n");
        
        System.out.println("   ┌─────────────────────────┐");
        System.out.println("   │   SOLICITUD RECIBIDA    │");
        System.out.println("   └───────────┬─────────────┘");
        System.out.println("               │");
        System.out.println("               ▼");
        System.out.println("   ┌─────────────────────────┐");
        System.out.println("   │  NIVEL 1: BÁSICO        │  ◄── Prioridad: BASIC");
        System.out.println("   │  (Soporte General)      │");
        System.out.println("   └───────────┬─────────────┘");
        System.out.println("               │ ¿No puede manejar?");
        System.out.println("               ▼");
        System.out.println("   ┌─────────────────────────┐");
        System.out.println("   │  NIVEL 2: INTERMEDIO    │  ◄── Prioridad: INTERMEDIATE");
        System.out.println("   │  (Técnico)              │");
        System.out.println("   └───────────┬─────────────┘");
        System.out.println("               │ ¿No puede manejar?");
        System.out.println("               ▼");
        System.out.println("   ┌─────────────────────────┐");
        System.out.println("   │  NIVEL 3: CRÍTICO       │  ◄── Prioridad: CRITICAL");
        System.out.println("   │  (Ingeniería)           │");
        System.out.println("   └─────────────────────────┘\n");
        
        System.out.println("📌 Características del patrón:");
        System.out.println("   ✅ Desacoplamiento: El cliente no sabe quién procesa");
        System.out.println("   ✅ Escalabilidad: Fácil agregar nuevos niveles");
        System.out.println("   ✅ Flexibilidad: Cada handler decide si procesa o delega");
        System.out.println("   ✅ Responsabilidad única: Cada nivel tiene su propósito\n");
    }
    
    private static int leerOpcion(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int opcion = Integer.parseInt(input);
                
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    System.out.print("Opción inválida. Ingrese un número entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }
    
    private static void imprimirBanner() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║         🔗 SISTEMA DE SOPORTE MULTINIVEL 🔗                ║");
        System.out.println("║                                                            ║");
        System.out.println("║            Patrón Chain of Responsibility                  ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("   Estudiante: Javi");
        System.out.println("   Código: 20231020172");
        System.out.println("   Universidad Distrital Francisco José de Caldas");
        System.out.println("   Materia: Ingeniería de Software");
        System.out.println();
    }
}   