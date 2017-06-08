package com.cxd.cli;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by childe on 2017/6/8.
 */
public class CliStart {

    static Logger logger = LoggerFactory.getLogger(CliStart.class);

    /**
     * -nnn t -aaa w 不报错
     * -nnn t 不报错
     * -nnn -aaa 报错
     * 说明hasArg只判断当参数存在时其参数值不能为空，并不要求参数必须存在
     * option.setRequired(true);  来设置参数是否必须
     */
    public static void main(String[] args) throws Exception {
        Options options = new Options();

//        options.addOption("nnn", true,"name");

        Option option = new Option("aaa","age");
        option.setRequired(true);
        option.setValueSeparator("#".charAt(0));
        options.addOption(option);

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options,args,true);


        if (cmd.hasOption("nnn")) {
            logger.info("set name is " + cmd.getOptionValue("nnn"));
        }
        if (cmd.hasOption("aaa")) {
            logger.info("set age is " + cmd.getOptionValue("aaa"));
        }
    }
}
