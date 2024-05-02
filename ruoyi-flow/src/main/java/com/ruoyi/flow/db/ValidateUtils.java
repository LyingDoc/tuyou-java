package com.ruoyi.flow.db;




import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.vo.ParamBizFromVO;
import com.ruoyi.flow.vo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    /**
     * 整数.
     * "^" ：匹配输入字符串开始的位置
     * "?"： 零次或一次匹配前面的字符或子表达式
     * "[1-9]" ： 1-9任意数字
     * "\\d" ：数字，等效于[0-9]
     * "*" ：零次或多次匹配前面的字符或子表达式
     * "$" ：匹配输入字符串结尾的位置
     * 合起来就是：以 “-” 开头，“-”可以出现一次或零次，第一个数字非零，其他数字为0到9中的任意数字，可以出现出现零次或多次，以数字结尾。这不就是整数吗?
     */
    private static final String V_INTEGER = "^-?[1-9]\\d*$";

    /**
     * 正整数
     * 同理，以1到9的数字开头，其他数字是0到9中任意数字，可以出现多次或零次，并以数字结尾，即正整数
     */
    private static final String V_Z_INDEX = "^[1-9]\\d*$";

    /**
     * 负整数
     * 以“-”开头，紧跟着1到9中的任意数字，后面其他数字可以是0到9，可以出现多次或零次，以数字结尾，即负整数
     */
    private static final String V_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    /**
     * 数字
     * “[+-]”：字符集，可以是+ ，也可以是-
     * 同理，以+ 或是 - 开头，+和-可以出现1次或零次，后面有数字，有“.”，“.”可以出现0次或一次，后面又有数字，并以数字结尾，即所有数字，包括任意整数和小数
     */
    private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";

    /**
     * 正数
     */
    private static final String V_POSITIVE_NUMBER = "^[1-9]\\d*|0$";

    /**
     * 负数
     */
    private static final String V_NEGATINE_NUMBER = "^-[1-9]\\d*|0$";

    /**
     * 浮点数
     */
    private static final String V_FLOAT = "^([+-]?)\\d*\\.\\d+$";

    /**
     * 正浮点数
     */
    private static final String V_POSTTIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

    /**
     * 负浮点数
     */
    private static final String V_NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

    /**
     * 非负浮点数（正浮点数 + 0）
     */
    private static final String V_UNPOSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

    /**
     * 非正浮点数（负浮点数 + 0）
     */
    private static final String V_UN_NEGATIVE_FLOAT = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

    /**
     * 邮件
     * “\\w”：匹配任何字类字符，包括下划线。"[A-Za-z0-9_]"
     * “+”，出现1次或多次
     * “((-\w+)|(\.\w+))*” 和 “((\.|-)[A-Za-z0-9]+)*” 可以出现0次或多次，这个这个地方不太明白是什么规则
     * “\@[A-Za-z0-9]+”出现@、或是任意字符，出现一次或多次，
     * “\.[A-Za-z0-9]+$” 出现“.”或任意字符，痴线一次或多次，然后结尾。
     * 可以看出，第一是“@”在“.”之前出现，字符开头，字符结尾，大概就是邮件的格式吧。
     */
    private static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    /**
     * 颜色
     */
    private static final String V_COLOR = "^[a-fA-F0-9]{6}$";

    /**
     * url
     */
    private static final String V_URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    /**
     * 仅中文
     */
    private static final String V_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    /**
     * 仅ACSII字符
     */
    private static final String V_ASCII = "^[\\x00-\\xFF]+$";

    /**
     * 邮编
     * "\\d" 表示[0-9],
     * "^" 匹配输入字符串开始的位置
     * "{6}" 表示匹配左边相邻字符或表达式6次
     * "$" 匹配输入字符串结尾的位置
     * 连起来就是以数字开头，以数字结尾 的6位数
     */
    private static final String V_ZIPCODE = "^\\d{6}$";

    /**
     * 手机
     * 以 1开头，后面是0到9，出现十次0到9中任意数字，即11位手机号码
     */
    private static final String V_MOBILE = "^(1)[0-9]{10}$";

    /**
     * ip地址
     */
    private static final String V_IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    /**
     * 非空
     */
    private static final String V_NOTEMPTY = "^\\S+$";

    /**
     * 图片
     */
    private static final String V_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

    /**
     * 压缩文件
     */
    private static final String V_RAR = "(.*)\\.(rar|zip|7zip|tgz)$";

    /**
     * 日期
     */
    private static final String V_DATE = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
    private static final String V_DATE2 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))";

    /**
     * QQ号码
     */
    private static final String V_QQ_NUMBER = "^[1-9]*[1-9][0-9]*$";

    /**
     * 电话号码的函数(包括验证国内区号,国际区号,分机号)
     */
    private static final String V_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    /**
     * 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     */
    private static final String V_USERNAME = "^\\w+$";

    /**
     * 字母
     */
    private static final String V_LETTER = "^[A-Za-z]+$";

    /**
     * 大写字母
     */
    private static final String V_LETTER_U = "^[A-Z]+$";

    /**
     * 小写字母
     */
    private static final String V_LETTER_I = "^[a-z]+$";

    /**
     * 身份证
     */
    private static final String V_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**
     * 验证密码(数字和英文同时存在)
     */
    private static final String V_PASSWORD_REG = "[A-Za-z]+[0-9]";

    /**
     * 验证密码长度(6-18位)
     */
    private static final String V_PASSWORD_LENGTH = "^\\d{6,18}$";

    /**
     * 验证两位数
     */
    private static final String V_TWO＿POINT = "^[0-9]+(.[0-9]{2})?$";

    /**
     * 验证一个月的31天
     */
    private static final String V_31DAYS = "^((0?[1-9])|((1|2)[0-9])|30|31)$";


    private ValidateUtils() {
    }


    /**
     * 验证是不是整数
     *
     * @param value 要验证的字符串 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Integer(String value) {
        return match(V_INTEGER, value);
    }

    /**
     * 验证是不是正整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Z_index(String value) {
        return match(V_Z_INDEX, value);
    }

    /**
     * 验证是不是负整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Negative_integer(String value) {
        return match(V_NEGATIVE_INTEGER, value);
    }

    /**
     * 验证是不是数字
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Number(String value) {
        return match(V_NUMBER, value);
    }

    /**
     * 验证是不是正数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean PositiveNumber(String value) {
        return match(V_POSITIVE_NUMBER, value);
    }

    /**
     * 验证是不是负数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean NegatineNumber(String value) {
        return match(V_NEGATINE_NUMBER, value);
    }

    /**
     * 验证一个月的31天
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Is31Days(String value) {
        return match(V_31DAYS, value);
    }

    /**
     * 验证是不是ASCII
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean ASCII(String value) {
        return match(V_ASCII, value);
    }


    /**
     * 验证是不是中文
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Chinese(String value) {
        return match(V_CHINESE, value);
    }


    /**
     * 验证是不是颜色
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Color(String value) {
        return match(V_COLOR, value);
    }


    /**
     * 验证是不是日期
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Date(String value) {
        value=  value.replace("T"," ");
        boolean isdate=match(V_DATE, value);
        if(isdate==false){
             return match( V_DATE2, value);
        }
        return isdate;
    }

    /**
     * 验证是不是邮箱地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Email(String value) {
        return match(V_EMAIL, value);
    }

    /**
     * 验证是不是浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Float(String value) {
        return match(V_FLOAT, value);
    }

    /**
     * 验证是不是正确的身份证号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IDcard(String value) {
        return match(V_IDCARD, value);
    }

    /**
     * 验证是不是正确的IP地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IP4(String value) {
        return match(V_IP4, value);
    }

    /**
     * 验证是不是字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Letter(String value) {
        return match(V_LETTER, value);
    }

    /**
     * 验证是不是小写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Letter_i(String value) {
        return match(V_LETTER_I, value);
    }


    /**
     * 验证是不是大写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Letter_u(String value) {
        return match(V_LETTER_U, value);
    }


    /**
     * 验证是不是手机号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Mobile(String value) {
        return match(V_MOBILE, value);
    }

    /**
     * 验证是不是负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Negative_float(String value) {
        return match(V_NEGATIVE_FLOAT, value);
    }

    /**
     * 验证非空
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Notempty(String value) {
        return match(V_NOTEMPTY, value);
    }

    /**
     * 验证密码的长度(6~18位)
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Number_length(String value) {
        return match(V_PASSWORD_LENGTH, value);
    }

    /**
     * 验证密码(数字和英文同时存在)
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Password_reg(String value) {
        return match(V_PASSWORD_REG, value);
    }

    /**
     * 验证图片
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Picture(String value) {
        return match(V_PICTURE, value);
    }

    /**
     * 验证正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Posttive_float(String value) {
        return match(V_POSTTIVE_FLOAT, value);
    }

    /**
     * 验证QQ号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean QQnumber(String value) {
        return match(V_QQ_NUMBER, value);
    }

    /**
     * 验证压缩文件
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Rar(String value) {
        return match(V_RAR, value);
    }

    /**
     * 验证电话
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Tel(String value) {
        return match(V_TEL, value);
    }

    /**
     * 验证两位小数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Two_point(String value) {
        return match(V_TWO＿POINT, value);
    }

    /**
     * 验证非正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Un_negative_float(String value) {
        return match(V_UN_NEGATIVE_FLOAT, value);
    }

    /**
     * 验证非负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Unpositive_float(String value) {
        return match(V_UNPOSITIVE_FLOAT, value);
    }

    /**
     * 验证URL
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Url(String value) {
        return match(V_URL, value);
    }

    /**
     * 验证用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean UserName(String value) {
        return match(V_USERNAME, value);
    }

    /**
     * 验证邮编
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean Zipcode(String value) {
        return match(V_ZIPCODE, value);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static R<String> ValidateParam(ParamBizFromVO paramitem , Object value){
        boolean IsValidate=true;
        if(paramitem.getRequired()){
            if(value==null||value.toString().equals("")){
                return R.newError("参数["+paramitem.getParamname()+"]不能为空！");
            }
        }
        IsValidate=ValidateParamType(paramitem.getParamtype(),value);
        if (!IsValidate) {
            return R.newError("参数[" + paramitem.getParamname() + "]格式不正确！");
        }
        return R.newOk("验证成功！");
    }
    public static   boolean ValidateParamType(String paramtype , Object value){
        boolean IsValidate=true;
        if(StringUtils.isNotBlank(paramtype)){

            if(value==null||value.toString().equals("")){
                return    IsValidate;
            }
            switch (paramtype.toUpperCase()) {
                case "V_INTEGER":
                    IsValidate = ValidateUtils.Integer(value.toString());
                    break;
                case "V_Z_INDEX":
                    IsValidate = ValidateUtils.Z_index(value.toString());
                    break;
                case "V_NEGATIVE_INTEGER":
                    IsValidate = ValidateUtils.Negative_integer(value.toString());
                    break;
                case "V_NUMBER":
                    IsValidate = ValidateUtils.Number(value.toString());
                    break;
                case "V_POSITIVE_NUMBER":
                    IsValidate = ValidateUtils.PositiveNumber(value.toString());
                    break;
                case "V_NEGATINE_NUMBER":
                    IsValidate = ValidateUtils.NegatineNumber(value.toString());
                    break;
                case "V_FLOAT":
                    IsValidate = ValidateUtils.Float(value.toString());
                    break;
                case "V_POSTTIVE_FLOAT":
                    IsValidate = ValidateUtils.Posttive_float(value.toString());
                    break;
                case "V_NEGATIVE_FLOAT":
                    IsValidate = ValidateUtils.Negative_float(value.toString());
                    break;
                case "V_UNPOSITIVE_FLOAT":
                    IsValidate = ValidateUtils.Unpositive_float(value.toString());
                    break;
                case "V_UN_NEGATIVE_FLOAT":
                    IsValidate = ValidateUtils.Un_negative_float(value.toString());
                    break;
                case "V_EMAIL":
                    IsValidate = ValidateUtils.Email(value.toString());
                    break;
                case "V_COLOR":
                    IsValidate = ValidateUtils.Color(value.toString());
                    break;
                case "V_URL":
                    IsValidate = ValidateUtils.Url(value.toString());
                    break;
                case "V_CHINESE":
                    IsValidate = ValidateUtils.Chinese(value.toString());
                    break;
                case "V_ASCII":
                    IsValidate = ValidateUtils.ASCII(value.toString());
                    break;
                case "V_ZIPCODE":
                    IsValidate = ValidateUtils.Zipcode(value.toString());
                    break;
                case "V_MOBILE":
                    IsValidate = ValidateUtils.Mobile(value.toString());
                    break;
                case "V_IP4":
                    IsValidate = ValidateUtils.IP4(value.toString());
                    break;
                case "V_PICTURE":
                    IsValidate = ValidateUtils.Picture(value.toString());
                    break;
                case "V_RAR":
                    IsValidate = ValidateUtils.Rar(value.toString());
                    break;
                case "V_DATE":
                    IsValidate = ValidateUtils.Date(value.toString());
                    break;
                case "V_QQ_NUMBER":
                    IsValidate = ValidateUtils.QQnumber(value.toString());
                    break;
                case "V_TEL":
                    IsValidate = ValidateUtils.Tel(value.toString());
                    break;
                case "V_LETTER":
                    IsValidate = ValidateUtils.Letter(value.toString());
                    break;
                case "V_LETTER_U":
                    IsValidate = ValidateUtils.Letter_u(value.toString());
                    break;
                case "V_LETTER_I":
                    IsValidate = ValidateUtils.Letter_i(value.toString());
                    break;
                case "V_IDCARD":
                    IsValidate = ValidateUtils.IDcard(value.toString());
                    break;
                case "V_PASSWORD_REG":
                    IsValidate = ValidateUtils.Password_reg(value.toString());
                    break;
                case "V_PASSWORD_LENGTH":
                    IsValidate = ValidateUtils.Number_length(value.toString());
                    break;
                case "V_TWO＿POINT":
                    IsValidate = ValidateUtils.Two_point(value.toString());
                    break;
                default:
                    try {
                        IsValidate = ValidateUtils.match(paramtype, value.toString());
                    }catch (Exception ex){
                        return false;
                    }
                    break;
            }
        }
        return IsValidate;
    }
}
