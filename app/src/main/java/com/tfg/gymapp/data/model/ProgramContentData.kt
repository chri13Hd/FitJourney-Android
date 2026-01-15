package com.tfg.gymapp.data.model

// Contenido diario de cada programa por día
val programDailyContentMap = mapOf(
    "Bajar de peso" to listOf(
        DailyActivity(1, "Rutina para bajar de peso", "Realiza una caminata rápida de 20 minutos para activar el metabolismo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2Fcardio.mp4?alt=media&token=031527f8-9809-4273-b2ad-1f4ee8abe341", 900),
        DailyActivity(2, "Cardio inicial", "Comienza con un calentamiento de 20 minutos, pedaleando a un ritmo suave.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2Fbicicleta.mp4?alt=media&token=4cc22eb7-3df9-4126-b721-d9e1d61a6b8b", 1000),
        DailyActivity(3, "Cardio inicial", "Salta la cuerda el máximo tiempo que puedas, repitelo 4 veces, recuerda descansar el tiempo que necesites.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2Fcuerda.mp4?alt=media&token=df22d193-cf14-4adf-abc9-1b886c155795", 1100),
        DailyActivity(4, "Sprint en Cinta de Correr", "Comienza corriendo lo mas rapido que puedas por 1 minuto, luego descansa 3 minutos o el tiempo que necesites para repetirlo unas 4 veces.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FsprintCinta.mp4?alt=media&token=0b4af0c0-4708-43c3-9eae-11eb20725e8d", 1200),
        DailyActivity(5, "Cardio Skater a Burpee de una sola pierna", "Comienza saltando hacia un lado, aterriza en el pie derecho, con la pierna izquierda hacia el lado, luego realiza un burpee con una sola pierna, solo con la pierna derecha, manos al suelo y regresa a la posición de pie sobre el derecho, salta lateralmente a la izquierda, repite con la pierna izquierda, hazlo 4 veces a 12 repeticiones cada una.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2Fburpee.mp4?alt=media&token=204731ff-8ef5-4042-a77a-b0cc87419373", 1300)
    ),

    "Ganar masa muscular" to listOf(
        DailyActivity(1, "Pecho y tríceps", "Ejercicios para desarrollar pectorales y brazos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FPechoTricep.mp4?alt=media&token=56705268-b606-48b4-9149-b3a10025530b", 900),
        DailyActivity(2, "Espalda y bíceps", "Fortalece tu espalda y bíceps con remo y curl con peso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FEspaldaBicep.mp4?alt=media&token=75871aae-5f13-4d27-8fa1-1f19d43c4572", 1000),
        DailyActivity(3, "Piernas completas", "Sentadillas, zancadas y peso muerto para trabajar tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FPiernaCompleta.mp4?alt=media&token=d97df808-5380-4f0d-bd1b-726126afdf7b", 1100),
        DailyActivity(4, "Hombros y core", "Fortalece deltoides, trapecio y abdominales en una rutina mixta.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FHombrosCore.mp4?alt=media&token=4136a982-5fde-40cf-8a7e-8ec30396201f", 1200),
        DailyActivity(5, "Full body", "Entrenamiento global con foco en fuerza, repeticiones y técnica.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FCrucesSaltos.mp4?alt=media&token=759739c2-87bc-4a73-b999-521f2e88aba5", 1300)
    ),

    "Flexibilidad" to listOf(
        DailyActivity(1, "Yoga suave", "Secuencia básica de yoga para activar el cuerpo y calmar la mente.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FSecuenciaSuave.mp4?alt=media&token=5ed0dffd-86e1-434f-9427-0167d8372e42", 900),
        DailyActivity(2, "Movilidad articular", "Ejercicios para lubricar articulaciones y mejorar rango de movimiento.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FArticulaciones.mp4?alt=media&token=3b1f9f22-b272-49a0-ba1a-3bba43b5422a", 1000),
        DailyActivity(3, "Estiramientos largos", "Estiramiento de cadenas musculares sostenidos por 30 segundos o más.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FEstiramientoLargo.mp4?alt=media&token=cf0cb26a-c06e-4ab8-a82e-b4e1b9253078", 1100),
        DailyActivity(4, "Yoga flow", "Flujo continuo con respiración para ganar elasticidad y control corporal.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FYogaFlow.mp4?alt=media&token=210f7f13-42a6-4d91-9b1d-c43ce335721b", 1200)
    ),

    "Mejorar resistencia" to listOf(
        DailyActivity(1, "HIIT cardiovascular", "Entrenamiento con intervalos que mejora el VO2 max y quema grasa.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FCardiovascular.mp4?alt=media&token=33d329d0-b0fd-4f23-9d64-150977594fa4", 900),
        DailyActivity(2, "Carrera continua", "Sesión de trote constante de 25-30 minutos a ritmo moderado.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FCarreraContinua.mp4?alt=media&token=855cf2dd-97a8-4568-87f0-68a838523ea6", 1000),
        DailyActivity(3, "Pliometría", "Saltos, burpees y escaladores para entrenar velocidad y potencia.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FCardioBurpee.mp4?alt=media&token=135cb28f-5df8-4f55-898d-55ac0fd1411b", 1100)
    ),

    "Comer más saludable" to listOf(
        DailyActivity(1, "Planificación semanal", "Aprende a planificar tus comidas para mantenerte enfocado en tus objetivos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FPlanificacionSemanal.mp4?alt=media&token=529caa61-fbab-4d3a-8168-7f02d5a9c307", 900),
        DailyActivity(2, "Compras inteligentes", "Tips para elegir productos reales y evitar ultraprocesados.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FCompraInteligente.mp4?alt=media&token=5107a4c3-bd0f-4493-a12d-6a047fcc844a", 1000),
        DailyActivity(3, "Comidas balanceadas", "Descubre cómo combinar proteínas, carbohidratos y grasas de forma adecuada.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FComidaBalanceada.mp4?alt=media&token=63238fc8-79d3-4e4e-ac3e-ae7bb2d19b34", 1100),
        DailyActivity(4, "Evita tentaciones", "Estrategias para controlar el hambre emocional y antojos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FEvitaTentaciones.mp4?alt=media&token=9ebb0cfc-f804-49fe-a79a-7677a1cb0bf3", 1200),
        DailyActivity(5, "Recetas saludables", "Preparaciones simples y rápidas con ingredientes nutritivos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FRecetasSaludable.mp4?alt=media&token=9db6b596-11aa-4abc-8ac4-d0c565382fa5", 1300),
        DailyActivity(6, "Organiza tu cocina", "Ten a la mano alimentos saludables y deja fuera lo que no necesitas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FOrganizaCocina.mp4?alt=media&token=334be226-f8aa-4801-8e18-a8813085f194", 1400),
        DailyActivity(7, "Día libre", "Disfruta tu comida favorita sin culpa. ¡El equilibrio es clave!", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/ComerSaludable%2FComidaLibre.mp4?alt=media&token=3a74e311-7223-4966-be3a-6d7ddd8c30d5", 300)
    ),

    "Tonificar cuerpo" to listOf(
        DailyActivity(1, "Rutina con bandas", "Tonifica brazos y piernas con bandas elásticas de resistencia.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinaBandas.mp4?alt=media&token=45a2b392-ea00-4b42-acdb-5d5007cec9e9", 900),
        DailyActivity(2, "Peso corporal", "Usa tu propio cuerpo para trabajar abdomen, glúteos y pecho.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FPesoCorporal.mp4?alt=media&token=b2491f36-8779-4618-b549-11216941dc64", 1000),
        DailyActivity(3, "Tabata tonificante", "Entrena con series cortas y pausas para definir sin pesas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FTabata.mp4?alt=media&token=cbacae2c-091a-4ac6-a0f8-2369a57d45b0", 1100),
        DailyActivity(4, "Full body suave", "Rutina accesible pero efectiva para definir con poco impacto.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FFullBody.mp4?alt=media&token=61840506-d7f5-494e-80b8-ed26d2291213", 1200)
    )
)
