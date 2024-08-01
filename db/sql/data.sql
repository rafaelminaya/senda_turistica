INSERT INTO medios_pago (nombre, porcentaje_comision) VALUES 
('Efectivo', 0.00),
('Tarjeta de debito', 3.00),
('Tarjeta de credito', 9.00),
('Monedero virtual', 0.00),
('Transferencia', 2.45);

INSERT INTO tipos_servicio (nombre) VALUES 
('Hotel por noche/s'),
('Alquiler de auto'),
('Pasajes de colectivo'),
('Pasajes de avion'),
('Pasajes de tren'),
('Excursiones'),
('Entradas a eventos');

INSERT INTO clientes (nombre, apellido, direccion, dni, fecha_nacimiento, nacionalidad, celular, email) VALUES 
('Carlos', 'Heredia', 'Av. Rosales 1293', '38467245','1985-07-12', 'Peruana', '934854732', 'carlos.heredia@gmail.com'),
('Fabiola', 'Juarez', 'Av. Arequipa 3321', '47829456', '1992-04-06', 'Peruana', '984937122', 'fabiola.juarez@gmail.com'),
('Luis', 'Tuesta', 'Jr. Gardecias 293', '67892373', '1993-11-22', 'Peruana', '973843726', 'luis.tuesta@gmail.com'),
('Paola', 'Bernal', 'Calle La Paz 183', '63783201', '1996-06-27', 'Peruana', '974152637', 'paola.bernal@gmail.com'),
('Omar', 'Aviles', 'Jr. Camino Real 1723', '57839450', '1993-10-05', 'Peruana', '983172317', 'omar.aviles@gmail.com'),
('Eba', 'Downage', '839 Oakridge Trail', '4300245665', '1994-10-18', 'United States', '8326163753', 'edownage0@walmart.com'),
('Callie', 'Sammonds', '7366 Fuller Road', '8816370181', '1995-03-16', 'Kosovo', '6435870795', 'csammonds1@lycos.com'),
('Ashly', 'Kermott', '1268 Sachtjen Avenue', '8871014545', '1992-08-12', 'Philippines', '3133943279', 'akermott2@com.com'),
('Donetta', 'Champley', '43 Boyd Drive', '0213124726', '1997-08-11', 'Brazil', '6986949258', 'dchampley3@mayoclinic.com'),
('Seward', 'Danes', '64089 Anzinger Alley', '9932158704', '1997-01-23', 'Russia', '2458915912', 'sdanes4@phoca.cz'),
('Rubin', 'Admans', '33008 Annamark Hill', '5418464671', '1995-04-16', 'Portugal', '2333805347', 'radmans5@livejournal.com'),
('Norma', 'Dorbin', '93 Moland Avenue', '4993195532', '1993-07-05', 'Indonesia', '1955606813', 'ndorbin6@senate.gov'),
('Town', 'Jukes', '584 Carioca Court', '1251108873', '1995-12-26', 'Czech Republic', '8742333982', 'tjukes7@mail.ru'),
('Joelynn', 'Divill', '5 David Trail', '1390476502', '1995-11-14', 'Finland', '1939334362', 'jdivill8@seesaa.net'),
('Evelina', 'de Clercq', '649 Mallard Circle', '5241368361', '1991-11-15', 'Brazil', '3222722262', 'edeclercq9@gnu.org');

INSERT INTO empleados (id_cliente, cargo, sueldo) VALUES 
(1, 'Administrador', 6000),
(2, 'Vendedor', 2000),
(3, 'Vendedor', 1500),
(4, 'Vendedor', 2100),
(5, 'Vendedor', 1900);

INSERT INTO paquetes_turisticos (costo_paquete) VALUES 
(1000.00),
(2000.00),
(3000.00),
(4000.00);

INSERT INTO servicios (id_tipo_servicio, id_paquete_turistico, nombre, descripcion_breve, fecha_servicio, costo_servicio) VALUES 
(7, null, 'analyzer', 'nonummy maecenas', '2024-05-05', '194.76'),
(2, 4, 'Persevering', 'quam nec dui', '2023-12-14', '64.42'),
(6, 4, 'array', 'quisque erat eros viverra eget', '2024-06-07', '53.97'),
(4, 1, 'bottom-line', 'cubilia curae', '2023-10-29', '105.77'),
(6, 2, 'support', 'nullam sit amet', '2024-03-07', '30.05'),
(7, 4, 'upward-trending', 'nibh in hac', '2023-08-06', '164.34'),
(5, 1, 'installation', 'sapien dignissim', '2023-09-12', '17.90'),
(6, null, 'structure', 'vulputate elementum', '2024-01-02', '12.91'),
(2, 2, 'interactive', 'morbi vestibulum velit', '2023-09-27', '80.51'),
(5, 1, 'software', 'dis parturient montes nascetur', '2023-12-22', '180.47'),
(1, 2, 'Object-based', 'cras pellentesque volutpat dui', '2024-01-20', '54.14'),
(4, 1, 'Total', 'vehicula consequat morbi a ipsum', '2024-05-07', '91.82'),
(1, 4, 'initiative', 'at nulla suspendisse', '2024-07-13', '132.82'),
(1, null, 'uniform', 'id consequat in consequat ut', '2023-09-30', '170.82'),
(5, 2, 'flexibility', 'aliquet pulvinar sed', '2024-03-16', '189.34'),
(6, null, 'Intuitive', 'non ligula pellentesque ultrices phasellus', '2024-06-26', '93.62'),
(6, 3, 'toolset', 'donec semper sapien', '2024-05-16', '175.43'),
(3, 4, 'matrix', 'nunc proin at', '2024-06-24', '171.62'),
(4, 1, 'model', 'erat vestibulum', '2023-09-09', '148.81'),
(6, 1, 'attitude-oriented', 'eget eleifend luctus ultricies eu', '2024-05-14', '101.62');

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