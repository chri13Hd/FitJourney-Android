package com.tfg.gymapp.data.model

val programRoutineMap = mapOf(
    "Bajar de peso" to listOf(
        Routine(
            dayNumber = 1,
            exercises = listOf(
                // 🔥 Calentamiento
                Exercise(
                    name = "Jumping Jacks",
                    description = "Activa todo el cuerpo, eleva pulsaciones y prepara para la rutina.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FJumpinJacks.mp4?alt=media&token=ebe0b66e-1f01-49de-96d9-aa993a956e2d",
                    reps = "2 min",
                    type = ExerciseType.CALENTAMIENTO
                ),

                // 💪 Ejercicios principales
                Exercise(
                    name = "Sentadillas con peso corporal",
                    description = "Fortalece glúteos y piernas, ayuda a quemar grasa.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FSentadillaPesoCorporal.mp4?alt=media&token=82310018-5c3b-4d87-a1db-791783c19a3b",
                    reps = "3x15",
                    type = ExerciseType.PRINCIPAL
                ),
                Exercise(
                    name = "Mountain Climbers",
                    description = "Ejercicio cardiovascular intenso para abdomen y resistencia.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FMountainClimbers.mp4?alt=media&token=26927515-dc36-43a4-881b-ee3989429944",
                    reps = "3x30 seg",
                    type = ExerciseType.PRINCIPAL
                ),
                Exercise(
                    name = "Zancadas alternas",
                    description = "Activa tren inferior y mejora coordinación.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FZancadas.mp4?alt=media&token=b09d4f73-9b38-4fab-9c7d-6d06510694f4",
                    reps = "3x12 por pierna",
                    type = ExerciseType.PRINCIPAL
                ),
                Exercise(
                    name = "Burpees",
                    description = "Trabajo completo del cuerpo con alto gasto calórico.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FBurpee.mp4?alt=media&token=543657ed-337d-46ef-8fff-135cd6a63a32",
                    reps = "3x10",
                    type = ExerciseType.PRINCIPAL
                ),
                Exercise(
                    name = "Plancha abdominal",
                    description = "Fortalece el core y mejora la estabilidad general.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FPlancha.mp4?alt=media&token=0082aa26-73f2-413c-ae8e-0fb0f7bca2d9",
                    reps = "3x30 seg",
                    type = ExerciseType.PRINCIPAL
                ),

                // 🧘 Estiramientos
                Exercise(
                    name = "Estiramiento de cuádriceps",
                    description = "Relaja piernas tras los ejercicios intensos.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoCu%C3%A1driceps.mp4?alt=media&token=d459f816-3b59-4392-8431-f61bc6eaafde",
                    reps = "30 seg por pierna",
                    type = ExerciseType.ESTIRAMIENTO
                ),
                Exercise(
                    name = "Estiramiento de espalda baja",
                    description = "Alivia tensión acumulada en zona lumbar.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoEspaldaBaja.mp4?alt=media&token=af084f37-6827-4c97-a2ff-ded2468ba3e7",
                    reps = "30 seg",
                    type = ExerciseType.ESTIRAMIENTO
                ),
                Exercise(
                    name = "Estiramiento de isquiotibiales",
                    description = "Mejora flexibilidad posterior y recuperación.",
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoIsquiotibiales.mp4?alt=media&token=cdc22784-1717-4f8d-b421-f0bd5f14ba7f",
                    reps = "30 seg por pierna",
                    type = ExerciseType.ESTIRAMIENTO
                )
            )
        ),

        Routine(
            dayNumber = 2,
            exercises = listOf(
                Exercise("Jumping Jacks Día 2", "Activa todo el cuerpo, eleva pulsaciones y prepara para la rutina.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FJumpinJacks.mp4?alt=media&token=ebe0b66e-1f01-49de-96d9-aa993a956e2d", "1 seg", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con peso corporal", "Fortalece glúteos y piernas, ayuda a quemar grasa.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FSentadillaPesoCorporal.mp4?alt=media&token=82310018-5c3b-4d87-a1db-791783c19a3b", "1x1", ExerciseType.PRINCIPAL),
                Exercise("Mountain Climbers", "Ejercicio cardiovascular intenso para abdomen y resistencia.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FMountainClimbers.mp4?alt=media&token=26927515-dc36-43a4-881b-ee3989429944", "1x1 seg", ExerciseType.PRINCIPAL),
                Exercise("Zancadas alternas", "Activa tren inferior y mejora coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FZancadas.mp4?alt=media&token=b09d4f73-9b38-4fab-9c7d-6d06510694f4", "1x1 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Burpees", "Trabajo completo del cuerpo con alto gasto calórico.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FBurpee.mp4?alt=media&token=543657ed-337d-46ef-8fff-135cd6a63a32", "1x1", ExerciseType.PRINCIPAL),
                Exercise("Plancha abdominal", "Fortalece el core y mejora la estabilidad general.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FPlancha.mp4?alt=media&token=0082aa26-73f2-413c-ae8e-0fb0f7bca2d9", "1x1 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de cuádriceps", "Relaja piernas tras los ejercicios intensos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoCu%C3%A1driceps.mp4?alt=media&token=d459f816-3b59-4392-8431-f61bc6eaafde", "1 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda baja", "Alivia tensión acumulada en zona lumbar.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoEspaldaBaja.mp4?alt=media&token=af084f37-6827-4c97-a2ff-ded2468ba3e7", "1 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de isquiotibiales", "Mejora flexibilidad posterior y recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoIsquiotibiales.mp4?alt=media&token=cdc22784-1717-4f8d-b421-f0bd5f14ba7f", "1 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 3,
            exercises = listOf(
                Exercise("Jumping Jacks Día 3", "Activa todo el cuerpo, eleva pulsaciones y prepara para la rutina.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FJumpinJacks.mp4?alt=media&token=ebe0b66e-1f01-49de-96d9-aa993a956e2d", "2 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con peso corporal", "Fortalece glúteos y piernas, ayuda a quemar grasa.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FSentadillaPesoCorporal.mp4?alt=media&token=82310018-5c3b-4d87-a1db-791783c19a3b", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Mountain Climbers", "Ejercicio cardiovascular intenso para abdomen y resistencia.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FMountainClimbers.mp4?alt=media&token=26927515-dc36-43a4-881b-ee3989429944", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Zancadas alternas", "Activa tren inferior y mejora coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FZancadas.mp4?alt=media&token=b09d4f73-9b38-4fab-9c7d-6d06510694f4", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Burpees", "Trabajo completo del cuerpo con alto gasto calórico.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FBurpee.mp4?alt=media&token=543657ed-337d-46ef-8fff-135cd6a63a32", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Plancha abdominal", "Fortalece el core y mejora la estabilidad general.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FPlancha.mp4?alt=media&token=0082aa26-73f2-413c-ae8e-0fb0f7bca2d9", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de cuádriceps", "Relaja piernas tras los ejercicios intensos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoCu%C3%A1driceps.mp4?alt=media&token=d459f816-3b59-4392-8431-f61bc6eaafde", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda baja", "Alivia tensión acumulada en zona lumbar.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoEspaldaBaja.mp4?alt=media&token=af084f37-6827-4c97-a2ff-ded2468ba3e7", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de isquiotibiales", "Mejora flexibilidad posterior y recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoIsquiotibiales.mp4?alt=media&token=cdc22784-1717-4f8d-b421-f0bd5f14ba7f", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 4,
            exercises = listOf(
                Exercise("Jumping Jacks Día 4", "Activa todo el cuerpo, eleva pulsaciones y prepara para la rutina.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FJumpinJacks.mp4?alt=media&token=ebe0b66e-1f01-49de-96d9-aa993a956e2d", "2 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con peso corporal", "Fortalece glúteos y piernas, ayuda a quemar grasa.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FSentadillaPesoCorporal.mp4?alt=media&token=82310018-5c3b-4d87-a1db-791783c19a3b", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Mountain Climbers", "Ejercicio cardiovascular intenso para abdomen y resistencia.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FMountainClimbers.mp4?alt=media&token=26927515-dc36-43a4-881b-ee3989429944", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Zancadas alternas", "Activa tren inferior y mejora coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FZancadas.mp4?alt=media&token=b09d4f73-9b38-4fab-9c7d-6d06510694f4", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Burpees", "Trabajo completo del cuerpo con alto gasto calórico.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FBurpee.mp4?alt=media&token=543657ed-337d-46ef-8fff-135cd6a63a32", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Plancha abdominal", "Fortalece el core y mejora la estabilidad general.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FPlancha.mp4?alt=media&token=0082aa26-73f2-413c-ae8e-0fb0f7bca2d9", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de cuádriceps", "Relaja piernas tras los ejercicios intensos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoCu%C3%A1driceps.mp4?alt=media&token=d459f816-3b59-4392-8431-f61bc6eaafde", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda baja", "Alivia tensión acumulada en zona lumbar.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoEspaldaBaja.mp4?alt=media&token=af084f37-6827-4c97-a2ff-ded2468ba3e7", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de isquiotibiales", "Mejora flexibilidad posterior y recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoIsquiotibiales.mp4?alt=media&token=cdc22784-1717-4f8d-b421-f0bd5f14ba7f", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 5,
            exercises = listOf(
                Exercise("Jumping Jacks Día 5", "Activa todo el cuerpo, eleva pulsaciones y prepara para la rutina.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FJumpinJacks.mp4?alt=media&token=ebe0b66e-1f01-49de-96d9-aa993a956e2d", "2 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con peso corporal", "Fortalece glúteos y piernas, ayuda a quemar grasa.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FSentadillaPesoCorporal.mp4?alt=media&token=82310018-5c3b-4d87-a1db-791783c19a3b", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Mountain Climbers", "Ejercicio cardiovascular intenso para abdomen y resistencia.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FMountainClimbers.mp4?alt=media&token=26927515-dc36-43a4-881b-ee3989429944", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Zancadas alternas", "Activa tren inferior y mejora coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FZancadas.mp4?alt=media&token=b09d4f73-9b38-4fab-9c7d-6d06510694f4", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Burpees", "Trabajo completo del cuerpo con alto gasto calórico.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FBurpee.mp4?alt=media&token=543657ed-337d-46ef-8fff-135cd6a63a32", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Plancha abdominal", "Fortalece el core y mejora la estabilidad general.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FPlancha.mp4?alt=media&token=0082aa26-73f2-413c-ae8e-0fb0f7bca2d9", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de cuádriceps", "Relaja piernas tras los ejercicios intensos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoCu%C3%A1driceps.mp4?alt=media&token=d459f816-3b59-4392-8431-f61bc6eaafde", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda baja", "Alivia tensión acumulada en zona lumbar.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoEspaldaBaja.mp4?alt=media&token=af084f37-6827-4c97-a2ff-ded2468ba3e7", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de isquiotibiales", "Mejora flexibilidad posterior y recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/GanarMusculo%2FRutinas%2FEstiramientoIsquiotibiales.mp4?alt=media&token=cdc22784-1717-4f8d-b421-f0bd5f14ba7f", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
    ),

    "Ganar masa muscular" to listOf(
        Routine(
            dayNumber = 1,
            exercises = listOf(
                Exercise("Calentamiento articular Día 1", "Movilidad y activación general antes de levantar peso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCalentamiento.mp4?alt=media&token=bf168ddb-0134-4708-bac4-b57c2c7c2f40", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Press banca con mancuernas", "Focalizado en pectorales mayores y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressBancaMancuernas.mp4?alt=media&token=bd9a06a5-b294-44f3-a39f-831d49f2be6e", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Remo con mancuerna", "Desarrolla dorsales y trapecios.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FRemoMancuerna.mp4?alt=media&token=ed3dc7b8-258a-43ad-8ed4-716e2a73cc40", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Curl de bíceps alterno", "Aísla el bíceps con control del movimiento.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCurlBicep.mp4?alt=media&token=42aeab3b-8ed1-4497-a0ef-98268f94730e", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Zancadas con peso", "Ejercicio compuesto para tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FZancadas.mp4?alt=media&token=5a85a6d9-5321-4aa2-bbf9-58be01910bcf", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Press militar con mancuernas", "Fortalece hombros y mejora estabilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressMilitar.mp4?alt=media&token=9bca9f04-4b3f-4b6c-976e-9de704faf3d9", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de pectorales", "Previene rigidez tras entrenamiento de torso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetoPectoral.mp4?alt=media&token=68f53c7d-a8a4-4321-ab1c-e448638bf789", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de bíceps y antebrazos", "Relaja los brazos y mejora circulación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiramientoBicep.mp4?alt=media&token=b12c612b-76ad-4957-b650-f0e02036a7c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de glúteos y piernas", "Alivia tensión posterior al trabajo de piernas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetosPiernas.mp4?alt=media&token=02762a3d-05c7-48ef-bd62-862fa04c1efb", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 2,
            exercises = listOf(
                Exercise("Calentamiento articular Día 2", "Movilidad y activación general antes de levantar peso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCalentamiento.mp4?alt=media&token=bf168ddb-0134-4708-bac4-b57c2c7c2f40", "1 min", ExerciseType.CALENTAMIENTO),
                Exercise("Press banca con mancuernas", "Focalizado en pectorales mayores y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressBancaMancuernas.mp4?alt=media&token=bd9a06a5-b294-44f3-a39f-831d49f2be6e", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Remo con mancuerna", "Desarrolla dorsales y trapecios.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FRemoMancuerna.mp4?alt=media&token=ed3dc7b8-258a-43ad-8ed4-716e2a73cc40", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Curl de bíceps alterno", "Aísla el bíceps con control del movimiento.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCurlBicep.mp4?alt=media&token=42aeab3b-8ed1-4497-a0ef-98268f94730e", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Zancadas con peso", "Ejercicio compuesto para tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FZancadas.mp4?alt=media&token=5a85a6d9-5321-4aa2-bbf9-58be01910bcf", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Press militar con mancuernas", "Fortalece hombros y mejora estabilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressMilitar.mp4?alt=media&token=9bca9f04-4b3f-4b6c-976e-9de704faf3d9", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de pectorales", "Previene rigidez tras entrenamiento de torso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetoPectoral.mp4?alt=media&token=68f53c7d-a8a4-4321-ab1c-e448638bf789", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de bíceps y antebrazos", "Relaja los brazos y mejora circulación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiramientoBicep.mp4?alt=media&token=b12c612b-76ad-4957-b650-f0e02036a7c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de glúteos y piernas", "Alivia tensión posterior al trabajo de piernas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetosPiernas.mp4?alt=media&token=02762a3d-05c7-48ef-bd62-862fa04c1efb", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 3,
            exercises = listOf(
                Exercise("Calentamiento articular Día 3", "Movilidad y activación general antes de levantar peso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCalentamiento.mp4?alt=media&token=bf168ddb-0134-4708-bac4-b57c2c7c2f40", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Press banca con mancuernas", "Focalizado en pectorales mayores y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressBancaMancuernas.mp4?alt=media&token=bd9a06a5-b294-44f3-a39f-831d49f2be6e", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Remo con mancuerna", "Desarrolla dorsales y trapecios.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FRemoMancuerna.mp4?alt=media&token=ed3dc7b8-258a-43ad-8ed4-716e2a73cc40", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Curl de bíceps alterno", "Aísla el bíceps con control del movimiento.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCurlBicep.mp4?alt=media&token=42aeab3b-8ed1-4497-a0ef-98268f94730e", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Zancadas con peso", "Ejercicio compuesto para tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FZancadas.mp4?alt=media&token=5a85a6d9-5321-4aa2-bbf9-58be01910bcf", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Press militar con mancuernas", "Fortalece hombros y mejora estabilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressMilitar.mp4?alt=media&token=9bca9f04-4b3f-4b6c-976e-9de704faf3d9", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de pectorales", "Previene rigidez tras entrenamiento de torso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetoPectoral.mp4?alt=media&token=68f53c7d-a8a4-4321-ab1c-e448638bf789", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de bíceps y antebrazos", "Relaja los brazos y mejora circulación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiramientoBicep.mp4?alt=media&token=b12c612b-76ad-4957-b650-f0e02036a7c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de glúteos y piernas", "Alivia tensión posterior al trabajo de piernas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetosPiernas.mp4?alt=media&token=02762a3d-05c7-48ef-bd62-862fa04c1efb", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 4,
            exercises = listOf(
                Exercise("Calentamiento articular Día 4", "Movilidad y activación general antes de levantar peso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCalentamiento.mp4?alt=media&token=bf168ddb-0134-4708-bac4-b57c2c7c2f40", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Press banca con mancuernas", "Focalizado en pectorales mayores y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressBancaMancuernas.mp4?alt=media&token=bd9a06a5-b294-44f3-a39f-831d49f2be6e", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Remo con mancuerna", "Desarrolla dorsales y trapecios.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FRemoMancuerna.mp4?alt=media&token=ed3dc7b8-258a-43ad-8ed4-716e2a73cc40", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Curl de bíceps alterno", "Aísla el bíceps con control del movimiento.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCurlBicep.mp4?alt=media&token=42aeab3b-8ed1-4497-a0ef-98268f94730e", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Zancadas con peso", "Ejercicio compuesto para tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FZancadas.mp4?alt=media&token=5a85a6d9-5321-4aa2-bbf9-58be01910bcf", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Press militar con mancuernas", "Fortalece hombros y mejora estabilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressMilitar.mp4?alt=media&token=9bca9f04-4b3f-4b6c-976e-9de704faf3d9", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de pectorales", "Previene rigidez tras entrenamiento de torso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetoPectoral.mp4?alt=media&token=68f53c7d-a8a4-4321-ab1c-e448638bf789", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de bíceps y antebrazos", "Relaja los brazos y mejora circulación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiramientoBicep.mp4?alt=media&token=b12c612b-76ad-4957-b650-f0e02036a7c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de glúteos y piernas", "Alivia tensión posterior al trabajo de piernas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetosPiernas.mp4?alt=media&token=02762a3d-05c7-48ef-bd62-862fa04c1efb", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 5,
            exercises = listOf(
                Exercise("Calentamiento articular Día 5", "Movilidad y activación general antes de levantar peso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCalentamiento.mp4?alt=media&token=bf168ddb-0134-4708-bac4-b57c2c7c2f40", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Press banca con mancuernas", "Focalizado en pectorales mayores y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressBancaMancuernas.mp4?alt=media&token=bd9a06a5-b294-44f3-a39f-831d49f2be6e", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Remo con mancuerna", "Desarrolla dorsales y trapecios.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FRemoMancuerna.mp4?alt=media&token=ed3dc7b8-258a-43ad-8ed4-716e2a73cc40", "4x10", ExerciseType.PRINCIPAL),
                Exercise("Curl de bíceps alterno", "Aísla el bíceps con control del movimiento.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FCurlBicep.mp4?alt=media&token=42aeab3b-8ed1-4497-a0ef-98268f94730e", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Zancadas con peso", "Ejercicio compuesto para tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FZancadas.mp4?alt=media&token=5a85a6d9-5321-4aa2-bbf9-58be01910bcf", "3x12 por pierna", ExerciseType.PRINCIPAL),
                Exercise("Press militar con mancuernas", "Fortalece hombros y mejora estabilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FPressMilitar.mp4?alt=media&token=9bca9f04-4b3f-4b6c-976e-9de704faf3d9", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de pectorales", "Previene rigidez tras entrenamiento de torso.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetoPectoral.mp4?alt=media&token=68f53c7d-a8a4-4321-ab1c-e448638bf789", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de bíceps y antebrazos", "Relaja los brazos y mejora circulación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiramientoBicep.mp4?alt=media&token=b12c612b-76ad-4957-b650-f0e02036a7c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de glúteos y piernas", "Alivia tensión posterior al trabajo de piernas.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/BajarPeso%2FRutinas%2FEstiraminetosPiernas.mp4?alt=media&token=02762a3d-05c7-48ef-bd62-862fa04c1efb", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
            )
        ),
    ),

    "Flexibilidad" to listOf(
        Routine(
            dayNumber = 1,
            exercises = listOf(
                Exercise("Respiración y activación Día 1", "Inhala profundo y prepara el cuerpo para una sesión suave de estiramientos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiracion.mp4?alt=media&token=a4bf9a01-9d55-449e-8321-0404ff2df0c5", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Estiramiento de cuello y hombros", "Libera tensiones cervicales y activa la parte superior del cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoCuelloHombros.mp4?alt=media&token=4027ef50-4203-49b5-bd02-7761d0a42f83", "2x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de columna en gato-vaca", "Mejora movilidad y flexión de espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaGatoVaca.mp4?alt=media&token=b839bd24-c0e3-4c48-81ef-261a22e55ed6", "2x10", ExerciseType.PRINCIPAL),
                Exercise("Flexión hacia adelante sentado", "Alarga la espalda baja e isquiotibiales.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FFlexionAdelante.mp4?alt=media&token=49be328f-00b4-4ef3-9533-4cc1898930ed", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Postura del niño", "Relaja todo el cuerpo y estira la espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaNi%C3%B1o.mp4?alt=media&token=b0c2b15c-a527-49f5-a2d1-2c5e16301009", "2x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Estocada con torsión", "Estira flexores de cadera y activa el core.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstocadaTorsion.mp4?alt=media&token=c47b967b-4b22-4bc2-9baf-c4f1eebe6657", "2x30 seg por lado", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de glúteos en el suelo", "Relaja la zona lumbar y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoGluteo.mp4?alt=media&token=8be252c3-a5ce-4e39-a638-8e445073ac80", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas en mariposa", "Abre caderas y mejora movilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FMariposa.mp4?alt=media&token=38b6c3f5-6ccb-42c9-8a41-d1f2e850efad", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Relajación final acostado", "Respira profundo y suelta todo el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiraci%C3%B3nFinal.mp4?alt=media&token=9329f638-a398-430d-8b4e-c61de609e443", "2 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 2,
            exercises = listOf(
                Exercise("Respiración y activación Día 2", "Inhala profundo y prepara el cuerpo para una sesión suave de estiramientos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiracion.mp4?alt=media&token=a4bf9a01-9d55-449e-8321-0404ff2df0c5", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Estiramiento de cuello y hombros", "Libera tensiones cervicales y activa la parte superior del cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoCuelloHombros.mp4?alt=media&token=4027ef50-4203-49b5-bd02-7761d0a42f83", "2x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de columna en gato-vaca", "Mejora movilidad y flexión de espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaGatoVaca.mp4?alt=media&token=b839bd24-c0e3-4c48-81ef-261a22e55ed6", "2x10", ExerciseType.PRINCIPAL),
                Exercise("Flexión hacia adelante sentado", "Alarga la espalda baja e isquiotibiales.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FFlexionAdelante.mp4?alt=media&token=49be328f-00b4-4ef3-9533-4cc1898930ed", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Postura del niño", "Relaja todo el cuerpo y estira la espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaNi%C3%B1o.mp4?alt=media&token=b0c2b15c-a527-49f5-a2d1-2c5e16301009", "2x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Estocada con torsión", "Estira flexores de cadera y activa el core.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstocadaTorsion.mp4?alt=media&token=c47b967b-4b22-4bc2-9baf-c4f1eebe6657", "2x30 seg por lado", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de glúteos en el suelo", "Relaja la zona lumbar y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoGluteo.mp4?alt=media&token=8be252c3-a5ce-4e39-a638-8e445073ac80", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas en mariposa", "Abre caderas y mejora movilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FMariposa.mp4?alt=media&token=38b6c3f5-6ccb-42c9-8a41-d1f2e850efad", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Relajación final acostado", "Respira profundo y suelta todo el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiraci%C3%B3nFinal.mp4?alt=media&token=9329f638-a398-430d-8b4e-c61de609e443", "2 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 3,
            exercises = listOf(
                Exercise("Respiración y activación Día 3", "Inhala profundo y prepara el cuerpo para una sesión suave de estiramientos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiracion.mp4?alt=media&token=a4bf9a01-9d55-449e-8321-0404ff2df0c5", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Estiramiento de cuello y hombros", "Libera tensiones cervicales y activa la parte superior del cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoCuelloHombros.mp4?alt=media&token=4027ef50-4203-49b5-bd02-7761d0a42f83", "2x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de columna en gato-vaca", "Mejora movilidad y flexión de espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaGatoVaca.mp4?alt=media&token=b839bd24-c0e3-4c48-81ef-261a22e55ed6", "2x10", ExerciseType.PRINCIPAL),
                Exercise("Flexión hacia adelante sentado", "Alarga la espalda baja e isquiotibiales.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FFlexionAdelante.mp4?alt=media&token=49be328f-00b4-4ef3-9533-4cc1898930ed\n", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Postura del niño", "Relaja todo el cuerpo y estira la espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaNi%C3%B1o.mp4?alt=media&token=b0c2b15c-a527-49f5-a2d1-2c5e16301009", "2x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Estocada con torsión", "Estira flexores de cadera y activa el core.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstocadaTorsion.mp4?alt=media&token=c47b967b-4b22-4bc2-9baf-c4f1eebe6657", "2x30 seg por lado", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de glúteos en el suelo", "Relaja la zona lumbar y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoGluteo.mp4?alt=media&token=8be252c3-a5ce-4e39-a638-8e445073ac80", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas en mariposa", "Abre caderas y mejora movilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FMariposa.mp4?alt=media&token=38b6c3f5-6ccb-42c9-8a41-d1f2e850efad", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Relajación final acostado", "Respira profundo y suelta todo el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiraci%C3%B3nFinal.mp4?alt=media&token=9329f638-a398-430d-8b4e-c61de609e443", "2 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 4,
            exercises = listOf(
                Exercise("Respiración y activación Día 4", "Inhala profundo y prepara el cuerpo para una sesión suave de estiramientos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiracion.mp4?alt=media&token=a4bf9a01-9d55-449e-8321-0404ff2df0c5", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Estiramiento de cuello y hombros", "Libera tensiones cervicales y activa la parte superior del cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoCuelloHombros.mp4?alt=media&token=4027ef50-4203-49b5-bd02-7761d0a42f83", "2x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de columna en gato-vaca", "Mejora movilidad y flexión de espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaGatoVaca.mp4?alt=media&token=b839bd24-c0e3-4c48-81ef-261a22e55ed6", "2x10", ExerciseType.PRINCIPAL),
                Exercise("Flexión hacia adelante sentado", "Alarga la espalda baja e isquiotibiales.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FFlexionAdelante.mp4?alt=media&token=49be328f-00b4-4ef3-9533-4cc1898930ed", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Postura del niño", "Relaja todo el cuerpo y estira la espalda.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FPosturaNi%C3%B1o.mp4?alt=media&token=b0c2b15c-a527-49f5-a2d1-2c5e16301009", "2x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Estocada con torsión", "Estira flexores de cadera y activa el core.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstocadaTorsion.mp4?alt=media&token=c47b967b-4b22-4bc2-9baf-c4f1eebe6657", "2x30 seg por lado", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de glúteos en el suelo", "Relaja la zona lumbar y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FEstiramientoGluteo.mp4?alt=media&token=8be252c3-a5ce-4e39-a638-8e445073ac80", "30 seg por lado", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas en mariposa", "Abre caderas y mejora movilidad.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FMariposa.mp4?alt=media&token=38b6c3f5-6ccb-42c9-8a41-d1f2e850efad", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Relajación final acostado", "Respira profundo y suelta todo el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Flexibilidad%2FRutinas%2FRespiraci%C3%B3nFinal.mp4?alt=media&token=9329f638-a398-430d-8b4e-c61de609e443", "2 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
    ),

    "Mejorar resistencia" to listOf(
        Routine(
            dayNumber = 1,
            exercises = listOf(
                Exercise("Calentamiento activo Día 1", "Movilidad general con trote suave y rotaciones articulares.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FCalentamiento.mp4?alt=media&token=998eda98-d863-4357-90bf-752fd5e8de2a", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Trote en el lugar", "Activa el ritmo cardiaco para preparar el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FTrote.mp4?alt=media&token=b72bb6bf-b75b-4b61-833b-c1000bb21f19", "3x2 min", ExerciseType.PRINCIPAL),
                Exercise("Saltos de tijera", "Ejercicio cardiovascular que mejora agilidad y coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FSaltosTijeras.mp4?alt=media&token=794971a5-39f9-41ed-90c4-c611b39fed07", "3x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Skaters laterales", "Potencia piernas y mejora la resistencia lateral.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FSkatersLAterales.mp4?alt=media&token=d6df19bc-4e14-4e2e-a819-c0727faa334d", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Escaladores", "Activa core y cardio al mismo tiempo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEscaladores.mp4?alt=media&token=e7fdb615-8f13-4b75-b529-51df0afe35a2", "3x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Burpees con salto", "Ejercicio intenso para todo el cuerpo y máxima quema de calorías.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FBurpeeSaltos.mp4?alt=media&token=d1cea2d9-a1e1-4ead-92ac-0e99dfd58cb7", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de piernas", "Alivia tensión en piernas y mejora recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientosPierna.mp4?alt=media&token=bead4adf-299e-4ca0-9b0d-e1ddb34bd752", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda", "Relaja la zona lumbar después de trabajo cardiovascular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientosEspalda.mp4?alt=media&token=754dbc67-f0ae-4aca-8817-bdd61164874a", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento global de cuerpo", "Relaja todo el cuerpo y disminuye la frecuencia cardíaca.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientoGlobal.mp4?alt=media&token=571f3d80-6541-44d6-82b7-60bd6aa13744", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 2,
            exercises = listOf(
                Exercise("Calentamiento activo Día 2", "Movilidad general con trote suave y rotaciones articulares.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FCalentamiento.mp4?alt=media&token=998eda98-d863-4357-90bf-752fd5e8de2a", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Trote en el lugar", "Activa el ritmo cardiaco para preparar el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FTrote.mp4?alt=media&token=b72bb6bf-b75b-4b61-833b-c1000bb21f19", "3x2 min", ExerciseType.PRINCIPAL),
                Exercise("Saltos de tijera", "Ejercicio cardiovascular que mejora agilidad y coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FSaltosTijeras.mp4?alt=media&token=794971a5-39f9-41ed-90c4-c611b39fed07", "3x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Skaters laterales", "Potencia piernas y mejora la resistencia lateral.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FSkatersLAterales.mp4?alt=media&token=d6df19bc-4e14-4e2e-a819-c0727faa334d", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Escaladores", "Activa core y cardio al mismo tiempo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEscaladores.mp4?alt=media&token=e7fdb615-8f13-4b75-b529-51df0afe35a2", "3x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Burpees con salto", "Ejercicio intenso para todo el cuerpo y máxima quema de calorías.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FBurpeeSaltos.mp4?alt=media&token=d1cea2d9-a1e1-4ead-92ac-0e99dfd58cb7", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de piernas", "Alivia tensión en piernas y mejora recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientosPierna.mp4?alt=media&token=bead4adf-299e-4ca0-9b0d-e1ddb34bd752", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda", "Relaja la zona lumbar después de trabajo cardiovascular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientosEspalda.mp4?alt=media&token=754dbc67-f0ae-4aca-8817-bdd61164874a", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento global de cuerpo", "Relaja todo el cuerpo y disminuye la frecuencia cardíaca.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientoGlobal.mp4?alt=media&token=571f3d80-6541-44d6-82b7-60bd6aa13744", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 3,
            exercises = listOf(
                Exercise("Calentamiento activo Día 3", "Movilidad general con trote suave y rotaciones articulares.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FCalentamiento.mp4?alt=media&token=998eda98-d863-4357-90bf-752fd5e8de2a", "3 min", ExerciseType.CALENTAMIENTO),
                Exercise("Trote en el lugar", "Activa el ritmo cardiaco para preparar el cuerpo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FTrote.mp4?alt=media&token=b72bb6bf-b75b-4b61-833b-c1000bb21f19", "3x2 min", ExerciseType.PRINCIPAL),
                Exercise("Saltos de tijera", "Ejercicio cardiovascular que mejora agilidad y coordinación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FSaltosTijeras.mp4?alt=media&token=794971a5-39f9-41ed-90c4-c611b39fed07", "3x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Skaters laterales", "Potencia piernas y mejora la resistencia lateral.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FSkatersLAterales.mp4?alt=media&token=d6df19bc-4e14-4e2e-a819-c0727faa334d", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Escaladores", "Activa core y cardio al mismo tiempo.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEscaladores.mp4?alt=media&token=e7fdb615-8f13-4b75-b529-51df0afe35a2", "3x40 seg", ExerciseType.PRINCIPAL),
                Exercise("Burpees con salto", "Ejercicio intenso para todo el cuerpo y máxima quema de calorías.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FBurpeeSaltos.mp4?alt=media&token=d1cea2d9-a1e1-4ead-92ac-0e99dfd58cb7", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de piernas", "Alivia tensión en piernas y mejora recuperación.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientosPierna.mp4?alt=media&token=bead4adf-299e-4ca0-9b0d-e1ddb34bd752", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de espalda", "Relaja la zona lumbar después de trabajo cardiovascular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientosEspalda.mp4?alt=media&token=754dbc67-f0ae-4aca-8817-bdd61164874a", "30 seg", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento global de cuerpo", "Relaja todo el cuerpo y disminuye la frecuencia cardíaca.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/MejorarResistencia%2FRutinas%2FEstiramientoGlobal.mp4?alt=media&token=571f3d80-6541-44d6-82b7-60bd6aa13744", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
    ),

    "Tonificar cuerpo" to listOf(
        Routine(
            dayNumber = 1,
            exercises = listOf(
                Exercise("Activación articular Día 1", "Movimientos suaves para activar músculos y articulaciones.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FACtivarCuerpo.mp4?alt=media&token=fad71fc8-90ad-4788-a7c9-6b12ece679f9", "2 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con salto", "Ejercicio dinámico para piernas y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FSentadillasSalto.mp4?alt=media&token=1ab4a064-ffd3-4c53-a0e5-c54178a3a681", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Flexiones de brazos", "Tonifica pectorales, hombros y brazos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FFlexiones.mp4?alt=media&token=abb7ec3d-a7f8-4027-af12-c80c0ec884c1", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Puente de glúteos", "Activa y fortalece el tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FPuenteGluteo.mp4?alt=media&token=1d537791-0335-4a42-a874-eeb24b70f145", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Abdominales bicicleta", "Trabaja abdomen y oblicuos con control.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FBicicleta.mp4?alt=media&token=84a89f94-1cc9-4e2d-ae20-9dbd354f6a02", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Elevaciones laterales con bandas", "Define hombros sin peso adicional.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FLateralesBanda.mp4?alt=media&token=e9d0b493-a209-4724-b2e5-667175eaf3fe", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de brazos", "Relaja hombros, bíceps y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoBrazos.mp4?alt=media&token=ee095870-8166-408e-b1a4-1504683c76c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas y glúteos", "Reduce tensión después del trabajo muscular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoPierna.mp4?alt=media&token=89866def-5463-4d85-a511-0777db3900c2", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Respiración final y elongación", "Respira profundo y recupera postura.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FRespiracionFinal.mp4?alt=media&token=b1d8b5d7-552b-4671-b6ca-377d0133caee", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 2,
            exercises = listOf(
                Exercise("Activación articular Día 2", "Movimientos suaves para activar músculos y articulaciones.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FACtivarCuerpo.mp4?alt=media&token=fad71fc8-90ad-4788-a7c9-6b12ece679f9", "4 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con salto", "Ejercicio dinámico para piernas y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FSentadillasSalto.mp4?alt=media&token=1ab4a064-ffd3-4c53-a0e5-c54178a3a681", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Flexiones de brazos", "Tonifica pectorales, hombros y brazos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FFlexiones.mp4?alt=media&token=abb7ec3d-a7f8-4027-af12-c80c0ec884c1", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Puente de glúteos", "Activa y fortalece el tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FPuenteGluteo.mp4?alt=media&token=1d537791-0335-4a42-a874-eeb24b70f145", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Abdominales bicicleta", "Trabaja abdomen y oblicuos con control.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FBicicleta.mp4?alt=media&token=84a89f94-1cc9-4e2d-ae20-9dbd354f6a02", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Elevaciones laterales con bandas", "Define hombros sin peso adicional.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FLateralesBanda.mp4?alt=media&token=e9d0b493-a209-4724-b2e5-667175eaf3fe", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de brazos", "Relaja hombros, bíceps y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoBrazos.mp4?alt=media&token=ee095870-8166-408e-b1a4-1504683c76c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas y glúteos", "Reduce tensión después del trabajo muscular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoPierna.mp4?alt=media&token=89866def-5463-4d85-a511-0777db3900c2", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Respiración final y elongación", "Respira profundo y recupera postura.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FRespiracionFinal.mp4?alt=media&token=b1d8b5d7-552b-4671-b6ca-377d0133caee", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 3,
            exercises = listOf(
                Exercise("Activación articular Día 3", "Movimientos suaves para activar músculos y articulaciones.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FACtivarCuerpo.mp4?alt=media&token=fad71fc8-90ad-4788-a7c9-6b12ece679f9", "4 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con salto", "Ejercicio dinámico para piernas y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FSentadillasSalto.mp4?alt=media&token=1ab4a064-ffd3-4c53-a0e5-c54178a3a681", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Flexiones de brazos", "Tonifica pectorales, hombros y brazos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FFlexiones.mp4?alt=media&token=abb7ec3d-a7f8-4027-af12-c80c0ec884c1", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Puente de glúteos", "Activa y fortalece el tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FPuenteGluteo.mp4?alt=media&token=1d537791-0335-4a42-a874-eeb24b70f145", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Abdominales bicicleta", "Trabaja abdomen y oblicuos con control.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FBicicleta.mp4?alt=media&token=84a89f94-1cc9-4e2d-ae20-9dbd354f6a02", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Elevaciones laterales con bandas", "Define hombros sin peso adicional.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FLateralesBanda.mp4?alt=media&token=e9d0b493-a209-4724-b2e5-667175eaf3fe", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de brazos", "Relaja hombros, bíceps y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoBrazos.mp4?alt=media&token=ee095870-8166-408e-b1a4-1504683c76c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas y glúteos", "Reduce tensión después del trabajo muscular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoPierna.mp4?alt=media&token=89866def-5463-4d85-a511-0777db3900c2", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Respiración final y elongación", "Respira profundo y recupera postura.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FRespiracionFinal.mp4?alt=media&token=b1d8b5d7-552b-4671-b6ca-377d0133caee", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
        Routine(
            dayNumber = 4,
            exercises = listOf(
                Exercise("Activación articular Día 4", "Movimientos suaves para activar músculos y articulaciones.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FACtivarCuerpo.mp4?alt=media&token=fad71fc8-90ad-4788-a7c9-6b12ece679f9", "4 min", ExerciseType.CALENTAMIENTO),
                Exercise("Sentadillas con salto", "Ejercicio dinámico para piernas y glúteos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FSentadillasSalto.mp4?alt=media&token=1ab4a064-ffd3-4c53-a0e5-c54178a3a681", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Flexiones de brazos", "Tonifica pectorales, hombros y brazos.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FFlexiones.mp4?alt=media&token=abb7ec3d-a7f8-4027-af12-c80c0ec884c1", "3x10", ExerciseType.PRINCIPAL),
                Exercise("Puente de glúteos", "Activa y fortalece el tren inferior.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FPuenteGluteo.mp4?alt=media&token=1d537791-0335-4a42-a874-eeb24b70f145", "3x15", ExerciseType.PRINCIPAL),
                Exercise("Abdominales bicicleta", "Trabaja abdomen y oblicuos con control.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FBicicleta.mp4?alt=media&token=84a89f94-1cc9-4e2d-ae20-9dbd354f6a02", "3x30 seg", ExerciseType.PRINCIPAL),
                Exercise("Elevaciones laterales con bandas", "Define hombros sin peso adicional.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FLateralesBanda.mp4?alt=media&token=e9d0b493-a209-4724-b2e5-667175eaf3fe", "3x12", ExerciseType.PRINCIPAL),
                Exercise("Estiramiento de brazos", "Relaja hombros, bíceps y tríceps.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoBrazos.mp4?alt=media&token=ee095870-8166-408e-b1a4-1504683c76c6", "30 seg por brazo", ExerciseType.ESTIRAMIENTO),
                Exercise("Estiramiento de piernas y glúteos", "Reduce tensión después del trabajo muscular.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FEstiramientoPierna.mp4?alt=media&token=89866def-5463-4d85-a511-0777db3900c2\n", "30 seg por pierna", ExerciseType.ESTIRAMIENTO),
                Exercise("Respiración final y elongación", "Respira profundo y recupera postura.", "https://firebasestorage.googleapis.com/v0/b/historiapp-cdg.firebasestorage.app/o/Tonificar%2FRutinas%2FRespiracionFinal.mp4?alt=media&token=b1d8b5d7-552b-4671-b6ca-377d0133caee", "1 min", ExerciseType.ESTIRAMIENTO),
            )
        ),
    ),
)