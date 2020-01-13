package com.cxd.json;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/8/9 16:09
 **/
public class JsonUtil {
    private JsonUtil(){}

    private static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();

    private static final ParserConfig PARSER_CONFIG = new ParserConfig();

    static {
        SERIALIZE_CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        PARSER_CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    public static SerializeConfig snakeCaseSerializeConfig() {
        return SERIALIZE_CONFIG;
    }

    public static ParserConfig snakeCaseParseConfig() {
        return PARSER_CONFIG;
    }
}
