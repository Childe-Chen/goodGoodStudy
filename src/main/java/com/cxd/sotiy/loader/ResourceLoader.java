package com.cxd.sotiy.loader;

import com.cxd.sotiy.exception.AbstractSotiyException;
import com.cxd.sotiy.handler.document.IDocumentHandler;
import com.cxd.sotiy.handler.document.XmlDocumentHandler;
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
    final static Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    final static String PATH = "solr.template";

    void load() {
        Enumeration<URL> resources = null;
        try {
            resources = ResourceLoader.class.getClassLoader().getResources(PATH);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            File f = new File(url.getFile());
            // should not be file , but a directory
            if (!f.isDirectory()) {
                continue;
            }

            String[] files = f.list((File file, String s) -> s.endsWith(".xml"));


            try {
                loadFile(url.getPath(), files);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                break;
            }
        }
    }

    private void loadFile(String pathPre, String[] files) throws AbstractSotiyException {
        SAXReader reader = new SAXReader();
        for (int i = 0; i < files.length; i++) {
            handleFile(pathPre + File.separator + files[i],reader);
        }
    }

    private void handleFile(String path, SAXReader reader) throws AbstractSotiyException {
        InputStream is = null;
        Document document = null;

        try {
            is = new FileInputStream(path);
            document = reader.read(is);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }

        IDocumentHandler documentHandler = new XmlDocumentHandler();
        documentHandler.handle(document,path);
    }

}
