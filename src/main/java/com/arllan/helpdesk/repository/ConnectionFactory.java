package com.arllan.helpdesk.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            // Carrega o arquivo da pasta resources
            InputStream is = ConnectionFactory.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado!");
            }

            props.load(is);

            return DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password")
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar configurações ou conectar ao banco!", e);
        }
    }
}