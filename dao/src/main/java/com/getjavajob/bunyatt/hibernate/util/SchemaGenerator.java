package com.getjavajob.bunyatt.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class SchemaGenerator {
    public static final String SCRIPT_FILE = System.getProperty("user.dir") + "\\dao\\src\\main\\resources\\mySqlSchema.sql";
    public static final File HIBERNATE_CFG = new File( System.getProperty("user.dir") + "\\dao\\src\\main\\resources\\META-INF\\hibernate-mysql.cfg.xml");

    public static void main(String[] args) {
        String configFileName = "hibernate-oracle.cfg.xml";
//        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/webapp\n" +
//                "            ?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC");
//        configuration.setProperty("hibernate.connection.username", "root");
//        configuration.setProperty("hibernate.connection.password", "root");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//        configuration.configure();
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory();


        //todo NEED TO LEARN SOME LESSONS ABOUT HIBERNATE. AND READ DOCUMENTATION OhMineHod...
        //todo the trouble because my hibernate did not understand any cfg. Maybe cfg wrong or maybe i am stupid ^_^
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(HIBERNATE_CFG).build();

        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        SchemaExport export = getSchemaExport();
        dropDataBase(export, metadata);
        createDataBase(export, metadata);
    }

    private static void dropDataBase(SchemaExport export, Metadata metadata){
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        export.drop(targetTypes, metadata);
    }

    private static SchemaExport getSchemaExport(){
        SchemaExport export = new SchemaExport();
//        File output = new File(SCRIPT_FILE);
        export.setDelimiter(";");
        export.setOutputFile(SCRIPT_FILE);
        export.setHaltOnError(false);
        return export;
    }

    public static void createDataBase(SchemaExport export, Metadata metadata) {
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        SchemaExport.Action action = SchemaExport.Action.CREATE;
        export.execute(targetTypes, action, metadata);
    }
}
