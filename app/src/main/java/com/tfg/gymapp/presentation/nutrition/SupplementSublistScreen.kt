// SupplementSublistScreen.kt
package com.tfg.gymapp.presentation.nutrition

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tfg.gymapp.R

data class SupplementItem(
    val title: String,
    val imageRes: Int,
    val description: String
)

@Composable
fun SupplementSublistScreen(navController: NavController, supplementType: String) {
    val proteinList = listOf(
        SupplementItem(
            "Caseína",
            R.drawable.caseina,
            "La caseína es una proteína completa de alto valor biológico que se encuentra en la leche, representando aproximadamente el 80% del contenido proteico total. A diferencia del suero, la caseína se caracteriza por su digestión lenta y sostenida, lo que permite una liberación gradual de aminoácidos en el torrente sanguíneo durante un período de hasta 7-8 horas. Esta propiedad la convierte en una opción ideal para consumir antes de dormir, ayudando a mantener un estado anabólico durante el sueño y reduciendo el catabolismo muscular nocturno.\n\nAdemás, su perfil de aminoácidos es excelente, destacando por su alto contenido en glutamina, un aminoácido crucial para la recuperación y el sistema inmune. Diversos estudios han demostrado que la suplementación con caseína puede contribuir significativamente al desarrollo muscular, especialmente cuando se combina con entrenamiento de fuerza y una ingesta adecuada de proteínas diarias.\n\nTambién es apreciada por su capacidad para generar sensación de saciedad, lo cual puede ser útil en programas de control de peso. Se presenta comúnmente en formato de polvo para mezclar con agua o leche, y puede encontrarse como caseína micelar (la forma más pura y de liberación prolongada) o como caseinato cálcico (de absorción algo más rápida).\n\nEn resumen, la caseína es una herramienta eficaz tanto para la ganancia de masa muscular como para la preservación de tejido magro durante periodos de ayuno o déficit calórico prolongado."
        ),
        SupplementItem(
            "Complejos proteicos",
            R.drawable.complejos,
            "Los complejos proteicos son suplementos que combinan varias fuentes de proteínas en una misma fórmula, con el objetivo de proporcionar una liberación tanto rápida como sostenida de aminoácidos. Estos productos suelen mezclar proteínas de digestión rápida, como el suero de leche (whey), con otras de absorción media y lenta como la caseína, la albúmina del huevo o incluso proteínas vegetales.\n\nGracias a esta combinación, los complejos proteicos ofrecen una cobertura más amplia en cuanto a síntesis proteica: actúan de forma inmediata tras su ingesta y también mantienen un suministro constante de aminoácidos en el tiempo. Esto los convierte en una opción muy versátil, adecuada para tomar en cualquier momento del día, especialmente entre comidas o después del entrenamiento cuando no se puede hacer una comida completa.\n\nMuchos complejos también están enriquecidos con vitaminas, minerales o enzimas digestivas para mejorar la absorción y el aprovechamiento de los nutrientes. Son especialmente útiles en dietas con alta demanda proteica, en etapas de ganancia muscular o para personas con dificultades para alcanzar sus requerimientos diarios solo a través de la alimentación sólida.\n\nEn definitiva, los complejos proteicos son una solución eficaz para mantener un estado anabólico constante y apoyar tanto la recuperación como el crecimiento muscular de forma equilibrada."
        ),
        SupplementItem(
            "Huevo",
            R.drawable.huevo,
            "La proteína del huevo, en especial la albúmina (presente en la clara), es una de las fuentes proteicas más completas y con mayor valor biológico disponible. Contiene todos los aminoácidos esenciales en proporciones óptimas, lo que la convierte en una excelente opción para apoyar la síntesis de proteínas musculares y la recuperación postentrenamiento.\n\nAdemás de su perfil de aminoácidos, destaca por su alta digestibilidad y su contenido natural en vitaminas del grupo B, como la B12 y la biotina, esenciales para el metabolismo energético y el funcionamiento muscular. La proteína de huevo es una opción muy valorada por personas con intolerancia a la lactosa o alergia a las proteínas de la leche, ya que no contiene lactosa ni caseína.\n\nSuele presentarse en forma de polvo (clara deshidratada o albúmina pura), aunque también existen suplementos combinados que incluyen tanto la yema como la clara para aprovechar sus beneficios completos. Su digestión es más lenta que la del suero, pero más rápida que la de la caseína, por lo que puede considerarse una opción intermedia para cualquier momento del día.\n\nEn resumen, la proteína de huevo es una alternativa eficaz, bien tolerada y de alta calidad para deportistas que buscan una fuente proteica completa, especialmente adecuada para quienes siguen dietas sin lácteos."
        ),
        SupplementItem(
            "Matrix",
            R.drawable.matrix,
            "Las fórmulas proteicas tipo Matrix están diseñadas para ofrecer una liberación secuencial de aminoácidos mediante la combinación de múltiples tipos de proteínas con distintas velocidades de absorción. Suelen incluir proteínas de suero (rápida), albúmina de huevo (media) y caseína micelar (lenta), creando así una matriz equilibrada que proporciona nutrición muscular tanto inmediata como prolongada.\n\nEste tipo de suplemento es ideal para quienes buscan mantener el cuerpo en un estado anabólico continuo durante horas, reduciendo la degradación muscular y favoreciendo la síntesis proteica a lo largo del día. Por ello, puede utilizarse estratégicamente entre comidas, como batido nocturno antes de dormir, o como sustituto de una comida ligera.\n\nAdemás, muchas proteínas Matrix están enriquecidas con enzimas digestivas para mejorar la tolerancia y absorción, así como con vitaminas y minerales esenciales para optimizar el metabolismo proteico. Algunas fórmulas también incorporan ingredientes como colágeno, glutamina o creatina, dependiendo del enfoque del suplemento (recuperación, ganancia muscular, mantenimiento).\n\nEn definitiva, las proteínas Matrix son una solución versátil y completa, perfecta para deportistas que desean maximizar la recuperación muscular sin necesidad de múltiples suplementos aislados."
        ),
        SupplementItem(
            "Carne de vacuno",
            R.drawable.carne,
            "La proteína de carne de vacuno es una alternativa potente y de alta calidad a las proteínas lácteas. Se extrae principalmente de la carne magra mediante procesos hidrolizados o aislados, eliminando prácticamente toda la grasa, el colesterol y la lactosa. Este tipo de suplemento es ideal para quienes buscan una fuente de proteína animal distinta al suero o tienen intolerancia a los productos lácteos.\n\nLa proteína de vacuno destaca por su alto contenido en creatina natural, hierro hemo (de alta biodisponibilidad) y un perfil completo de aminoácidos esenciales, especialmente los de cadena ramificada (BCAA), fundamentales para el crecimiento y la recuperación muscular. También suele contener colágeno y otros compuestos que benefician las articulaciones y la salud ósea, lo cual la hace popular entre atletas de fuerza y resistencia.\n\nEn cuanto a su velocidad de absorción, se sitúa entre el suero y la caseína, siendo adecuada tanto para el postentrenamiento como para su uso durante el día. Muchas fórmulas modernas vienen saborizadas y enriquecidas con vitaminas para mejorar el perfil nutricional global.\n\nEn resumen, la proteína de carne de vacuno es una opción robusta, bien tolerada y efectiva para deportistas que desean variedad en sus fuentes proteicas y un suplemento con beneficios adicionales más allá del crecimiento muscular."
        ),
        SupplementItem(
            "Suero",
            R.drawable.suero,
            "La proteína de suero, conocida como Whey Protein, es la forma más popular y estudiada de suplemento proteico en el ámbito deportivo. Se obtiene como subproducto del proceso de elaboración del queso a partir de la leche y destaca por su rápida digestión y absorción, lo que la convierte en la opción ideal para consumir justo después del entrenamiento, cuando los músculos están más receptivos a los nutrientes.\n\nWhey protein posee un perfil de aminoácidos excelente, rico en leucina, isoleucina y valina (BCAA), los cuales son esenciales para iniciar la síntesis proteica y favorecer la recuperación muscular. Además, contiene lactoglobulina, inmunoglobulinas y otros péptidos bioactivos que pueden apoyar al sistema inmune, especialmente en atletas con entrenamientos intensos.\n\nExisten distintas formas de suero, siendo las más comunes el concentrado (WPC), el aislado (WPI) y el hidrolizado (WPH). El concentrado conserva parte de los carbohidratos y grasas naturales de la leche, mientras que el aislado es más puro y contiene más del 90% de proteína, ideal para personas con intolerancia a la lactosa o que buscan una opción más limpia. El hidrolizado, por su parte, es parcialmente digerido para una absorción aún más rápida.\n\nEn resumen, el suero es una fuente proteica completa, de alto valor biológico, ideal para favorecer la recuperación postentreno, estimular el crecimiento muscular y complementar la dieta en deportistas de todos los niveles."
        ),
        SupplementItem(
            "Suero aislado",
            R.drawable.isolate,
            "El suero aislado, o Whey Protein Isolate (WPI), es una forma altamente purificada de proteína de suero, obtenida mediante procesos avanzados de filtración que eliminan casi por completo la lactosa, las grasas y los carbohidratos. El resultado es un suplemento con una concentración proteica superior al 90%, ideal para quienes buscan una opción limpia, rápida y altamente eficiente para la recuperación muscular.\n\nGracias a su elevada pureza, el suero aislado es ideal para personas con intolerancia a la lactosa o que siguen dietas estrictas bajas en calorías. Su absorción es extremadamente rápida, lo que lo convierte en una opción excelente para el postentreno, momento en el que el cuerpo necesita una fuente inmediata de aminoácidos para reparar y construir masa muscular.\n\nAdemás de su perfil completo de aminoácidos esenciales, el WPI es rico en leucina, fundamental para activar la síntesis proteica. Muchos suplementos de suero aislado también están enriquecidos con enzimas digestivas para mejorar la tolerancia gastrointestinal y maximizar su aprovechamiento.\n\nEn definitiva, el suero aislado es una opción premium para deportistas exigentes que buscan una fuente proteica de la más alta calidad, sin añadidos innecesarios y con una excelente biodisponibilidad."
        ),
        SupplementItem(
            "Soja",
            R.drawable.soja,
            "La proteína de soja es una de las mejores fuentes proteicas de origen vegetal, reconocida por ser una proteína completa, es decir, que contiene todos los aminoácidos esenciales que el cuerpo necesita para la síntesis muscular. Se obtiene a partir de los granos de soja mediante procesos de aislamiento o concentración, y su valor biológico es comparable al de algunas proteínas animales.\n\nEs una opción excelente para personas que siguen dietas vegetarianas o veganas, así como para quienes presentan intolerancia a la lactosa o alergias a otras proteínas animales. Además, la soja es rica en arginina y glutamina, aminoácidos clave para el crecimiento muscular y la recuperación, y contiene isoflavonas naturales, compuestos con propiedades antioxidantes que pueden favorecer la salud cardiovascular y hormonal.\n\nGracias a su digestión moderadamente rápida, la proteína de soja puede consumirse tanto después del entrenamiento como en cualquier momento del día. Muchas fórmulas actuales están enriquecidas con vitaminas del grupo B y minerales como el hierro, para complementar la nutrición de quienes siguen dietas vegetales estrictas.\n\nEn resumen, la soja es una alternativa vegetal eficaz y sostenible, ideal para apoyar el rendimiento deportivo, la recuperación muscular y mantener una alimentación equilibrada sin productos de origen animal."
        )
    )

    val creatineList = listOf(
        SupplementItem(
            "Monohidrato",
            R.drawable.monohidrato,
            "El monohidrato de creatina es la forma más investigada, efectiva y utilizada de creatina en el mundo del deporte y la musculación. Se trata de un compuesto natural que el cuerpo produce a partir de los aminoácidos arginina, glicina y metionina, y que se almacena en los músculos en forma de fosfocreatina, una fuente clave de energía rápida durante esfuerzos cortos e intensos.\n\nLa suplementación con creatina monohidrato ha demostrado aumentar la fuerza, la potencia, el rendimiento anaeróbico y la masa muscular magra. Es especialmente útil en entrenamientos de alta intensidad, como levantamiento de pesas, sprints o deportes explosivos, al facilitar una recuperación más rápida del ATP (la moneda energética del cuerpo).\n\nEste suplemento también ha mostrado beneficios en la mejora cognitiva, la salud ósea y la protección neuromuscular, además de una buena tolerancia general. Su formato más habitual es el polvo, aunque también se encuentra en cápsulas o tabletas. Para optimizar su absorción, suele recomendarse tomarla con una fuente de carbohidratos simples, especialmente durante la fase de carga.\n\nEn definitiva, el monohidrato de creatina es una herramienta ergogénica segura, económica y científicamente validada para mejorar el rendimiento físico y la recuperación muscular, tanto en deportistas principiantes como avanzados."
        ),
        SupplementItem(
            "Transporte",
            R.drawable.transporte,
            "La creatina con sistema de transporte es una versión avanzada diseñada para mejorar la absorción y eficacia de la creatina en el músculo. A menudo, estos suplementos combinan monohidrato de creatina con carbohidratos simples, electrolitos o ingredientes como la taurina, el sodio y el ácido alfa-lipoico, que facilitan su transporte celular mediante picos de insulina o canales iónicos activos.\n\nEl objetivo de estos productos es acelerar la entrada de creatina a las fibras musculares, optimizando la carga intramuscular y logrando beneficios más rápidos en fuerza, potencia y recuperación. También pueden reducir la retención de agua subcutánea, mejorando la apariencia física.\n\nSon especialmente útiles para personas que no responden completamente al monohidrato tradicional, o para quienes desean obtener efectos más inmediatos. Suelen tomarse antes o después del entrenamiento y pueden presentarse en polvo saborizado, cápsulas o fórmulas líquidas.\n\nEn resumen, la creatina con transporte es una opción eficaz y avanzada para quienes buscan maximizar el aprovechamiento de este suplemento clave en el rendimiento deportivo."
        )
    )

    val aminoList = listOf(
        SupplementItem(
            "BCAA",
            R.drawable.bcaa,
            "Los BCAA (Branched-Chain Amino Acids) son aminoácidos de cadena ramificada compuestos por leucina, isoleucina y valina. Estos tres aminoácidos esenciales representan aproximadamente un tercio del tejido muscular esquelético y son fundamentales para la síntesis de proteínas, el crecimiento muscular y la recuperación tras el ejercicio intenso.\n\nA diferencia de otros aminoácidos, los BCAA pueden ser metabolizados directamente en el músculo en lugar de en el hígado, lo que les permite actuar como fuente de energía rápida durante el entrenamiento, especialmente en sesiones prolongadas o en ayunas. La leucina, en particular, desempeña un papel clave en la activación de la vía mTOR, responsable de iniciar la construcción de tejido muscular.\n\nLa suplementación con BCAA puede reducir la fatiga central, prevenir el catabolismo muscular (pérdida de masa), y mejorar la recuperación reduciendo el dolor muscular post-entrenamiento (DOMS). Son especialmente útiles para entrenamientos en volumen, definición o deportes de resistencia.\n\nGeneralmente se presentan en polvo saborizado o cápsulas, y pueden tomarse antes, durante o después del ejercicio, dependiendo del objetivo.\n\nEn resumen, los BCAA son un apoyo esencial para deportistas que desean mejorar su rendimiento, acelerar la recuperación y proteger su masa muscular frente a la degradación."
        ),
        SupplementItem(
            "Arginina",
            R.drawable.arginina,
            "La L-arginina es un aminoácido semi-esencial que desempeña un papel crucial en múltiples procesos fisiológicos, especialmente en el ámbito del rendimiento deportivo. Una de sus funciones más destacadas es su papel como precursor del óxido nítrico (NO), una molécula que favorece la vasodilatación, mejorando el flujo sanguíneo, el suministro de oxígeno y el transporte de nutrientes hacia los músculos durante el ejercicio.\n\nGracias a este efecto vasodilatador, la arginina se asocia comúnmente con un mejor 'pump' muscular durante el entrenamiento y una mayor eficiencia cardiovascular. También puede contribuir a reducir la fatiga y mejorar la resistencia, tanto en ejercicios anaeróbicos como aeróbicos.\n\nAdemás, la arginina participa en la síntesis de creatina y en la eliminación del amoníaco, lo que contribuye a una mejor recuperación postentrenamiento y a la reducción del estrés metabólico. También se ha relacionado con beneficios inmunológicos y en la cicatrización de tejidos.\n\nSe encuentra disponible en forma de cápsulas, comprimidos o polvo, y suele consumirse antes del entrenamiento para maximizar su efecto vasodilatador.\n\nEn resumen, la arginina es un suplemento versátil y funcional que potencia el rendimiento físico, favorece la recuperación y mejora la vascularización muscular durante el ejercicio."
        ),
        SupplementItem(
            "Glutamina",
            R.drawable.glutamina,
            "La L-glutamina es el aminoácido más abundante en el cuerpo humano, representando aproximadamente el 60% del total de aminoácidos libres en el tejido muscular. Es considerada un aminoácido condicionalmente esencial, lo que significa que bajo situaciones de estrés físico intenso (como entrenamientos duros, lesiones o enfermedad), la demanda corporal puede superar su producción natural.\n\nLa glutamina desempeña múltiples funciones clave: interviene en la síntesis proteica, regula el equilibrio ácido-base en los riñones, apoya el sistema inmunológico y actúa como combustible principal para las células del intestino delgado, favoreciendo la salud intestinal y la absorción de nutrientes.\n\nEn el ámbito deportivo, la glutamina es valorada por su capacidad para acelerar la recuperación muscular, reducir el dolor postentrenamiento (DOMS) y minimizar la pérdida de masa muscular durante dietas hipocalóricas o fases de sobreentrenamiento. También se ha asociado a una mejora en la respuesta inmunitaria, especialmente en atletas sometidos a entrenamientos exigentes.\n\nSe presenta comúnmente en polvo o cápsulas, y puede tomarse antes o después del ejercicio, así como en ayunas o antes de dormir, dependiendo del objetivo.\n\nEn resumen, la glutamina es un suplemento altamente funcional que apoya la recuperación, la salud digestiva y la integridad muscular, especialmente útil en fases de alto desgaste físico o estrés fisiológico."
        )
    )

    val vitaminList = listOf(
        SupplementItem(
            "Multivitamínico",
            R.drawable.multivitaminico,
            "Los suplementos multivitamínicos están formulados para proporcionar una combinación equilibrada de vitaminas y minerales esenciales que el cuerpo necesita diariamente para funcionar correctamente. En el contexto deportivo, son fundamentales para apoyar el metabolismo energético, la función muscular, el sistema inmunológico y la recuperación celular tras entrenamientos intensos.\n\nIncluyen nutrientes clave como las vitaminas del grupo B (B1, B2, B6, B12), que intervienen en la producción de energía; la vitamina D, esencial para la salud ósea y muscular; la vitamina C, que actúa como antioxidante y favorece la absorción del hierro; y minerales como el magnesio, zinc, calcio y hierro, que regulan funciones neuromusculares, hormonales e inmunológicas.\n\nEl uso de un buen multivitamínico puede ayudar a cubrir posibles deficiencias nutricionales, sobre todo en dietas restrictivas, en personas con alto desgaste físico o en fases de alta exigencia deportiva. Aunque no sustituyen a una dieta equilibrada, sí actúan como un refuerzo útil para mantener un estado óptimo de salud y rendimiento.\n\nEn resumen, un multivitamínico bien formulado puede convertirse en un pilar básico del plan nutricional de cualquier deportista, ayudando a optimizar funciones vitales y prevenir carencias que puedan afectar el rendimiento físico y mental."
        ),
        SupplementItem(
            "Antioxidantes",
            R.drawable.antioxidantes,
            "Los antioxidantes son compuestos que ayudan a neutralizar los radicales libres generados durante el metabolismo celular y el ejercicio físico intenso. Estas moléculas inestables, si no se controlan, pueden dañar células, tejidos y el ADN, contribuyendo al envejecimiento prematuro, la fatiga muscular y un aumento del riesgo de lesiones o enfermedades.\n\nEn el ámbito deportivo, los antioxidantes cumplen un rol fundamental en la protección del organismo frente al estrés oxidativo generado por el entrenamiento intenso. Entre los más conocidos se encuentran las vitaminas C y E, el selenio, el zinc, el betacaroteno y compuestos naturales como el resveratrol, la coenzima Q10 o los polifenoles del té verde.\n\nEl consumo adecuado de antioxidantes puede favorecer una mejor recuperación muscular, reducir el daño celular, fortalecer el sistema inmunológico y mejorar la capacidad de adaptación al ejercicio. También son importantes para la salud cardiovascular, la función cognitiva y la prevención de inflamaciones crónicas.\n\nAunque una dieta rica en frutas y verduras es la fuente principal de estos compuestos, los suplementos antioxidantes pueden ser útiles en fases de alta carga física, durante competiciones o en atletas con requerimientos aumentados.\n\nEn resumen, los antioxidantes son aliados clave para proteger al cuerpo del desgaste físico y celular, ayudando a mantener el rendimiento y la salud general del deportista."
        ),
        SupplementItem(
            "Vitamina C",
            R.drawable.vitaminac,
            "La vitamina C, también conocida como ácido ascórbico, es una vitamina hidrosoluble esencial que desempeña múltiples funciones vitales en el organismo, especialmente relevantes para quienes practican actividad física regular o de alta intensidad. Actúa como un potente antioxidante, neutralizando radicales libres y reduciendo el daño oxidativo generado durante el ejercicio intenso.\n\nUna de sus funciones más importantes es el fortalecimiento del sistema inmunológico, lo que ayuda a reducir el riesgo de infecciones y enfermedades respiratorias comunes en deportistas sometidos a entrenamiento constante. Además, participa activamente en la síntesis de colágeno, una proteína fundamental para la salud de las articulaciones, ligamentos, tendones, piel y vasos sanguíneos.\n\nLa vitamina C también mejora la absorción del hierro no hemo (procedente de alimentos vegetales), favoreciendo la oxigenación celular y el rendimiento físico. Algunos estudios sugieren que su suplementación puede ayudar a reducir la fatiga muscular y acelerar la recuperación tras el ejercicio.\n\nDisponible en comprimidos, cápsulas, polvos efervescentes o gomitas, la vitamina C es una opción accesible y efectiva para proteger el organismo frente al estrés físico y ambiental.\n\nEn resumen, la vitamina C es un micronutriente clave en la nutrición del deportista, contribuyendo a una recuperación más rápida, un sistema inmunológico fuerte y una mejor salud articular y general."
        )
    )

    val specialList = listOf(
        SupplementItem(
            "Recuperación tras el ejercicio",
            R.drawable.recuperacion,
            "Los suplementos diseñados para la recuperación tras el ejercicio tienen como objetivo acelerar el proceso de regeneración muscular, reponer los nutrientes perdidos durante el entrenamiento y optimizar la adaptación al esfuerzo físico. Están formulados para ayudar al cuerpo a reducir el daño muscular, restaurar los niveles de glucógeno y mejorar la síntesis proteica.\n\nEstos productos suelen combinar varios ingredientes clave: proteínas de alta calidad para reparar las fibras musculares; carbohidratos simples y complejos para reponer la energía; electrolitos como sodio, potasio y magnesio para equilibrar la hidratación; y, en algunos casos, compuestos funcionales como BCAA, glutamina, creatina, colágeno, o extractos antiinflamatorios como la cúrcuma o el jengibre.\n\nEl uso regular de estos suplementos puede disminuir la fatiga postentrenamiento, reducir el dolor muscular de aparición tardía (DOMS) y permitir una mejor frecuencia de entrenamiento, especialmente en disciplinas de alta intensidad o volumen.\n\nSu formato varía entre batidos en polvo, bebidas preparadas, cápsulas o sobres individuales, y suelen tomarse justo después del ejercicio para maximizar su efectividad.\n\nEn resumen, los suplementos de recuperación son aliados esenciales para quienes entrenan con regularidad, ya que permiten optimizar el rendimiento, prevenir lesiones y mantener una progresión constante en los objetivos físicos."
        ),
        SupplementItem(
            "Pre-entrenos",
            R.drawable.preentreno,
            "Los suplementos pre-entreno están formulados para potenciar el rendimiento físico y mental antes de una sesión de ejercicio. Su objetivo principal es aumentar la energía, la concentración, la resistencia y el enfoque, permitiendo al deportista entrenar con mayor intensidad y eficacia desde el primer minuto.\n\nEstos productos suelen contener una combinación de ingredientes activos como cafeína (estimulante del sistema nervioso central), beta-alanina (reduce la fatiga muscular), citrulina malato (mejora la vasodilatación y el bombeo muscular), creatina (potencia fuerza explosiva), y aminoácidos como la taurina o tirosina (favorecen la función cognitiva y el estado de alerta).\n\nAdemás, muchos pre-entrenos incluyen vitaminas del grupo B, adaptógenos como el ginseng o la ashwagandha, y electrolitos para favorecer el rendimiento sostenido en entrenamientos exigentes. Su efecto suele comenzar entre 20 y 30 minutos tras la ingesta, por lo que se recomienda tomarlo poco antes del entrenamiento.\n\nExisten formulaciones con y sin estimulantes, adaptadas a distintos objetivos (hipertrofia, resistencia, pérdida de peso, etc.) y niveles de tolerancia. Los formatos más comunes son los polvos solubles saborizados y las cápsulas concentradas.\n\nEn resumen, los pre-entrenos son una herramienta eficaz para maximizar la energía, el enfoque y el rendimiento en cada sesión, ideales para deportistas que buscan dar su máximo potencial desde el inicio del entrenamiento."
        ),
        SupplementItem(
            "Suplementos post entreno",
            R.drawable.postentreno,
            "Los suplementos post entreno están diseñados para acelerar la recuperación física tras el ejercicio, restaurar el equilibrio metabólico y favorecer la regeneración muscular y energética. Se consideran clave para aprovechar la llamada 'ventana anabólica', un período posterior al entrenamiento en el que el cuerpo es especialmente receptivo a la absorción de nutrientes.\n\nEstas fórmulas suelen incluir proteínas de rápida absorción (como suero o aislado), carbohidratos simples (para reponer glucógeno muscular), y en muchos casos creatina, glutamina, BCAA y electrolitos para rehidratar, reducir la fatiga y facilitar la síntesis de proteínas. Algunos suplementos post entreno también incorporan enzimas digestivas, antioxidantes o extractos antiinflamatorios para optimizar la asimilación y combatir el estrés oxidativo.\n\nEl objetivo principal es reducir el tiempo de recuperación, prevenir el catabolismo muscular y mejorar el rendimiento en futuras sesiones de entrenamiento. Son especialmente útiles en fases de alta carga o en rutinas de doble sesión.\n\nPueden encontrarse en formatos como batidos listos para tomar, polvos para mezclar, o combinaciones personalizadas según el tipo de entrenamiento (fuerza, resistencia, crossfit, etc.).\n\nEn resumen, los suplementos post entreno son una herramienta estratégica para maximizar los resultados del entrenamiento, acelerar la recuperación y asegurar un entorno anabólico óptimo tras el esfuerzo físico."
        )
    )

    val allSupplements = when (supplementType.lowercase()
        .replace("í", "i")
        .replace("á", "a")
        .replace("é", "e")
        .replace("ó", "o")
        .replace("ú", "u")
        .replace(" ", "")) {
        "proteina" -> proteinList
        "creatina" -> creatineList
        "aminoacidos" -> aminoList
        "vitaminasyminerales" -> vitaminList
        "productosespeciales" -> specialList
        else -> emptyList()
    }

    var searchQuery by remember { mutableStateOf("") }

    val filteredList = allSupplements.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAF5)) // Fondo claro Fit Journey
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Text(
            text = supplementType.replaceFirstChar { it.uppercase() },
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F5933),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar suplemento...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1F5933),
                cursorColor = Color(0xFF1F5933)
            )
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredList.size) { index ->
                val item = filteredList[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable {
                            val encodedTitle = Uri.encode(item.title)
                            val encodedDesc = Uri.encode(item.description)
                            navController.navigate("detail/$encodedTitle/${item.imageRes}/$encodedDesc")
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.title,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1F5933),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = item.description.take(80) + "...",
                            fontSize = 14.sp,
                            color = Color.DarkGray,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}