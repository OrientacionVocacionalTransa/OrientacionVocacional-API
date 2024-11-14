INSERT INTO area (name)
VALUES
    ('ÁREA I: Arte y Creatividad'),
    ('ÁREA II: Ciencias Sociales'),
    ('ÁREA III: Económica, Administrativa y Financiera'),
    ('ÁREA IV: Ciencia y Tecnología'),
    ('ÁREA V: Ciencias Ecológicas, Biológicas y de Salud');

INSERT INTO question (text, area_id)
VALUES
    ('1. Diseñar programas de computación y explorar nuevas aplicaciones tecnológicas para uso del internet.', 4),
    ('2. Criar, cuidar y tratar animales domésticos y de campo', 5),
    ('3. Investigar sobre áreas verdes, medio ambiente y cambios climáticos', 5),
    ('4. Ilustrar, dibujar y animar digitalmente', 1),
    ('5. Seleccionar, capacitar y motivar al personal de una organización/empresa', 3),
    ('6. Realizar excavaciones para descubrir restos del pasado', 2),
    ('7. Resolver problemas de cálculo para construir un puente', 4),
    ('8. Diseñar cursos para enseñar a la gente sobre temas de salud e higiene', 5),
    ('9. Tocar un instrumento y componer música', 1),
    ('10. Planificar cuáles son las metas de una organización pública o privada a mediano y largo plazo.', 3),
    ('11. Diseñar y planificar la producción masiva de artículos como muebles, autos, equipos de oficina, empaques y envases para alimentos y otros.', 4),
    ('12. Diseñar logotipos y portadas de una revista', 1),
    ('13. Organizar eventos y atender a sus asistentes', 2),
    ('14. Atender la salud de personas enfermas.', 5),
    ('15. Controlar ingresos y egresos de fondos y presentar el balance final de una institución.', 3),
    ('16. Hacer experimentos con plantas (frutas, árboles, flores)', 5),
    ('17. Concebir planos para viviendas, edificios y ciudadelas.', 4),
    ('18. Investigar y probar nuevos productos farmacéuticos.', 4),
    ('19. Hacer propuestas y formular estrategias para aprovechar las relaciones económicas entre dos países.', 3),
    ('20. Pintar, hacer esculturas, ilustrar libros de arte, etcétera.', 1),
    ('21. Elaborar campañas para introducir un nuevo producto al mercado.', 3),
    ('22. Examinar y tratar los problemas visuales', 5),
    ('23. Defender a clientes individuales o empresas en juicios de diferente naturaleza.', 2),
    ('24. Diseñar máquinas que puedan simular actividades humanas.', 4),
    ('25. Investigar las causas y efectos de los trastornos emocionales', 2),
    ('26. Supervisar las ventas de un centro comercial', 3),
    ('27. Atender y realizar ejercicios a personas que tienen limitaciones físicas, problemas de lenguaje, etcétera.', 5),
    ('28. Prepararse para ser modelo profesional.', 1),
    ('29. Aconsejar a las personas sobre planes de ahorro e inversiones.', 3),
    ('30. Elaborar mapas, planos e imágenes para el estudio y análisis de datos geográficos.', 4),
    ('31. Diseñar juegos interactivos electrónicos para computadora', 1),
    ('32. Realizar el control de calidad de los alimentos', 5),
    ('33. Tener un negocio propio de tipo comercial.', 3),
    ('34. Escribir artículos periodísticos, cuentos, novelas y otros.', 2),
    ('35. Redactar guiones y libretos para un programa de televisión', 1),
    ('36. Organizar un plan de distribución y venta de un gran almacén.', 3),
    ('37. Estudiar la diversidad cultural en el ámbito rural y urbano.', 2),
    ('38. Gestionar y evaluar convenios internacionales de cooperación para el desarrollo social.', 2),
    ('39. Crear campañas publicitarias', 1),
    ('40. Trabajar investigando la reproducción de peces, camarones y otros animales marinos.', 5),
    ('41. Dedicarse a fabricar productos alimenticios de consumo masivo', 4),
    ('42. Gestionar y evaluar proyectos de desarrollo en una institución educativa y/o fundación.', 2),
    ('43. Rediseñar y decorar espacios físicos en viviendas, oficinas y locales comerciales.', 1),
    ('44. Administrar una empresa de turismo y/o agencias de viaje.', 3),
    ('45. Aplicar métodos alternativos a la medicina tradicional para atender personas con dolencias de diversa índole.', 5),
    ('46. Diseñar ropa para niños, jóvenes y adultos.', 1),
    ('47. Investigar organismos vivos para elaborar vacunas.', 5),
    ('48. Manejar y/o dar mantenimiento a dispositivos/aparatos tecnológicos en aviones, barcos, radares, etcétera.', 4),
    ('49. Estudiar idiomas extranjeros –actuales y antiguos- para hacer traducción.', 2),
    ('50. Restaurar piezas y obras de arte', 1);








INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 1);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 1);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 2);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 2);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 3);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 3);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 4);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 4);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 5);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 5);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 6);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 6);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 7);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 7);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 8);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 8);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 9);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 9);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 10);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 10);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 11);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 11);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 12);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 12);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 13);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 13);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 14);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 14);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 15);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 15);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 16);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 16);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 17);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 17);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 18);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 18);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 19);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 19);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 20);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 20);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 21);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 21);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 22);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 22);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 23);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 23);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 24);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 24);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 25);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 25);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 26);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 26);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 27);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 27);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 28);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 28);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 29);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 29);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 30);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 30);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 31);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 31);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 32);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 32);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 33);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 33);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 34);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 34);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 35);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 35);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 36);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 36);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 37);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 37);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 38);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 38);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 39);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 39);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 40);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 40);


INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 41);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 41);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 42);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 42);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 43);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 43);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 44);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 44);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 45);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 45);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 46);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 46);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 47);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 47);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 48);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 48);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 49);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 49);

INSERT INTO option (text, score, question_id) VALUES ('Me interesa', 1, 50);
INSERT INTO option (text, score, question_id) VALUES ('No me interesa', 0, 50);

INSERT INTO location (city, region, country) VALUES
                                                 ('Lima', 'Lima', 'Peru'),
                                                 ('Arequipa', 'Arequipa', 'Peru'),
                                                 ('Trujillo', 'La Libertad', 'Peru'),
                                                 ('Cusco', 'Cusco', 'Peru'),
                                                 ('Piura', 'Piura', 'Peru'),
                                                 ('Chiclayo', 'Lambayeque', 'Peru'),
                                                 ('Huancayo', 'Junin', 'Peru'),
                                                 ('Iquitos', 'Loreto', 'Peru'),
                                                 ('Puno', 'Puno', 'Peru'),
                                                 ('Tacna', 'Tacna', 'Peru');


INSERT INTO career (location_id, area_id, name, img, description, price_monthly)
VALUES
    (1, 4, 'Ingeniería de Sistemas', 'https://worldcampus.saintleo.edu/img/article/estudiar-ingenieria-en-sistemas-cuales-son-los-retos-de-ser-ingenierio-en-sistemas.webp', 'Desarrollo y gestión de sistemas de información, software y redes tecnológicas.', 'PUCP: 1800 | UNMSM: Gratuito | UPC: 1500'),
    (1, 5, 'Medicina', 'https://cayetano.edu.pe/wp-content/uploads/2022/08/carrera-medicina.png', 'Ciencia de la salud enfocada en la prevención, diagnóstico y tratamiento de enfermedades.', 'UPCH: 2400 | UNMSM: Gratuito | UCV: 1600'),
    (1, 4, 'Ingeniería Industrial', 'https://img.universidades.com.pe/carreras/perfil/ingenieria-industrial.jpg', 'Optimización de sistemas productivos, procesos y recursos en empresas.', 'PUCP: 1800 | UNMSM: Gratuito | UPC: 1400'),
    (1, 3, 'Administración de Empresas', 'https://www.upn.edu.pe/sites/default/files/styles/carreras_listado_1500x1125/public/2020-09/administracion_y_negocios_internacionales.jpg', 'Gestión y dirección de recursos empresariales y organizaciones.', 'USIL: 1200 | UNMSM: Gratuito | UPC: 1300'),
    (1, 3, 'Contabilidad', 'https://www.esan.edu.pe/aplication/assets/images/uploads/blog/carrera-contabilidad.jpg', 'Control financiero y auditoría de empresas y organizaciones.', 'UPC: 1300 | UNMSM: Gratuito | UCV: 800'),
    (1, 3, 'Derecho', 'https://www.udep.edu.pe/wp-content/uploads/2015/12/derecho.jpg', 'Estudio y aplicación de leyes y normativas para la justicia y el orden.', 'PUCP: 1800 | UNMSM: Gratuito | UCV: 1100'),
    (1, 2, 'Psicología', 'https://www.upc.edu.pe/aplication/assets/images/uploads/carreras/psicologia.jpg', 'Estudio del comportamiento humano y sus procesos mentales.', 'UPC: 1400 | UNMSM: Gratuito | UCV: 1000'),
    (1, 3, 'Marketing', 'https://www.euroinnova.pe/img/carrera-de-marketing.jpg', 'Estrategias para promover y vender productos o servicios.', 'ESAN: 1300 | UNMSM: Gratuito | UPC: 1200'),
    (1, 4, 'Arquitectura', 'https://arquitecturayempresa.es/sites/default/files/styles/articulo/public/edificio_arquitectura.jpg', 'Diseño y construcción de edificios y espacios urbanos.', 'UNI: Gratuito | UPC: 1800 | PUCP: 2000'),
    (1, 3, 'Economía', 'https://www.uma.edu.pe/images/carreras/economia/economia.jpg', 'Estudio de los recursos, producción y distribución de bienes y servicios.', 'PUCP: 1800 | UNMSM: Gratuito | UPC: 1300'),
    (1, 2, 'Ciencias de la Comunicación', 'https://img.universidades.com.pe/carreras/perfil/ciencias-de-la-comunicacion.jpg', 'Estudio de los medios y técnicas de comunicación.', 'USMP: 1300 | UNMSM: Gratuito | UPC: 1200'),
    (1, 5, 'Enfermería', 'https://www.upn.edu.pe/aplication/assets/images/uploads/carreras/enfermeria.jpg', 'Atención y cuidado de la salud de pacientes en entornos clínicos.', 'UNMSM: Gratuito | UCV: 900 | UPC: 1100'),
    (1, 4, 'Ingeniería Civil', 'https://img.universidades.com.pe/carreras/perfil/ingenieria-civil.jpg', 'Diseño, construcción y mantenimiento de infraestructura civil.', 'UNI: Gratuito | UPC: 1800 | PUCP: 1900'),
    (1, 2, 'Educación', 'https://www.upc.edu.pe/aplication/assets/images/uploads/carreras/educacion.jpg', 'Formación de futuros profesionales en el campo educativo.', 'UNMSM: Gratuito | UPC: 1300 | UCV: 700'),
    (1, 3, 'Negocios Internacionales', 'https://www.usil.edu.pe/sites/default/files/2020-04/carrera-de-negocios-internacionales-usil.jpg', 'Gestión de operaciones comerciales y estrategias a nivel global.', 'USIL: 1400 | UNMSM: Gratuito | UPC: 1350'),
    (3, 1, 'Ingeniería de Sistemas', 'https://worldcampus.saintleo.edu/img/article/estudiar-ingenieria-en-sistemas-cuales-son-los-retos-de-ser-ingenierio-en-sistemas.webp', 'Desarrollo y gestión de sistemas de información, software y redes tecnológicas.', 'UNT: Gratuito | UPAO: 900 | UCV: 799'),
    (3, 2, 'Medicina', 'https://cayetano.edu.pe/wp-content/uploads/2022/08/carrera-medicina.png', 'Ciencia de la salud enfocada en la prevención, diagnóstico y tratamiento de enfermedades.', 'UNT: Gratuito | UPAO: 1500 | UCV: 1299'),
    (3, 3, 'Derecho', 'https://imgmedia.larepublica.pe/640x371/larepublica/original/2022/04/21/6261eca2ea80e22fb844650d.webp', 'Estudio de las leyes y su aplicación en la defensa de los derechos y justicia.', 'UNT: Gratuito | UPAO: 900 | UCV: 849'),
    (3, 4, 'Arquitectura', 'https://static-wc.arcux.net/uploads/20200529130551/la-arquitectura-1.jpg', 'Diseño y construcción de edificaciones funcionales y estéticas.', 'UPAO: 1000 | UCV: 899'),
    (3, 5, 'Administración de Empresas', 'https://isil.pe/blog/wp-content/uploads/2023/09/administracion-empresas.webp', 'Gestión de recursos empresariales para la optimización de procesos y toma de decisiones.', 'UNT: Gratuito | UPAO: 850 | UCV: 799'),
    (3, 1, 'Ingeniería Industrial', 'https://alumni.upeu.edu.pe/wp-content/uploads/2022/11/careers-ingenieria-industrial.webp', 'Optimización de procesos productivos y mejora en la eficiencia operativa de las empresas.', 'UNT: Gratuito | UPAO: 950 | UCV: 849'),
    (3, 2, 'Contabilidad', 'https://mosaic.cdnwp.com/media/Contabilidad-e1618393015635.png', 'Registro y análisis de la información financiera para la toma de decisiones económicas.', 'UNT: Gratuito | UPAO: 800 | UCV: 749'),
    (3, 3, 'Psicología', 'https://s3.amazonaws.com/cb-prd/www.colegiobostonschool.edu.pe/psicologia.webp', 'Estudio del comportamiento humano y los procesos mentales para la intervención en la salud mental.', 'UNT: Gratuito | UPAO: 900 | UCV: 849'),
    (3, 4, 'Marketing', 'https://www.eumed.net/rev/tlatemoani/31/mercadotecnia.jpg', 'Estrategias y análisis de mercado para promover productos y servicios.', 'UPAO: 850 | UCV: 799'),
    (3, 5, 'Diseño Gráfico', 'https://media.licdn.com/dms/image/C4E03AQFJZiZAmEnMiQ/profile-displayphoto-shrink_200_200/0/1603986313800?e=1686182400&v=beta&t=dG67J8BghDkV8enVCJFD-MDx49FA27T9P_YxFbDLeGc', 'Creación de contenido visual para transmitir mensajes de forma creativa y efectiva.', 'UPAO: 900 | UCV: 849');


INSERT INTO plans (name, price,description) VALUES
                                                ('Premium', 1000, 'Premium'),
                                                ('Free', 0, 'Free');
