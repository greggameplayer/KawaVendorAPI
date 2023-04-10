package com.kawa.service.dto.request.mongo;

import org.springframework.context.annotation.Bean;

public class ProductFindOneMongoRequestDTO {
    private String dataSource = "MainCluster";
    private String database = "kawavendorapi";
    private String  collection = "products";

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }


    @Bean
    public Filter filter(){
        return new Filter();
    }


    public static class Filter {
        private String _id;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }

}
