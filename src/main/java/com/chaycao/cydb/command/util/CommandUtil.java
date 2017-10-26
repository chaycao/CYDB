package com.chaycao.cydb.command.util;

import com.chaycao.cydb.command.impl.Get;
import com.chaycao.cydb.command.impl.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chaycao on 2017/10/26.
 * chaycao@163.com
 */
public class CommandUtil {
    /**
     * 检验命令是否合法
     * 若合法，返回OK
     * 若不合法，返回提示信息
     * @param str
     * @return
     */
    public String vaildCommand(String str) {

        String[] args = str.trim().split(" ");
        int len = args.length;
        String c = args[0].toLowerCase();
        if (c.equals("get")) {
            if (vaildArgNum(2, 2, len))
                return errorCommandHint(1, c);
            return "OK";
        }
        if (c.equals("set")) {
            if (vaildArgNum(3, 3, len))
                return errorCommandHint(1, c);
            return "OK";
        }
        return errorCommandHint(0, c);
    }

    /**
     * 检验参数数量是否符合 [d, u]
     * @param d 下限
     * @param u 上限
     * @param len 参数数量
     * @return
     */
    public boolean vaildArgNum(int d, int u, int len) {
        if (len >= d && len <= u)
            return false;
        return true;
    }

    /**
     * 错误命令的提示信息
     * @param type 错误类型
     * @param command 命令
     * @return
     */
    public String errorCommandHint(int type, String command) {
        switch (type) {
            case 0: //命令不存在
                return "(error) ERR unknown command '" + command + "'";
            case 1: //命令参数数量错误
                return "(error) ERR wrong number of arguments for '" + command + "' command";
        }
        return "type has error";
    }
}

