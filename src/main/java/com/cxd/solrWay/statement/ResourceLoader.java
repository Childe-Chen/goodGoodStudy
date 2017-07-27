package com.cxd.solrWay.statement;

import com.cxd.solrWay.parse.v1.statementParser.StatementParser;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by childe on 2017/7/14.
 */
public class ResourceLoader {
    static Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    static String PATH = "solr.template";

    static void load() {
        Enumeration<URL> resources = null;
        try {
            resources = ResourceLoader.class.getClassLoader().getResources(PATH);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            File f = new File(url.getFile());
            if (!f.isDirectory()) { // should not be file , but a directory
                continue;
            }

            String[] files = f.list((File file, String s) -> s.endsWith(".xml"));
            SAXReader reader = new SAXReader();

            try {
                loadSatement(url.getPath(), files,reader);
            } catch (Exception e) {
                logger.error("Load statement fail, please check!");
                break;
            }
        }
    }

    private static void loadSatement(String pathPre, String[] files, SAXReader reader) throws Exception {
        for (int i = 0; i < files.length; i++) {

            loadDocument(pathPre + File.separator + files[i], reader);
        }
    }

    private static void loadDocument(String path, SAXReader reader) throws Exception {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            Document document = reader.read(is);

            if (document == null) {
                logger.error("Load Sotiy file error, {}",path);
            }

            StatementParser.parseStatement(document);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw e;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }

}
