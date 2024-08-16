INSERT INTO medios_pago (nombre, porcentaje_comision) VALUES 
('Efectivo', 0.00),
('Tarjeta de debito', 3.00),
('Tarjeta de credito', 9.00),
('Monedero virtual', 0.00),
('Transferencia', 2.45);

INSERT INTO tipos_servicio (nombre, activo, created_date, modified_date) VALUES
('Hotel por noche/s', 1, NOW(), NOW()),
('Alquiler de auto', 1, NOW(), NOW()),
('Pasajes de colectivo', 1, NOW(), NOW()),
('Pasajes de avion', 1, NOW(), NOW()),
('Pasajes de tren', 1, NOW(), NOW()),
('Excursiones', 1, NOW(), NOW()),
('Entradas a eventos', 1, NOW(), NOW());

INSERT INTO clientes (nombre, apellido, direccion, dni, fecha_nacimiento, nacionalidad, celular, email, activo, created_date, modified_date) VALUES
('Carlos', 'Heredia', 'Av. Rosales 1293', '38467245','1985-07-12', 'Peruana', '934854732', 'carlos.heredia@gmail.com', 1, NOW(), NOW()),
('Fabiola', 'Juarez', 'Av. Arequipa 3321', '47829456', '1992-04-06', 'Peruana', '984937122', 'fabiola.juarez@gmail.com', 1, NOW(), NOW()),
('Luis', 'Tuesta', 'Jr. Gardecias 293', '67892373', '1993-11-22', 'Peruana', '973843726', 'luis.tuesta@gmail.com', 1, NOW(), NOW()),
('Paola', 'Bernal', 'Calle La Paz 183', '63783201', '1996-06-27', 'Peruana', '974152637', 'paola.bernal@gmail.com', 1, NOW(), NOW()),
('Omar', 'Aviles', 'Jr. Camino Real 1723', '57839450', '1993-10-05', 'Peruana', '983172317', 'omar.aviles@gmail.com', 1, NOW(), NOW()),
('Eba', 'Downage', '839 Oakridge Trail', '4300245665', '1994-10-18', 'United States', '8326163753', 'edownage0@walmart.com', 1, NOW(), NOW()),
('Callie', 'Sammonds', '7366 Fuller Road', '8816370181', '1995-03-16', 'Kosovo', '6435870795', 'csammonds1@lycos.com', 1, NOW(), NOW()),
('Ashly', 'Kermott', '1268 Sachtjen Avenue', '8871014545', '1992-08-12', 'Philippines', '3133943279', 'akermott2@com.com', 1, NOW(), NOW()),
('Donetta', 'Champley', '43 Boyd Drive', '0213124726', '1997-08-11', 'Brazil', '6986949258', 'dchampley3@mayoclinic.com', 1, NOW(), NOW()),
('Seward', 'Danes', '64089 Anzinger Alley', '9932158704', '1997-01-23', 'Russia', '2458915912', 'sdanes4@phoca.cz', 1, NOW(), NOW()),
('Rubin', 'Admans', '33008 Annamark Hill', '5418464671', '1995-04-16', 'Portugal', '2333805347', 'radmans5@livejournal.com', 1, NOW(), NOW()),
('Norma', 'Dorbin', '93 Moland Avenue', '4993195532', '1993-07-05', 'Indonesia', '1955606813', 'ndorbin6@senate.gov', 1, NOW(), NOW()),
('Town', 'Jukes', '584 Carioca Court', '1251108873', '1995-12-26', 'Czech Republic', '8742333982', 'tjukes7@mail.ru', 1, NOW(), NOW()),
('Joelynn', 'Divill', '5 David Trail', '1390476502', '1995-11-14', 'Finland', '1939334362', 'jdivill8@seesaa.net', 0, NOW(), NOW()),
('Evelina', 'de Clercq', '649 Mallard Circle', '5241368361', '1991-11-15', 'Brazil', '3222722262', 'edeclercq9@gnu.org', 0, NOW(), NOW());

INSERT INTO empleados (id_cliente, cargo, sueldo) VALUES 
(1, 'Administrador', 6000),
(2, 'Vendedor', 2000),
(3, 'Vendedor', 1500),
(4, 'Vendedor', 2100),
(5, 'Vendedor', 1900);

INSERT INTO paquetes_turisticos (costo_paquete, activo, created_date, modified_date) VALUES 
(540.00, 1, NOW(), NOW()),
(2000.00, 1, NOW(), NOW()),
(3000.00, 1, NOW(), NOW()),
(4000.00, 0, NOW(), NOW());

INSERT INTO servicios (id_tipo_servicio, id_paquete_turistico, nombre, descripcion_breve, fecha_servicio, costo_servicio, activo, created_date, modified_date) VALUES
(1, 1, 'Alquiler 1 dormitorio', 'Alquiler de 1 dormitorio para persona sola por 3 noches', '2024-05-05 13:20:00', 500.00, 1, NOW(), NOW()),
(2, 1, 'Alquiler auto BMW 193', 'Alquiler de auto para el dia entero', '2024-05-05 13:20:00', 100.00, 1, NOW(), NOW()),
(6, 4, 'array', 'quisque erat eros viverra eget', '2024-06-07 13:20:00', 53.97, 1, NOW(), NOW()),
(4, 1, 'bottom-line', 'cubilia curae', '2023-10-29 13:20:00', 105.77, 1, NOW(), NOW()),
(6, 2, 'support', 'nullam sit amet', '2024-03-07 13:20:00', 30.05, 1, NOW(), NOW()),
(7, 4, 'upward-trending', 'nibh in hac', '2023-08-06 13:20:00', 164.34, 1, NOW(), NOW()),
(5, 1, 'installation', 'sapien dignissim', '2023-09-12 13:20:00', 17.90, 1, NOW(), NOW()),
(6, null, 'structure', 'vulputate elementum', '2024-01-02 13:20:00', 12.91, 1, NOW(), NOW()),
(2, 2, 'interactive', 'morbi vestibulum velit', '2023-09-27 13:20:00', 80.51, 1, NOW(), NOW()),
(5, 1, 'software', 'dis parturient montes nascetur', '2023-12-22 13:20:00', 180.47, 1, NOW(), NOW()),
(1, 2, 'Object-based', 'cras pellentesque volutpat dui', '2024-01-20 13:20:00', 54.14, 1, NOW(), NOW()),
(4, 1, 'Total', 'vehicula consequat morbi a ipsum', '2024-05-07 13:20:00', 91.82, 1, NOW(), NOW()),
(1, 4, 'initiative', 'at nulla suspendisse', '2024-07-13 13:20:00', 132.82, 1, NOW(), NOW()),
(1, null, 'uniform', 'id consequat in consequat ut', '2023-09-30 13:20:00', 170.82, 1, NOW(), NOW()),
(5, 2, 'flexibility', 'aliquet pulvinar sed', '2024-03-16 13:20:00', 189.34, 1, NOW(), NOW()),
(6, null, 'Intuitive', 'non ligula pellentesque ultrices phasellus', '2024-06-26 13:20:00', 93.62, 1, NOW(), NOW()),
(6, 3, 'toolset', 'donec semper sapien', '2024-05-16 13:20:00', 175.43, 1, NOW(), NOW()),
(3, 4, 'matrix', 'nunc proin at', '2024-06-24 13:20:00', 171.62, 0, NOW(), NOW()),
(4, 1, 'model', 'erat vestibulum', '2023-09-09 13:20:00', 148.81, 0, NOW(), NOW()),
(6, 1, 'attitude-oriented', 'eget eleifend luctus ultricies eu', '2024-05-14 13:20:00', 101.62, 0, NOW(), NOW());

INSERT INTO ventas (id_cliente, id_medio_pago, id_servicio, id_paquete_turistico, fecha_venta, tipo) VALUES
(12, 3, 2, null, '2024-07-03', 'S'),
(9, 2, 9, null, '2024-07-24', 'S'),
(15, 4, 18, null, '2024-07-10', 'S'),
(1, 5, null, 3, '2024-07-12', 'P'),
(7, 2, 3, null, '2024-07-23', 'S'),
(10, 2, null, 3, '2024-07-16', 'P'),
(14, 5, null, 1, '2024-07-06', 'P'),
(8, 4, 19, null, '2024-07-08', 'S'),
(8, 1, null, 3, '2024-07-26', 'P'),
(3, 4, 11, null, '2024-07-03', 'S'),
(1, 5, 12, null, '2024-07-21', 'S'),
(3, 4, 2, null, '2024-07-19', 'S'),
(11, 3, 3, null, '2024-07-30', 'S'),
(15, 2, null, 2, '2024-07-03', 'P'),
(10, 3, 13, null, '2024-07-27', 'S'),
(12, 3, 10, null, '2024-07-31', 'S'),
(8, 3, null, 2, '2024-07-08', 'P'),
(1, 1, 17, null, '2024-07-19', 'S'),
(6, 4, 9, null, '2024-07-07', 'S'),
(14, 2, 13, null, '2024-07-26', 'S');