package com.cxd.sotiy.cache;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * desc
 *
 * @author childe
 * @date 2017/12/5 20:59
 **/
public class Item {

    private String collection;

    private SolrQuery solrQuery;

    public Item() {
    }

    public Item(String collection, SolrQuery solrQuery) {
        this.collection = collection;
        this.solrQuery = solrQuery;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public SolrQuery getSolrQuery() {
        return solrQuery;
    }

    public void setSolrQuery(SolrQuery solrQuery) {
        this.solrQuery = solrQuery;
    }
}
