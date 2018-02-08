package com.getjavajob.bunyatt.hibernate.util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class SchemaGenerator {
    public static final String SCRIPT_FILE = System.getProperty("user.dir") + "\\dao\\src\\main\\resources\\mySqlSchema.sql";
    public static final File CONFIX_FILE = new File( System.getProperty("user.dir") + "\\dao\\src\\main\\resources\\META-INF\\persistence.xml");

    public static void main(String[] args) {
        String configFileName = "persistence.xml";
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(configFileName).build();
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
