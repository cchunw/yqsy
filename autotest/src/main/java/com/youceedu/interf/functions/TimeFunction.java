package com.youceedu.interf.functions;

import com.youceedu.interf.util.DateTimeUtil;

/**
 * @program: yqsy
 * @ClassName TimeFunction
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-07-07 13:48
 * @Version 1.0
 */
public class TimeFunction implements Function{

    @Override
    public String execute(String[] args) {
        //初始化
        String result = null;
        if (args.length == 0){
            //时间戳
            result = String.valueOf(DateTimeUtil.getTimeImpl());
        } else if (args.length ==1 && args[0].equals("YMDHMS")) {
            //年月日时分秒
            result = DateTimeUtil.getDateTime();
        }else if (args.length == 1 && args[0].equals("YMD")){
            //年月日
            result = DateTimeUtil.getDate();
        }else if (args.length == 1 && args[0].equals("HMS")){
            //时分秒
            result = DateTimeUtil.getTime();
        }else {
            result = DateTimeUtil.getPatternDateTime(args[0]);
        }
        return result;
    }

    @Override
    public String getReferenceKey() {
        return "Time";
    }
}
