
    -- -----------------------------------------------------
    -- Table medios_pago
    -- -----------------------------------------------------
    CREATE TABLE medios_pago (
      id_medio_pago INT NOT NULL AUTO_INCREMENT,
      nombre VARCHAR(45) NOT NULL,
      porcentaje_comision DECIMAL(6,2) NOT NULL,
      CONSTRAINT pk_medios_pago PRIMARY KEY (id_medio_pago)
    );


    -- -----------------------------------------------------
    -- Table clientes
    -- -----------------------------------------------------
    CREATE TABLE clientes (
      id_cliente INT NOT NULL AUTO_INCREMENT,
      nombre VARCHAR(60) NOT NULL,
      apellido VARCHAR(60) NOT NULL,
      direccion VARCHAR(60) NOT NULL,
      dni VARCHAR(20) NOT NULL,
      fecha_nacimiento DATE NOT NULL,
      nacionalidad VARCHAR(45) NOT NULL,
      celular VARCHAR(45) NOT NULL,
      email VARCHAR(50) NOT NULL,
      activo TINYINT NOT NULL,
      created_date DATETIME NOT NULL,
      modified_date DATETIME NOT NULL,
      CONSTRAINT pk_clientes PRIMARY KEY (id_cliente)
      );


    -- -----------------------------------------------------
    -- Table empleados
    -- -----------------------------------------------------
    CREATE TABLE empleados (
      id_cliente INT NOT NULL,
      cargo VARCHAR(45) NOT NULL,
      sueldo DECIMAL(6,2) NOT NULL,
      CONSTRAINT pk_empleados PRIMARY KEY (id_cliente),
      CONSTRAINT fk_empleados_clientes1
        FOREIGN KEY (id_cliente)
        REFERENCES clientes (id_cliente));

    -- -----------------------------------------------------
    -- Table tipos_servicio
    -- -----------------------------------------------------
    CREATE TABLE tipos_servicio (
      id_tipo_servicio INT NOT NULL AUTO_INCREMENT,
      nombre VARCHAR(45) NOT NULL,
      activo TINYINT NOT NULL,
      created_date DATETIME NOT NULL,
      modified_date DATETIME NOT NULL,
      CONSTRAINT pk_tipos_servicio PRIMARY KEY (id_tipo_servicio));

    -- -----------------------------------------------------
    -- Table paquetes_turisticos
    -- -----------------------------------------------------
    CREATE TABLE paquetes_turisticos (
      id_paquete_turistico INT NOT NULL AUTO_INCREMENT,
      costo_paquete DECIMAL(6,2) NOT NULL,
      activo TINYINT NOT NULL,
      created_date DATETIME NOT NULL,
      modified_date DATETIME NOT NULL,
      CONSTRAINT pk_paquetes_turisticos PRIMARY KEY (id_paquete_turistico));

    -- -----------------------------------------------------
    -- Table servicios
    -- -----------------------------------------------------
    CREATE TABLE servicios (
      id_servicio INT NOT NULL AUTO_INCREMENT,
      id_tipo_servicio INT NOT NULL,
      id_paquete_turistico INT NULL,
      nombre VARCHAR(45) NOT NULL,
      descripcion_breve VARCHAR(60) NOT NULL,
      fecha_servicio DATETIME NOT NULL,
      costo_servicio DECIMAL(6,2) NOT NULL,
      activo TINYINT NOT NULL,
      created_date DATETIME NOT NULL,
      modified_date DATETIME NOT NULL,
      CONSTRAINT pk_servicios PRIMARY KEY (id_servicio),
      INDEX fk_servicio_tipo_servicio1_idx (id_tipo_servicio ASC) VISIBLE,
      INDEX fk_servicios_paquetes_turisticos1_idx (id_paquete_turistico ASC) VISIBLE,
      CONSTRAINT fk_servicio_tipo_servicio1
        FOREIGN KEY (id_tipo_servicio)
        REFERENCES tipos_servicio (id_tipo_servicio),
      CONSTRAINT fk_servicios_paquetes_turisticos1
        FOREIGN KEY (id_paquete_turistico)
        REFERENCES paquetes_turisticos (id_paquete_turistico)
    );

    -- -----------------------------------------------------
    -- Table ventas
    -- -----------------------------------------------------
    CREATE TABLE ventas (
      id_venta INT NOT NULL AUTO_INCREMENT,
      id_empleado INT NOT NULL,
      id_cliente INT NOT NULL,
      id_medio_pago INT NOT NULL,
      id_servicio INT NULL,
      id_paquete_turistico INT NULL,
      fecha_venta DATETIME NOT NULL,
      tipo CHAR(1) NOT NULL,
      porcentaje_comision DECIMAL(6,2) NOT NULL,
      activo TINYINT NOT NULL,
      created_date DATETIME NOT NULL,
      modified_date DATETIME NOT NULL,
      CONSTRAINT pk_ventas PRIMARY KEY (id_venta),
      INDEX fk_venta_empleado_idx (id_empleado ASC) VISIBLE,
      INDEX fk_venta_cliente_idx (id_cliente ASC) VISIBLE,
      INDEX fk_venta_medio_pago1_idx (id_medio_pago ASC) VISIBLE,
      INDEX fk_ventas_servicios1_idx (id_servicio ASC) VISIBLE,
      INDEX fk_ventas_paquetes_turisticos1_idx (id_paquete_turistico ASC) VISIBLE,
      CONSTRAINT fk_venta_empleado
          FOREIGN KEY (id_empleado)
          REFERENCES empleados (id_cliente),
      CONSTRAINT fk_venta_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES clientes (id_cliente),
      CONSTRAINT fk_venta_medio_pago1
        FOREIGN KEY (id_medio_pago)
        REFERENCES medios_pago (id_medio_pago),
      CONSTRAINT fk_ventas_servicios1
        FOREIGN KEY (id_servicio)
        REFERENCES servicios (id_servicio),
      CONSTRAINT fk_ventas_paquetes_turisticos1
        FOREIGN KEY (id_paquete_turistico)
        REFERENCES paquetes_turisticos (id_paquete_turistico)
    );
